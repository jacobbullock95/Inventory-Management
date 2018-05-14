package gui;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Window extends JFrame implements ActionListener, Runnable {
	
	public static final int WIDTH = 300;
	public static final int HEIGHT = 200;

//	public Window() throws HeadlessException {
//		// TODO Auto-generated constructor stub
//	}

//	public Window(GraphicsConfiguration gc) {
//		super(gc);
//		// TODO Auto-generated constructor stub
//	}

	public Window(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

//	public Window(String title, GraphicsConfiguration gc) {
//		super(title, gc);
//		// TODO Auto-generated constructor stub
//	}

	@Override
	public void run() {
		createGUI();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new Window("Hello"));

	}
	
	private void createGUI() {
		setSize(WIDTH, HEIGHT);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
	    
//	    pnlDisplay = createPanel(Color.WHITE);
//	    pnlTwo = createPanel(Color.LIGHT_GRAY);
//	    pnlBtn = createPanel(Color.LIGHT_GRAY);
//	    pnlFour = createPanel(Color.LIGHT_GRAY);
//	    pnlFive = createPanel(Color.LIGHT_GRAY);
//	    
//	    btnLoad = createButton("Load");
//	    btnUnload = createButton("Unload");
//	    btnFind = createButton("Find");
//	    btnSwitch = createButton("Switch");
//	    
//	    areDisplay = createTextArea();
//	    
//	    pnlDisplay.setLayout(new BorderLayout());
//	    pnlDisplay.add(areDisplay, BorderLayout.CENTER);
//	 
//	    layoutButtonPanel(); 
//	    
//	    this.getContentPane().add(pnlDisplay,BorderLayout.CENTER);
//	    this.getContentPane().add(pnlTwo,BorderLayout.NORTH);
//	    this.getContentPane().add(pnlBtn,BorderLayout.SOUTH);
//	    this.getContentPane().add(pnlFour,BorderLayout.EAST);
//	    this.getContentPane().add(pnlFive,BorderLayout.WEST);
	    //repaint(); 
	    this.setVisible(true);
	}

}
