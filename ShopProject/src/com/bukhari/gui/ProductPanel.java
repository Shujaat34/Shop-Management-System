package com.bukhari.gui;


import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
import java.util.Vector;

public class ProductPanel extends JPanel {

	private JTextField txtProductNum;
	private JTextField txtBrand;
	private JTextField txtCategory;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnClear;
	private JTable table;
	private JTextField txtDescription;
	private JTextField txtCapital;
	private JTextField txtPrice;
	private JTextField txtSupplierName;
	private JTextField txtProductCode;
	private JButton btnTransfer;
	private JRadioButton rdbstore;
	private JRadioButton rdbwhouse;
	private JTextField txtQuantity;
	
	private int id;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public ProductPanel() {

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnAdd.setEnabled(true);
				btnAdd.setBorder(new LineBorder(new Color(30, 144, 255)));

				btnDelete.setEnabled(false);
				btnDelete.setBorder(new LineBorder(new Color(55, 53, 61)));

				btnUpdate.setEnabled(false);
				btnUpdate.setBorder(new LineBorder(new Color(55, 53, 61)));
				
				btnTransfer.setEnabled(false);
				btnTransfer.setBorder(new LineBorder(new Color(55, 53, 61)));

			}
		});
	
		setBounds(0, 0, 1117, 605);
		setBackground(new Color(252, 252, 252));
		setLayout(null);

		btnClear = new JButton("Clear Fields");
		btnClear.setBounds(1001, 553, 106, 28);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtProductNum.setText("");
				txtBrand.setText("");
				txtCategory.setText("");
				txtDescription.setText("");
				txtCapital.setText("");
				txtPrice.setText("");
				txtSupplierName.setText("");
				txtProductCode.setText("");

				JOptionPane.showMessageDialog(ProductPanel.this, "Fields Cleared", "Message",
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

		JSeparator separator = new JSeparator();
		separator.setBounds(932, 192, 175, 4);
		separator.setBackground(new Color(30, 144, 255));
		add(separator);

		txtProductNum = new JTextField();
		txtProductNum.setBounds(932, 165, 175, 20);
		txtProductNum.setForeground(Color.BLACK);
		txtProductNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					// login();
				}

			}
		});
		txtProductNum.setOpaque(false);
		txtProductNum.setBorder(null);
		txtProductNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(txtProductNum);

		JLabel lblProductNo = new JLabel("Product No");
		lblProductNo.setBounds(772, 165, 111, 20);
		lblProductNo.setForeground(new Color(30, 144, 255));
		lblProductNo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		add(lblProductNo);

		JLabel lblBook = new JLabel("Product");
		lblBook.setBounds(235, 17, 96, 39);
		lblBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblBook.setForeground(new Color(30, 144, 255)); // 40, 20, 82 Dark purple
		// Blue 30 ,144, 255
		lblBook.setFont(new Font("Segoe UI Black", Font.BOLD, 21));
		add(lblBook);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(330, 0, 72, 80);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(ProductPanel.class.getResource("/com/bukhari/imgs/blue_trolley_64px.png")));
		add(lblNewLabel);

		txtBrand = new JTextField();
		txtBrand.setBounds(932, 217, 175, 20);
		txtBrand.setOpaque(false);
		txtBrand.setForeground(Color.BLACK);
		txtBrand.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBrand.setBorder(null);
		add(txtBrand);

		JLabel lblBrand = new JLabel("Brand");
		lblBrand.setBounds(772, 215, 111, 20);
		lblBrand.setForeground(new Color(30, 144, 255));
		lblBrand.setFont(new Font("Segoe UI", Font.BOLD, 16));
		add(lblBrand);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(932, 242, 175, 4);
		separator_1.setBackground(new Color(30, 144, 255));
		add(separator_1);

		JLabel lblCate = new JLabel("Category");
		lblCate.setBounds(772, 257, 111, 20);
		lblCate.setForeground(new Color(30, 144, 255));
		lblCate.setFont(new Font("Segoe UI", Font.BOLD, 16));
		add(lblCate);

		txtCategory = new JTextField();
		txtCategory.setBounds(932, 257, 175, 20);
		txtCategory.setOpaque(false);
		txtCategory.setForeground(Color.BLACK);
		txtCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCategory.setBorder(null);
		add(txtCategory);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(932, 284, 175, 4);
		separator_2.setBackground(new Color(30, 144, 255));
		add(separator_2);

		btnAdd = new JButton("Add");
		btnAdd.setBounds(10, 165, 89, 28);

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (btnAdd.isEnabled()) {
					if (txtProductNum.getText().isEmpty() || txtBrand.getText().isEmpty()
							|| txtCategory.getText().isEmpty() || txtDescription.getText().isEmpty()
							|| txtCapital.getText().isEmpty() || txtPrice.getText().isEmpty()
							|| txtSupplierName.getText().isEmpty() || txtProductCode.getText().isEmpty()) {
						JOptionPane.showMessageDialog(ProductPanel.this, "Please Fill the Fields", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					} else {

						String productNum = txtProductNum.getText().toString();
						String brand = txtBrand.getText().toString();
						String category = txtCategory.getText().toString();
						String description = txtDescription.getText().toString();
						String sQty = "100";
						String wQty = "100";
						String capital = txtCapital.getText().toString();
						String price = txtPrice.getText().toString();
						String supplier = txtSupplierName.getText().toString();
						String productCode = txtProductCode.getText().toString();

						Validation validate = new Validation();

						boolean isProductNumber = validate.isNumber(productNum);
						boolean isProductNumUniqu = validate.isNumberUnique(productNum, "product", "product_no");

						boolean isBrand = validate.nameValidate(brand);
						boolean isCategory = validate.nameValidate(category);
						boolean isSQty = validate.isNumber(sQty); 
						boolean isWQty = validate.isNumber(wQty);
						
						boolean isCapitalNum = validate.isNumber(capital);
						boolean isCapitalFloat = validate.isFloat(capital);
						
						
						boolean isPriceNum = validate.isNumber(price);
						boolean isPriceFloat = validate.isFloat(price);
						boolean isSupplier = validate.nameValidate(supplier);
						

						if (isProductNumber == false) {
							JOptionPane.showMessageDialog(ProductPanel.this, "Invalid Product Number",
									"Message",JOptionPane.DEFAULT_OPTION);
						} else if (isProductNumUniqu == false) {
							JOptionPane.showMessageDialog(ProductPanel.this, "Product Num can't be duplicate",
									"Message", JOptionPane.DEFAULT_OPTION);
						} else if (isBrand == false) {
							JOptionPane.showMessageDialog(ProductPanel.this, "Invalid Brand Name",
									"Message", JOptionPane.DEFAULT_OPTION);
						} else if (isCategory == false) {
							JOptionPane.showMessageDialog(ProductPanel.this, "Invalid Category Name", "Message",
									JOptionPane.DEFAULT_OPTION);
						} else if (isSQty == false) {
							JOptionPane.showMessageDialog(ProductPanel.this, "Store Quantity must be a number",
									"Message", JOptionPane.DEFAULT_OPTION);
						} else if (isWQty == false) {
							JOptionPane.showMessageDialog(ProductPanel.this, "Warehouse Quantity must be a number",
									"Message", JOptionPane.DEFAULT_OPTION);
						} else if (isPriceNum == false && isPriceFloat == false) {
							JOptionPane.showMessageDialog(ProductPanel.this, "Price must be a number", "Message",
									JOptionPane.DEFAULT_OPTION);
						} else if (isCapitalNum == false && isCapitalFloat == false) {
							JOptionPane.showMessageDialog(ProductPanel.this, "Capital must be a number", "Message",
									JOptionPane.DEFAULT_OPTION);
						}  
						else if (isSupplier == false) {
							JOptionPane.showMessageDialog(ProductPanel.this, "Invalid Supplier Name", "Message",
									JOptionPane.DEFAULT_OPTION);
						} else {
							ProductDAO pdao = new ProductDAOImpl();

							int row = pdao.addProduct(productNum, brand, category, description, sQty, wQty, capital,price, supplier, productCode);
							if (row == 1) {
								JOptionPane.showMessageDialog(ProductPanel.this, "Product Added Successfully",
										"Message", JOptionPane.DEFAULT_OPTION);

							} else {
								JOptionPane.showMessageDialog(ProductPanel.this, "Product Cant be added", "Message",
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
		btnUpdate.setBounds(126, 165, 89, 28);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnUpdate.isEnabled()) {

					if (txtProductNum.getText().isEmpty() || txtBrand.getText().isEmpty()
							|| txtCategory.getText().isEmpty() || txtDescription.getText().isEmpty()
							|| txtCapital.getText().isEmpty() || txtPrice.getText().isEmpty()
							|| txtSupplierName.getText().isEmpty() || txtProductCode.getText().isEmpty()) {
						JOptionPane.showMessageDialog(ProductPanel.this, "Please Fill the Fields", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					} else {

						Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);

						String productNum = txtProductNum.getText().toString();
						String brand = txtBrand.getText().toString();
						String category = txtCategory.getText().toString();
						String description = txtDescription.getText().toString();
						String sQty = "";
						String wQty = "";
						String capital = txtCapital.getText().toString();
						String price = txtPrice.getText().toString();
						String supplier = txtSupplierName.getText().toString();
						String productCode = txtProductCode.getText().toString();

						
						ProductDAO adao = new ProductDAOImpl();
						
						int row = adao.updateProduct(productNum, brand, category, description, sQty, wQty, capital, price, supplier, productCode, id);
						if(row==1) {
							JOptionPane.showMessageDialog(ProductPanel.this, "Product Updated Successfully", "Message", JOptionPane.DEFAULT_OPTION);
						}else {
							JOptionPane.showMessageDialog(ProductPanel.this, "Product Not Updated", "Message", JOptionPane.ERROR_MESSAGE);
						}
						populateTable();
						
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
		btnDelete.setBounds(245, 165, 89, 28);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ProductDAO dao = new ProductDAOImpl();
				Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				int row = dao.deleteProduct(id);
				if (row == 1) {
					JOptionPane.showMessageDialog(ProductPanel.this, "Product Deleted Successful", "Message",
							JOptionPane.DEFAULT_OPTION);
				} else {
					JOptionPane.showMessageDialog(ProductPanel.this, "Product Not Deleted", "Message",
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

		// tableModel = new BookTableModel(getBookList());
		// table = new JTable(tableModel);

		btnAdd.setEnabled(true);

		btnDelete.setEnabled(false);
		btnDelete.setBorder(new LineBorder(new Color(55, 53, 61)));

		btnUpdate.setEnabled(false);
		btnUpdate.setBorder(new LineBorder(new Color(55, 53, 61)));

		JLabel lblDescrip = new JLabel("Description");
		lblDescrip.setBounds(772, 310, 111, 20);
		lblDescrip.setForeground(new Color(30, 144, 255));
		lblDescrip.setFont(new Font("Segoe UI", Font.BOLD, 16));
		add(lblDescrip);

		// txtQuantity = new JTextField();
		// txtQuantity.setText("Book");
		// txtQuantity.setOpaque(false);
		// txtQuantity.setForeground(Color.WHITE);
		// txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		// txtQuantity.setBorder(null);
		// txtQuantity.setBounds(553, 280, 245, 20);
		// add(txtQuantity);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(932, 337, 175, 4);
		separator_3.setBackground(new Color(30, 144, 255));
		add(separator_3);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 221, 752, 360);
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

				txtProductNum.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				txtBrand.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				txtCategory.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
				txtDescription.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
//				txtStoreQuantity.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
//				txtWHouseQuantity.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
				txtCapital.setText(table.getValueAt(table.getSelectedRow(), 7).toString());
				txtPrice.setText(table.getValueAt(table.getSelectedRow(), 8).toString());
				txtSupplierName.setText(table.getValueAt(table.getSelectedRow(), 9).toString());
				txtProductCode.setText(table.getValueAt(table.getSelectedRow(), 10).toString());
				
				btnTransfer.setEnabled(true);
				btnTransfer.setBorder(new LineBorder(new Color(30, 144, 255)));
				
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

		txtDescription = new JTextField();
		txtDescription.setOpaque(false);
		txtDescription.setForeground(Color.BLACK);
		txtDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDescription.setBorder(null);
		txtDescription.setBounds(932, 313, 175, 20);
		add(txtDescription);

		JLabel lblCapital = new JLabel("Capital");
		lblCapital.setForeground(new Color(30, 144, 255));
		lblCapital.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblCapital.setBounds(772, 352, 111, 20);
		add(lblCapital);

		txtCapital = new JTextField();
		txtCapital.setOpaque(false);
		txtCapital.setForeground(Color.BLACK);
		txtCapital.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCapital.setBorder(null);
		txtCapital.setBounds(932, 352, 175, 20);
		add(txtCapital);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(new Color(30, 144, 255));
		lblPrice.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblPrice.setBounds(772, 404, 111, 20);
		add(lblPrice);

		txtPrice = new JTextField();
		txtPrice.setOpaque(false);
		txtPrice.setForeground(Color.BLACK);
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPrice.setBorder(null);
		txtPrice.setBounds(932, 407, 175, 20);
		add(txtPrice);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(new Color(30, 144, 255));
		separator_4.setBounds(932, 431, 175, 4);
		add(separator_4);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBackground(new Color(30, 144, 255));
		separator_5.setBounds(932, 379, 175, 4);
		add(separator_5);

		JLabel lblProductCode = new JLabel("Product Code");
		lblProductCode.setForeground(new Color(30, 144, 255));
		lblProductCode.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblProductCode.setBounds(772, 511, 111, 20);
		add(lblProductCode);

		JLabel lblSupplier = new JLabel("Supplier Name");
		lblSupplier.setForeground(new Color(30, 144, 255));
		lblSupplier.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblSupplier.setBounds(772, 460, 134, 20);
		add(lblSupplier);

		txtSupplierName = new JTextField();
		txtSupplierName.setOpaque(false);
		txtSupplierName.setForeground(Color.BLACK);
		txtSupplierName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSupplierName.setBorder(null);
		txtSupplierName.setBounds(932, 460, 175, 20);
		add(txtSupplierName);

		JSeparator separator_8 = new JSeparator();
		separator_8.setBackground(new Color(30, 144, 255));
		separator_8.setBounds(932, 487, 175, 4);
		add(separator_8);

		txtProductCode = new JTextField();
		txtProductCode.setOpaque(false);
		txtProductCode.setForeground(Color.BLACK);
		txtProductCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtProductCode.setBorder(null);
		txtProductCode.setBounds(932, 514, 175, 20);
		add(txtProductCode);

		JSeparator separator_9 = new JSeparator();
		separator_9.setBackground(new Color(30, 144, 255));
		separator_9.setBounds(932, 538, 175, 4);
		add(separator_9);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnAdd.setEnabled(true);
				btnAdd.setBorder(new LineBorder(new Color(30, 144, 255)));

				btnDelete.setEnabled(false);
				btnDelete.setBorder(new LineBorder(new Color(55, 53, 61)));

				btnUpdate.setEnabled(false);
				btnUpdate.setBorder(new LineBorder(new Color(55, 53, 61)));
				
				btnTransfer.setEnabled(false);
				btnTransfer.setBorder(new LineBorder(new Color(55, 53, 61)));
			}
		});
		panel.setBackground(new Color(252, 252, 252));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(477, 24, 278, 180);
		add(panel);
		panel.setLayout(null);
		
		btnTransfer = new JButton("Transfer");
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
//				int id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				if (btnTransfer.isEnabled()) {
					
					if(id != 0){
						
						if(txtQuantity.getText().isEmpty()){
							JOptionPane.showMessageDialog(ProductPanel.this, "Please Fill the Field", "Message",
									JOptionPane.ERROR_MESSAGE);
						}else{
							Validation validate = new Validation();
							
							String quantity = txtQuantity.getText().toString();
							
							boolean isqunatityNumber = validate.isNumber(quantity);
							
							
							if (isqunatityNumber == false) {
								JOptionPane.showMessageDialog(ProductPanel.this, "Quantity must be a number", 
										"Message", JOptionPane.DEFAULT_OPTION);
							}else{
								
								int input_quantity = Integer.parseInt(quantity);
								
								try {
									int store_quantity = (Integer)table.getValueAt(table.getSelectedRow(), 5);
									int warehouse_quantity = (Integer)table.getValueAt(table.getSelectedRow(), 6);
									StockDAO dao = new StockDAOImpl();
									ProductDAO productDao = new ProductDAOImpl();
									if(rdbstore.isSelected()){
										if (input_quantity > warehouse_quantity) {
											JOptionPane.showMessageDialog(ProductPanel.this, "Input Quantity is more than Availabe Quantity in Warehouse",
													"Message", JOptionPane.DEFAULT_OPTION);
										}else{
											store_quantity = store_quantity + input_quantity;
											warehouse_quantity = warehouse_quantity - input_quantity;
											
											int row = productDao.updateProudctQuantities(store_quantity, warehouse_quantity, id);
											String product_code = table.getValueAt(table.getSelectedRow(), 10).toString();
											
											//getting the store quantity and store_quantity
											
											Integer stock_storequantity = dao.getStockStoreQuantity(product_code);
											Integer stock_warehousequantity = dao.getStockWarehouseQuantity(product_code);
											
											stock_storequantity = stock_storequantity + input_quantity;
											stock_warehousequantity = stock_warehousequantity - input_quantity;
											
									
											dao.updateStockQuantitiesByProductCode(stock_storequantity, stock_warehousequantity, product_code);
											
										
											
											if(row ==1){
												JOptionPane.showMessageDialog(ProductPanel.this, "Stock Transfered From WareHouse to Store",
														"Message", JOptionPane.DEFAULT_OPTION);
											}
											
										}
										
									}else{
										if (input_quantity > store_quantity) {
											JOptionPane.showMessageDialog(ProductPanel.this, "Input Quantity is more than Availabe Quantity in Store",
													"Message", JOptionPane.DEFAULT_OPTION);
										}else{
											store_quantity = store_quantity - input_quantity;
											warehouse_quantity = warehouse_quantity + input_quantity;
											
											int row = productDao.updateProudctQuantities(store_quantity, warehouse_quantity, id);
											String product_code = table.getValueAt(table.getSelectedRow(), 10).toString();
											
											Integer stock_storequantity = dao.getStockStoreQuantity(product_code);
											Integer stock_warehousequantity = dao.getStockWarehouseQuantity(product_code);
											
											stock_storequantity = stock_storequantity - input_quantity;
											stock_warehousequantity = stock_warehousequantity + input_quantity;
											
											
											dao.updateStockQuantitiesByProductCode(stock_storequantity, stock_warehousequantity, product_code);
											
											if(row ==1){
												JOptionPane.showMessageDialog(ProductPanel.this, "Stock Transfered From Store to WareHouse",
														"Message", JOptionPane.DEFAULT_OPTION);
											}
											
										}
										
										
									}
								}catch(Exception e) {
									JOptionPane.showMessageDialog(ProductPanel.this, "Please Select a Row", 
											"Message", JOptionPane.DEFAULT_OPTION);
								}
								
								
								
								populateTable();
							}
						}
					
					}else{
						JOptionPane.showMessageDialog(ProductPanel.this, "Please Select a Row", "Message",
								JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});

		btnTransfer.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnTransfer.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (btnTransfer.isEnabled()) {
					mouseClickedEffect(btnTransfer);
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnTransfer.isEnabled()) {
					mouseReleasedEffect(btnTransfer);
				}

			}
		});
		btnTransfer.setOpaque(false);
		btnTransfer.setForeground(new Color(30, 144, 255));
		btnTransfer.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnTransfer.setContentAreaFilled(false);
		btnTransfer.setBorder(new LineBorder(new Color(30, 144, 255)));
		btnTransfer.setBounds(162, 149, 106, 20);
		panel.add(btnTransfer);
		
		
		
		btnAdd.setEnabled(true);

		btnDelete.setEnabled(false);
		btnDelete.setBorder(new LineBorder(new Color(55, 53, 61)));

		btnUpdate.setEnabled(false);
		btnUpdate.setBorder(new LineBorder(new Color(55, 53, 61)));
		
		btnTransfer.setEnabled(false);
		btnTransfer.setBorder(new LineBorder(new Color(55, 53, 61)));
		
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
		btnLoadData.setBounds(361, 165, 106, 28);
		add(btnLoadData);
		
		JLabel lblTitle = new JLabel("Stock Transfer");
		lblTitle.setBounds(79, 11, 111, 20);
		panel.add(lblTitle);
		lblTitle.setForeground(new Color(30, 144, 255));
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(10, 53, 111, 20);
		panel.add(lblQuantity);
		lblQuantity.setForeground(new Color(30, 144, 255));
		lblQuantity.setFont(new Font("Segoe UI", Font.BOLD, 16));
		
		txtQuantity = new JTextField();
		txtQuantity.setBounds(113, 56, 151, 20);
		panel.add(txtQuantity);
		txtQuantity.setOpaque(false);
		txtQuantity.setForeground(Color.BLACK);
		txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtQuantity.setBorder(null);
		
		JSeparator separator_11 = new JSeparator();
		separator_11.setBounds(113, 80, 151, 4);
		panel.add(separator_11);
		separator_11.setBackground(new Color(30, 144, 255));
		
		rdbwhouse = new JRadioButton("Ware House");
		buttonGroup.add(rdbwhouse);
		rdbwhouse.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbwhouse.setBounds(12, 101, 109, 23);
		panel.add(rdbwhouse);
		
		rdbstore = new JRadioButton("Store");
		buttonGroup.add(rdbstore);
		rdbstore.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbstore.setBounds(123, 102, 109, 23);
		panel.add(rdbstore);
		
		rdbwhouse.setSelected(true);

		
		

		populateTable();
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
