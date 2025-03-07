package gr.aueb.cf.schoolapp.view_controller;

import gr.aueb.cf.schoolapp.Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LandingPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 */
	public LandingPage() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LandingPage.class.getResource("/images/eduv2.png")));
		setTitle("Ποιότητα στην Εκπαίδευση");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel header = new JPanel();
		header.setBackground(new Color(0, 52, 117));
		header.setBounds(0, 0, 450, 35);
		contentPane.add(header);
		header.setLayout(null);
		
		JLabel govImage = new JLabel("");
		govImage.setIcon(new ImageIcon(LandingPage.class.getResource("/images/gov_logo_small.png")));
		govImage.setBounds(0, 0, 92, 27);
		header.add(govImage);
		
		JLabel lblAuthRequired = new JLabel("Απαιτείται ταυτοποίηση");
		lblAuthRequired.setFont(new Font("Serif", Font.BOLD, 18));
		lblAuthRequired.setBounds(20, 59, 244, 17);
		contentPane.add(lblAuthRequired);
		
		JLabel lblMustConnect = new JLabel("Για να προχωρήσετε πρέπει να συνδεθείτε");
		lblMustConnect.setBounds(20, 98, 277, 17);
		contentPane.add(lblMustConnect);
		
		JButton btnConnect = new JButton("Σύνδεση");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getLandingPage().setVisible(false);
				Main.getConnectionPage().setVisible(true);
			}
		});
		btnConnect.setEnabled(false);
		btnConnect.setForeground(UIManager.getColor("OptionPane.background"));
		btnConnect.setBackground(new Color(0, 100, 0));
		btnConnect.setBounds(20, 183, 105, 27);
		
		JCheckBox privacyCheckBox = new JCheckBox("Δηλώνω ότι αποδέχομαι τη");
		privacyCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED ) {
					btnConnect.setEnabled(true);
				} else {
					btnConnect.setEnabled(false);
				}
			}
		});
		privacyCheckBox.setBounds(20, 131, 189, 25);
		contentPane.add(privacyCheckBox);
		
		JLabel lblPrivacy = new JLabel("Δήλωση Ιδιωτικότητας");
		lblPrivacy.setForeground(Color.BLUE);
		lblPrivacy.setBounds(223, 131, 152, 25);
		contentPane.add(lblPrivacy);
		
		
		contentPane.add(btnConnect);
		
		JPanel footer = new JPanel();
		footer.setBounds(0, 265, 450, 35);
		contentPane.add(footer);
		footer.setLayout(null);
		
		JLabel lblManuall = new JLabel("Εγχειρίδιο Xρήσης");
		lblManuall.setForeground(new Color(0, 0, 205));
		lblManuall.setBounds(18, 12, 119, 17);
		footer.add(lblManuall);
		
		JLabel lblQuestions = new JLabel("Συχνές Ερωτήσεις");
		lblQuestions.setForeground(new Color(0, 0, 205));
		lblQuestions.setBounds(155, 12, 119, 17);
		footer.add(lblQuestions);
		
		JLabel lblSupport = new JLabel("Υποστήριξη Πολιτών");
		lblSupport.setForeground(new Color(0, 0, 205));
		lblSupport.setBounds(292, 12, 139, 17);
		footer.add(lblSupport);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 255));
		separator.setBounds(0, 261, 450, 4);
		contentPane.add(separator);
	}
}
