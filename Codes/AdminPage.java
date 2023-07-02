package Project;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminPage extends JFrame {

	private JPanel contentPane;
	private JPasswordField PasswordInput;
	private JTextField UsernameInput;
	private JLabel AdminLoginExit;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
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
	public AdminPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		UsernameInput = new JTextField();
		UsernameInput.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		UsernameInput.setBounds(310, 250, 506, 43);
		UsernameInput.setBackground(null);
		UsernameInput.setBorder(null);
		UsernameInput.setOpaque(false);
		contentPane.add(UsernameInput);
		UsernameInput.setColumns(10);
		
		PasswordInput = new JPasswordField();
		PasswordInput.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		PasswordInput.setBackground(null);
		PasswordInput.setBorder(null);
		PasswordInput.setOpaque(false);
		PasswordInput.setBounds(310, 336, 493, 43);
		contentPane.add(PasswordInput);
		PasswordInput.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\VJRM14\\Downloads\\MicrosoftTeams-image (1).png"));
		lblNewLabel.setBounds(0, 0, 1000, 600);
		contentPane.add(lblNewLabel);
		
        
		btnNewButton = new JButton("Login button");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	                
				
				boolean userExists = false; 
				
				String UserInput = UsernameInput.getText();
		        String PassInput = new String(PasswordInput.getPassword());
		        String[] username = {"Admin1"};
                String[] password = {"123"};
                
                   for (int i = 0; i < password.length; i++) {
                	
                	
					if (UserInput.equals("Admin1") && PassInput.equals("123")) {
						JOptionPane.showMessageDialog(null, "Login successful!");
                        AdminChoice ADM = new AdminChoice();
                        ADM.setVisible(true);
                        new AdminChoice();
                        dispose();
                        userExists = true;
                        break;
                  
                    } 
					else if (!UserInput.equals("Admin1") && PassInput.equals("123")) {
                        JOptionPane.showMessageDialog(null, "Incorrect username.");
                    }
					else if (UserInput.equals("Admin1") && !PassInput.equals("123")) {
                        JOptionPane.showMessageDialog(null, "Incorrect password.");	
                }
					else {
						JOptionPane.showMessageDialog(null, "Incorrect username and password.");
					}
                   }
			}
        });
		btnNewButton.setBounds(462, 436, 104, 34);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		contentPane.add(btnNewButton);
		
		AdminLoginExit = new JLabel("");
		AdminLoginExit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		AdminLoginExit.setBounds(151, 109, 26, 26);
		contentPane.add(AdminLoginExit);
	}
}
