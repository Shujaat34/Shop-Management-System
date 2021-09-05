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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class ClientPanel extends JPanel { 

	private JTextField txtFirstName;
	private JTextField txtMiddleName;
	private JTextField txtLastName;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnClear;
	private JTable table;
	private JTextField txtAddress;
	private JTextField txtContact;
	private JTextField txtBank;
	private JTextField txtCompany;

	public ClientPanel() {

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
				txtFirstName.setText("");
				txtMiddleName.setText("");
				txtLastName.setText("");
				txtAddress.setText("");
				txtContact.setText("");
				txtBank.setText("");
				txtCompany.setText("");

				JOptionPane.showMessageDialog(ClientPanel.this, "Fields Cleared", "Message",
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
		separator.setBounds(932, 166, 175, 4);
		separator.setBackground(new Color(30, 144, 255));
		add(separator);

		txtFirstName = new JTextField();
		txtFirstName.setBounds(932, 139, 175, 20);
		txtFirstName.setForeground(Color.BLACK);
		txtFirstName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					// login();
				}

			}
		});
		txtFirstName.setOpaque(false);
		txtFirstName.setBorder(null);
		txtFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(txtFirstName);

		JLabel lblProductNo = new JLabel("First Name");
		lblProductNo.setBounds(772, 139, 111, 20);
		lblProductNo.setForeground(new Color(30, 144, 255));
		lblProductNo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		add(lblProductNo);

		JLabel lblBook = new JLabel("Client");
		lblBook.setBounds(391, 28, 96, 39);
		lblBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblBook.setForeground(new Color(30, 144, 255)); // 40, 20, 82 Dark purple
		// Blue 30 ,144, 255
		lblBook.setFont(new Font("Segoe UI Black", Font.BOLD, 21));
		add(lblBook);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(476, 11, 72, 69);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(ClientPanel.class.getResource("/com/bukhari/imgs/user_64px.png")));
		add(lblNewLabel);

		txtMiddleName = new JTextField();
		txtMiddleName.setBounds(932, 207, 175, 20);
		txtMiddleName.setOpaque(false);
		txtMiddleName.setForeground(Color.BLACK);
		txtMiddleName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMiddleName.setBorder(null);
		add(txtMiddleName);

		JLabel lblBrand = new JLabel("Middle Name");
		lblBrand.setBounds(772, 207, 111, 20);
		lblBrand.setForeground(new Color(30, 144, 255));
		lblBrand.setFont(new Font("Segoe UI", Font.BOLD, 16));
		add(lblBrand);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(932, 234, 175, 4);
		separator_1.setBackground(new Color(30, 144, 255));
		add(separator_1);

		JLabel lblCate = new JLabel("Last Name");
		lblCate.setBounds(772, 273, 111, 20);
		lblCate.setForeground(new Color(30, 144, 255));
		lblCate.setFont(new Font("Segoe UI", Font.BOLD, 16));
		add(lblCate);

		txtLastName = new JTextField();
		txtLastName.setBounds(932, 273, 175, 20);
		txtLastName.setOpaque(false);
		txtLastName.setForeground(Color.BLACK);
		txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtLastName.setBorder(null);
		add(txtLastName);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(932, 300, 175, 4);
		separator_2.setBackground(new Color(30, 144, 255));
		add(separator_2);

		btnAdd = new JButton("Add");
		btnAdd.setBounds(151, 142, 106, 28);

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (btnAdd.isEnabled()) {
					if (txtFirstName.getText().isEmpty() || txtMiddleName.getText().isEmpty()
							|| txtLastName.getText().isEmpty() || txtAddress.getText().isEmpty()
							|| txtContact.getText().isEmpty() || txtBank.getText().isEmpty()
							|| txtCompany.getText().isEmpty() ) {
						JOptionPane.showMessageDialog(ClientPanel.this, "Please Fill the Fields", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					} else {

						String firstName = txtFirstName.getText().toString();
						String middleName = txtMiddleName.getText().toString();
						String lastName = txtLastName.getText().toString();
						String address = txtAddress.getText().toString(); 
						String contact = txtContact.getText().toString(); 
						String bank = txtBank.getText().toString();
						String company = txtCompany.getText().toString();

						Validation validate = new Validation();

						boolean isFirstName = validate.nameValidate(firstName);
						boolean isMiddleName =  validate.nameValidate(middleName);
						boolean isLastName =  validate.nameValidate(lastName);
						boolean isBank = validate.nameValidate(bank);
						boolean isCompany =  validate.nameValidate(company);
						

						if (isFirstName == false) {
							JOptionPane.showMessageDialog(ClientPanel.this, "Invalid First Name",
									"Message",JOptionPane.DEFAULT_OPTION);
						} else if (isMiddleName == false) {
							JOptionPane.showMessageDialog(ClientPanel.this, "Invalid Middle Name",
									"Message", JOptionPane.DEFAULT_OPTION);
						} else if (isLastName == false) {
							JOptionPane.showMessageDialog(ClientPanel.this, "Invalid Last Name",
									"Message", JOptionPane.DEFAULT_OPTION);
						} else if (isBank == false) {
							JOptionPane.showMessageDialog(ClientPanel.this, "Invalid Bank Name", "Message",
									JOptionPane.DEFAULT_OPTION);
						} else if (isCompany == false) {
							JOptionPane.showMessageDialog(ClientPanel.this, "Invalid Company Name",
									"Message", JOptionPane.DEFAULT_OPTION);
						} else {
							ClientDAO cdao = new ClientDAOImpl(); 

							int row = cdao.addClient(firstName, middleName, lastName, address, contact, bank, company);
							if (row == 1) {
								JOptionPane.showMessageDialog(ClientPanel.this, "Client Added Successfully",
										"Message", JOptionPane.DEFAULT_OPTION);

							} else {
								JOptionPane.showMessageDialog(ClientPanel.this, "Client Cant be added", "Message",
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
		btnUpdate.setBounds(342, 142, 106, 28);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnUpdate.isEnabled()) {

					if (txtFirstName.getText().isEmpty() || txtMiddleName.getText().isEmpty()
							|| txtLastName.getText().isEmpty() || txtAddress.getText().isEmpty()
							|| txtContact.getText().isEmpty() || txtBank.getText().isEmpty()
							|| txtCompany.getText().isEmpty() ) {
						JOptionPane.showMessageDialog(ClientPanel.this, "Please Fill the Fields", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					} else {

						Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);

						String firstName = txtFirstName.getText().toString();
						String middleName = txtMiddleName.getText().toString();
						String lastName = txtLastName.getText().toString();
						String address = txtAddress.getText().toString();
						String contact = txtContact.getText().toString();
						String bank = txtBank.getText().toString();
						String company = txtCompany.getText().toString();
						

						
						ClientDAO dao = new ClientDAOImpl();
						
						int row = dao.updateClient(firstName, middleName, lastName, address, contact, bank, company, id);					if(row==1) {
							JOptionPane.showMessageDialog(ClientPanel.this, "Client Updated Successfully", "Message", JOptionPane.DEFAULT_OPTION);
						}else {
							JOptionPane.showMessageDialog(ClientPanel.this, "Client Not Updated", "Message", JOptionPane.ERROR_MESSAGE);
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
		btnDelete.setBounds(537, 142, 106, 28);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ClientDAO dao = new ClientDAOImpl();
				Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				int row = dao.deleteClient(id);
				if (row == 1) {
					JOptionPane.showMessageDialog(ClientPanel.this, "Client Deleted Successful", "Message",
							JOptionPane.DEFAULT_OPTION);
				} else {
					JOptionPane.showMessageDialog(ClientPanel.this, "Client Not Deleted", "Message",
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

		JLabel lblDescrip = new JLabel("Address");
		lblDescrip.setBounds(772, 337, 111, 20);
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
		separator_3.setBounds(932, 364, 175, 4);
		separator_3.setBackground(new Color(30, 144, 255));
		add(separator_3);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 193, 752, 389);
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

				int id = (Integer) table.getValueAt(table.getSelectedRow(), 0);

				txtFirstName.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				txtMiddleName.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				txtLastName.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
				txtAddress.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
				txtContact.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
				txtBank.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
				txtCompany.setText(table.getValueAt(table.getSelectedRow(), 7).toString());
		
				
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

		txtAddress = new JTextField();
		txtAddress.setOpaque(false);
		txtAddress.setForeground(Color.BLACK);
		txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAddress.setBorder(null);
		txtAddress.setBounds(932, 340, 175, 20);
		add(txtAddress);

		JLabel lblStoreQuantity = new JLabel("Contact No.");
		lblStoreQuantity.setForeground(new Color(30, 144, 255));
		lblStoreQuantity.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblStoreQuantity.setBounds(772, 391, 135, 20);
		add(lblStoreQuantity);

		txtContact = new JTextField();
		txtContact.setOpaque(false);
		txtContact.setForeground(Color.BLACK);
		txtContact.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtContact.setBorder(null);
		txtContact.setBounds(932, 393, 175, 20);
		add(txtContact);

		JLabel lblWarehouseQuantity = new JLabel("Bank");
		lblWarehouseQuantity.setForeground(new Color(30, 144, 255));
		lblWarehouseQuantity.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblWarehouseQuantity.setBounds(772, 450, 150, 20);
		add(lblWarehouseQuantity);

		txtBank = new JTextField();
		txtBank.setOpaque(false);
		txtBank.setForeground(Color.BLACK);
		txtBank.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtBank.setBorder(null);
		txtBank.setBounds(932, 450, 175, 20);
		add(txtBank);

		JLabel lblCapital = new JLabel("Company");
		lblCapital.setForeground(new Color(30, 144, 255));
		lblCapital.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblCapital.setBounds(772, 502, 111, 20);
		add(lblCapital);

		txtCompany = new JTextField();
		txtCompany.setOpaque(false);
		txtCompany.setForeground(Color.BLACK);
		txtCompany.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCompany.setBorder(null);
		txtCompany.setBounds(932, 502, 175, 20);
		add(txtCompany);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBackground(new Color(30, 144, 255));
		separator_5.setBounds(932, 529, 175, 4);
		add(separator_5);

		JSeparator separator_6 = new JSeparator();
		separator_6.setBackground(new Color(30, 144, 255));
		separator_6.setBounds(932, 477, 175, 4);
		add(separator_6);

		JSeparator separator_7 = new JSeparator();
		separator_7.setBackground(new Color(30, 144, 255));
		separator_7.setBounds(932, 420, 175, 4);
		add(separator_7);

		populateTable();
	}

	private void populateTable() {
		ClientDAO cdao = new ClientDAOImpl();
		ResultSet rs = cdao.getDataResultSet();
		table.setModel(buildTableModel(rs));

		table.getColumnModel().getColumn(1).setHeaderValue("First Name");
		table.getColumnModel().getColumn(2).setHeaderValue("Middle Name");
		table.getColumnModel().getColumn(3).setHeaderValue("Last Name");
		table.getColumnModel().getColumn(5).setHeaderValue("Contact");


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
