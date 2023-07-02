package Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserPage extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField address;
	private JTextField mobileNumber;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserPage frame = new UserPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	private void UserPage(String name, String address, String phone) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("Information.txt", true));
			writer.write( name + " , " + address + " , " + phone);
			writer.newLine();
			writer.close();
			
			JOptionPane.showMessageDialog(null, "Successfully Login");
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Invalid Credentials");
			ex.printStackTrace();
			
		}
	}
	public UserPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Name = new JTextField();
		Name.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		Name.setBounds(322, 236, 439, 28);
		Name.setBackground(null);
		Name.setBorder(null);
		Name.setOpaque(false);
		contentPane.add(Name);
		Name.setColumns(10);
		
		address = new JTextField();
		address.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		address.setBounds(322, 291, 445, 34);
		address.setBackground(null);
		address.setBorder(null);
		address.setOpaque(false);
		contentPane.add(address);
		address.setColumns(10);
		
		mobileNumber = new JTextField();
		mobileNumber.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		mobileNumber.setBounds(322, 346, 445, 34);
		mobileNumber.setBackground(null);
		mobileNumber.setBorder(null);
		mobileNumber.setOpaque(false);
		contentPane.add(mobileNumber);
		mobileNumber.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\VJRM14\\Downloads\\MicrosoftTeams-image (7).png"));
		lblNewLabel.setBounds(0, 0, 1000, 600);
		contentPane.add(lblNewLabel);
		
		btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = Name.getText();
				String addr = address.getText();
				String phone = mobileNumber.getText();
				
				if (phone.startsWith("09") && phone.length() == 11) {
					UserPage(name, addr, phone);
					UserView userView = new UserView();
					userView.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Invalid phone number. Please enter a valid 11-digit number starting with '09'.");
				}
				
				
			}
		});
		btnNewButton.setBounds(461, 417, 105, 41);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		contentPane.add(btnNewButton);
	}

}
