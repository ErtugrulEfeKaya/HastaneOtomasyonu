package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Bashekim;
import Model.Doctor;
import Model.Hasta;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastatc;
	private JTextField fld_doctortc;
	private DBConnection conn = new DBConnection();
	private JPasswordField fld_doctorpass;
	private JPasswordField fld_hastapass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Yonetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("medicine-2-512.png")));
		lbl_logo.setBounds(27, 41, 50, 50);
		w_pane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("Hastane Sistemimize Hosgeldiniz!");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 24));
		lblNewLabel.setBounds(101, 41, 381, 50);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(11, 122, 471, 239);
		w_pane.add(w_tabpane);
		
		JPanel w_hastalogin = new JPanel();
		w_hastalogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Hasta Girisi", null, w_hastalogin, null);
		w_hastalogin.setLayout(null);
		
		JLabel lblTc = new JLabel("T.C. Numaraniz :");
		lblTc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblTc.setBounds(33, 28, 180, 35);
		w_hastalogin.add(lblTc);
		
		JLabel lblifreniz = new JLabel("Sifreniz :");
		lblifreniz.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblifreniz.setBounds(33, 84, 180, 35);
		w_hastalogin.add(lblifreniz);
		
		fld_hastatc = new JTextField();
		fld_hastatc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 20));
		fld_hastatc.setBounds(209, 28, 247, 35);
		w_hastalogin.add(fld_hastatc);
		fld_hastatc.setColumns(10);
		
		JButton btn_register = new JButton("Kayit Ol");
		btn_register.setBackground(new Color(0, 206, 209));
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_register.setBounds(10, 145, 211, 55);
		w_hastalogin.add(btn_register);
		
		JButton btn_hastalogin = new JButton("Giris Yap");
		btn_hastalogin.setBackground(new Color(0, 250, 154));
		btn_hastalogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_hastatc.getText().length() == 0 || fld_hastapass.getText().length() == 0  ) {
					Helper.showMsg("fill");
					
				}else {
					boolean key = true;
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs =st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(fld_hastatc.getText().equals(rs.getString("tcno")) && fld_hastapass.getText().equals(rs.getString("password"))) {
								if(rs.getString("type").equals("hasta")) {
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setPassword(rs.getString("password"));
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("name"));
									hasta.setType(rs.getString("type"));
									HastaGUI hGUI = new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
									key = false;
								}
							}
						}
					}catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					if(key) {
						Helper.showMsg("Sistemimizde boyle bir hasta kaydi bulunmamaktadir. \nLuften kay�t olunuz !");
					}
				}
			}
		});
		btn_hastalogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		btn_hastalogin.setBounds(245, 145, 211, 55);
		w_hastalogin.add(btn_hastalogin);
		
		fld_hastapass = new JPasswordField();
		fld_hastapass.setBounds(209, 82, 247, 37);
		w_hastalogin.add(fld_hastapass);
		
		JPanel w_doktorlogin = new JPanel();
		w_doktorlogin.setBackground(new Color(255, 255, 255));
		w_tabpane.addTab("Doktor Girisi", null, w_doktorlogin, null);
		w_doktorlogin.setLayout(null);
		
		JLabel lblTc_1 = new JLabel("T.C. Numaraniz :");
		lblTc_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblTc_1.setBounds(33, 28, 180, 35);
		w_doktorlogin.add(lblTc_1);
		
		JLabel lblifreniz_1 = new JLabel("Sifreniz :");
		lblifreniz_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblifreniz_1.setBounds(33, 84, 180, 35);
		w_doktorlogin.add(lblifreniz_1);
		
		fld_doctortc = new JTextField();
		fld_doctortc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 20));
		fld_doctortc.setColumns(10);
		fld_doctortc.setBounds(209, 28, 247, 35);
		w_doktorlogin.add(fld_doctortc);
		
		JButton btn_doktorlogin = new JButton("Giris Yap");
		btn_doktorlogin.setBackground(new Color(0, 250, 154));
		btn_doktorlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doctortc.getText().length()== 0 || fld_doctorpass.getText().length() == 0) {
					Helper.showMsg("fill");				
				}else {
					boolean key = true;
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs =st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(fld_doctortc.getText().equals(rs.getString("tcno")) && fld_doctorpass.getText().equals(rs.getString("password"))) {
								if(rs.getString("type").equals("bashekim")) {
									Bashekim bhekim = new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setPassword(rs.getString("password"));
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									BashekimGUI bGUI = new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();
									key=false;
								}
								if(rs.getString("type").equals("doktor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword(rs.getString("password"));
									doctor.setTcno(rs.getString("tcno"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();
									key=false;
								} 
							}
						}
					}
					catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(key) {
						Helper.showMsg("Sistemimizde boyle bir doktor kaydi bulunmamaktadir. \nLüften Bashekime basvurunuz !");
					}
				}
				
			}
		});
		btn_doktorlogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		btn_doktorlogin.setBounds(10, 145, 446, 55);
		w_doktorlogin.add(btn_doktorlogin);
		
		fld_doctorpass = new JPasswordField();
		fld_doctorpass.setBounds(209, 84, 247, 37);
		w_doktorlogin.add(fld_doctorpass);
	}
}
