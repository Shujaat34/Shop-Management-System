package com.bukhari.gui;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;


import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.bukhari.connection.DBConnection;
import com.bukhari.dao.ReportDAO;
import com.bukhari.daoimpl.ReportDAOImpl;
import com.toedter.calendar.JDateChooser;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


public class LastReportPanel extends JPanel { 
	private JButton btnReport;
	private JTable table;
	
	private String firstName;
	private String lastName;
	private String client;
	private String contact;
	private String paymentType;
	private List<String> brandList = new ArrayList<>();
	private List<String> priceList = new ArrayList<>();
	private List<String> quantityList = new ArrayList<>();
	private String totalAmount;
	private JDateChooser dateChooser;

	private Integer id =0 ;
	public LastReportPanel() {

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {


	


			}
		});
		setBounds(0, 0, 1117, 605);
		setBackground(new Color(252, 252, 252));
		setLayout(null);

		btnReport = new JButton("Generate Report");
		btnReport.setBounds(10, 103, 143, 28);
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				populateTable();

//				JOptionPane.showMessageDialog(ReportPanel.this, "Fields Cleared", "Message",
//						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnReport.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (btnReport.isEnabled()) {
					mouseClickedEffect(btnReport);
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnReport.isEnabled()) {
					mouseReleasedEffect(btnReport);
				}

			}
		});

		btnReport.setForeground(new Color(30, 144, 255));
		// For Transpareting the Button
		btnReport.setOpaque(false);
		btnReport.setContentAreaFilled(false);
		btnReport.setBorder(new LineBorder(new Color(30, 144, 255)));
		add(btnReport);

		JLabel lblBook = new JLabel("Report");
		lblBook.setBounds(391, 28, 96, 39);
		lblBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblBook.setForeground(new Color(30, 144, 255)); // 40, 20, 82 Dark purple
		// Blue 30 ,144, 255
		lblBook.setFont(new Font("Segoe UI Black", Font.BOLD, 21));
		add(lblBook);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(476, 11, 72, 69);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(LastReportPanel.class.getResource("/com/bukhari/imgs/pie_chart_report_64px.png")));
		add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 150, 1097, 339);
		add(scrollPane);

		table = new JTable();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				table.setSelectionForeground(Color.WHITE);
			}
		});
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		table.getTableHeader().setForeground(new Color(30, 144, 255));
		table.setRowHeight(20);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setShowVerticalLines(false);
		table.setSelectionBackground(new Color(30, 144, 255));

		table.setAutoCreateRowSorter(true);

		scrollPane.setViewportView(table);
		
		JLabel lblPayment = new JLabel("Current Date");
		lblPayment.setForeground(new Color(30, 144, 255));
		lblPayment.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblPayment.setBounds(854, 111, 119, 20);
		add(lblPayment);
		

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		JLabel label = new JLabel(""+formatter.format(date));
		label.setForeground(new Color(30, 144, 255));
		label.setFont(new Font("Segoe UI", Font.BOLD, 16));
		label.setBounds(996, 111, 111, 20);
		add(label);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setForeground(new Color(30, 144, 255));
		lblDate.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblDate.setBounds(279, 111, 85, 20);
		add(lblDate);
		
		JButton btnFilterOnwards = new JButton("Filter onwards");
		btnFilterOnwards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(dateChooser.getDate() == null){
					JOptionPane.showMessageDialog(LastReportPanel.this, "Please Chosse a Date", "Message",
							JOptionPane.DEFAULT_OPTION);
				}else{
					ReportDAO dao = new ReportDAOImpl();
					Date date = dateChooser.getDate();
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					Timestamp time = new Timestamp(cal.getTimeInMillis());
					
					
					
					populateTableByDate( time);
				}
				
			}
		});
		btnFilterOnwards.setOpaque(false);
		btnFilterOnwards.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (btnFilterOnwards.isEnabled()) {
					mouseClickedEffect(btnFilterOnwards);
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnFilterOnwards.isEnabled()) {
					mouseReleasedEffect(btnFilterOnwards);
				}

			}
		});
		btnFilterOnwards.setForeground(new Color(30, 144, 255));
		btnFilterOnwards.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnFilterOnwards.setContentAreaFilled(false);
		btnFilterOnwards.setBorder(new LineBorder(new Color(30, 144, 255)));
		btnFilterOnwards.setBounds(578, 107, 143, 28);
		add(btnFilterOnwards);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(374, 111, 175, 20);
		add(dateChooser);
		
		JButton btnPrintReport = new JButton("Print Report");
		btnPrintReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
//				try {
//					
//					String reportPath = "C:\\Users\\shuja\\eclipse-workspace\\ShopProject\\Report.jrxml";		  
//				
//					
//				
//					
//					Connection con = DBConnection.getConnection();
//			       
//			        HashMap parameters = new HashMap<>();
////			        parameters.put("StudentName", cmboStudent.getSelectedItem().toString());
////			        parameters.put("Percentage",lblPercentage.getText().toString());
////			        parameters.put("Eligibility", lblEligibility.getText().toString());
////			       
//			        JasperReport jr = JasperCompileManager.compileReport(reportPath);
//			        JasperPrint jp = JasperFillManager.fillReport(jr,   null, con);
//			        JasperViewer.viewReport(jp);
//			        
//			      
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
				
				new ReportThread();
			}
		});
		btnPrintReport.setOpaque(false);
		btnPrintReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (btnPrintReport.isEnabled()) {
					mouseClickedEffect(btnPrintReport);
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnPrintReport.isEnabled()) {
					mouseReleasedEffect(btnPrintReport);
				}

			}
		});
		btnPrintReport.setOpaque(false);
		btnPrintReport.setForeground(new Color(30, 144, 255));
		btnPrintReport.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnPrintReport.setContentAreaFilled(false);
		btnPrintReport.setBorder(new LineBorder(new Color(30, 144, 255)));
		btnPrintReport.setBounds(964, 518, 143, 28);
		add(btnPrintReport);
	}

	private void populateTable() {
		ReportDAO rdao = new ReportDAOImpl();
		ResultSet rs = rdao.getDataResultSetForLastReport();
		table.setModel(buildTableModel(rs));

		table.getColumnModel().getColumn(0).setHeaderValue("DATE");
		table.getColumnModel().getColumn(1).setHeaderValue("INVOICE #");
		table.getColumnModel().getColumn(2).setHeaderValue("PO#");
		table.getColumnModel().getColumn(3).setHeaderValue("DESCRIPTION");
		table.getColumnModel().getColumn(4).setHeaderValue("CHARGES");
		table.getColumnModel().getColumn(5).setHeaderValue("PAYMENT");
	

	}
	
	private void populateTableByDate(Timestamp time) {
		ReportDAO rdao = new ReportDAOImpl();
		ResultSet rs = rdao.getDataResultsetByDate(time);
		table.setModel(buildTableModel(rs));

		table.getColumnModel().getColumn(0).setHeaderValue("DATE");
		table.getColumnModel().getColumn(1).setHeaderValue("INVOICE #");
		table.getColumnModel().getColumn(2).setHeaderValue("PO#");
		table.getColumnModel().getColumn(3).setHeaderValue("DESCRIPTION");
		table.getColumnModel().getColumn(4).setHeaderValue("CHARGES");
		table.getColumnModel().getColumn(5).setHeaderValue("PAYMENT");
	

	}
	

	// For Data Population
	public static DefaultTableModel buildTableModel(ResultSet rs) {
		Vector<String> colNames = null;
		Vector<Vector<Object>> data = null;
		try {
			ResultSetMetaData metadata = rs.getMetaData();
			colNames = new Vector<String>();
			int colcount = metadata.getColumnCount();

			for (int col = 1; col <= colcount; col++) {
				colNames.add(metadata.getColumnName(col));
			}
			data = new Vector<Vector<Object>>();

			while (rs.next()) {
				Vector<Object> vector = new Vector();
				for (int colindex = 1; colindex <= colcount; colindex++) {
					vector.add(rs.getObject(colindex));
				}
				data.add(vector);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new DefaultTableModel(data, colNames);
	}

	public void mouseClickedEffect(JButton button) {
		button.setForeground(new Color(200, 100, 100));
		button.setBorder(new LineBorder(new Color(200, 100, 100)));
	}

	public void mouseReleasedEffect(JButton button) {
		button.setForeground(new Color(30, 144, 255));
		button.setBorder(new LineBorder(new Color(30, 144, 255)));
	}
}

class ReportThread implements Runnable {
	Thread t;
	public ReportThread() {
		t = new Thread(this, "Thread1");
		t.start();
	}

	public void run() {
		try {
			reportRun();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void reportRun() {
		try {
			
			String reportPath = "Report.jrxml";		  
		
			
		
			
			Connection con = DBConnection.getConnection();
	       
	        HashMap parameters = new HashMap<>();
//	        parameters.put("StudentName", cmboStudent.getSelectedItem().toString());
//	        parameters.put("Percentage",lblPercentage.getText().toString());
//	        parameters.put("Eligibility", lblEligibility.getText().toString());
//	       
	        JasperReport jr = JasperCompileManager.compileReport(reportPath);
	        JasperPrint jp = JasperFillManager.fillReport(jr,   null, con);
	        JasperViewer.viewReport(jp,false);
	        
	      
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
