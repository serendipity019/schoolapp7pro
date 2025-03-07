package gr.aueb.cf.schoolapp;

import gr.aueb.cf.schoolapp.view_controller.*;

import java.awt.EventQueue;

public class Main {
	private final static LandingPage landingPage = new LandingPage();
	private final static ConnectionPage connectionPage = new ConnectionPage();
	private static final Dashboard2 dashboard = new Dashboard2();
	private static final TeacherIdentity teacherIdentity = new TeacherIdentity();
	private static final ViewTeachersPage teacherPage = new ViewTeachersPage();
	private static final ViewTeacherForm teacherForm = new ViewTeacherForm();
	private static final UpdateTeacherPage updatePage = new UpdateTeacherPage();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					landingPage.setVisible(true);
					landingPage.setLocationRelativeTo(null);
					
					connectionPage.setVisible(false);
					connectionPage.setLocationRelativeTo(null);
					
					dashboard.setVisible(false);
					dashboard.setLocationRelativeTo(null);
					
					teacherIdentity.setVisible(false);
					teacherIdentity.setLocationRelativeTo(null);
					
					teacherPage.setVisible(false);
					teacherPage.setLocationRelativeTo(null);
					
					teacherForm.setVisible(false);
					teacherForm.setLocationRelativeTo(null);
					
					updatePage.setVisible(false);
					updatePage.setLocationRelativeTo(null);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static LandingPage getLandingPage() {
		return landingPage;
	}
	
	public static ConnectionPage getConnectionPage() {
		return connectionPage;
	}

	public static Dashboard2 getDashboard() {
		return dashboard;
	}

	public static TeacherIdentity getTeacherIdentity() {
		return teacherIdentity;
	}

	public static ViewTeachersPage getTeacherPage() {
		return teacherPage;
	}

	public static ViewTeacherForm getTeacherform() {
		return teacherForm;
	}

	public static UpdateTeacherPage getUpdatePage() {
		return updatePage;
	}
	
	
	

}
