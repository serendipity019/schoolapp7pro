package gr.aueb.cf.schoolapp.view_controller;

import gr.aueb.cf.schoolapp.Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class ConnectionPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textFieldUser;
	


	/**
	 * Create the frame.
	 */
	public ConnectionPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConnect = new JLabel("Σύνδεση");
		lblConnect.setBounds(178, 25, 77, 22);
		lblConnect.setFont(new Font("DialogInput", Font.BOLD, 18));
		contentPane.add(lblConnect);
		
		JLabel lblOrders = new JLabel("Παρακαλώ εισάγετε τους κωδικούς σας για να συνδεθείτε.");
		lblOrders.setBounds(40, 60, 367, 17);
		contentPane.add(lblOrders);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(30, 90, 390, 2);
		contentPane.add(separator);
		
		JLabel Userlabel = new JLabel("Χρήστης:");
		Userlabel.setForeground(new Color(0, 0, 0));
		Userlabel.setBounds(69, 104, 60, 17);
		contentPane.add(Userlabel);
		
		JLabel passLabel = new JLabel("Κωδικός:");
		passLabel.setForeground(Color.BLACK);
		passLabel.setBounds(69, 162, 60, 17);
		contentPane.add(passLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(69, 180, 170, 30);
		contentPane.add(passwordField);
		
		textFieldUser = new JTextField();
		textFieldUser.setBounds(69, 120, 170, 30);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);
		
		JButton btnConnect2 = new JButton("Σύνδεση");
		btnConnect2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((textFieldUser.getText().matches("[aA]dmin")) && (Arrays.equals(passwordField.getPassword(), "12345".toCharArray() ))) {
					Main.getConnectionPage().setVisible(false);
					Main.getDashboard().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Λάθος username ή password", "Αδυναμία Σύνδεσης", JOptionPane.ERROR_MESSAGE);
					textFieldUser.setText("");
					passwordField.setText("");
				}
			}
		});
		btnConnect2.setForeground(new Color(224, 255, 255));
		btnConnect2.setBackground(new Color(0, 100, 0));
		btnConnect2.setBounds(69, 231, 170, 30);
		contentPane.add(btnConnect2);
	}
}
