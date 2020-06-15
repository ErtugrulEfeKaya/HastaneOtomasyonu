package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Hasta;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_tcno;
	private JPasswordField fld_pass;
	private Hasta hasta = new Hasta();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setResizable(false);
		setTitle("Hastane Kayit Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 310, 355);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel label = new JLabel("Ad Soyad");
		label.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		label.setBounds(10, 11, 172, 26);
		w_pane.add(label);
		
		fld_name = new JTextField();
		fld_name.setBackground(new Color(255, 255, 255));
		fld_name.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		fld_name.setColumns(10);
		fld_name.setBounds(10, 37, 274, 36);
		w_pane.add(fld_name);
		
		JLabel lblTcNumaras = new JLabel("T.C. Numarasi");
		lblTcNumaras.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblTcNumaras.setBounds(10, 74, 172, 26);
		w_pane.add(lblTcNumaras);
		
		fld_tcno = new JTextField();
		fld_tcno.setBackground(new Color(255, 255, 255));
		fld_tcno.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(10, 101, 274, 36);
		w_pane.add(fld_tcno);
		
		JLabel lblifre = new JLabel("Sifre");
		lblifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		lblifre.setBounds(10, 138, 172, 26);
		w_pane.add(lblifre);
		
		fld_pass = new JPasswordField();
		fld_pass.setBackground(new Color(255, 255, 255));
		fld_pass.setFont(new Font("Yu Gothic UI", Font.PLAIN, 15));
		fld_pass.setBounds(10, 165, 274, 36);
		w_pane.add(fld_pass);
		
		JButton btn_register = new JButton("Kayit Ol");
		btn_register.setBackground(new Color(0, 250, 154));
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_tcno.getText().length() == 0 || fld_pass.getText().length() == 0 || fld_name.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					try {
						boolean control = hasta.register(fld_tcno.getText(), fld_pass.getText(), fld_name.getText());
						if(control) {
							Helper.showMsg("success");
							LoginGUI login = new LoginGUI();
							login.setVisible(true);
							dispose();
						}else {
							Helper.showMsg("error");
						}
							
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btn_register.setBounds(10, 213, 274, 43);
		w_pane.add(btn_register);
		
		JButton btn_backto = new JButton("Geri Don");
		btn_backto.setBackground(new Color(0, 206, 209));
		btn_backto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_backto.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btn_backto.setBounds(10, 267, 274, 43);
		w_pane.add(btn_backto);
	}
}
