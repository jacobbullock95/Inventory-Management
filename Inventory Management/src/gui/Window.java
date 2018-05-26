package gui;

import java.awt.BorderLayout;

import csv.CSVFormatException;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import csv.ParseItems;
import csv.ParseManifest;
import csv.ParseSales;
import item.Stock;
import item.StockException;
import store.Store;
import truck.DeliveryException;
import truck.Manifest;


/**
 * Entry point for program. Launches the GUI, handles the flow of the program and handles all exceptions.
 * @author Jesse Haviland
 * NOTE: Some boilerplate code from CAB302 Lectures/Practicals has been utilised in constructing elements of the GUI
 */

@SuppressWarnings("serial")
public class Window extends JFrame implements ActionListener, Runnable {
	
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 600;
	
	private boolean storeInitialised = false;
	
	private JButton btnLoadItem;
	private JButton btnLoadSales;
	private JButton btnLoadManifest;
	private JButton btnGenManifest;
	
	private JLabel storeNameLabel;
	private JLabel storeCapLabel;
	
	private JPanel pnlBtn;
	private JPanel pnlLabel;
	
	private JScrollPane tableScrollPane;
	private JTable table;
	
	private static Store store;
	
	
	/**
	 * Program entry point. Creates and initialises the store. Launches the window
	 * @param args
	 */
	public static void main(String[] args) {
		
		String storeName = "SuperMart";
		int initialCapital = 100000;
		store = Store.getInstance();
		store.setName(storeName);
		store.setCapital(initialCapital);
				
		JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new Window("Hello"));

	}

	/**
	 * Launches a JFrame window
	 * @param title of the window
	 * @throws HeadlessException
	 */
	public Window(String title) throws HeadlessException {
		super(title);
	}
	
	
	@Override
	public void run() {
		createWindow();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

	
	/**
	 * Builds the window. Adds the panel which house the labels, table and buttons. 
	 * Adds action listeners for each of the buttons. These listeners enable the 
	 * flow of the program, warn the user using JOptionPanes when applicable and
	 * displays exceptions to the user.
	 */
	private void createWindow() {
		setSize(WIDTH, HEIGHT);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());

	    pnlLabel = createPanel(Color.WHITE);
	    pnlLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
	    pnlBtn = createPanel(Color.WHITE);
	    pnlBtn.setBorder(new EmptyBorder(10, 10, 10, 10));
	    
	    // Load Item Properties Button
	    btnLoadItem = createButton("Load Item Properties");
	    btnLoadItem.setPreferredSize(new Dimension(300, 50));
	    
	    btnLoadItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				
				// Assume yes for now
				int userOption = JOptionPane.YES_OPTION;
				
				// If the store has already been initialised, warn user of consequences
				if (storeInitialised) {
					userOption = JOptionPane.showConfirmDialog(null
				               ,"Performing this step again not effect items already imported.\nIt will successfully add items not currently in your stock list.\nDo you wish to continue?"
				               ,"Warning"
				               ,JOptionPane.YES_NO_OPTION);
				}
				
				// If user selected no, exit now
				if (userOption == JOptionPane.NO_OPTION) {
					return;
				}
				
				String file = createFileChooser(true);
				ParseItems parser;
				
				// If user did not select a file, exit now
				if (file.equals("")) {
					return;
				}
				
				// Attempt to parse the CSV file, update the store and add them to the table
				// Let user know when it goes wrong
				try {
					parser = new ParseItems(file);
					parser.parseResults(store.getInventory());
					addItemsToTable();
					storeInitialised = true;
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Sorry, there was an error with the file you selected. See error details below \n\n" + e);
				} catch (StockException e) {
					JOptionPane.showMessageDialog(null, "Sorry, there was an error with the stores stock. See error details below \n\n" + e);
				} catch (CSVFormatException e) {
					JOptionPane.showMessageDialog(null, "Sorry, there was an error with the format of the CSV you selected. See error details below \n\n" + e);
				}
			}
	    });
	    
	    // Load Sales Log Button
	    btnLoadSales = createButton("Load Sales Log");
	    btnLoadSales.setPreferredSize(new Dimension(300, 50));
	    
	    btnLoadSales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// If item properties have not been loaded in, do not let the user continue, exit now
				if (!storeInitialised) {
					JOptionPane.showMessageDialog(null, "Can not perform this task.\nPlease click the 'Load Item Properties' button to initialise your store.");
					return;
				}
				
				String file = createFileChooser(true);
				
				// If user did not select a file, exit now
				if (file.equals("")) {
					return;
				}
				
				// Attempt to parse the CSV file, update the store and add them to the table
				// Let user know when it goes wrong
				ParseSales parser;
				try {
					parser = new ParseSales(file);
					store.profit(parser.parseResults(store.getInventory()));
					updateItemQuantityTable();
					storeCapLabel.setText(store.toString());
					System.out.println(store.getCapital());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Sorry, there was an error with the file you selected. See error details below \n\n" + e);
				} catch (CSVFormatException e) {
					JOptionPane.showMessageDialog(null, "Sorry, there was an error with the format of the CSV you selected. See error details below \n\n" + e);
				}
			}
	    });
	    
	    // Load Manifest Button
	    btnLoadManifest = createButton("Load Manifest");
	    btnLoadManifest.setPreferredSize(new Dimension(300, 50));
	    
	    btnLoadManifest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// If item properties have not been loaded in, do not let the user continue, exit now
				if (!storeInitialised) {
					JOptionPane.showMessageDialog(null, "Can not perform this task.\nPlease click the 'Load Item Properties' button to initialise your store.");
					return;
				}
				
				String file = createFileChooser(true);
				
				// If user did not select a file, exit now
				if (file.equals("")) {
					return;
				}
				
				// Attempt to parse the CSV file, update the store and add them to the table
				// Let user know when it goes wrong
				ParseManifest parser;
				Manifest manifest;
				try {
					parser = new ParseManifest(file);
					manifest = parser.parseResults(store.getInventory());
					store.loss(manifest.getTotalCost());
					updateItemQuantityTable();
					storeCapLabel.setText(store.toString());
					System.out.println("Total Cost: " + manifest.getTotalCost());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Sorry, there was an error with the file you selected. See error details below \n\n" + e);
				} catch (CSVFormatException e) {
					JOptionPane.showMessageDialog(null, "Sorry, there was an error with the format of the CSV you selected. See error details below \n\n" + e);
				} catch (DeliveryException e) {
					JOptionPane.showMessageDialog(null, "Sorry, there was an error with stores delivery system. See error details below \n\n" + e);
				}

				System.out.println(store.getCapital());
			}
	    });
	    
	    // Export Manifest Button
	    btnGenManifest = createButton("Export Manifest");
	    btnGenManifest.setPreferredSize(new Dimension(300, 50));
	    
	    
	    btnGenManifest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// If item properties have not been loaded in, do not let the user continue, exit now
				if (!storeInitialised) {
					JOptionPane.showMessageDialog(null, "Can not perform this task.\nPlease click the 'Load Item Properties' button to initialise your store.");
					return;
				}
				
				String file = createFileChooser(false);
				
				// If user did not select a file, exit now
				if (file.equals("")) {
					return;
				}
				
				// Attempt to export the CSV file, update the store and add them to the table
				// Let user know when it goes wrong
				Manifest manifest = new Manifest(store.getInventory());
				try {
					manifest.CalculateManifest();
					manifest.exportManifest(file);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Sorry, there was an error with the file you selected. See error details below \n\n" + e);
				} catch (DeliveryException e) {
					JOptionPane.showMessageDialog(null, "Sorry, there was an error with stores delivery system. See error details below \n\n" + e);
				}
			}
	    });
	    
	    // Create and format header labels
	    storeNameLabel = new JLabel(store.getName());
	    storeNameLabel.setFont(new Font("Serif", Font.PLAIN, 28));
	    storeCapLabel = new JLabel(store.toString());
	    storeCapLabel.setFont(new Font("Serif", Font.PLAIN, 28));
	    
	    // Create empty table
	    createTable();
	    layoutButtonPanel(); 
	    layoutLabelPanel();
	    
	    // Add panels to window
	    this.getContentPane().add(pnlLabel, BorderLayout.NORTH);
	    this.getContentPane().add(pnlBtn,   BorderLayout.SOUTH);

	    this.setVisible(true);
	}
	
	/**
	 * Updates the quantities in the table based on the current stock levels
	 */
	private void updateItemQuantityTable() {
		final int QUANTITY_COL = 1;
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Stock inv = store.getInventory();
		int tableHeight = inv.uniqueItems();
		
		// Loop through table updating values
		for (int y = 0; y < tableHeight; y++) {
	    	model.setValueAt(inv.currentQuantity(inv.getItemByIndex(y).getName()), y, QUANTITY_COL);
	    }
	}
	
	/**
	 * Adds all items and their properties to the table
	 */
	private void addItemsToTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int rowCount = model.getRowCount();
		
		for (int i = 0; i < rowCount; i++) {
			model.removeRow(0);
		}
		
		Stock inv = store.getInventory();
		int tableWidth = 7;
		int tableHeight = inv.uniqueItems();
		
		Object[] data = new Object[tableWidth];
	    
	    for (int y = 0; y < tableHeight; y++) {
	    	data[0] = inv.getItemByIndex(y).getName();
	    	data[1] = inv.currentQuantity(inv.getItemByIndex(y).getName());
	    	data[2] = inv.getItemByIndex(y).getCost();
	    	data[3] = inv.getItemByIndex(y).getSellPrice();
	    	data[4] = inv.getItemByIndex(y).getReorderPoint();
	    	data[5] = inv.getItemByIndex(y).getReorderAmount();
	    	
	    	if (inv.getItemByIndex(y).getRequiresTemperature()) {
	    		data[6] = inv.getItemByIndex(y).getTemperature();
	    	} else {
	    		data[6] = "";
	    	} 	
	    	
	    	model.addRow(data);
	    }
	}
	
	/**
	 * Creates an empty table with only the column headings
	 */
	private void createTable() {
		String[] columnNames = {"Item Name", "Item Quantity", "Manufacturing cost ($)", "Sell Price ($)", "Reorder Point", "Reorder Amount", "Temperature (C)"};
	    
	    table = new JTable(null, columnNames);
	    
	    // Sets cells to not be editable
	    DefaultTableModel tableModel = new DefaultTableModel(null, columnNames) {

	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }
	    };

	    table.setModel(tableModel);
	    
	    // Centers all data in the table
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    for (int i = 0; i < 7; i++) {
	    	table.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }
	    
	    // If table gets larger than the container it will be scrollable
	    tableScrollPane = new JScrollPane(table);
	    tableScrollPane.setBorder(new EmptyBorder(0, 10, 0, 10));
	    tableScrollPane.setBackground(Color.WHITE);
	    table.setFillsViewportHeight(true);
	    
	    this.getContentPane().add(tableScrollPane,BorderLayout.CENTER);
	}
	
	/**
	 * Creates a file chooser
	 * @param openDialog is true if opening a file, else it will show the save option
	 * @return the file location the user chose
	 */
	private String createFileChooser(boolean openDialog) {
		final JFileChooser fc =  new JFileChooser();
		
		int returnVal;
		
		if (openDialog) {
			returnVal =  fc.showOpenDialog(this);
		} else {
			returnVal =  fc.showSaveDialog(this);
		}
	    
	    
	    if (returnVal==JFileChooser.APPROVE_OPTION) {
	    	File  file =  fc.getSelectedFile();
	    	String filename = file.getAbsolutePath();
	    	return filename;
	    } else if (returnVal == JFileChooser.CANCEL_OPTION) {
	    	
	    }
	    
	    return "";
	}
	
	private JButton createButton(String str) {
		JButton jb = new JButton(str); 
		jb.addActionListener(this);
		return jb; 
	}
	
	private JPanel createPanel(Color c) {
		JPanel jp = new JPanel();
		jp.setBackground(c);
		return jp;
	}
	
	private void layoutButtonPanel() {
		GridBagLayout layout = new GridBagLayout();
	    pnlBtn.setLayout(layout);
	    
	    // Add components to grid
	    GridBagConstraints constraints = new GridBagConstraints(); 
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.anchor = GridBagConstraints.CENTER;
	    constraints.weightx = 100;
	    constraints.weighty = 100;	
	    
	    addToPanel(pnlBtn, btnLoadItem,  constraints, 0, 0, 1, 1); 
	    addToPanel(pnlBtn, btnLoadSales, constraints, 1, 0, 1, 1); 
	    addToPanel(pnlBtn, btnLoadManifest, constraints, 2, 0, 1, 1); 
	    addToPanel(pnlBtn, btnGenManifest, constraints, 3, 0, 1, 1); 	
	}
	
	private void layoutLabelPanel() {
		GridBagLayout layout = new GridBagLayout();
	    pnlLabel.setLayout(layout);
	    
	    // Add components to grid
	    GridBagConstraints constraints = new GridBagConstraints(); 
	    constraints.fill = GridBagConstraints.NONE;
	    constraints.anchor = GridBagConstraints.CENTER;
	    
	    addToPanel(pnlLabel, storeNameLabel, constraints, 0, 0, 1, 1);
	    addToPanel(pnlLabel, storeCapLabel, constraints, 2, 0, 1, 1);
	}
	
	private void addToPanel(JPanel jp, Component c, GridBagConstraints constraints, int x, int y, int w, int h) {  
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		jp.add(c, constraints);
	}

}
