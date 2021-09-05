package com.bukhari.gui;

import java.awt.Color;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.bukhari.dao.ProductDAO;
import com.bukhari.dao.StockDAO;
import com.bukhari.daoimpl.ProductDAOImpl;
import com.bukhari.daoimpl.StockDAOImpl;
import com.bukhari.util.Validation;

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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class StockPanel extends JPanel {

	private JTextField txtTransaction;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnClear;
	private JTable table;
	private JTextField txtPrice;
	private JTextField txtStoreQuantity;
	private JTextField txtWHouseQuantity;
	private JTextField txtInvoiceNum;
	private JDateChooser dateChooser;
	private JComboBox cmboProductCode;
	private JLabel lblCategory;
	private JLabel lblBrand;
	
	
	
	private int id;
	

	public StockPanel() {

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnAdd.setEnabled(true);
				btnAdd.setBorder(new LineBorder(new Color(30, 144, 255)));

				btnDelete.setEnabled(false);
				btnDelete.setBorder(new LineBorder(new Color(55, 53, 61)));

				btnUpdate.setEnabled(false);
				btnUpdate.setBorder(new LineBorder(new Color(55, 53, 61)));
				

			}
		});
		setBounds(0, 0, 1117, 605);
		setBackground(new Color(252, 252, 252));
		setLayout(null);

		btnClear = new JButton("Clear Fields");
		btnClear.setBounds(1001, 554, 106, 28);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// txtProductNum.setText("");
				// txtBrand.setText("");
				txtTransaction.setText("");
				txtPrice.setText("");
				txtStoreQuantity.setText("");
				txtWHouseQuantity.setText("");
				txtInvoiceNum.setText("");

				JOptionPane.showMessageDialog(StockPanel.this, "Fields Cleared", "Message",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnClear.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (btnClear.isEnabled()) {
					mouseClickedEffect(btnClear);
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnClear.isEnabled()) {
					mouseReleasedEffect(btnClear);
				}

			}
		});

		btnClear.setForeground(new Color(30, 144, 255));
		// For Transpareting the Button
		btnClear.setOpaque(false);
		btnClear.setContentAreaFilled(false);
		btnClear.setBorder(new LineBorder(new Color(30, 144, 255)));
		add(btnClear);

		JLabel lblProductNo = new JLabel("Product Code");
		lblProductNo.setBounds(772, 167, 111, 20);
		lblProductNo.setForeground(new Color(30, 144, 255));
		lblProductNo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		add(lblProductNo);

		JLabel lblBook = new JLabel("Stock");
		lblBook.setBounds(290, 19, 96, 39);
		lblBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblBook.setForeground(new Color(30, 144, 255)); // 40, 20, 82 Dark purple
		// Blue 30 ,144, 255
		lblBook.setFont(new Font("Segoe UI Black", Font.BOLD, 21));
		add(lblBook);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(372, -2, 72, 80);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(StockPanel.class.getResource("/com/bukhari/imgs/new_stock_64px.png")));
		add(lblNewLabel);

		JLabel lblArrivalDate = new JLabel("Arrival Date");
		lblArrivalDate.setBounds(772, 226, 111, 20);
		lblArrivalDate.setForeground(new Color(30, 144, 255));
		lblArrivalDate.setFont(new Font("Segoe UI", Font.BOLD, 16));
		add(lblArrivalDate);

		JLabel lblTransaction = new JLabel("Transaction");
		lblTransaction.setBounds(772, 278, 111, 20);
		lblTransaction.setForeground(new Color(30, 144, 255));
		lblTransaction.setFont(new Font("Segoe UI", Font.BOLD, 16));
		add(lblTransaction);

		txtTransaction = new JTextField();
		txtTransaction.setBounds(932, 278, 175, 20);
		txtTransaction.setOpaque(false);
		txtTransaction.setForeground(Color.BLACK);
		txtTransaction.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTransaction.setBorder(null);
		add(txtTransaction);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(932, 305, 175, 4);
		separator_2.setBackground(new Color(30, 144, 255));
		add(separator_2);

		btnAdd = new JButton("Add");
		btnAdd.setBounds(10, 199, 72, 28);

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (btnAdd.isEnabled()) {
					if (cmboProductCode.getSelectedItem().toString().equals("Select")) {
						JOptionPane.showMessageDialog(StockPanel.this, "Please Select a Product Code", "Message",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					if (dateChooser.getDate() == null || txtTransaction.getText().isEmpty()
							|| txtPrice.getText().isEmpty() || txtStoreQuantity.getText().isEmpty()
							|| txtWHouseQuantity.getText().isEmpty() || txtInvoiceNum.getText().isEmpty()) {
						JOptionPane.showMessageDialog(StockPanel.this, "Please Fill the Fields", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					} else {

						String productCode = cmboProductCode.getSelectedItem().toString();

						Date date = dateChooser.getDate();
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						Timestamp اarr_date = new Timestamp(cal.getTimeInMillis());

						String transaction = txtTransaction.getText().toString();
						String price = txtPrice.getText().toString();
						String sQty = txtStoreQuantity.getText().toString();
						String wQty = txtWHouseQuantity.getText().toString();
						String invoiceNum = txtInvoiceNum.getText().toString();

						Validation validate = new Validation();

						boolean isTransactionNum = validate.isNumber(transaction);

						boolean isSQty = validate.isNumber(sQty);
						boolean isWQty = validate.isNumber(wQty);
						boolean isPrice = validate.isNumber(price);
						boolean isSalesInvoiceNum = validate.isNumber(invoiceNum);

						if (isTransactionNum == false) {
							JOptionPane.showMessageDialog(StockPanel.this, "Transaction must be a number", "Message",
									JOptionPane.DEFAULT_OPTION);
						} else if (isSQty == false) {
							JOptionPane.showMessageDialog(StockPanel.this, "Store Quantity must be a number", "Message",
									JOptionPane.DEFAULT_OPTION);
						} else if (isWQty == false) {
							JOptionPane.showMessageDialog(StockPanel.this, "Warehouse Quantity must be a number",
									"Message", JOptionPane.DEFAULT_OPTION);
						} else if (isPrice == false) {
							JOptionPane.showMessageDialog(StockPanel.this, "Price must be a number", "Message",
									JOptionPane.DEFAULT_OPTION);
						} else if (isSalesInvoiceNum == false) {
							JOptionPane.showMessageDialog(StockPanel.this, "Invoice must be a number", "Message",
									JOptionPane.DEFAULT_OPTION);
						} else {

							StockDAO dao = new StockDAOImpl();

							int row = dao.addStock(productCode, اarr_date, transaction, price, sQty, wQty, invoiceNum);
							if (row == 1) {
								JOptionPane.showMessageDialog(StockPanel.this, "Stock Added Successfully", "Message",
										JOptionPane.DEFAULT_OPTION);

							} else {
								JOptionPane.showMessageDialog(StockPanel.this, "Stock Cant be added", "Message",
										JOptionPane.ERROR_MESSAGE);
							}
							populateTable();

						}
					}

				}
			}
		});

		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (btnAdd.isEnabled()) {
					mouseClickedEffect(btnAdd);
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnAdd.isEnabled()) {
					mouseReleasedEffect(btnAdd);
				}

			}
		});

		btnAdd.setForeground(new Color(30, 144, 255));
		// For Transpareting the Button
		btnAdd.setOpaque(false);
		btnAdd.setContentAreaFilled(false);
		btnAdd.setBorder(new LineBorder(new Color(30, 144, 255)));
		add(btnAdd);

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(116, 199, 80, 28);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnUpdate.isEnabled()) {

					if (cmboProductCode.getSelectedItem().toString().equals("Select")) {
						JOptionPane.showMessageDialog(StockPanel.this, "Please Select a Product Code", "Message",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					if (dateChooser.getDate() == null || txtTransaction.getText().isEmpty()
							|| txtPrice.getText().isEmpty() || txtStoreQuantity.getText().isEmpty()
							|| txtWHouseQuantity.getText().isEmpty() || txtInvoiceNum.getText().isEmpty()) {
						JOptionPane.showMessageDialog(StockPanel.this, "Please Fill the Fields", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					} else {

//						id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
						if(id != 0){
							String productCode = cmboProductCode.getSelectedItem().toString();
	
							Date date = dateChooser.getDate();
							Calendar cal = Calendar.getInstance();
							cal.setTime(date);
							Timestamp arr_date = new Timestamp(cal.getTimeInMillis());
	
							String transaction = txtTransaction.getText().toString();
							String price = txtPrice.getText().toString();
							String sQty = txtStoreQuantity.getText().toString();
							String wQty = txtWHouseQuantity.getText().toString();
							String invoiceNum = txtInvoiceNum.getText().toString();
	
							StockDAO dao = new StockDAOImpl();
	
							int row = dao.updateStock(productCode, arr_date, transaction, price, sQty, wQty, invoiceNum, id);
							if (row == 1) {
								JOptionPane.showMessageDialog(StockPanel.this, "Stock Updated Successfully", "Message",
										JOptionPane.DEFAULT_OPTION);
							} else {
								JOptionPane.showMessageDialog(StockPanel.this, "Stock Not Updated", "Message",
										JOptionPane.ERROR_MESSAGE);
							}
							populateTable();
						}else{
							JOptionPane.showMessageDialog(StockPanel.this, "Please Select a Row", "Message",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		btnUpdate.setOpaque(false);
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (btnUpdate.isEnabled()) {
					mouseClickedEffect(btnUpdate);
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnUpdate.isEnabled()) {
					mouseReleasedEffect(btnUpdate);
				}

			}
		});
		btnUpdate.setForeground(new Color(30, 144, 255));
		btnUpdate.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBorder(new LineBorder(new Color(30, 144, 255)));
		add(btnUpdate);

		btnDelete = new JButton("Delete");
		btnDelete.setBounds(226, 199, 80, 28);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				StockDAO dao = new StockDAOImpl();
//				Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				if(id != 0){
					int row = dao.deleteStock(id);
					if (row == 1) {
						JOptionPane.showMessageDialog(StockPanel.this, "Stock Deleted Successful", "Message",
								JOptionPane.DEFAULT_OPTION);
					} else {
						JOptionPane.showMessageDialog(StockPanel.this, "Stock Not Deleted", "Message",
								JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(StockPanel.this, "Please Select a Row", "Message",
							JOptionPane.ERROR_MESSAGE);
				}

				populateTable();

			}
		});
		btnDelete.setOpaque(false);
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (btnDelete.isEnabled()) {
					mouseClickedEffect(btnDelete);
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnDelete.isEnabled()) {
					mouseReleasedEffect(btnDelete);
				}

			}
		});
		btnDelete.setForeground(new Color(30, 144, 255));
		btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnDelete.setContentAreaFilled(false);
		btnDelete.setBorder(new LineBorder(new Color(30, 144, 255)));
		add(btnDelete);


		JButton btnLoadData = new JButton("Load Data");
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				populateTable();
			}
		});
		btnLoadData.setOpaque(false);
		btnLoadData.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (btnLoadData.isEnabled()) {
					mouseClickedEffect(btnLoadData);
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnLoadData.isEnabled()) {
					mouseReleasedEffect(btnLoadData);
				}

			}
		});	
		btnLoadData.setForeground(new Color(30, 144, 255));
		btnLoadData.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnLoadData.setContentAreaFilled(false);
		btnLoadData.setBorder(new LineBorder(new Color(30, 144, 255)));
		btnLoadData.setBounds(338, 199, 106, 28);
		add(btnLoadData);
		

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(772, 333, 111, 20);
		lblPrice.setForeground(new Color(30, 144, 255));
		lblPrice.setFont(new Font("Segoe UI", Font.BOLD, 16));
		add(lblPrice);


		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(932, 360, 175, 4);
		separator_3.setBackground(new Color(30, 144, 255));
		add(separator_3);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 238, 752, 344);
		add(scrollPane);

		table = new JTable();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				btnAdd.setEnabled(false);
				btnAdd.setBorder(new LineBorder(new Color(55, 53, 61)));

				btnDelete.setEnabled(true);
				btnDelete.setBorder(new LineBorder(new Color(30, 144, 255)));
				btnUpdate.setEnabled(true);
				btnUpdate.setBorder(new LineBorder(new Color(30, 144, 255)));
				


				id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				
//				yyyy-mm-dd hh:mm:ss
				String date = table.getValueAt(table.getSelectedRow(), 2).toString();

				cmboProductCode.setSelectedItem(table.getValueAt(table.getSelectedRow(), 1).toString());
				
				String sDate1="31/12/1998";  
				Date d1 = null;
			    try {
					d1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}  
				
				dateChooser.setDate(d1);
				txtTransaction.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
				txtPrice.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
				txtStoreQuantity.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
				txtWHouseQuantity.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
				txtInvoiceNum.setText(table.getValueAt(table.getSelectedRow(), 7).toString());
	

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

		txtPrice = new JTextField();
		txtPrice.setOpaque(false);
		txtPrice.setForeground(Color.BLACK);
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPrice.setBorder(null);
		txtPrice.setBounds(932, 336, 175, 20);
		add(txtPrice);

		JLabel lblStoreQuantity = new JLabel("Store Quantity");
		lblStoreQuantity.setForeground(new Color(30, 144, 255));
		lblStoreQuantity.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblStoreQuantity.setBounds(772, 388, 135, 20);
		add(lblStoreQuantity);

		txtStoreQuantity = new JTextField();
		txtStoreQuantity.setOpaque(false);
		txtStoreQuantity.setForeground(Color.BLACK);
		txtStoreQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtStoreQuantity.setBorder(null);
		txtStoreQuantity.setBounds(932, 390, 175, 20);
		add(txtStoreQuantity);

		JLabel lblWarehouseQuantity = new JLabel("W-House Quantity");
		lblWarehouseQuantity.setForeground(new Color(30, 144, 255));
		lblWarehouseQuantity.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblWarehouseQuantity.setBounds(772, 449, 150, 20);
		add(lblWarehouseQuantity);

		txtWHouseQuantity = new JTextField();
		txtWHouseQuantity.setOpaque(false);
		txtWHouseQuantity.setForeground(Color.BLACK);
		txtWHouseQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtWHouseQuantity.setBorder(null);
		txtWHouseQuantity.setBounds(932, 449, 175, 20);
		add(txtWHouseQuantity);

		JLabel lblCapital = new JLabel("Sales Invoice No");
		lblCapital.setForeground(new Color(30, 144, 255));
		lblCapital.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblCapital.setBounds(772, 502, 135, 20);
		add(lblCapital);

		txtInvoiceNum = new JTextField();
		txtInvoiceNum.setOpaque(false);
		txtInvoiceNum.setForeground(Color.BLACK);
		txtInvoiceNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtInvoiceNum.setBorder(null);
		txtInvoiceNum.setBounds(932, 502, 175, 20);
		add(txtInvoiceNum);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBackground(new Color(30, 144, 255));
		separator_5.setBounds(932, 529, 175, 4);
		add(separator_5);

		JSeparator separator_6 = new JSeparator();
		separator_6.setBackground(new Color(30, 144, 255));
		separator_6.setBounds(932, 476, 175, 4);
		add(separator_6);

		JSeparator separator_7 = new JSeparator();
		separator_7.setBackground(new Color(30, 144, 255));
		separator_7.setBounds(932, 417, 175, 4);
		add(separator_7);

		lblBrand = new JLabel("");
		lblBrand.setForeground(Color.BLACK);
		lblBrand.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblBrand.setBounds(918, 97, 111, 20);
		add(lblBrand);

		lblCategory = new JLabel("");
		lblCategory.setForeground(Color.BLACK);
		lblCategory.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblCategory.setBounds(918, 58, 111, 20);
		add(lblCategory);

		cmboProductCode = new JComboBox();
		cmboProductCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmboProductCode.getSelectedItem() != null) {

					String productCode = cmboProductCode.getSelectedItem().toString();
					if (!productCode.equalsIgnoreCase("Select")) {
						StockDAO dao = new StockDAOImpl();
						String brandCate[] = dao.getBrandCateogryByProductCode(productCode);

						lblCategory.setText(brandCate[0].toString());
						lblBrand.setText(brandCate[1].toString());
					}
				}

			}
		});
		cmboProductCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmboProductCode.setBounds(932, 170, 175, 20);
		add(cmboProductCode);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(932, 226, 175, 20);
		add(dateChooser);

		JLabel LABELCATE = new JLabel("Category");
		LABELCATE.setForeground(new Color(30, 144, 255));
		LABELCATE.setFont(new Font("Segoe UI", Font.BOLD, 16));
		LABELCATE.setBounds(772, 58, 111, 20);
		add(LABELCATE);

		JLabel lblBrand_1 = new JLabel("Brand");
		lblBrand_1.setForeground(new Color(30, 144, 255));
		lblBrand_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblBrand_1.setBounds(772, 97, 111, 20);
		add(lblBrand_1);

		JButton btnLoadCodes = new JButton("Load codes");
		btnLoadCodes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmboProductCode.getSelectedItem() != null) {
					cmboProductCode.removeAllItems();
				}

				populateComboBox();
			}
		});

		btnLoadCodes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (btnLoadCodes.isEnabled()) {
					mouseClickedEffect(btnLoadCodes);
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnLoadCodes.isEnabled()) {
					mouseReleasedEffect(btnLoadCodes);
				}

			}
		});

		btnLoadCodes.setOpaque(false);
		btnLoadCodes.setForeground(new Color(30, 144, 255));
		btnLoadCodes.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnLoadCodes.setContentAreaFilled(false);
		btnLoadCodes.setBorder(new LineBorder(new Color(30, 144, 255)));
		btnLoadCodes.setBounds(1001, 136, 106, 20);
		add(btnLoadCodes);
		
		populateComboBox();
		populateTable();

	}

	public void populateComboBox() {
		StockDAO dao = new StockDAOImpl();
		List<String> names = dao.getProductCodes();
		cmboProductCode.addItem("Select");
		for (String n : names) {
			cmboProductCode.addItem(n);
		}
	}

	private void populateTable() {
		StockDAO cdao = new StockDAOImpl();
		ResultSet rs = cdao.getDataResultSet();
		table.setModel(buildTableModel(rs));

		table.getColumnModel().getColumn(1).setHeaderValue("Prod code");
		table.getColumnModel().getColumn(2).setHeaderValue("Arrival Date");
		table.getColumnModel().getColumn(3).setHeaderValue("Transaction");
		table.getColumnModel().getColumn(4).setHeaderValue("Price");
		table.getColumnModel().getColumn(5).setHeaderValue("S-Qty");
		table.getColumnModel().getColumn(6).setHeaderValue("WH-Qty");
		table.getColumnModel().getColumn(7).setHeaderValue("Sales Invoice");

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
