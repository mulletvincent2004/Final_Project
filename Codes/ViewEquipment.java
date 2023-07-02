package Project;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Font;

public class ViewEquipment extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel BckgrndPic;
    private JLabel BttnLabel;
    private JLabel equipmentLabel;
    private JLabel reportLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewEquipment frame = new ViewEquipment();
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

    public ViewEquipment() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(87, 164, 808, 344);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setBackground(new Color(255, 255, 153));
        table.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Equipment", "Quantity"}
        );
        table.setModel(tableModel);
        scrollPane.setViewportView(table);
        
        BckgrndPic = new JLabel("");
        BckgrndPic.setIcon(new ImageIcon("C:\\Users\\VJRM14\\Downloads\\MicrosoftTeams-image (4).png"));
        BckgrndPic.setBounds(0, 0, 1000, 600);
        contentPane.add(BckgrndPic);
        
        BttnLabel = new JLabel("");
        BttnLabel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		AdminChoice adminChoice = new AdminChoice();
				adminChoice.setVisible(true);
				dispose();
        	}
        });
        BttnLabel.setBounds(838, 11, 89, 37);
        contentPane.add(BttnLabel);
        
        equipmentLabel = new JLabel("");
        equipmentLabel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		AddEquipment adminAdd = new AddEquipment();
				adminAdd.setVisible(true);
				dispose();
        	}
        });
        equipmentLabel.setBounds(22, 547, 228, 14);
        contentPane.add(equipmentLabel);
        
        reportLabel = new JLabel("");
        reportLabel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Records records = new Records();
				records.setVisible(true);
				dispose();
        	}
        });
        reportLabel.setBounds(442, 26, 246, 37);
        contentPane.add(reportLabel);

        loadEquipmentData(); // Load equipment data from file
    }
}

