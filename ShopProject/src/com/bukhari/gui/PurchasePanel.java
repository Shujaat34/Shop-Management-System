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
import com.bukhari.dao.ProductDAO;
import com.bukhari.daoimpl.ClientDAOImpl;
import com.bukhari.daoimpl.ProductDAOImpl;
import com.bukhari.util.Validation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.FlatteningPathIterator;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PurchasePanel extends JPanel {
	private JTextField txtClientID;
	private JButton btnReset;
	private JButton btnAddtoCart;
	private JButton btnProceed;
	private JTable table;
	private JTextField txtSearch;
	private JTextField txtQuantity;
	private JTable tableCart;
	private JPanel panel;
	private JLabel lblStatus;
	private JLabel lblStatus1;

	private JComboBox cmboPayment;
	private JLabel lblPayable;
	private JLabel lblPayableAmount;
	private JLabel lblcmStatus;
	private JSeparator separator;

	private Integer idQuantity[];

	private Float productPrice[];

	private int id = 0;

	public PurchasePanel() {

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				btnAddtoCart.setEnabled(false);
				btnAddtoCart.setBorder(new LineBorder(new Color(55, 53, 61)));
				
				txtPriceEdit.setEditable(false);

			}
		});
		setBounds(0, 0, 1117, 605);
		setBackground(new Color(252, 252, 252));
		setLayout(null);

		JLabel lblBook = new JLabel("Purchase");
		lblBook.setBounds(391, 28, 111, 39);
		lblBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblBook.setForeground(new Color(30, 144, 255)); // 40, 20, 82 Dark
														// purple
		// Blue 30 ,144, 255
		lblBook.setFont(new Font("Segoe UI Black", Font.BOLD, 21));
		add(lblBook);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(500, 11, 72, 80);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(
				new ImageIcon(PurchasePanel.class.getResource("/com/bukhari/imgs/add_shopping_cart_64px.png")));
		add(lblNewLabel);

		btnReset = new JButton("Reset");
		btnReset.setBounds(613, 277, 106, 28);

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (btnReset.isEnabled()) {
					values.clear();

					remove(panel);
					revalidate();
					repaint();

					DefaultTableModel model = (DefaultTableModel) tableCart.getModel();
					model.setRowCount(0);

					lblStatus1.setText("");
					lblStatus.setText("");
					lblTotalAmount.setText("");

					if (model.getRowCount() == 0) {
						btnProceed.setEnabled(false);
						btnProceed.setBorder(new LineBorder(new Color(55, 53, 61)));
					} else {
						btnProceed.setEnabled(true);
						btnProceed.setBorder(new LineBorder(new Color(30, 144, 255)));
					}
				}

			}
		});

		btnReset.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (btnReset.isEnabled()) {
					mouseClickedEffect(btnReset);
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnReset.isEnabled()) {
					mouseReleasedEffect(btnReset);
				}

			}
		});

		btnReset.setForeground(new Color(30, 144, 255));
		// For Transpareting the Button
		btnReset.setOpaque(false);
		btnReset.setContentAreaFilled(false);
		btnReset.setBorder(new LineBorder(new Color(30, 144, 255)));
		add(btnReset);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(772, 122, 335, 398);
		panel.setLayout(null);

		//////////////////////////////////////////////

		/////////////////////////////////////////////

		txtClientID = new JTextField();
		txtClientID.setBounds(150, 183, 175, 20);
		panel.add(txtClientID);
		txtClientID.setOpaque(false);
		txtClientID.setForeground(Color.BLACK);
		txtClientID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtClientID.setBorder(null);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(150, 210, 175, 4);
		separator_1.setBackground(new Color(30, 144, 255));

		JLabel lblPaymentMethod = new JLabel("Payment");
		lblPaymentMethod.setForeground(Color.BLACK);
		lblPaymentMethod.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblPaymentMethod.setBounds(10, 11, 141, 20);

		cmboPayment = new JComboBox();
		cmboPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String status = cmboPayment.getSelectedItem().toString();
				if (status.equalsIgnoreCase("cash")) {
					lblcmStatus.setText("");
					txtPaymentNumber.setEnabled(false);
					separator.setVisible(false);
				} else {
					txtPaymentNumber.setEnabled(true);
					separator.setVisible(true);
					lblcmStatus.setText(status + " #");
				}
			}
		});
		cmboPayment.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmboPayment.setModel(new DefaultComboBoxModel(new String[] { "Cash", "Cheque", "Loan" }));
		cmboPayment.setBounds(150, 54, 175, 20);

		JLabel lblMethod = new JLabel("Method");
		lblMethod.setForeground(Color.BLACK);
		lblMethod.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblMethod.setBounds(10, 51, 72, 20);

		JLabel lblClientId = new JLabel("Client ID");
		lblClientId.setForeground(Color.BLACK);
		lblClientId.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblClientId.setBounds(10, 183, 72, 20);

		JButton btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pay_num = " ";
				if (!cmboPayment.getSelectedItem().toString().equalsIgnoreCase("Cash")) {
					if (txtPaymentNumber.getText().isEmpty()) {
						JOptionPane.showMessageDialog(PurchasePanel.this, "Fill the Fields", "Message",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					} else {
						pay_num = txtPaymentNumber.getText().toString();
					}
				}

				if (txtClientID.getText().isEmpty()) {
					JOptionPane.showMessageDialog(PurchasePanel.this, "Fill the Field", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				} else {

					Validation validate = new Validation();
					boolean isNumber = validate.isNumber(txtClientID.getText().toString());
					if (isNumber == false) {
						JOptionPane.showMessageDialog(PurchasePanel.this, "Invalid Client ID", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						ClientDAO cdao = new ClientDAOImpl();
						String clientID = txtClientID.getText().toString();
						boolean idExist = cdao.clientIdExist(clientID);

						if (idExist) {
							String paymentType = cmboPayment.getSelectedItem().toString();

							idQuantity = new Integer[tableCart.getRowCount() * 2];

							ProductDAO pdao = new ProductDAOImpl();

							for (int i = 0; i < tableCart.getRowCount(); i++) {
								String id = (String) tableCart.getValueAt(i, 0);
								String quantity = (String) tableCart.getValueAt(i, 4);

								idQuantity[i] = Integer.parseInt(id); // This
																		// is
																		// ID

								idQuantity[i + 1] = Integer.parseInt(quantity);// This
																				// is
																				// Quantity

								DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
								Date d = new Date();
								String dateapproved = dateFormat.format(d);

								Float total = (productPrice[i] * idQuantity[i + 1]);
								
								cdao.addPayment(paymentType, clientID, productPrice[i].toString(), id,
										idQuantity[i + 1], dateapproved, 1, pay_num,total);

								Integer qty = pdao.getStoreQuantityById(idQuantity[i]);
								pdao.updateProductQuantity((qty - idQuantity[i + 1]), idQuantity[i]);
								
								String product_code = table.getValueAt(table.getSelectedRow(), 10).toString();
										
								pdao.updateStockQuantity((qty - idQuantity[i + 1]), product_code);

							}

							JOptionPane.showMessageDialog(PurchasePanel.this, "Payment Done on " + paymentType,
									"Message", JOptionPane.INFORMATION_MESSAGE);

							populateTable();

							values.clear();

							remove(panel);
							revalidate();
							repaint();

							DefaultTableModel model = (DefaultTableModel) tableCart.getModel();
							model.setRowCount(0);

							lblStatus.setText("");
							lblStatus1.setText("");
							lblTotalAmount.setText("");

						} else {
							lblStatus1.setText("Client With this ID does not Exist OR");
							lblStatus.setText("Unregistered Client");

						}
					}

				}

			}
		});
		btnPay.setOpaque(false);
		btnPay.setForeground(new Color(30, 144, 255));
		btnPay.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnPay.setContentAreaFilled(false);
		btnPay.setBorder(new LineBorder(new Color(30, 144, 255)));
		btnPay.setBounds(219, 359, 106, 28);

		btnPay.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (btnPay.isEnabled()) {
					mouseClickedEffect(btnPay);
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnPay.isEnabled()) {
					mouseReleasedEffect(btnPay);
				}

			}
		});

		lblPayableAmount = new JLabel("Payable Amount");
		lblPayableAmount.setForeground(Color.BLACK);
		lblPayableAmount.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblPayableAmount.setBounds(10, 312, 141, 20);

		lblPayable = new JLabel("Payable Amount");
		lblPayable.setForeground(Color.BLACK);
		lblPayable.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblPayable.setBounds(161, 312, 141, 20);

		lblcmStatus = new JLabel("Method");
		lblcmStatus.setForeground(Color.BLACK);
		lblcmStatus.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblcmStatus.setBounds(10, 111, 72, 20);

		txtPaymentNumber = new JTextField();
		txtPaymentNumber.setOpaque(false);
		txtPaymentNumber.setForeground(Color.BLACK);
		txtPaymentNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPaymentNumber.setBorder(null);
		txtPaymentNumber.setBounds(150, 111, 175, 20);

		separator = new JSeparator();
		separator.setBackground(new Color(30, 144, 255));
		separator.setBounds(150, 138, 175, 4);

		// ===================================================================================

		// ====================================================================================
		
		
		
		txtPriceEdit = new JTextField();
		txtPriceEdit.setOpaque(false);
		txtPriceEdit.setForeground(Color.BLACK);
		txtPriceEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPriceEdit.setBorder(null);
		txtPriceEdit.setBounds(150, 245, 175, 20);
		
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(new Color(30, 144, 255));
		separator_2.setBounds(150, 272, 175, 4);
		
		
		JButton btnEditPrice = new JButton("Edit Price");
		btnEditPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPriceEdit.setEditable(true);
			}
		});
		btnEditPrice.setOpaque(false);
		btnEditPrice.setForeground(new Color(30, 144, 255));
		btnEditPrice.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnEditPrice.setContentAreaFilled(false);
		btnEditPrice.setBorder(new LineBorder(new Color(30, 144, 255)));
		btnEditPrice.setBounds(10, 253, 95, 20);
		
		
		JButton btnSetPrice = new JButton("Set Price");
		btnSetPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtPriceEdit.getText().isEmpty()) {
					JOptionPane.showMessageDialog(PurchasePanel.this, "Fill the Field", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				}else {
					lblPayable.setText("$" + txtPriceEdit.getText().toString());
				}
			}
		});
		btnSetPrice.setOpaque(false);
		btnSetPrice.setForeground(new Color(30, 144, 255));
		btnSetPrice.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnSetPrice.setContentAreaFilled(false);
		btnSetPrice.setBorder(new LineBorder(new Color(30, 144, 255)));
		btnSetPrice.setBounds(219, 276, 106, 20);
		
		
		

		btnProceed = new JButton("Proceed");
		btnProceed.setBounds(656, 480, 106, 28);
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add(panel);

				panel.add(lblPaymentMethod);
				panel.add(separator_1);
				panel.add(cmboPayment);
				panel.add(lblMethod);
				panel.add(lblClientId);
				panel.add(btnPay);
				panel.add(lblPayableAmount);
				panel.add(lblPayable);
				panel.add(lblcmStatus);

				panel.add(txtPaymentNumber);
				panel.add(separator);
				panel.add(txtPriceEdit);
				panel.add(separator_2);
				panel.add(btnEditPrice);
	
				panel.add(btnSetPrice);

				panel.validate();
				panel.repaint();
				
				
				Float sum = 0f;
				int rows = tableCart.getRowCount();

				productPrice = new Float[rows];

				for (int i = 0; i < tableCart.getRowCount(); i++) {
					String quantity = (String) tableCart.getValueAt(i, 4);
					String price = (String) tableCart.getValueAt(i, 5);

					sum = sum + (Integer.parseInt(quantity) * Float.parseFloat(price));

					productPrice[i] = Float.parseFloat(price);
				}

				lblTotalAmount.setText("$" + sum);
				lblPayable.setText("$" + sum);

				lblcmStatus.setText("");
				txtPaymentNumber.setEnabled(false);
				separator.setVisible(false);
			}
		});////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		btnProceed.setOpaque(false);
		btnProceed.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (btnProceed.isEnabled()) {
					mouseClickedEffect(btnProceed);
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnProceed.isEnabled()) {
					mouseReleasedEffect(btnProceed);
				}

			}
		});
		btnProceed.setForeground(new Color(30, 144, 255));
		btnProceed.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnProceed.setContentAreaFilled(false);
		btnProceed.setBorder(new LineBorder(new Color(30, 144, 255)));
		add(btnProceed);

		btnAddtoCart = new JButton("Add to Cart");
		btnAddtoCart.setBounds(483, 277, 106, 28);
		btnAddtoCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (btnAddtoCart.isEnabled()) {
					if (txtQuantity.getText().isEmpty()) {
						JOptionPane.showMessageDialog(PurchasePanel.this, "Please Enter the Quantity", "Message",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Validation validation = new Validation();

						String quantity = txtQuantity.getText().toString();

						boolean isNumber = validation.isNumber(quantity);

						if (isNumber == false) {
							JOptionPane.showMessageDialog(PurchasePanel.this, "Quantity must be a number", "Message",
									JOptionPane.ERROR_MESSAGE);
						} else {
							Integer availableQuantity = (Integer) table.getValueAt(table.getSelectedRow(), 5);
							Integer qty = Integer.parseInt(quantity);

							if (qty > availableQuantity) {
								JOptionPane.showMessageDialog(PurchasePanel.this,
										"Input Quantity is more than Available Quantity", "Message",
										JOptionPane.ERROR_MESSAGE);
							} else {
								populateCartTable(quantity);

								if (model.getRowCount() == 0) {
									btnProceed.setEnabled(false);
									btnProceed.setBorder(new LineBorder(new Color(55, 53, 61)));
								} else {
									btnProceed.setEnabled(true);
									btnProceed.setBorder(new LineBorder(new Color(30, 144, 255)));
								}
							}

						}

					}

				}

			}
		});
		btnAddtoCart.setOpaque(false);
		btnAddtoCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (btnAddtoCart.isEnabled()) {
					mouseClickedEffect(btnAddtoCart);
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnAddtoCart.isEnabled()) {
					mouseReleasedEffect(btnAddtoCart);
				}

			}
		});
		btnAddtoCart.setForeground(new Color(30, 144, 255));
		btnAddtoCart.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAddtoCart.setContentAreaFilled(false);
		btnAddtoCart.setBorder(new LineBorder(new Color(30, 144, 255)));
		add(btnAddtoCart);

		// tableModel = new BookTableModel(getBookList());
		// table = new JTable(tableModel);

		btnAddtoCart.setEnabled(false);
		btnAddtoCart.setBorder(new LineBorder(new Color(55, 53, 61)));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 122, 752, 131);
		add(scrollPane);

		table = new JTable();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				btnAddtoCart.setEnabled(true);
				btnAddtoCart.setBorder(new LineBorder(new Color(30, 144, 255)));

				id = (Integer) table.getValueAt(table.getSelectedRow(), 0);

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

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setForeground(new Color(30, 144, 255));
		lblSearch.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblSearch.setBounds(94, 83, 72, 20);
		add(lblSearch);

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

		txtSearch.setText("");
		txtSearch.setOpaque(false);
		txtSearch.setForeground(Color.BLACK);
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSearch.setBorder(null);
		txtSearch.setBounds(176, 83, 175, 20);
		add(txtSearch);

		JSeparator separator_10 = new JSeparator();
		separator_10.setBackground(new Color(30, 144, 255));
		separator_10.setBounds(176, 110, 175, 4);
		add(separator_10);

		JSeparator separator_11 = new JSeparator();
		separator_11.setBackground(new Color(30, 144, 255));
		separator_11.setBounds(269, 301, 175, 4);
		add(separator_11);

		txtQuantity = new JTextField();
		txtQuantity.setText("");
		txtQuantity.setOpaque(false);
		txtQuantity.setForeground(Color.BLACK);
		txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtQuantity.setBorder(null);
		txtQuantity.setBounds(269, 274, 175, 20);
		add(txtQuantity);

		JLabel lblQuantity = new JLabel("Enter Quantity you want to Buy");
		lblQuantity.setForeground(new Color(30, 144, 255));
		lblQuantity.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblQuantity.setBounds(10, 274, 245, 28);
		add(lblQuantity);

		tableCart = new JTable();
		tableCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tableCart.setSelectionForeground(Color.WHITE);
			}
		});
		tableCart.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		tableCart.getTableHeader().setForeground(new Color(30, 144, 255));
		tableCart.setRowHeight(20);
		tableCart.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableCart.setShowVerticalLines(false);
		tableCart.setSelectionBackground(new Color(30, 144, 255));

		tableCart.setAutoCreateRowSorter(true);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 354, 750, 117);
		add(scrollPane_1);
		scrollPane_1.setViewportView(tableCart);

		JLabel LABELTOTALAMOUNT = new JLabel("Total Amount");
		LABELTOTALAMOUNT.setForeground(Color.BLACK);
		LABELTOTALAMOUNT.setFont(new Font("Segoe UI", Font.BOLD, 16));
		LABELTOTALAMOUNT.setBounds(465, 539, 141, 20);
		add(LABELTOTALAMOUNT);

		lblTotalAmount = new JLabel("");
		lblTotalAmount.setForeground(Color.BLACK);
		lblTotalAmount.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTotalAmount.setBounds(618, 539, 141, 20);
		add(lblTotalAmount);

		lblStatus = new JLabel("");
		lblStatus.setForeground(Color.RED);
		lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblStatus.setBounds(772, 566, 335, 28);
		add(lblStatus);

		lblStatus1 = new JLabel("");
		lblStatus1.setForeground(Color.RED);
		lblStatus1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblStatus1.setBounds(772, 531, 335, 28);
		add(lblStatus1);

		btnProceed.setEnabled(false);
		btnProceed.setBorder(new LineBorder(new Color(55, 53, 61)));

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
		btnLoadData.setBounds(656, 83, 106, 28);
		add(btnLoadData);

		populateTable();

	}

	List<Object[]> values = new ArrayList<Object[]>();

	DefaultTableModel model;
	private JLabel lblTotalAmount;
	private JTextField txtPaymentNumber;
	private JTextField txtPriceEdit;

	private void populateCartTable(String quantity) {
		Object columnNames[] = { "id", "Product No", "Brand", "Category", "Quantity", "Price" };

		String id = table.getValueAt(table.getSelectedRow(), 0).toString();
		String prodNum = table.getValueAt(table.getSelectedRow(), 1).toString();
		String brand = table.getValueAt(table.getSelectedRow(), 2).toString();
		String category = table.getValueAt(table.getSelectedRow(), 3).toString();
		// String availableQuantity = table.getValueAt(table.getSelectedRow(),
		// 5).toString();
		String price = table.getValueAt(table.getSelectedRow(), 8).toString();

		Object rowData[] = { id, prodNum, brand, category, quantity, price };
		values.add(rowData);

		// model = new DefaultTableModel(columnNames, 0);
		model = new DefaultTableModel(values.toArray(new Object[][] {}), columnNames);
		// model.addRow(rowData);

		tableCart.setModel(model);

	}

	private void populateTable() {
		ProductDAO cdao = new ProductDAOImpl();
		ResultSet rs = cdao.getDataResultSet();
		table.setModel(buildTableModel(rs));

		table.getColumnModel().getColumn(1).setHeaderValue("Prod No");
		table.getColumnModel().getColumn(3).setHeaderValue("Categ");
		table.getColumnModel().getColumn(4).setHeaderValue("Descrip");
		table.getColumnModel().getColumn(5).setHeaderValue("S-Qty");
		table.getColumnModel().getColumn(6).setHeaderValue("WH-Qty");
		table.getColumnModel().getColumn(10).setHeaderValue("Prod code");

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
