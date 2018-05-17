package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import csv.ParseItems;
import csv.ParseManifest;
import csv.ParseSales;
import item.Item;
import item.Stock;
import store.Store;

public class Window extends JFrame implements ActionListener, Runnable {
	
	public static final int WIDTH = 300;
	public static final int HEIGHT = 200;
	private JButton btnLoadItem;
	private JButton btnLoadSales;
	private JButton btnLoadManifest;
	private JPanel pnlBtn;
	
	private static Store store;

//	public Window() throws HeadlessException {
//		// TODO Auto-generated constructor stub
//	}

//	public Window(GraphicsConfiguration gc) {
//		super(gc);
//		// TODO Auto-generated constructor stub
//	}

	public Window(String title) throws HeadlessException {
		super(title);
	}

//	public Window(String title, GraphicsConfiguration gc) {
//		super(title, gc);
//		// TODO Auto-generated constructor stub
//	}

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
		
		JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new Window("Hello"));

	}
	
	private void createWindow() {
		setSize(WIDTH, HEIGHT);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
	    
	    
	    
//	    pnlDisplay = createPanel(Color.WHITE);
//	    pnlTwo = createPanel(Color.LIGHT_GRAY);
	    pnlBtn = createPanel(Color.LIGHT_GRAY);
//	    pnlFour = createPanel(Color.LIGHT_GRAY);
//	    pnlFive = createPanel(Color.LIGHT_GRAY);
//	    
	    btnLoadItem = createButton("Load Item Properties");
	    
	    btnLoadItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				String file = createFileChooser();
				ParseItems parser = new ParseItems(file);
				parser.parseResults(store.getInventory()); 
			}
	    });
	    
	    btnLoadSales = createButton("Load Sales Log");
	    
	    btnLoadSales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String file = createFileChooser();
				ParseSales parser = new ParseSales(file);
				parser.parseResults(store.getInventory());
			}
	    });
	    
	    btnLoadManifest = createButton("Load Manifest");
	    
	    btnLoadManifest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String file = createFileChooser();
				ParseManifest parser = new ParseManifest(file);
				parser.parseResults(store.getInventory());
			}
	    });
	    
	    
//	    btnUnload = createButton("Unload");
//	    btnFind = createButton("Find");
//	    btnSwitch = createButton("Switch");
//	    
//	    areDisplay = createTextArea();
//	    
//	    pnlDisplay.setLayout(new BorderLayout());
//	    pnlDisplay.add(areDisplay, BorderLayout.CENTER);
//	 
	    layoutButtonPanel(); 
//	    
//	    this.getContentPane().add(pnlDisplay,BorderLayout.CENTER);
//	    this.getContentPane().add(pnlTwo,BorderLayout.NORTH);
	    this.getContentPane().add(pnlBtn,BorderLayout.SOUTH);
//	    this.getContentPane().add(pnlFour,BorderLayout.EAST);
//	    this.getContentPane().add(pnlFive,BorderLayout.WEST);
	    //repaint(); 
	    this.setVisible(true);
	}
	
	public String createFileChooser() {
		final JFileChooser fc =  new JFileChooser();
	    int returnVal =  fc.showOpenDialog(this);
	    
	    
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
//	    addToPanel(pnlBtn, btnSwitch,constraints,3,2,2,1); 	
	}
	
	private void addToPanel(JPanel jp, Component c, GridBagConstraints constraints, int x, int y, int w, int h) {  
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		jp.add(c, constraints);
	}

}
