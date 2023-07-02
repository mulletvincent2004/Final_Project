package Project;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Records extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Records frame = new Records();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Records() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel AdminBackBtn = new JLabel("");
        AdminBackBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                AdminChoice AdmChc = new AdminChoice();
                AdmChc.setVisible(true);
                dispose();
            }
        });
        AdminBackBtn.setBounds(820, 11, 115, 40);
        contentPane.add(AdminBackBtn);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(99, 134, 805, 341);
        contentPane.add(scrollPane);

        model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Name", "Address", "Phone number", "Venue/Equipment", "Quantity", "Month", "Day", "Year", "Time"}
        );

        table = new JTable();
        table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        table.setModel(model);
        scrollPane.setViewportView(table);

        // Load data from Rent.txt and populate the table
        model.setRowCount(0); // Clear existing rows
        loadRentData();

        JLabel equipmentLabel = new JLabel("");
        equipmentLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddEquipment adminAdd = new AddEquipment();
                adminAdd.setVisible(true);
                dispose();
            }
        });
        equipmentLabel.setBounds(22, 547, 226, 14);
        contentPane.add(equipmentLabel);

        JLabel availableLabel = new JLabel("");
        availableLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ViewEquipment view = new ViewEquipment();
                view.setVisible(true);
                dispose();
            }
        });
        availableLabel.setBounds(238, 518, 247, 32);
        contentPane.add(availableLabel);

        JLabel ViewRecordsPic = new JLabel("");
        ViewRecordsPic.setIcon(new ImageIcon("C:\\Users\\VJRM14\\Downloads\\MicrosoftTeams-image (11).png"));
        ViewRecordsPic.setBounds(0, 0, 1000, 600);
        contentPane.add(ViewRecordsPic);

        JButton btnNewButton = new JButton("");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    model.removeRow(selectedRow);
                    updateRentFile();
                    JOptionPane.showMessageDialog(contentPane, "Remove Successfully");
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Please choose a row");
                }
            }
        });
        btnNewButton.setBounds(831, 502, 73, 32);
        btnNewButton.setOpaque(false);
        btnNewButton.setContentAreaFilled(false);
        btnNewButton.setBorderPainted(false);
        contentPane.add(btnNewButton);
    }

    private void loadRentData() {
        try (BufferedReader br = new BufferedReader(new FileReader("Rent.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] rentData = line.split(",");
                model.addRow(rentData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateRentFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Rent.txt"))) {
            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    bw.write(model.getValueAt(row, col).toString());
                    if (col < model.getColumnCount() - 1) {
                        bw.write(",");
                    }
                }
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
