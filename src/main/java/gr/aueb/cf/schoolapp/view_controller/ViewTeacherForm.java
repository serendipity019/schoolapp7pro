package gr.aueb.cf.schoolapp.view_controller;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.view_controller.LandingPage;
import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.model.City;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewTeacherForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblGetTeacherCode;
	private JLabel lblGetTeacherName;
	private JLabel lblGetTeacherSurname;
	private JLabel lsbGetTeacherFatherName;
	private JLabel lblGetTeacherAFM;
	private JLabel lblGetTeacherTelephone;
	private JLabel lblGetTeacherEmail;
	private JLabel lblGetStreet;
	private JLabel lblGetStreetNumber;
	private JLabel lblGetTK;
	private JLabel lblGetTeacherTown;
	
	private List<City> cities = new ArrayList<>(); 


	/**
	 * Create the frame.
	 */
	public ViewTeacherForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				cities = fetchCitiesFromDB();
				//cities.forEach( city -> cityComboBox.addItem(city)); // one way
				fetchTeacherFromDatabase(Main.getTeacherPage().getSelectedId());
				//Need to continue here
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 640, 500);
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
		
		//Main div
		// Left div 
		
		JLabel lblMain = new JLabel("Αίτηση Εκπαιδευτή");
		lblMain.setForeground(Color.BLACK);
		lblMain.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblMain.setBounds(221, 49, 217, 17);
		contentPane.add(lblMain);
		
		JLabel lblTeacherCode = new JLabel("Κωδικός Εκπαιδευτή:");
		lblTeacherCode.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTeacherCode.setForeground(Color.BLACK);
		lblTeacherCode.setBounds(40, 80, 140, 18);
		contentPane.add(lblTeacherCode);
		
		JLabel lblTeacherName = new JLabel("Όνομα Εκπαιδευτή:");
		lblTeacherName.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTeacherName.setForeground(Color.BLACK);
		lblTeacherName.setBounds(40, 110, 140, 18);
		contentPane.add(lblTeacherName);
		
		JLabel lblTeacherSurname = new JLabel("Επώνυμο Εκπαιδευτή:");
		lblTeacherSurname.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTeacherSurname.setForeground(Color.BLACK);
		lblTeacherSurname.setBounds(40, 140, 140, 18);
		contentPane.add(lblTeacherSurname);
		
		JSeparator separator1 = new JSeparator();
		separator1.setBackground(new Color(128, 128, 128));
		separator1.setBounds(20, 165, 600, 2);
		contentPane.add(separator1);
		
		JLabel lblTeacherAFM = new JLabel("ΑΦΜ Εκπαιδευτή:");
		lblTeacherAFM.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTeacherAFM.setForeground(Color.BLACK);
		lblTeacherAFM.setBounds(40, 175, 140, 18);
		contentPane.add(lblTeacherAFM);
		
		JLabel lsbTeacherFatherName = new JLabel("Πατρώνυμο Εκπαιδευτή:");
		lsbTeacherFatherName.setFont(new Font("Dialog", Font.PLAIN, 12));
		lsbTeacherFatherName.setForeground(Color.BLACK);
		lsbTeacherFatherName.setBounds(40, 205, 140, 18);
		contentPane.add(lsbTeacherFatherName);
		
		JSeparator separator2 = new JSeparator();
		separator2.setBackground(new Color(128, 128, 128));
		separator2.setBounds(20, 230, 600, 2);
		contentPane.add(separator2);
		
		JLabel lblTeacherTelephone = new JLabel("Τηλέφωνο Εκπαιδευτή:");
		lblTeacherTelephone.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTeacherTelephone.setForeground(Color.BLACK);
		lblTeacherTelephone.setBounds(40, 230, 140, 18);
		contentPane.add(lblTeacherTelephone);
		
		JLabel lblTeacherEmail = new JLabel("E-mail Εκπαιδευτή:");
		lblTeacherEmail.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTeacherEmail.setForeground(Color.BLACK);
		lblTeacherEmail.setBounds(40, 260, 140, 18);
		contentPane.add(lblTeacherEmail);
		
		JLabel lblStreet = new JLabel("Διεύθυνση Εκπαιδευτή:");
		lblStreet.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblStreet.setForeground(Color.BLACK);
		lblStreet.setBounds(40, 290, 140, 18);
		contentPane.add(lblStreet);
		
		JLabel lblStreetNumber = new JLabel("Αριθμός Διεύθυνσης:");
		lblStreetNumber.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblStreetNumber.setForeground(Color.BLACK);
		lblStreetNumber.setBounds(40, 320, 140, 18);
		contentPane.add(lblStreetNumber);
		
		JSeparator separator3 = new JSeparator();
		separator3.setBackground(new Color(128, 128, 128));
		separator3.setBounds(20, 345, 600, 2);
		contentPane.add(separator3);
		
		JLabel lblTeacherTown = new JLabel("Πόλη Εκπαιδευτή:");
		lblTeacherTown.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTeacherTown.setForeground(Color.BLACK);
		lblTeacherTown.setBounds(40, 350, 140, 18);
		contentPane.add(lblTeacherTown);
		
		JLabel lblTK = new JLabel("TK:");
		lblTK.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTK.setForeground(Color.BLACK);
		lblTK.setBounds(40, 380, 140, 18);
		contentPane.add(lblTK);
		
		JButton btnClose = new JButton("Κλείσιμο");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeacherform().setVisible(false);
				Main.getTeacherPage().setEnabled(true);				
			}
		});
		btnClose.setForeground(Color.BLACK);
		btnClose.setBounds(487, 415, 115, 35);
		contentPane.add(btnClose);
		
		// right div. These we will take with get
		
	    lblGetTeacherCode = new JLabel("Κωδικός Εκπαιδευτή:");
		lblGetTeacherCode.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblGetTeacherCode.setForeground(Color.BLACK);
		lblGetTeacherCode.setBounds(300, 80, 140, 18);
		contentPane.add(lblGetTeacherCode);
		
	    lblGetTeacherName = new JLabel("Όνομα Εκπαιδευτή:");
		lblGetTeacherName.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblGetTeacherName.setForeground(Color.BLACK);
		lblGetTeacherName.setBounds(300, 110, 140, 18);
		contentPane.add(lblGetTeacherName);
		
		lblGetTeacherSurname = new JLabel("Επώνυμο Εκπαιδευτή:");
		lblGetTeacherSurname.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblGetTeacherSurname.setForeground(Color.BLACK);
		lblGetTeacherSurname.setBounds(300, 140, 140, 18);
		contentPane.add(lblGetTeacherSurname);
		
	
		
		lblGetTeacherAFM = new JLabel("ΑΦΜ Εκπαιδευτή:");
		lblGetTeacherAFM.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblGetTeacherAFM.setForeground(Color.BLACK);
		lblGetTeacherAFM.setBounds(300, 175, 140, 18);
		contentPane.add(lblGetTeacherAFM);
		
		lsbGetTeacherFatherName = new JLabel("Πατρώνυμο Εκπαιδευτή:");
		lsbGetTeacherFatherName.setFont(new Font("Dialog", Font.PLAIN, 12));
		lsbGetTeacherFatherName.setForeground(Color.BLACK);
		lsbGetTeacherFatherName.setBounds(300, 205, 140, 18);
		contentPane.add(lsbGetTeacherFatherName);
		
		
		lblGetTeacherTelephone = new JLabel("Τηλέφωνο Εκπαιδευτή:");
		lblGetTeacherTelephone.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblGetTeacherTelephone.setForeground(Color.BLACK);
		lblGetTeacherTelephone.setBounds(300, 230, 140, 18);
		contentPane.add(lblGetTeacherTelephone);
		
		lblGetTeacherEmail = new JLabel("E-mail Εκπαιδευτή:");
		lblGetTeacherEmail.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblGetTeacherEmail.setForeground(Color.BLACK);
		lblGetTeacherEmail.setBounds(300, 260, 140, 18);
		contentPane.add(lblGetTeacherEmail);
		
		lblGetStreet = new JLabel("Διεύθυνση Εκπαιδευτή:");
		lblGetStreet.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblGetStreet.setForeground(Color.BLACK);
		lblGetStreet.setBounds(300, 290, 140, 18);
		contentPane.add(lblGetStreet);
		
		lblGetStreetNumber = new JLabel("");
		lblGetStreetNumber.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblGetStreetNumber.setForeground(Color.BLACK);
		lblGetStreetNumber.setBounds(300, 320, 140, 18);
		contentPane.add(lblGetStreetNumber);
		
		
		lblGetTeacherTown = new JLabel("");
		lblGetTeacherTown.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblGetTeacherTown.setForeground(Color.BLACK);
		lblGetTeacherTown.setBounds(300, 350, 140, 18);
		contentPane.add(lblGetTeacherTown);
		
		lblGetTK = new JLabel("TK:");
		lblGetTK.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblGetTK.setForeground(Color.BLACK);
		lblGetTK.setBounds(300, 380, 140, 18);
		contentPane.add(lblGetTK);
	}
	
	// this method is to write the name of city in lblGetTeacherTown label
		private List<City> fetchCitiesFromDB() {
			String sql = "SELECT * FROM Cities";
			List<City> cities = new ArrayList();
			
			Connection connection = Dashboard2.getConnection();
			
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					
					City city = new City(id, name);
					cities.add(city);
				} 
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Select cities error", "Error", JOptionPane.ERROR_MESSAGE);
			}
			return cities; 
		}
		
		/*Next method fill the LblGetFields with Teacher info*/
		//private void fetchTeacherFromDatabase(int id) {
		private void fetchTeacherFromDatabase(String uuid) {
			//String sql = "SELECT * FROM Teachers WHERE id =?";
			String sql = "SELECT * FROM Teachers WHERE uuid =?";
			
			Connection conn = Dashboard2.getConnection();
			
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				//ps.setInt(1, id);
				ps.setString(1, uuid);
				ResultSet rs = ps.executeQuery();
				
				if (rs.next()) {
					System.out.println(lblGetTeacherCode);
					lblGetTeacherCode.setText(rs.getString("uuid"));
					lblGetTeacherName.setText(rs.getString("firstname"));
					lblGetTeacherSurname.setText(rs.getString("lastname"));
					lsbGetTeacherFatherName.setText(rs.getString("fatherName"));
					lblGetTeacherAFM.setText(rs.getString("vat"));
					lblGetTeacherTelephone.setText(rs.getString("phone_num"));
					lblGetTeacherEmail.setText(rs.getString("email"));
					lblGetStreet.setText(rs.getString("street"));
					lblGetStreetNumber.setText(rs.getString("street_num"));
					lblGetTK.setText(rs.getString("zipcode"));
					//cityComboBox.setSelectedIndex(rs.getInt("city_id")-1);
					
					// !!! Here need attention how to pass the city name from the database
					int cityIdFromDB = rs.getInt("city_id"); // First we get the id from the DB
					// find the matching city using Stream options
					City selectedCity = cities.stream().filter(city -> city.getId() == cityIdFromDB)
							.findFirst().orElse(null); //Returns null if no match is found. This method functioned with this way, we need the else so or either
					
					
					//Select the city in the JComboBox
					if (selectedCity != null) {
						lblGetTeacherTown.setText(selectedCity.getName());
					} else {
						lblGetTeacherTown.setText("null");
					}
					
					//Below these 2 lines are for test purposes
					//City selectedCity = (City) cityComboBox.getSelectedItem();
					//int cityId = selectedCity.getId();
				}
				
				
			} catch (SQLException e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "Select error in fetch teacher", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

}
