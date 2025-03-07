package gr.aueb.cf.schoolapp.view_controller;

import gr.aueb.cf.schoolapp.view_controller.LandingPage;
import gr.aueb.cf.schoolapp.Main;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dashboard2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Connection connection; 
	

	/**
	 * Create the frame.
	 */
	public Dashboard2() {
		//we don't use here this because we made util class DBUtil
//		addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowOpened(WindowEvent e) {
//				String sql = "jdbc:mysql://localhost:3306/school7dbpro?serverTimezone=UTC";
//				String username = "kleidimos";
//				String password = "6978706049";
//
//				try {
//					//Class.forName("com.mysql.cj.jdbc.Driver"); // This would must write if not had put in Classpath the driver.
//					setConnection(DriverManager.getConnection(sql, username, password));
//					System.out.println("Connection success");
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//				//catch (ClassNotFoundException  e1) {
//				//	e1.printStackTrace();
//				//}
//			}
//		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard2.class.getResource("/images/eduv2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel header = new JPanel();
		header.setBackground(new Color(0, 52, 117));
		header.setBounds(0, 0, 640, 35);
		contentPane.add(header);
		header.setLayout(null);
		
		JLabel govImage = new JLabel("");
		govImage.setIcon(new ImageIcon(LandingPage.class.getResource("/images/gov_logo_small.png")));
		govImage.setBounds(0, 0, 92, 27);
		header.add(govImage);
		
		JLabel lblNameLabel = new JLabel("ΠΑΠΑΠΑΝΑΓΙΩΤΟΥ ΠΑΝΑΓΙΩΤΗΣ");
		lblNameLabel.setForeground(Color.WHITE);
		lblNameLabel.setBackground(new Color(0, 52, 117));
		lblNameLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNameLabel.setBounds(413, 10, 163, 17);
		header.add(lblNameLabel);
		
		JPanel footer = new JPanel();
		footer.setBounds(0, 365, 640, 35);
		contentPane.add(footer);
		footer.setLayout(null);
		
		JLabel lblManuall = new JLabel("Εγχειρίδιο Xρήσης");
		lblManuall.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblManuall.setForeground(new Color(0, 0, 205));
		lblManuall.setBounds(65, 12, 119, 17);
		footer.add(lblManuall);
		
		JLabel lblQuestions = new JLabel("Συχνές Ερωτήσεις");
		lblQuestions.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblQuestions.setForeground(new Color(0, 0, 205));
		lblQuestions.setBounds(249, 12, 119, 17);
		footer.add(lblQuestions);
		
		JLabel lblSupport = new JLabel("Υποστήριξη Πολιτών");
		lblSupport.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSupport.setForeground(new Color(0, 0, 205));
		lblSupport.setBounds(433, 12, 139, 17);
		footer.add(lblSupport);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 255));
		separator.setBounds(0, 361, 640, 4);
		contentPane.add(separator);
		
		JPanel MenuPanel = new JPanel();
		MenuPanel.setBackground(new Color(0, 52, 117));
		MenuPanel.setBounds(0, 35, 187, 326);
		contentPane.add(MenuPanel);
		MenuPanel.setLayout(null);
		
		JButton btnLandPage = new JButton("Αρχική");
		btnLandPage.setBackground(new Color(0, 52, 117));
		btnLandPage.setForeground(Color.ORANGE);
		btnLandPage.setFont(new Font("Dialog", Font.BOLD, 14));
		btnLandPage.setBounds(24, 27, 105, 27);
		MenuPanel.add(btnLandPage);
		
		JLabel lblTeachers = new JLabel("Εκπαιδευτές");
		lblTeachers.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTeachers.setForeground(Color.WHITE);
		lblTeachers.setBounds(24, 66, 105, 17);
		MenuPanel.add(lblTeachers);
		
		JButton btnAppearTeachers = new JButton("Προβολή Εκπαιδευτών");
		btnAppearTeachers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getDashboard().setEnabled(false);
				Main.getTeacherPage().setVisible(true);
			}
		});
		btnAppearTeachers.setBackground(new Color(0, 52, 117));
		btnAppearTeachers.setForeground(Color.WHITE);
		btnAppearTeachers.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnAppearTeachers.setBounds(12, 86, 170, 27);
		MenuPanel.add(btnAppearTeachers);
		
		JButton btnInsertTeacher = new JButton("Εισαγωγή Εκπαιδευτών");
		btnInsertTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getDashboard().setEnabled(false);
				Main.getTeacherIdentity().setVisible(true);
			}
		});
		btnInsertTeacher.setBackground(new Color(0, 52, 117));
		btnInsertTeacher.setForeground(Color.WHITE);
		btnInsertTeacher.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnInsertTeacher.setBounds(12, 115, 170, 27);
		MenuPanel.add(btnInsertTeacher);
		
		JLabel lblBigLabel = new JLabel("Ποιότητα στην Εκπαίδευση");
		lblBigLabel.setForeground(Color.BLACK);
		lblBigLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblBigLabel.setBounds(306, 65, 260, 17);
		contentPane.add(lblBigLabel);
		
		JLabel lblAppearAM = new JLabel("Προβολή Μητρώου Εκπαιδευτών");
		lblAppearAM.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAppearAM.setForeground(Color.BLACK);
		lblAppearAM.setBounds(195, 107, 250, 17);
		contentPane.add(lblAppearAM);
		
		JLabel lblInsertAM = new JLabel("Εισαγωγή Εκπαιδευτή στο Μητρώο Εκπαιδευτών");
		lblInsertAM.setFont(new Font("Dialog", Font.BOLD, 14));
		lblInsertAM.setForeground(Color.BLACK);
		lblInsertAM.setBounds(195, 235, 358, 17);
		contentPane.add(lblInsertAM);
		
		JLabel lblInsertAMText = new JLabel("Εισαγωγή Εκπαιδευτή στο Μητρώο του Coding Factory.");
		lblInsertAMText.setForeground(Color.BLACK);
		lblInsertAMText.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblInsertAMText.setBounds(195, 262, 358, 17);
		contentPane.add(lblInsertAMText);
		
		JLabel lblAppearAMText = new JLabel("Προβολή Μητρώου Εγγεγραμμένων Εκπαιδευτών στο Μητρώο του  Coding Factory.");
		lblAppearAMText.setVerticalAlignment(SwingConstants.TOP);
		lblAppearAMText.setForeground(Color.BLACK);
		lblAppearAMText.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblAppearAMText.setBounds(195, 129, 420, 35);
		contentPane.add(lblAppearAMText);
		
		JButton btnContinueAMAppear = new JButton("Συνέχεια");
		btnContinueAMAppear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getDashboard().setEnabled(false);
				Main.getTeacherPage().setVisible(true);
			}
		});
		btnContinueAMAppear.setForeground(UIManager.getColor("Button.background"));
		btnContinueAMAppear.setBackground(new Color(0, 100, 0));
		btnContinueAMAppear.setBounds(195, 157, 105, 27);
		contentPane.add(btnContinueAMAppear);
		
		JButton btnContinueAMInsert = new JButton("Συνέχεια");
		btnContinueAMInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getDashboard().setEnabled(false);
				Main.getTeacherIdentity().setVisible(true);
			}
		});
		btnContinueAMInsert.setForeground(Color.WHITE);
		btnContinueAMInsert.setBackground(new Color(0, 100, 0));
		btnContinueAMInsert.setBounds(195, 291, 105, 27);
		contentPane.add(btnContinueAMInsert);
		
		
	}


	public static Connection getConnection() {
		return connection;
	}


	public static void setConnection(Connection connection) {
		Dashboard2.connection = connection;
	}
}
