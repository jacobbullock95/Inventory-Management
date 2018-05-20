package gui;

import java.awt.BorderLayout;
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
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import store.Store;
import truck.DeliveryException;
import truck.Manifest;

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

	public Window(String title) throws HeadlessException {
		super(title);
	}

	@Override
	public void run() {
		createWindow();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		
		String storeName = "SuperMart";
		int initialCapital = 100000;
		store = Store.getInstance();
		store.setName(storeName);
		store.setCapital(initialCapital);
		
		System.out.println("Starting Capital: " + store.getCapital());
		
		JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new Window("Hello"));

	}
	
	private void createWindow() {
		setSize(WIDTH, HEIGHT);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());

	    pnlLabel = createPanel(Color.WHITE);
	    pnlLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
	    pnlBtn = createPanel(Color.WHITE);
	    pnlBtn.setBorder(new EmptyBorder(10, 10, 10, 10));
	    
	    btnLoadItem = createButton("Load Item Properties");
	    btnLoadItem.setPreferredSize(new Dimension(300, 50));
	    
	    btnLoadItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				String file = createFileChooser(true);
				ParseItems parser = new ParseItems(file);
				parser.parseResults(store.getInventory()); 
				addItemsToTable();
			}
	    });
	    
	    btnLoadSales = createButton("Load Sales Log");
	    btnLoadSales.setPreferredSize(new Dimension(300, 50));
	    
	    btnLoadSales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String file = createFileChooser(true);
				ParseSales parser = new ParseSales(file);
				store.profit(parser.parseResults(store.getInventory()));
				updateItemQuantityTable();
				storeCapLabel.setText("Current Capital: $" + Double.toString(store.getCapital()));
				
				System.out.println(store.getCapital());
			}
	    });
	    
	    btnLoadManifest = createButton("Load Manifest");
	    btnLoadManifest.setPreferredSize(new Dimension(300, 50));
	    
	    btnLoadManifest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String file = createFileChooser(true);
				ParseManifest parser = new ParseManifest(file);
				
				Manifest manifest;
				try {
					manifest = parser.parseResults(store.getInventory());
					store.loss(manifest.getTotalCost());
					updateItemQuantityTable();
					storeCapLabel.setText("Current Capital: $" + Double.toString(store.getCapital()));
					System.out.println("Total Cost: " + manifest.getTotalCost());
					
				} catch (DeliveryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				System.out.println(store.getCapital());
			}
	    });
	    
	    btnGenManifest = createButton("Export Manifest");
	    btnGenManifest.setPreferredSize(new Dimension(300, 50));
	    
	    
	    btnGenManifest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String file = createFileChooser(false);
				Manifest manifest = new Manifest(store.getInventory());
				
				try {
					manifest.CalculateManifest();
				} catch (DeliveryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				manifest.exportManifest(file);
			}
	    });
	    
	    storeNameLabel = new JLabel(store.getName());
	    storeNameLabel.setFont(new Font("Serif", Font.PLAIN, 28));
	    storeCapLabel = new JLabel("Current Capital: $" + Double.toString(store.getCapital()));
	    storeCapLabel.setFont(new Font("Serif", Font.PLAIN, 28));
	    
	    createTable();
	    layoutButtonPanel(); 
	    layoutLabelPanel();
	    
	    this.getContentPane().add(pnlLabel, BorderLayout.NORTH);
	    this.getContentPane().add(pnlBtn,   BorderLayout.SOUTH);

	    //repaint(); 
	    this.setVisible(true);
	}
	
	private void updateItemQuantityTable() {
		final int QUANTITY_COL = 1;
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Stock inv = store.getInventory();
		int tableWidth = 7;
		int tableHeight = inv.uniqueItems();
		int rowCount = model.getRowCount();
		
		for (int y = 0; y < tableHeight; y++) {
	    	model.setValueAt(inv.currentQuantity(inv.getItemByIndex(y).getName()), y, QUANTITY_COL);
	    }
	}
	
	private void addItemsToTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int rowCount = model.getRowCount();
		
		for (int i = 0; i < rowCount; i++) {
			model.removeRow(i);
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
	
	private void createTable() {
		String[] columnNames = {"Item Name", "Item Quantity", "Manufacturing cost ($)", "Sell Price ($)", "Reorder Point", "Reorder Amount", "Temperature (C)"};
	    
	    table = new JTable(null, columnNames);
	    
	    DefaultTableModel tableModel = new DefaultTableModel(null, columnNames) {

	        @Override
	        public boolean isCellEditable(int row, int column) {
	           //all cells false
	           return false;
	        }
	    };

	    table.setModel(tableModel);
	    
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    for (int i = 0; i < 7; i++) {
	    	table.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }
	    
	    tableScrollPane = new JScrollPane(table);
	    tableScrollPane.setBorder(new EmptyBorder(0, 10, 0, 10));
	    tableScrollPane.setBackground(Color.WHITE);
	    table.setFillsViewportHeight(true);
	    
	    this.getContentPane().add(tableScrollPane,BorderLayout.CENTER);
	}
	
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
	    
	    // Defaults
	    constraints.fill = GridBagConstraints.NONE;
	    constraints.anchor = GridBagConstraints.CENTER;
	    constraints.weightx = 100;
	    constraints.weighty = 100;
	    
	    addToPanel(pnlBtn, btnLoadItem,  constraints, 0, 0, 2, 1); 
	    addToPanel(pnlBtn, btnLoadSales, constraints, 3, 0, 2, 1); 
	    addToPanel(pnlBtn, btnLoadManifest, constraints, 0, 2, 2, 1); 
	    addToPanel(pnlBtn, btnGenManifest, constraints, 3, 2, 2, 1); 	
	}
	
	private void layoutLabelPanel() {
		GridBagLayout layout = new GridBagLayout();
	    pnlLabel.setLayout(layout);
	    
	    // Add components to grid
	    GridBagConstraints constraints = new GridBagConstraints(); 
	    
	    // Defaults
	    constraints.fill = GridBagConstraints.NONE;
	    constraints.anchor = GridBagConstraints.CENTER;
	    constraints.weightx = 100;
	    constraints.weighty = 100;
	    
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
