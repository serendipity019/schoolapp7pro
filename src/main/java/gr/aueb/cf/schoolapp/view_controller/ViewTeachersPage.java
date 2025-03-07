package gr.aueb.cf.schoolapp.view_controller;



import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.view_controller.Dashboard2;
import gr.aueb.cf.schoolapp.view_controller.LandingPage;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JTable;
//import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewTeachersPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFieldSurname;
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	//private int selectedId;
	//private String selectedId;
	private String selectedUUID;
	/**
	 * Create the frame.
	 */
	public ViewTeachersPage() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				buildTable();
			}
		});
		setTitle("Ποιότητα στην Εκπαίδευση");
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
		
		JLabel lblMain = new JLabel("Αιτήσεις Εκπαιδευτών");
		lblMain.setForeground(Color.BLACK);
		lblMain.setFont(new Font("Dialog", Font.BOLD, 18));
		lblMain.setBounds(221, 49, 217, 17);
		contentPane.add(lblMain);
		
		JLabel lblSurname = new JLabel("Επώνυμο");
		lblSurname.setFont(new Font("Dialog", Font.BOLD, 12));
		lblSurname.setForeground(Color.BLACK);
		lblSurname.setBounds(24, 80, 60, 17);
		contentPane.add(lblSurname);
		
		txtFieldSurname = new JTextField();
		txtFieldSurname.setBounds(85, 78, 130, 27);
		contentPane.add(txtFieldSurname);
		txtFieldSurname.setColumns(10);
		
		JButton btnSearch = new JButton("Αναζήτηση");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildTable();
			}
		});
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(0, 100, 0));
		btnSearch.setBounds(230, 78, 115, 27);
		contentPane.add(btnSearch);
		
		JButton btnClearense = new JButton("Εκκαθάριση");
		btnClearense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFieldSurname.setText("");
				buildTable();
			}
		});
		btnClearense.setForeground(Color.BLACK);
		btnClearense.setBounds(360, 78, 115, 27);
		contentPane.add(btnClearense);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// check if the selection is still adjusting
				if(!e.getValueIsAdjusting()) {
					// Get the selected row index
					int selectedRow = table.getSelectedRow();
					
					//Check if the row is selected
					if (selectedRow != -1) {
						//Get data from the selected row
						selectedUUID = (String) model.getValueAt(selectedRow, 0);
						 //If we want to take the id then:
						 //String selectedStr = (String) model.getValueAt(selectedRow, 0); // id column
						 //selectedId = Integer.parseInt(selectedStr);
						 
					}
				}
			}
		}); 
		
		table.setModel(new DefaultTableModel(
				new Object[][] { 
				}, 
				new String[] {
						"Κωδικός", "'Ονομα","Επώνυμο"}
		));
		//table.setBorder(new LineBorder(new Color(0, 0, 0)));
		//table.setColumnSelectionAllowed(true);
		table.setBounds(24, 130, 451, 320);
	    model = (DefaultTableModel) table.getModel();
		contentPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(23, 129, 453, 322);
		contentPane.add(scrollPane);
		
		JButton btnPreview = new JButton("Προβολή");
		btnPreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeacherPage().setEnabled(false);
				Main.getTeacherform().setVisible(true);
			}
		});
		btnPreview.setForeground(Color.WHITE);
		btnPreview.setBackground(new Color(0, 100, 0));
		btnPreview.setBounds(487, 155, 115, 35);
		contentPane.add(btnPreview);
		
		JButton btnEdit = new JButton("Επεξεργασία");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeacherform().setEnabled(false);
				Main.getUpdatePage().setVisible(true);
			}
		});
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setBackground(new Color(0, 100, 0));
		btnEdit.setBounds(487, 200, 115, 35);
		contentPane.add(btnEdit);
		
		JButton btnDelete = new JButton("Διαγραφή");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//doDelete(selectedId);
				doDelete(selectedUUID);
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(new Color(0, 100, 0));
		btnDelete.setBounds(487, 245, 115, 35);
		contentPane.add(btnDelete);
		
		JButton btnClose = new JButton("Κλείσιμο");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeacherPage().setVisible(false);
				Main.getDashboard().setEnabled(true);
			}
		});
		btnClose.setForeground(Color.BLACK);
		btnClose.setBounds(487, 415, 115, 35);
		contentPane.add(btnClose);
		
		
	}
	
	public String getSelectedId() {
		System.out.println(selectedUUID);// for debug
		return selectedUUID;
	}
	//if we want to take the id
	 //public String getSelectedId() {
		//return selectedId;
	 //}
	
	//To DO builtTable
	private void buildTable() {
		// String sql = "SELECT id, firstname, lastname FROM Teachers WHERE lastname LIKE ?"; // if we want the id
		String sql = "SELECT uuid, firstname, lastname FROM Teachers WHERE lastname LIKE ?"; 
		Connection conn = Dashboard2.getConnection();
		
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, txtFieldSurname.getText().trim()+ "%" );
			ResultSet rs = ps.executeQuery();
			
			model.setRowCount(0); //clear the table
			while (rs.next()) {
				Object[] row = {
						rs.getString("uuid"), //.substring(0, 8),
						//rs.getString("id"), // if we want to take the id
						rs.getString("firstname"),
						rs.getString("lastname")
				};
				model.addRow(row);
			}
		} catch(SQLException e) {
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Select Error", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	//To Do  doDelete
	// private void doDelete(int id) { // if want to use the id for delete
	private void doDelete(String uuid) {
		//String sql = "DELETE FROM Teachers WHERE id = ?";
		String sql = "DELETE FROM Teachers WHERE uuid = ?";
		Connection conn = Dashboard2.getConnection();
		
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			//ps.setString(1, id);
			ps.setString(1, uuid);
			
			int answer = JOptionPane.showConfirmDialog(null,"Είστε σίγουρος/η", "Διαγραφή", JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION) {
				int rowsAffected =  ps.executeUpdate();
				JOptionPane.showMessageDialog(null, rowsAffected + "γραμμή/ες διαγράφηκαν", "Διαγραφή", JOptionPane.INFORMATION_MESSAGE);
			} else {
				return; 
			}
		} catch (SQLException ex) {
			//ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Delete Error", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	}
