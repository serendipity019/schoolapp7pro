package gr.aueb.cf.schoolapp.view_controller;


import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.model.City;
import gr.aueb.cf.schoolapp.util.DBUtil;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TeacherIdentity extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldFather;
	private JTextField textFieldAFM;
	private JTextField textFieldEmail;
	private JTextField textFieldTelephone;
	private JTextField textFieldNumber;
	private JTextField textFieldStreet;
	private JTextField textFieldTK;
	private JLabel textFieldHSurname;
	private JLabel textFieldHName;
	private JLabel textFieldHFather;
	private JLabel textFieldHAFM;
	private JLabel textFieldHEmail;
	private JLabel textFieldHTelephone;
	private JLabel textFieldHNumb;
	private JLabel textFieldHStreet;
	private JLabel textFieldHTK;
	private JTextField textFieldSurname;
	private JComboBox<City> cityComboBox;
	private List<City> cities = new ArrayList<>();


	/**
	 * Create the frame.
	 */
	public TeacherIdentity() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				cities = fetchCitiesFromDB();
				cities.forEach(city -> cityComboBox.addItem(city));	
				
				textFieldName.setText("");
				textFieldSurname.setText("");
				textFieldFather.setText("");
				textFieldAFM.setText("");
				textFieldTelephone.setText("");
				textFieldEmail.setText("");
				textFieldStreet.setText("");
				textFieldNumber.setText("");
				cityComboBox.setSelectedIndex(0);
				textFieldTK.setText("");
				//Error fields
				textFieldHName.setText("");
				textFieldHSurname.setText("");
				textFieldHFather.setText("");
				textFieldHAFM.setText("");
				textFieldHTelephone.setText("");
				textFieldHEmail.setText("");
				textFieldHStreet.setText("");
				textFieldHNumb.setText("");
				textFieldHTK.setText("");
				
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(TeacherIdentity.class.getResource("/images/eduv2.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Ποιότητα στην Εκπαίδευση");
		setBounds(100, 100, 640, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
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
		lblNameLabel.setBounds(340, 10, 163, 17);
		header.add(lblNameLabel);
		
		JPanel footer = new JPanel();
		footer.setBackground(new Color(0, 52, 117));
		footer.setBounds(0, 465, 640, 35);
		contentPane.add(footer);
		footer.setLayout(null);
		
		JLabel lblManuall = new JLabel("Εγχειρίδιο Xρήσης");
		lblManuall.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblManuall.setForeground(Color.WHITE);
		lblManuall.setBounds(65, 12, 119, 17);
		footer.add(lblManuall);
		
		JLabel lblQuestions = new JLabel("Συχνές Ερωτήσεις");
		lblQuestions.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblQuestions.setForeground(Color.WHITE);
		lblQuestions.setBounds(249, 12, 119, 17);
		footer.add(lblQuestions);
		
		JLabel lblSupport = new JLabel("Υποστήριξη Πολιτών");
		lblSupport.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSupport.setForeground(Color.WHITE);
		lblSupport.setBounds(433, 12, 139, 17);
		footer.add(lblSupport);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 255));
		separator.setBounds(0, 464, 640, 2);
		contentPane.add(separator);
		
		JLabel lblMainLabel = new JLabel("Στοιχεία Εκπαιδευτή");
		lblMainLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMainLabel.setBounds(213, 36, 199, 17);
		contentPane.add(lblMainLabel);
		
		JLabel labelname = new JLabel("Ονομα*");
		labelname.setForeground(Color.BLACK);
		labelname.setBounds(29, 66, 60, 17);
		contentPane.add(labelname);
		
		JLabel labelAFM = new JLabel("ΑΦΜ*");
		labelAFM.setForeground(Color.BLACK);
		labelAFM.setBounds(45, 125, 44, 17);
		contentPane.add(labelAFM);
		
		JLabel labelTelephon = new JLabel("Τηλέφωνο*");
		labelTelephon.setForeground(Color.BLACK);
		labelTelephon.setBounds(12, 191, 77, 17);
		contentPane.add(labelTelephon);
		
		JLabel lblStreet = new JLabel("Διεύθυνση*");
		lblStreet.setForeground(Color.BLACK);
		lblStreet.setBounds(12, 254, 77, 17);
		contentPane.add(lblStreet);
		
		JLabel labelTown = new JLabel("Πόλη*");
		labelTown.setForeground(Color.BLACK);
		labelTown.setBounds(45, 309, 44, 17);
		contentPane.add(labelTown);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(90, 62, 150, 25);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		// For better user experience
		textFieldName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String inputFirstname;
				inputFirstname = textFieldName.getText().trim();
				
				textFieldHName.setText(inputFirstname.equals("") ? "Το όνομα είναι υποχρεωτικό" : "");
			}
		});
		
		textFieldAFM = new JTextField();
		textFieldAFM.setColumns(10);
		textFieldAFM.setBounds(90, 121, 150, 25);
		contentPane.add(textFieldAFM);
		
		textFieldAFM.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String inputAFM;
				inputAFM = textFieldAFM.getText().trim();
				
				textFieldHAFM.setText(inputAFM.equals("") ? "Το ΑΦΜ είναι υποχρεωτικό" : "");
			}
		});
		
		textFieldTelephone = new JTextField();
		textFieldTelephone.setColumns(10);
		textFieldTelephone.setBounds(90, 189, 150, 25);
		contentPane.add(textFieldTelephone);
		
		textFieldTelephone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String inputTelephone;
				inputTelephone = textFieldTelephone.getText().trim();
				
				textFieldHTelephone.setText(inputTelephone.equals("") ? "Το τηλέφωνο είναι υποχρεωτικό" : "");
			}
		});
		
		textFieldStreet = new JTextField();
		textFieldStreet.setColumns(10);
		textFieldStreet.setBounds(90, 252, 150, 25);
		contentPane.add(textFieldStreet);
		
		textFieldStreet.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String inputStreet;
				inputStreet = textFieldStreet.getText().trim();
				
				textFieldHStreet.setText(inputStreet.equals("") ? "Η διεύθηνση είναι υποχρεωτική" : "");
			}
		});
		
		textFieldHName = new JLabel();
		textFieldHName.setBackground(new Color(240, 240, 240));
		textFieldHName.setForeground(Color.RED);
		textFieldHName.setBounds(90, 88, 150, 25);
		contentPane.add(textFieldHName);
		
		textFieldHAFM = new JLabel();
		textFieldHAFM.setForeground(Color.RED);
		
		textFieldHAFM.setBounds(90, 147, 150, 25);
		contentPane.add(textFieldHAFM);
		
		textFieldHTelephone = new JLabel();
		textFieldHTelephone.setForeground(Color.RED);
		textFieldHTelephone.setBounds(90, 215, 150, 25);
		contentPane.add(textFieldHTelephone);
		
		textFieldHStreet = new JLabel();
		textFieldHStreet.setForeground(Color.RED);
		textFieldHStreet.setBounds(90, 278, 150, 25);
		contentPane.add(textFieldHStreet);
		
		textFieldHTK = new JLabel();
		textFieldHTK.setForeground(Color.RED);
		textFieldHTK.setBounds(90, 331, 150, 25);
		contentPane.add(textFieldHTK);
		
		JLabel labelSurname = new JLabel("Επώνυμο*");
		labelSurname.setForeground(Color.BLACK);
		labelSurname.setBounds(330, 66, 70, 17);
		contentPane.add(labelSurname);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(405, 62, 150, 25);
		contentPane.add(textFieldSurname);
		
		textFieldSurname.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String inputSurname;
				inputSurname = textFieldSurname.getText().trim();
				
				textFieldHSurname.setText(inputSurname.equals("") ? "Το επώνυμο είναι υποχρεωτικό" : "");
			}
		});
		
		JLabel labelFather = new JLabel("Πατρώνυμο*");
		labelFather.setForeground(Color.BLACK);
		labelFather.setBounds(320, 125, 80, 17);
		contentPane.add(labelFather);
		
		
		JLabel labelEmail = new JLabel("e-mail*");
		labelEmail.setForeground(Color.BLACK);
		labelEmail.setBounds(348, 191, 52, 17);
		contentPane.add(labelEmail);
		
		JLabel lblNumb = new JLabel("Αριθμός*");
		lblNumb.setForeground(Color.BLACK);
		lblNumb.setBounds(340, 254, 60, 17);
		contentPane.add(lblNumb);
		
		JLabel labelTK = new JLabel("ΤΚ*");
		labelTK.setForeground(Color.BLACK);
		labelTK.setBounds(370, 309, 30, 17);
		contentPane.add(labelTK);
		
		textFieldFather = new JTextField();
		textFieldFather.setColumns(10);
		textFieldFather.setBounds(405, 121, 150, 25);
		contentPane.add(textFieldFather);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(405, 189, 150, 25);
		contentPane.add(textFieldEmail);
		
		textFieldEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String inputEmail;
				inputEmail = textFieldEmail.getText().trim();
				
				textFieldHEmail.setText(inputEmail.equals("") ? "Το email είναι υποχρεωτικό" : "");
			}
		});
		
		textFieldNumber = new JTextField();
		textFieldNumber.setColumns(10);
		textFieldNumber.setBounds(405, 252, 150, 25);
		contentPane.add(textFieldNumber);
		
		textFieldTK = new JTextField();
		textFieldTK.setColumns(10);
		textFieldTK.setBounds(405, 305, 150, 25);
		contentPane.add(textFieldTK);
		
		textFieldHSurname = new JLabel();
		textFieldHSurname.setForeground(Color.RED);
		textFieldHSurname.setBounds(405, 88, 150, 25);
		contentPane.add(textFieldHSurname);
		
		textFieldHFather = new JLabel();
		textFieldHFather.setForeground(Color.RED);
		textFieldHFather.setBounds(405, 147, 150, 25);
		contentPane.add(textFieldHFather);
		
		textFieldHEmail = new JLabel();
		textFieldHEmail.setForeground(Color.RED);
		textFieldHEmail.setBounds(405, 215, 150, 25);
		contentPane.add(textFieldHEmail);
		
		textFieldHNumb = new JLabel();
		textFieldHNumb.setForeground(Color.RED);
		textFieldHNumb.setBounds(405, 278, 150, 25);
		contentPane.add(textFieldHNumb);
		
		textFieldHTK = new JLabel();
		textFieldHTK.setForeground(Color.RED);
		textFieldHTK.setBounds(405, 331, 150, 25);
		contentPane.add(textFieldHTK);
		
		JButton btnNewButton = new JButton("Κλείσιμο");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getDashboard().setEnabled(true);
				Main.getTeacherIdentity().setVisible(false);
			}
		});
		btnNewButton.setBounds(90, 380, 150, 40);
		contentPane.add(btnNewButton);
		
		JButton btnSubmit = new JButton("Υποβολή");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Data Biding
			String firstname = textFieldName.getText().trim();
			String lastname = textFieldSurname.getText().trim();
			String fathername = textFieldFather.getText().trim();
			String vat = textFieldAFM.getText().trim();
			String phoneNumber = textFieldTelephone.getText().trim();
			String email = 	textFieldEmail.getText().trim();
			String street = textFieldStreet.getText().trim();
			String streetNumber =textFieldNumber.getText().trim();
			City selectedCity = (City) cityComboBox.getSelectedItem();
			int cityId = selectedCity.getId();	
			String zipcode = textFieldTK.getText().trim();
				// Validate
//			textFieldHName.setText(firstname.equals("") ? "Το όνομα είναι υποχρεωτικό" : "");
//			textFieldHSurname.setText(lastname.equals("") ? "Το επώνυμο είναι υποχρεωτικό" : "");
//			textFieldAFM.setText(vat.equals("") ? "Το ΑΦΜ είναι υποχρεωτικό" : "");
//			textFieldEmail.setText(email.equals("") ? "Το email είναι υποχρεωτικό" : "");
//
//			//Return if any field is empty
//			if (selectedCity == null || firstname.equals("") || lastname.equals("") || vat.equals("") || email.equals("") ) {
//				JOptionPane.showMessageDialog(null, "Παρακαλώ συμπληρώστε όλα τα παιδία!", "Error", JOptionPane.ERROR_MESSAGE );
//				return;
//			}
//
//				// Insert
//			String sql = "INSERT INTO Teachers ( firstname, lastname, vat, fatherName, phone_num, email, street, street_num, zipcode, city_id, uuid)"
//					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//			//Connection conn = Dashboard2.getConnection(); //Because here we use DBUtil, put this inside the try
//
//			try (Connection conn = DBUtil.getConnection();
//				PreparedStatement ps = conn.prepareStatement(sql)) {
//				ps.setString(1, firstname);
//				ps.setString(2, lastname);
//				ps.setString(3, vat);
//				ps.setString(4, fathername);
//				ps.setString(5, phoneNumber);
//				ps.setString(6, email);
//				ps.setString(7, street);
//				ps.setString(8, streetNumber);
//				ps.setString(9, zipcode);
//				ps.setInt(10, cityId);
//
//				String uuid = UUID.randomUUID().toString();
//				ps.setString(11, uuid);
//
//				int n = ps.executeUpdate();
//
//				JOptionPane.showMessageDialog(null, n + "record(s) inserted", "INSERT", JOptionPane.PLAIN_MESSAGE );
//				} catch (SQLException e1) {
//				//e1.printStackTrace();
//				JOptionPane.showMessageDialog(null, "insertion error", "error", JOptionPane.ERROR_MESSAGE);
//			}
			
			}
		});
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(SystemColor.desktop);
		btnSubmit.setBounds(405, 380, 150, 40);
		contentPane.add(btnSubmit);
		
	    cityComboBox = new JComboBox<>();
		cityComboBox.setBounds(90, 304, 150, 25);
		contentPane.add(cityComboBox);
	}
	
	private List<City> fetchCitiesFromDB() {
//		String sql = "SELECT * FROM Cities";
//		List<City> cities = new ArrayList();
//
//		//Connection connection = Dashboard2.getConnection();
//
//		try (Connection connection = DBUtil.getConnection();
//				PreparedStatement ps = connection.prepareStatement(sql)) {
//			ResultSet rs = ps.executeQuery();
//
//			while(rs.next()) {
//				int id = rs.getInt("id");
//				String name = rs.getString("name");
//
//				City city = new City(id, name);
//				cities.add(city);
//			}
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(null,"Select cities error", "Error", JOptionPane.ERROR_MESSAGE);
//		}
//		return cities;
		return null;
	}
}
