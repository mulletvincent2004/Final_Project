package Project;

import java.awt.Color;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.MatteBorder;

public class AddEquipment extends JFrame {

    private JPanel contentPane;
    private JTextField EqType;
    private JTextField NoOfEq;
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;
    private JButton EditBtn;
    private JLabel lblNewLabel;
    private JLabel availableLabel;
    private JLabel reportLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddEquipment frame = new AddEquipment();
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
                    int quantity = Integer.parseInt(data[1].trim());
                    model.addRow(new Object[] { equipment, quantity });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveEquipmentData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Equipments.txt"))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                String equipment = model.getValueAt(i, 0).toString();
                String quantity = model.getValueAt(i, 1).toString();
                bw.write(equipment + "," + quantity);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    

    public AddEquipment() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setVisible(true);
        contentPane.setLayout(null);
                
               EqType = new JTextField();
               EqType.setBounds(128, 196, 179, 20);
               EqType.setFont(new Font("Times New Roman", Font.BOLD, 16));
               EqType.setBackground(null);
               EqType.setBorder(null);
               EqType.setOpaque(false);
               contentPane.add(EqType);
               EqType.setColumns(10);
        
                NoOfEq = new JTextField();
                NoOfEq.setBounds(469, 196, 93, 20);
                NoOfEq.setFont(new Font("Times New Roman", Font.PLAIN, 16));
                NoOfEq.setBackground(null);
                NoOfEq.setBorder(null);
                NoOfEq.setOpaque(false);
                contentPane.add(NoOfEq);
                NoOfEq.setColumns(10);

        model = new DefaultTableModel(
                new Object[][] {},
                new String[] { "Equipment", "Quantity" }
        );

        JLabel AdminBackBtn = new JLabel("");
        AdminBackBtn.setBounds(827, 0, 102, 52);
        AdminBackBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                AdminChoice AdmChc = new AdminChoice();
                AdmChc.setVisible(true);
                dispose();
            }
        });
        contentPane.add(AdminBackBtn);

        JButton AddBtn = new JButton("");
        AddBtn.setBounds(840, 184, 76, 32);
        AddBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String equipment = EqType.getText();
                String quantity = NoOfEq.getText();

                if (!equipment.isEmpty() && !quantity.isEmpty()) {
                    model.addRow(new Object[] { equipment, quantity });
                    saveEquipmentData();
                    EqType.setText("");
                    NoOfEq.setText("");
                    JOptionPane.showMessageDialog(contentPane, "Add successful");
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Please complete fill up");
                }
            }
        });
        
                scrollPane = new JScrollPane();
                scrollPane.setBounds(72, 261, 739, 272);
                contentPane.add(scrollPane);
                table = new JTable(model);
                table.addMouseListener(new MouseAdapter() {
        			@Override
        			public void mouseClicked(MouseEvent e) {
        				int i = table.getSelectedRow();
        				EqType.setText(model.getValueAt(i, 0).toString());
        				NoOfEq.setText(model.getValueAt(i, 1).toString());
        			}
        		});
                table.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
                table.setBackground(new Color(128, 255, 128));
                scrollPane.setViewportView(table);
                table.setModel(model);
        AddBtn.setOpaque(false);
        AddBtn.setContentAreaFilled(false);
        AddBtn.setBorderPainted(false);
        contentPane.add(AddBtn);
                
                EditBtn = new JButton("");
                EditBtn.setBounds(840, 227, 76, 23);
                EditBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int selectedRow = table.getSelectedRow();
                        int i = table.getSelectedRow();
                        if (i >= 0) {
                            model.setValueAt(EqType.getText(), i, 0);
                            model.setValueAt(NoOfEq.getText(), i, 1);
                            saveEquipmentData(); // Save the updated equipment data
                            JOptionPane.showMessageDialog(null, "Update Successful!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Please select a row first.");
                        }
                    }
                });               
                EditBtn.setOpaque(false);
                EditBtn.setContentAreaFilled(false);
                EditBtn.setBorderPainted(false);
                contentPane.add(EditBtn);
                
                lblNewLabel = new JLabel("");
                lblNewLabel.setIcon(new ImageIcon("C:\\Users\\VJRM14\\Downloads\\MicrosoftTeams-image (3).png"));
                lblNewLabel.setBounds(0, 0, 1000, 600);
                contentPane.add(lblNewLabel);
                
                availableLabel = new JLabel("");
                availableLabel.addMouseListener(new MouseAdapter() {
                	@Override
                	public void mouseClicked(MouseEvent e) {
                		ViewEquipment view = new ViewEquipment();
        				view.setVisible(true);
        				dispose();
                	}
                });
                availableLabel.setBounds(192, 64, 247, 32);
                contentPane.add(availableLabel);
                
                reportLabel = new JLabel("");
                reportLabel.addMouseListener(new MouseAdapter() {
                	@Override
                	public void mouseClicked(MouseEvent e) {
                		Records records = new Records();
        				records.setVisible(true);
        				dispose();
                	}
                });
                reportLabel.setBounds(442, 29, 256, 40);
                contentPane.add(reportLabel);

        loadEquipmentData(); // Call to load equipment data
    }

}


