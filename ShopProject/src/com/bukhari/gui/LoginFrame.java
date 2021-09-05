package com.bukhari.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.bukhari.connection.DBConnection;


public class LoginFrame extends JFrame {
	

	// dark purple  281452   40, 20, 82
//#5DADE2 orange   247,131,63
//
//#37353D black  55,53,61

	Connection con = DBConnection.getConnection();
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JLabel lblCredentials;
	private JLabel lblStatus;
	private JLabel lblShowpass;
	private int count=0;
	private JTextField textField;
	
	public static String USER ="";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setTitle("Login");
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 622);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));	
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(40, 20, 82));
		panel.setForeground(Color.WHITE);
		panel.setBounds(0, 0, 349, 583);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Honesty is the Best");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 21));
		lblNewLabel.setBounds(30, 461, 268, 40);
		panel.add(lblNewLabel);
		
		JLabel lblSystem = new JLabel("Policy");
		lblSystem.setForeground(Color.WHITE);
		lblSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSystem.setFont(new Font("Segoe UI Black", Font.BOLD, 21));
		lblSystem.setBounds(30, 501, 268, 40);
		panel.add(lblSystem);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/com/bukhari/imgs/trolley_64px.png")));
		lblNewLabel_1.setBounds(125, 205, 100, 89);
		panel.add(lblNewLabel_1);
		
		JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(LoginFrame.class.getResource("/com/bukhari/imgs/shopping_bag_full_64px.png")));
		label.setBounds(208, 345, 90, 79);
		panel.add(label);
		
		
		JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/com/bukhari/imgs/barcode_64px.png")));
		label_1.setBounds(69, 345, 100, 79);
		panel.add(label_1);
		
		lblStatus = new JLabel("");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Segoe UI Emoji", Font.BOLD | Font.ITALIC, 13));
		lblStatus.setBounds(10, 552, 159, 20);
		panel.add(lblStatus);
		
		JLabel lblHealthCareSystem = new JLabel("Bukidnon");
		lblHealthCareSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblHealthCareSystem.setForeground(Color.WHITE);
		lblHealthCareSystem.setFont(new Font("Segoe UI Black", Font.BOLD, 29));
		lblHealthCareSystem.setBounds(30, 44, 278, 40);
		panel.add(lblHealthCareSystem);
		
		JLabel lblSystem_1 = new JLabel("Truckers");
		lblSystem_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSystem_1.setForeground(Color.WHITE);
		lblSystem_1.setFont(new Font("Segoe UI Black", Font.BOLD, 29));
		lblSystem_1.setBounds(30, 90, 278, 40);
		panel.add(lblSystem_1);
		

		
		
	
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.WHITE);
		panel_1.setBackground(new Color(252,252,252));
		panel_1.setBounds(347, 0, 377, 583);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		
		
		lblShowpass = new JLabel("");
		lblShowpass.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowpass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(count%2==0) {
				     txtPassword.setEchoChar((char)0); //password = JPasswordField
				}else {
					txtPassword.setEchoChar('•');
				}
				count++;
			}
		});
		lblShowpass.setBounds(325, 348, 31, 20);
		panel_1.add(lblShowpass);
		lblShowpass.setIcon(new ImageIcon(LoginFrame.class.getResource("/com/bukhari/imgs/eye_24px.png")));
		
		JLabel lblLogin = new JLabel("Sign in");
		lblLogin.setFont(new Font("Segoe Print", Font.BOLD, 26));
		lblLogin.setForeground(new Color(40, 20, 82));
		lblLogin.setBounds(35, 44, 159, 37);
		panel_1.add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(40, 20, 82));
		lblUsername.setFont(new Font("Segoe Script", Font.BOLD, 20));
		lblUsername.setBounds(35, 201, 106, 20);
		panel_1.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setForeground(new Color(40, 20, 82));
		txtUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
				
			}
		});
		txtUsername.setOpaque(false);
		txtUsername.setBorder(null);
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUsername.setBounds(35, 250, 320, 20);
		panel_1.add(txtUsername);
		txtUsername.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(40, 20, 82));
		separator.setBounds(35, 277, 320, 2);
		panel_1.add(separator);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(40, 20, 82));
		lblPassword.setFont(new Font("Segoe Script", Font.BOLD, 20));
		lblPassword.setBounds(35, 308, 106, 20);
		panel_1.add(lblPassword);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(35, 379, 320, 2);
		separator_1.setBackground(new Color(40, 20, 82));
		panel_1.add(separator_1);
		
		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		txtPassword.setForeground(new Color(40, 20, 82));
		txtPassword.setOpaque(false);
		txtPassword.setBorder(null);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPassword.setBounds(35, 348, 320, 20);
		panel_1.add(txtPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				mouseClickedEffect(btnLogin);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnLogin);
			}
		});
		btnLogin.setForeground(new Color(40, 20, 82));
		// For Transpareting the Button
		btnLogin.setOpaque(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBorder(new LineBorder(new Color(40, 20, 82)));

		btnLogin.setBounds(35, 419, 106, 28);
		panel_1.add(btnLogin);
		
		lblCredentials = new JLabel("");
		lblCredentials.setForeground(new Color(255,124,124));
		lblCredentials.setFont(new Font("Segoe UI Emoji", Font.BOLD | Font.ITALIC, 13));
		lblCredentials.setBounds(179, 427, 159, 20);
		panel_1.add(lblCredentials);
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		if(con != null) {
			lblStatus.setText("Connected");
		}else {
			lblStatus.setText("Not Connected");
		}
		
		//setUndecorated(true);
	}
	
	public void mouseClickedEffect(JButton button) {
		button.setForeground(new Color(93,173,226));
		button.setBorder(new LineBorder(new Color(93,173,226)));
	}
	public void mouseReleasedEffect(JButton button) {
		button.setForeground(new Color(40, 20, 82));
		button.setBorder(new LineBorder(new Color(40, 20, 82)));
	}
	
	
	
	public void login() {
		if(!(txtUsername.getText().toString().isEmpty() || 
				txtPassword.getText().toString().isEmpty())){
			
			String username = txtUsername.getText().toString().trim();
			String password = txtPassword.getText().toString().trim();
			
			boolean check =  letLoginin(username, password);
			
			if(check == true) {
				USER = username;
				JOptionPane.showMessageDialog(LoginFrame.this, "Login Successful","Login Screen",
						JOptionPane.INFORMATION_MESSAGE);
//			
				AdminFrame mainFrame = new AdminFrame(); 
				LoginFrame loginFrame = new LoginFrame(); 
				mainFrame.setTitle("Admin Panel");
				loginFrame.setVisible(false);
				mainFrame.setVisible(true);
				dispose();
				
			}else {
				lblCredentials.setText("Invalid Credentials");
			}
			
		}else {
			lblCredentials.setText("Please Fill the Fields");
		}
	}
	
	public boolean letLoginin(String username , String password) {
		PreparedStatement ps = null;
		String sql = "Select * from login where username=? and password=?";
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}

