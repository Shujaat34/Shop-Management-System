package com.bukhari.gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.bukhari.dao.ClientDAO;
import com.bukhari.dao.LoanDAO;
import com.bukhari.dao.ProductDAO;
import com.bukhari.daoimpl.ClientDAOImpl;
import com.bukhari.daoimpl.LoanDAOImpl;
import com.bukhari.daoimpl.ProductDAOImpl;
import com.bukhari.util.Validation;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.demo.DateChooserPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class LoanPanel extends JPanel {
	private JTable table;
	private JButton btnPayLoan;
	private Integer id=0;
	private JTextField txtPayment;
	private JLabel lblPayStatus;
	private JComboBox cmboPayMethod;
	private JDateChooser dateChooser;
	private JTextField txtSearch;
	private JTable tableTransactions;
	
	public LoanPanel() {
		

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
				btnPayLoan.setEnabled(false);
				btnPayLoan.setBorder(new LineBorder(new Color(55, 53, 61)));
			}
		});
		setBounds(0, 0, 1117, 605);
		setBackground(new Color(252, 252, 252));
		setLayout(null);
		
		JButton btnView = new JButton("View Data");
		btnView.setBounds(552, 150, 153, 28);
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if(txtcustomerId.getText().toString().isEmpty()){
//					JOptionPane.showMessageDialog(LoanPanel.this, "Fill the Field", "Message",
//							JOptionPane.DEFAULT_OPTION);
//				}else{
//					
//					String custoemr_id = txtcustomerId.getText().toString();
//					populateTable(Integer.parseInt(custoemr_id));
//					
//					if(table.getRowCount() == 0 ){
//						btnPayLoan.setEnabled(false);
//						btnPayLoan.setBorder(new LineBorder(new Color(55, 53, 61)));
//					}
////					else{
////						btnPayLoan.setEnabled(true);
////						btnPayLoan.setBorder(new LineBorder(new Color(30, 144, 255)));
////					}
//							
//				}
				populateTable();
				if(table.getRowCount()==0){
					JOptionPane.showMessageDialog(LoanPanel.this, "No Data to View", "Message",
							JOptionPane.DEFAULT_OPTION);
				}
				
			}
		});
		btnView.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnView.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (btnView.isEnabled()) {
					mouseClickedEffect(btnView);
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnView.isEnabled()) {
					mouseReleasedEffect(btnView);
				}

			}
		});
		
				btnView.setForeground(new Color(30, 144, 255));
				// For Transpareting the Button
				btnView.setOpaque(false);
				btnView.setContentAreaFilled(false);
				btnView.setBorder(new LineBorder(new Color(30, 144, 255)));
				add(btnView);

		JLabel lblBook = new JLabel("Loan");
		lblBook.setBounds(391, 28, 96, 39);
		lblBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblBook.setForeground(new Color(30, 144, 255)); // 40, 20, 82 Dark purple
		// Blue 30 ,144, 255
		lblBook.setFont(new Font("Segoe UI Black", Font.BOLD, 21));
		add(lblBook);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(476, 11, 72, 69);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(LoanPanel.class.getResource("/com/bukhari/imgs/debt_blue_64px.png")));
		add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 193, 695, 152);
		add(scrollPane);

		table = new JTable();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				btnPayLoan.setEnabled(true);
				btnPayLoan.setBorder(new LineBorder(new Color(30, 144, 255)));
				
				Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				
				
				populateTransactionTable(id);
				
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
		
		
		btnPayLoan = new JButton("Pay Loan");
		btnPayLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				LoanDAO dao = new LoanDAOImpl();
				id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				
				Validation validate = new Validation();
				if(id !=0){
					
					if(cmboPayMethod.getSelectedItem().toString().equalsIgnoreCase("cash")){
						
						if(txtPayment.getText().isEmpty() || dateChooser.getDate() == null){
							JOptionPane.showMessageDialog(LoanPanel.this, "Fill the fields", "Message",
									JOptionPane.DEFAULT_OPTION);
						}else{
							
							Date date = dateChooser.getDate();
							Calendar cal = Calendar.getInstance();
							cal.setTime(date);
							Timestamp date_paid = new Timestamp(cal.getTimeInMillis());

							
							String amount = txtPayment.getText().toString();
							
							boolean isNumber = validate.isNumber(amount); 
							boolean isFloat = validate.isFloat(amount);
							
							if (isNumber == false && isFloat == false) {
								JOptionPane.showMessageDialog(LoanPanel.this, "Amount must be a number",
										"Message", JOptionPane.DEFAULT_OPTION);
							}else{
								Double input_amount = Double.parseDouble(amount);
								
								Double loan_value = (Double) table.getValueAt(table.getSelectedRow(), 7);
								
								Double rem_amount = loan_value - input_amount ;
								
								String payment_type = cmboPayMethod.getSelectedItem().toString();
								
								
								if(rem_amount <= 0.0 ){
									
									  dao.addIntoLoanPaid(payment_type, input_amount.toString(), " ", date_paid, id);
										
									
									int row = dao.deleteLoan(id);
										
									if (row == 1) {
										JOptionPane.showMessageDialog(LoanPanel.this, "Loan Paid Successfully", "Message",
												JOptionPane.DEFAULT_OPTION);
									} else {
										JOptionPane.showMessageDialog(LoanPanel.this, "Loan cant be Paid", "Message",
												JOptionPane.ERROR_MESSAGE);
									}
									
								}else{
									
									int row = dao.updateLoanAmount(rem_amount.toString(), id);
									if (row == 1) {
										JOptionPane.showMessageDialog(LoanPanel.this, "Loan Partially Paid & table Updated Successfully", "Message",
												JOptionPane.DEFAULT_OPTION);
										dao.addIntoLoanPaid(payment_type, input_amount.toString(), " ", date_paid, id);
											
									} else {
										JOptionPane.showMessageDialog(LoanPanel.this, "Loan cant be Paid", "Message",
												JOptionPane.ERROR_MESSAGE);
									}
								}
								
								populateTable();
							}
						}
					}else{
						
						if(txtPayment.getText().isEmpty() || dateChooser.getDate() == null){
							JOptionPane.showMessageDialog(LoanPanel.this, "Fill the fields", "Message",
									JOptionPane.DEFAULT_OPTION);
						}else{
							
							Date date = dateChooser.getDate();
							Calendar cal = Calendar.getInstance();
							cal.setTime(date);
							Timestamp date_paid = new Timestamp(cal.getTimeInMillis());
							
							String cheqNum = txtPayment.getText().toString();
							dao.addIntoLoanPaid("Cheque", " ", cheqNum, date_paid, id);
							
							int row = dao.deleteLoan(id);
							
							if (row == 1) {
								JOptionPane.showMessageDialog(LoanPanel.this, "Loan Paid Successfully", "Message",
										JOptionPane.DEFAULT_OPTION);
							} else {
								JOptionPane.showMessageDialog(LoanPanel.this, "Loan cant be Paid", "Message",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					
					
//					int row = dao.deleteLoan(id);
//					if (row == 1) {
//						JOptionPane.showMessageDialog(LoanPanel.this, "Loan Paid Successfully", "Message",
//								JOptionPane.DEFAULT_OPTION);
//					} else {
//						JOptionPane.showMessageDialog(LoanPanel.this, "Loan cant be Paid", "Message",
//								JOptionPane.ERROR_MESSAGE);
//					}
				}else{
					JOptionPane.showMessageDialog(LoanPanel.this, "Please Select a record", "Message",
							JOptionPane.ERROR_MESSAGE);
				}
			
//

//				if(!txtcustomerId.getText().isEmpty()){
//					populateTable(Integer.parseInt(txtcustomerId.getText().toString()));
//				}
				
				
			}
		});
		
		btnPayLoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (btnPayLoan.isEnabled()) {
					mouseClickedEffect(btnPayLoan);
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnPayLoan.isEnabled()) {
					mouseReleasedEffect(btnPayLoan);
				}

			}
		});
		
		btnPayLoan.setOpaque(false);
		btnPayLoan.setForeground(new Color(30, 144, 255));
		btnPayLoan.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnPayLoan.setContentAreaFilled(false);
		btnPayLoan.setBorder(new LineBorder(new Color(30, 144, 255)));
		btnPayLoan.setBounds(915, 425, 175, 28);
		add(btnPayLoan);
		
		
			btnPayLoan.setEnabled(false);
			btnPayLoan.setBorder(new LineBorder(new Color(55, 53, 61)));
			
			JLabel lblPaymentMethod = new JLabel("Payment Method");
			lblPaymentMethod.setForeground(new Color(30, 144, 255));
			lblPaymentMethod.setFont(new Font("Segoe UI", Font.BOLD, 16));
			lblPaymentMethod.setBounds(737, 192, 153, 20);
			add(lblPaymentMethod);
			
			cmboPayMethod = new JComboBox();
			cmboPayMethod.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String status = cmboPayMethod.getSelectedItem().toString();
					if(status.equalsIgnoreCase("cash")){
						lblPayStatus.setText("Amount");
					}
					else{
						lblPayStatus.setText(status+" #");
					}
				}
			});
			cmboPayMethod.setFont(new Font("Tahoma", Font.BOLD, 13));
			cmboPayMethod.setModel(new DefaultComboBoxModel(new String[] {"Cash", "Cheque"}));
			cmboPayMethod.setBounds(915, 195, 175, 20);
			add(cmboPayMethod);
			
			lblPayStatus = new JLabel("Payment Method");
			lblPayStatus.setForeground(new Color(30, 144, 255));
			lblPayStatus.setFont(new Font("Segoe UI", Font.BOLD, 16));
			lblPayStatus.setBounds(737, 253, 144, 20);
			add(lblPayStatus);
			
			txtPayment = new JTextField();
			txtPayment.setOpaque(false);
			txtPayment.setForeground(Color.BLACK);
			txtPayment.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtPayment.setBorder(null);
			txtPayment.setBounds(915, 242, 175, 20);
			add(txtPayment);
			
			JSeparator separator = new JSeparator();
			separator.setBackground(new Color(30, 144, 255));
			separator.setBounds(915, 269, 175, 4);
			add(separator);
//		}else{
//			btnPayLoan.setEnabled(true);
//			btnPayLoan.setBorder(new LineBorder(new Color(30, 144, 255)));
//		}
			

			lblPayStatus.setText("Amount");
			
			dateChooser = new JDateChooser();
			dateChooser.setBounds(915, 328, 175, 20);
			add(dateChooser);
			
			JLabel lblDate = new JLabel("Date");
			lblDate.setForeground(new Color(30, 144, 255));
			lblDate.setFont(new Font("Segoe UI", Font.BOLD, 16));
			lblDate.setBounds(737, 328, 153, 20);
			add(lblDate);
			
			JSeparator separator_10 = new JSeparator();
			separator_10.setBackground(new Color(30, 144, 255));
			separator_10.setBounds(92, 174, 175, 4);
			add(separator_10);
			
			JLabel lblSearch = new JLabel("Search");
			lblSearch.setForeground(new Color(30, 144, 255));
			lblSearch.setFont(new Font("Segoe UI", Font.BOLD, 16));
			lblSearch.setBounds(10, 147, 72, 20);
			add(lblSearch);
			
			txtSearch = new JTextField();
			txtSearch.setOpaque(false);
			txtSearch = new JTextField();
			txtSearch.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					DefaultTableModel myTable = (DefaultTableModel) table.getModel();
					String search = txtSearch.getText().toString();
					TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(myTable);
					table.setRowSorter(tr);
					tr.setRowFilter(RowFilter.regexFilter("(?i)" + search));
				}
			});
			txtSearch.setForeground(Color.BLACK);
			txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtSearch.setBorder(null);
			txtSearch.setBounds(92, 147, 175, 20);
			add(txtSearch);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(10, 401, 695, 164);
			add(scrollPane_1);
			
			tableTransactions = new JTable();
			tableTransactions.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
//					btnPayLoan.setEnabled(true);
//					btnPayLoan.setBorder(new LineBorder(new Color(30, 144, 255)));
					//btn click Functionality
					
					tableTransactions.setSelectionForeground(Color.WHITE);
				}
			});
			tableTransactions.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
			tableTransactions.getTableHeader().setForeground(new Color(30, 144, 255));
			tableTransactions.setRowHeight(20);
			tableTransactions.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tableTransactions.setShowVerticalLines(false);
			tableTransactions.setSelectionBackground(new Color(30, 144, 255));

			tableTransactions.setAutoCreateRowSorter(true);
			scrollPane_1.setViewportView(tableTransactions);
			
			JLabel lblTransactionsReport = new JLabel("Transaction Report");
			lblTransactionsReport.setForeground(new Color(30, 144, 255));
			lblTransactionsReport.setFont(new Font("Segoe UI", Font.BOLD, 16));
			lblTransactionsReport.setBounds(10, 366, 153, 20);
			add(lblTransactionsReport);
			
			
			
	}

	private void populateTable() {
		LoanDAO cdao = new LoanDAOImpl();
		ResultSet rs = cdao.getDataResultSet();
		table.setModel(buildTableModel(rs));

		
		table.getColumnModel().getColumn(1).setHeaderValue("Custoemr id");
		table.getColumnModel().getColumn(2).setHeaderValue("Cust Name");
		table.getColumnModel().getColumn(3).setHeaderValue("PO#");
		table.getColumnModel().getColumn(4).setHeaderValue("Date Approved");
		table.getColumnModel().getColumn(5).setHeaderValue("Payment Type");
		
		table.getColumnModel().getColumn(6).setHeaderValue("Duration");
		table.getColumnModel().getColumn(7).setHeaderValue("Amount");
		
	}
	
	private void populateTransactionTable(int payment_id) {
		LoanDAO cdao = new LoanDAOImpl();
		ResultSet rs = cdao.getDataResultSetTransaction(payment_id);
		tableTransactions.setModel(buildTableModel(rs));

		
		tableTransactions.getColumnModel().getColumn(1).setHeaderValue("Customer Name id");
		tableTransactions.getColumnModel().getColumn(2).setHeaderValue("Payment Type");
		tableTransactions.getColumnModel().getColumn(3).setHeaderValue("Paid Amount");
		tableTransactions.getColumnModel().getColumn(4).setHeaderValue("Date Paid");
		tableTransactions.getColumnModel().getColumn(5).setHeaderValue("Balance");
		
		
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
