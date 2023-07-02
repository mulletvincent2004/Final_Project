package Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminChoice extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminChoice frame = new AdminChoice();
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
	public AdminChoice() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel AdminChoicePic = new JLabel("");
		AdminChoicePic.setIcon(new ImageIcon("C:\\Users\\VJRM14\\Downloads\\MicrosoftTeams-image (2).png"));
		AdminChoicePic.setBounds(0, 0, 1000, 600);
		contentPane.add(AdminChoicePic);
		
		JLabel AddEBtn = new JLabel("");
		AddEBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AddEquipment adminAdd = new AddEquipment();
				adminAdd.setVisible(true);
				dispose();
			}
		});
		AddEBtn.setBounds(809, 80, 165, 42);
		contentPane.add(AddEBtn);
		
		JLabel ViewEBtn = new JLabel("");
		ViewEBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ViewEquipment adminView = new ViewEquipment();
				adminView.setVisible(true);
				dispose();
			}
		});
		ViewEBtn.setBounds(809, 155, 165, 42);
		contentPane.add(ViewEBtn);
		
		JLabel ViewReportBtn = new JLabel("");
		ViewReportBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Records adminRecords = new Records();
				adminRecords.setVisible(true);
				dispose();
			}
		});
		ViewReportBtn.setBounds(809, 224, 165, 42);
		contentPane.add(ViewReportBtn);
		
		JLabel AdminLogout = new JLabel("");
		AdminLogout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				HomePage home = new HomePage();
				home.setVisible(true);
				dispose();
			}
		});
		AdminLogout.setBounds(894, 566, 90, 25);
		contentPane.add(AdminLogout);
	}
}
