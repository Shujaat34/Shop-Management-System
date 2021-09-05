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
import com.bukhari.dao.StockDAO;
import com.bukhari.dao.TransferedStockDAO;
import com.bukhari.daoimpl.ClientDAOImpl;
import com.bukhari.daoimpl.ProductDAOImpl;
import com.bukhari.daoimpl.StockDAOImpl;
import com.bukhari.daoimpl.TransferedStockDAOImpl;
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

public class StockTranferedPanel extends JPanel {  

	private JTextField txtStoreName;
	private JTextField txtQuantity;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnClear;
	private JTable table;

	public StockTranferedPanel() {

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			

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
		btnClear.setBounds(1001, 387, 106, 28);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtStoreName.setText("");
				txtQuantity.setText("");
//				txtLastName.setText("");
//				txtAddress.setText("");
//				txtContact.setText("");
//				txtBank.setText("");
//				txtCompany.setText("");

				JOptionPane.showMessageDialog(StockTranferedPanel.this, "Fields Cleared", "Message",
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
		separator.setBounds(932, 286, 175, 4);
		separator.setBackground(new Color(30, 144, 255));
		add(separator);

		txtStoreName = new JTextField();
		txtStoreName.setBounds(932, 259, 175, 20);
		txtStoreName.setForeground(Color.BLACK);
		txtStoreName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					// login();
				}

			}
		});
		txtStoreName.setOpaque(false);
		txtStoreName.setBorder(null);
		txtStoreName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(txtStoreName);

		JLabel LABELSTORENAME = new JLabel("Store Name");
		LABELSTORENAME.setBounds(772, 259, 111, 20);
		LABELSTORENAME.setForeground(new Color(30, 144, 255));
		LABELSTORENAME.setFont(new Font("Segoe UI", Font.BOLD, 16));
		add(LABELSTORENAME);

		JLabel lblBook = new JLabel("Stocks IN");
		lblBook.setBounds(352, 28, 135, 39);
		lblBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblBook.setForeground(new Color(30, 144, 255)); // 40, 20, 82 Dark purple
		// Blue 30 ,144, 255
		lblBook.setFont(new Font("Segoe UI Black", Font.BOLD, 21));
		add(lblBook);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(476, 11, 72, 69);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(StockTranferedPanel.class.getResource("/com/bukhari/imgs/internal_64px.png")));
		add(lblNewLabel);

		txtQuantity = new JTextField();
		txtQuantity.setBounds(932, 327, 175, 20);
		txtQuantity.setOpaque(false);
		txtQuantity.setForeground(Color.BLACK);
		txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtQuantity.setBorder(null);
		add(txtQuantity);

		JLabel lblBrand = new JLabel("Quantity");
		lblBrand.setBounds(772, 327, 111, 20);
		lblBrand.setForeground(new Color(30, 144, 255));
		lblBrand.setFont(new Font("Segoe UI", Font.BOLD, 16));
		add(lblBrand);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(932, 354, 175, 4);
		separator_1.setBackground(new Color(30, 144, 255));
		add(separator_1);

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(121, 142, 106, 28);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnUpdate.isEnabled()) {

					if (txtStoreName.getText().isEmpty() || txtQuantity.getText().isEmpty()) {
						JOptionPane.showMessageDialog(StockTranferedPanel.this, "Please Fill the Fields", "Message",
								JOptionPane.INFORMATION_MESSAGE);
					} else {

						Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);

						String store_name = txtStoreName.getText().toString();
						String quantity = txtQuantity.getText().toString();
						
						Validation validate = new Validation();
						
						boolean isName = validate.nameValidate(store_name);

						boolean isNumber = validate.isNumber(quantity);
						
						if(isName == false){
							JOptionPane.showMessageDialog(StockTranferedPanel.this, "Invalid Store Name", "Message", JOptionPane.DEFAULT_OPTION);
							
						}else if(isNumber == false){
							JOptionPane.showMessageDialog(StockTranferedPanel.this, "Quantity must be a Number", "Message", JOptionPane.DEFAULT_OPTION);
							
						}else{
							TransferedStockDAO dao = new TransferedStockDAOImpl();
							
							int row = dao.updateTransferedStock(store_name, quantity, id);
									
							if(row==1) {
								JOptionPane.showMessageDialog(StockTranferedPanel.this, "Stock IN Updated Successfully", "Message", JOptionPane.DEFAULT_OPTION);
							}else {
								JOptionPane.showMessageDialog(StockTranferedPanel.this, "Stock IN Not Updated", "Message", JOptionPane.ERROR_MESSAGE);
							}
							populateTable();
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
		btnDelete.setBounds(537, 142, 106, 28);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				TransferedStockDAO dao = new TransferedStockDAOImpl();
				Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				int row = dao.deleteTransferedStock(id);
				if (row == 1) {
					JOptionPane.showMessageDialog(StockTranferedPanel.this, "Stock IN Deleted Successful", "Message",
							JOptionPane.DEFAULT_OPTION);
				} else {
					JOptionPane.showMessageDialog(StockTranferedPanel.this, "Stock IN Not Deleted", "Message",
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

		btnDelete.setEnabled(false);
		btnDelete.setBorder(new LineBorder(new Color(55, 53, 61)));

		btnUpdate.setEnabled(false);
		btnUpdate.setBorder(new LineBorder(new Color(55, 53, 61)));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 193, 752, 389);
		add(scrollPane);

		table = new JTable();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {



				btnDelete.setEnabled(true);
				btnDelete.setBorder(new LineBorder(new Color(30, 144, 255)));
				btnUpdate.setEnabled(true);
				btnUpdate.setBorder(new LineBorder(new Color(30, 144, 255)));

				int id = (Integer) table.getValueAt(table.getSelectedRow(), 0);

				txtStoreName.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				txtQuantity.setText(table.getValueAt(table.getSelectedRow(), 3).toString());

		
				
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

		populateTable();
	}

	private void populateTable() {
		TransferedStockDAO cdao = new TransferedStockDAOImpl();
		ResultSet rs = cdao.getDataResultSet();
		table.setModel(buildTableModel(rs));

		table.getColumnModel().getColumn(1).setHeaderValue("Stock ID");
		table.getColumnModel().getColumn(2).setHeaderValue("Store Name");
		table.getColumnModel().getColumn(3).setHeaderValue("Quantity");
		table.getColumnModel().getColumn(4).setHeaderValue("Product Code");
		table.getColumnModel().getColumn(5).setHeaderValue("Price");
		table.getColumnModel().getColumn(6).setHeaderValue("Invoice #");


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
