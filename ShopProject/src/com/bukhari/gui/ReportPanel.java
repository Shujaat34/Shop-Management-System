package com.bukhari.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.bukhari.dao.ReportDAO;
import com.bukhari.daoimpl.ReportDAOImpl;

public class ReportPanel extends JPanel {
	private JButton btnReport;
	private JTable table;
	private JComboBox cmboPaymentType; 
	private JTextArea txtArea; 
	
	private String firstName;
	private String lastName;
	private String client;
	private String contact;
	private String paymentType;
	private List<String> brandList = new ArrayList<>();
	private List<String> priceList = new ArrayList<>();
	private List<String> quantityList = new ArrayList<>();
	private String totalAmount ;

	private Integer id =0 ;
	public ReportPanel() {

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
//				btnAdd.setEnabled(true);
//				btnAdd.setBorder(new LineBorder(new Color(30, 144, 255)));

				btnReport.setEnabled(false);
				btnReport.setBorder(new LineBorder(new Color(55, 53, 61)));

//				btnUpdate.setEnabled(false);
//				btnUpdate.setBorder(new LineBorder(new Color(55, 53, 61)));

			}
		});
		setBounds(0, 0, 1117, 605);
		setBackground(new Color(252, 252, 252));
		setLayout(null);

		btnReport = new JButton("Generate Report");
		btnReport.setBounds(10, 323, 143, 28);
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				brandList.clear();
				priceList.clear();
				quantityList.clear();
				
			
				
				for (int i = 0; i < table.getRowCount(); i++) {
						if(table.getValueAt(i, 7).toString().equalsIgnoreCase(firstName) 
								&& table.getValueAt(i, 8).toString().equalsIgnoreCase(lastName)){
							brandList.add(table.getValueAt(i, 3).toString());
							priceList.add(table.getValueAt(i, 5).toString());
							quantityList.add(table.getValueAt(i, 6).toString());
						}
					
				}
				
				
				
				String brands = "";
				String prices = "";
				String quantities = "";
				
				float sum = 0f;
				
				for (int i = 0; i < brandList.size(); i++) {
					brands += brandList.get(i)+"  |  ";
					prices += priceList.get(i)+"  |  ";
					quantities += quantityList.get(i)+"  |  ";
					
					int qty = Integer.parseInt(quantityList.get(i));
					float price = Float.parseFloat(priceList.get(i));
					
				
					sum = sum + (qty * price);
					
				}
				
				txtArea.setText("Bukidnon Truckers"+
						"\n-------------------------------------------"+
						"\nClient                    ---     "+client+
						"\nContact                   ---    "+contact+
						"\nPayment Type              ---     "+paymentType+
						"\nBrand                     ---     "+brands+
						"\nPrice                     ---     "+prices+
						"\nQuantity                  ---     "+quantities+
						"\nTotal Amount              ---     $"+sum+
						
						"\n\nThank you For using our Service");

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

		JLabel lblBook = new JLabel("Payment Report");
		lblBook.setBounds(404, 28, 205, 39);
		lblBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblBook.setForeground(new Color(30, 144, 255)); // 40, 20, 82 Dark purple
		// Blue 30 ,144, 255
		lblBook.setFont(new Font("Segoe UI Black", Font.BOLD, 21));
		add(lblBook);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(598, 11, 72, 69);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(ReportPanel.class.getResource("/com/bukhari/imgs/pie_chart_report_64px.png")));
		add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 150, 1097, 153);
		add(scrollPane);

		table = new JTable();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {



				btnReport.setEnabled(true);
				btnReport.setBorder(new LineBorder(new Color(30, 144, 255)));


				id = (Integer) table.getValueAt(table.getSelectedRow(), 0);

			
				firstName = table.getValueAt(table.getSelectedRow(), 7).toString();
				lastName=table.getValueAt(table.getSelectedRow(), 8).toString();
				
				client = firstName+" "+lastName;
				
				contact =" "+table.getValueAt(table.getSelectedRow(), 10).toString();
				paymentType = table.getValueAt(table.getSelectedRow(), 1).toString();
				
				
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
		
		JLabel lblPayment = new JLabel("Payment");
		lblPayment.setForeground(new Color(30, 144, 255));
		lblPayment.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblPayment.setBounds(58, 107, 111, 20);
		add(lblPayment);
		
		cmboPaymentType = new JComboBox();
		cmboPaymentType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String payment_type = cmboPaymentType.getSelectedItem().toString();
				populateTable(payment_type);
				
			}
		});
		cmboPaymentType.setModel(new DefaultComboBoxModel(new String[] {"Cash", "Cheque", "Loan"}));
		cmboPaymentType.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmboPaymentType.setBounds(192, 107, 135, 20);
		add(cmboPaymentType);
		
		txtArea = new JTextArea();
		txtArea.setFont(new Font("Monospaced", Font.BOLD, 15));
		txtArea.setBounds(175, 314, 751, 276);
		add(txtArea);
		
		txtArea.setEditable(false);
		
		btnReport.setEnabled(false);
		btnReport.setBorder(new LineBorder(new Color(55, 53, 61)));

		populateTable(cmboPaymentType.getSelectedItem().toString());
	}

	private void populateTable(String payment_type) {
		ReportDAO rdao = new ReportDAOImpl();
		ResultSet rs = rdao.getDataResultSet(payment_type);
		table.setModel(buildTableModel(rs));

		table.getColumnModel().getColumn(1).setHeaderValue("Pay Type");
		table.getColumnModel().getColumn(2).setHeaderValue("Pay #");
		table.getColumnModel().getColumn(3).setHeaderValue("Brand");
		table.getColumnModel().getColumn(4).setHeaderValue("Category");
		table.getColumnModel().getColumn(5).setHeaderValue("Price");
		table.getColumnModel().getColumn(6).setHeaderValue("Quantity");
		table.getColumnModel().getColumn(7).setHeaderValue("First Name");
		table.getColumnModel().getColumn(8).setHeaderValue("Last Name");
		table.getColumnModel().getColumn(9).setHeaderValue("Address");
		table.getColumnModel().getColumn(10).setHeaderValue("Contact");
		table.getColumnModel().getColumn(11).setHeaderValue("Bank");

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
