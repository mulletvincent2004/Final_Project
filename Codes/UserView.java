package Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;

public class UserView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserView frame = new UserView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	 private void loadEquipmentData() {
	        try (BufferedReader br = new BufferedReader(new FileReader("Equipments.txt"))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] data = line.split(",");
	                if (data.length == 2) {
	                    String equipment = data[0].trim();
	                    String quantity = data[1].trim();
	                    tableModel.addRow(new Object[]{equipment, quantity});
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	/**
	 * Create the frame.
	 */
	public UserView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(77, 231, 816, 304);
		contentPane.add(scrollPane);
		
		table = new JTable();
        table.setBackground(new Color(83, 172, 85));
        table.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Equipment", "Quantity"}
        );
        table.setModel(tableModel);
        scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\VJRM14\\Downloads\\MicrosoftTeams-image (8).png"));
		lblNewLabel.setBounds(0, 0, 1000, 600);
		contentPane.add(lblNewLabel);
		
		JLabel rentLabel = new JLabel("");
		rentLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Rent rent = new Rent();
				rent.setVisible(true);
				dispose();
			}
		});
		rentLabel.setBounds(191, 67, 257, 42);
		contentPane.add(rentLabel);
		
		 loadEquipmentData(); // Load equipment data from file
	}
}
