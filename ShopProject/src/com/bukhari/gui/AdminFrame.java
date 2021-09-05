package com.bukhari.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class AdminFrame extends JFrame {

	private JPanel contentPane;
	
	private JButton btnLogout;
	
	// Panels
	private ProductPanel panelProduct;
	private StockPanel panelStock;
	private ClientPanel panelClient;
	private PurchasePanel panelPurchase;
	private StockTranferedPanel stockTransferPanel;
	private ReportPanel reportPanel;
	private LastReportPanel lastReportPanel;
	private LoanPanel loanPanel;
	
//	private JPanelBooks panelBooks;
//	private JPanelssued panelIssued;
	//Resizing the Icon
//	private Image icon = new ImageIcon(AdminFrame.class.getResource("/com/bukhari/imgs/knowledge_sharing_80px.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
	private Image cashIcon = new ImageIcon(AdminFrame.class.getResource("/com/bukhari/imgs/cash_48px.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
	
	private Image userIcon = new ImageIcon(AdminFrame.class.getResource("/com/bukhari/imgs/user_60px.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);

	private Image stockIcon = new ImageIcon(AdminFrame.class.getResource("/com/bukhari/imgs/box_48px.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);

	private Image stockTransfer = new ImageIcon(AdminFrame.class.getResource("/com/bukhari/imgs/data_transfer_100px.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
	private Image reports =new ImageIcon(AdminFrame.class.getResource("/com/bukhari/imgs/report_file_64px.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
	
	private Image lastReport =	new ImageIcon(AdminFrame.class.getResource("/com/bukhari/imgs/graph_report_64px.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
	
	private Image loan = new ImageIcon(AdminFrame.class.getResource("/com/bukhari/imgs/debt_64px.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
	




	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame frame = new AdminFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public AdminFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1361, 712);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelProduct = new ProductPanel();
		panelStock = new StockPanel();
		panelClient = new ClientPanel();
		panelPurchase = new PurchasePanel();
		stockTransferPanel = new StockTranferedPanel();
		reportPanel = new ReportPanel();
		
		lastReportPanel = new LastReportPanel();
		
		loanPanel = new LoanPanel();
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 229, 673);
		panel.setBackground(new Color(40, 20, 82));
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnLogout = new JButton("Log out");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminFrame adminFrame = new AdminFrame(); 
				LoginFrame loginFrame = new LoginFrame(); 
				loginFrame.setVisible(true);
				adminFrame.setVisible(false);
				loginFrame.setTitle("Login");
				dispose();
			}
		});
		
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				mouseClickedEffect(btnLogout);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseReleasedEffect(btnLogout);
			}
		});
		
		btnLogout.setOpaque(false);
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Segoe Script", Font.PLAIN, 14));
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBorder(new LineBorder(Color.WHITE));
		btnLogout.setBounds(10, 634, 106, 28);
		panel.add(btnLogout);
		
		final JPanel pProduct = new JPanel();
		pProduct.addMouseListener(new PanelButtonMouseAdapter(pProduct){
			@Override
			public void mouseClicked(MouseEvent e){
				menuClicked(panelProduct);
			}
		});
		pProduct.setBounds(0, 118, 229, 42);
		pProduct.setBackground(new Color(55, 28, 115));
		panel.add(pProduct);
		pProduct.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Product");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(79, 11, 105, 20);
		pProduct.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(AdminFrame.class.getResource("/com/bukhari/imgs/open_box_32px.png")));
		lblNewLabel_2.setBounds(28, 0, 36, 42);
		pProduct.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Admin");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblNewLabel.setBounds(10, 11, 86, 19);
		panel.add(lblNewLabel);
		
		final JPanel pCash = new JPanel();
		pCash.addMouseListener(new PanelButtonMouseAdapter(pCash){
			@Override
			public void mouseClicked(MouseEvent e){
				menuClicked(panelPurchase);
			}
		});

		pCash.setLayout(null);
		pCash.setBackground(new Color(55, 28, 115));
		pCash.setBounds(0, 203, 229, 42);
		panel.add(pCash);
		
		JLabel lblCash = new JLabel("Purchase");
		lblCash.setForeground(Color.WHITE);
		lblCash.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCash.setBounds(83, 11, 105, 20);
		pCash.add(lblCash);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(cashIcon));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(26, 0, 36, 42);
		pCash.add(label_1);
		
		JPanel pClients = new JPanel();
		pClients.addMouseListener(new PanelButtonMouseAdapter(pClients){
			@Override
			public void mouseClicked(MouseEvent e){
				menuClicked(panelClient);
			}
		});
		pClients.setLayout(null);
		pClients.setBackground(new Color(55, 28, 115));
		pClients.setBounds(-1, 246, 229, 42);
		panel.add(pClients);
		
		JLabel lblIssuedBooks = new JLabel("Clients");
		lblIssuedBooks.setForeground(Color.WHITE);
		lblIssuedBooks.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblIssuedBooks.setBounds(83, 11, 136, 20);
		pClients.add(lblIssuedBooks);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(userIcon));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(26, 0, 36, 42);
		pClients.add(label_2);
		
		JPanel pStock = new JPanel();
		pStock.addMouseListener(new PanelButtonMouseAdapter(pStock){
			@Override
			public void mouseClicked(MouseEvent e){
				menuClicked(panelStock);
			}
		});
		pStock.setLayout(null);
		pStock.setBackground(new Color(55, 28, 115));
		pStock.setBounds(0, 160, 229, 42);
		panel.add(pStock);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setForeground(Color.WHITE);
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblStock.setBounds(79, 11, 105, 20);
		pStock.add(lblStock);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(stockIcon));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(28, 0, 36, 42);
		pStock.add(label_3);
		
		JPanel pTransferStock = new JPanel();
		pTransferStock.addMouseListener(new PanelButtonMouseAdapter(pTransferStock) {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menuClicked(stockTransferPanel);
			}
		});
		pTransferStock.setLayout(null);
		pTransferStock.setBackground(new Color(55, 28, 115));
		pTransferStock.setBounds(0, 288, 229, 42);
		panel.add(pTransferStock);
		
		JLabel lblTransferedStock = new JLabel("Transfered Stock");
		lblTransferedStock.setForeground(Color.WHITE);
		lblTransferedStock.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTransferedStock.setBounds(83, 11, 136, 20);
		pTransferStock.add(lblTransferedStock);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(stockTransfer));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(26, 0, 36, 42);
		pTransferStock.add(label_4);
		
		JPanel rPanel = new JPanel();
		rPanel.addMouseListener(new PanelButtonMouseAdapter(rPanel) {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menuClicked(reportPanel);
			}
		});
		rPanel.setLayout(null);
		rPanel.setBackground(new Color(55, 28, 115));
		rPanel.setBounds(0, 373, 229, 42);
		panel.add(rPanel);
		
		JLabel lblReports = new JLabel("Payment Report");
		lblReports.setForeground(Color.WHITE);
		lblReports.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblReports.setBounds(83, 11, 136, 20);
		rPanel.add(lblReports);
		
		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(reports));
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(26, 0, 36, 42);
		rPanel.add(label_5);
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new PanelButtonMouseAdapter(panel_1) {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menuClicked(lastReportPanel);
			}
		});
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(55, 28, 115));
		panel_1.setBounds(0, 417, 229, 42);
		panel.add(panel_1);
		
		JLabel lblReport = new JLabel("Report");
		lblReport.setForeground(Color.WHITE);
		lblReport.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblReport.setBounds(83, 11, 71, 20);
		panel_1.add(lblReport);
		
		JLabel label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon(lastReport));
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(26, 0, 36, 42);
		panel_1.add(label_6);
		
		JPanel pLoan = new JPanel(); 
		pLoan.addMouseListener(new PanelButtonMouseAdapter(pLoan) {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menuClicked(loanPanel);
			}
		});
		pLoan.setLayout(null);
		pLoan.setBackground(new Color(55, 28, 115));
		pLoan.setBounds(0, 329, 229, 42);
		panel.add(pLoan);
		
		JLabel lblLoan = new JLabel("Loan");
		lblLoan.setForeground(Color.WHITE);
		lblLoan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLoan.setBounds(83, 11, 79, 20);
		pLoan.add(lblLoan);
		
		JLabel label_7 = new JLabel("");
		label_7.setIcon(new ImageIcon(loan));
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(26, 0, 36, 42);
		pLoan.add(label_7);
		
		JPanel jPanel_Main = new JPanel();
		jPanel_Main.setBackground(Color.WHITE);
		jPanel_Main.setForeground(Color.WHITE);
		jPanel_Main.setBounds(228, 68, 1117, 605);
		contentPane.add(jPanel_Main);
		jPanel_Main.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(228, 0, 1117, 65);
		panel_2.setBackground(new Color(30, 144, 255));
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBackground(Color.WHITE);
		separator.setBounds(345, 52, 425, 2);
		panel_2.add(separator);

		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Bukidnon Truckers");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Segoe UI Black", Font.PLAIN, 28));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(345, 11, 425, 39);
		panel_2.add(lblNewLabel_3);
		
		jPanel_Main.add(panelProduct);
		jPanel_Main.add(panelStock);
		jPanel_Main.add(panelClient);
		jPanel_Main.add(panelPurchase);
		jPanel_Main.add(stockTransferPanel);
		jPanel_Main.add(reportPanel);
		jPanel_Main.add(lastReportPanel);
		
		jPanel_Main.add(loanPanel);
		
		
		menuClicked(panelProduct); // BY DEFAULT v
		
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
        Date d = new Date();
        System.out.println((dateFormat.format(d)));
	}
	
	
	public void menuClicked(JPanel panel){
		
		panelProduct.setVisible(false);
		panelStock.setVisible(false);
		panelClient.setVisible(false);
		panelPurchase.setVisible(false);
		stockTransferPanel.setVisible(false);
		reportPanel.setVisible(false);
		lastReportPanel.setVisible(false);
		loanPanel.setVisible(false);
		
		panel.setVisible(true);
	}
	
	//This is for Mouse Hover Effect
	private class PanelButtonMouseAdapter extends MouseAdapter{
		
		JPanel panel;
		public PanelButtonMouseAdapter(JPanel panel){
			this.panel = panel;
		}
		@Override
		public void mouseEntered(MouseEvent event){
			panel.setBackground(new Color(95, 48, 197));
		}
		@Override
		public void mouseExited(MouseEvent event){
			panel.setBackground(new Color(55, 28, 115));
		}
		@Override
		public void mousePressed(MouseEvent event){
			panel.setBackground(new Color(180, 156, 231));

		}
		@Override
		public void mouseReleased(MouseEvent event){
			panel.setBackground(new Color(95, 48, 197));

		}
	}
	
	public static void mouseClickedEffect(JButton button) {
		button.setForeground(new Color(93,173,226));
		button.setBorder(new LineBorder(new Color(93,173,226)));
	}
	public static void mouseReleasedEffect(JButton button) {
		button.setForeground(new Color( 232, 115, 134));
		button.setBorder(new LineBorder(new Color(232, 115, 134)));
	}
}