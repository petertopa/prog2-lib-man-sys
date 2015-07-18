package lms.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import lms.controller.MenuController;
import lms.controller.ToolBarBottonController;

@SuppressWarnings("serial")
public class Menu extends JMenuBar {

	private JMenu fileMenu;
	
	private JMenu holdingMenu;
	
	private JMenu helpMenu;

	private JMenuItem exitItem;
	
	private JMenuItem intiLibrary;
	
	private JMenuItem resetLibrary;
	
	private JMenuItem addBook;
	
	private JMenuItem addVideo;
	
	private JMenuItem deleteBook;
	
	private JMenuItem deleteVideo;
	
	private JMenuItem about;
	
	private MainView mainView;
	
	private MenuController controller;

	public Menu(MainView mainView) {
		
		this.mainView = mainView;
		
		controller = new MenuController(this);

		// create Menu
		fileMenu = new JMenu("File");
		holdingMenu = new JMenu("Holding");
		helpMenu = new JMenu("Help");

		fileMenu.setMnemonic(KeyEvent.VK_F);
		holdingMenu.setMnemonic(KeyEvent.VK_H);
		helpMenu.setMnemonic(KeyEvent.VK_P);

		
		
		//add menu items
		intiLibrary = new JMenuItem("Initialise Library", KeyEvent.VK_I);
		intiLibrary.setAccelerator(KeyStroke
				.getKeyStroke('I', InputEvent.ALT_MASK));
		
		resetLibrary = new JMenuItem("Reset Library", KeyEvent.VK_R);
		resetLibrary.setAccelerator(KeyStroke
				.getKeyStroke('R', InputEvent.ALT_MASK));
		
		addBook = new JMenuItem("Add Book", KeyEvent.VK_A);
		addBook.setAccelerator(KeyStroke
				.getKeyStroke('A', InputEvent.ALT_MASK));
		
		addVideo = new JMenuItem("Add Video", KeyEvent.VK_V);
		addVideo.setAccelerator(KeyStroke
				.getKeyStroke('V', InputEvent.ALT_MASK));
		
		deleteBook = new JMenuItem("Delete Book", KeyEvent.VK_D);
		deleteBook.setAccelerator(KeyStroke
				.getKeyStroke('D', InputEvent.ALT_MASK));
		
		deleteVideo = new JMenuItem("Delete Video", KeyEvent.VK_O);
		deleteVideo.setAccelerator(KeyStroke
				.getKeyStroke('O', InputEvent.ALT_MASK));
		

		exitItem = new JMenuItem("Exit", KeyEvent.VK_X);
		exitItem.setAccelerator(KeyStroke
				.getKeyStroke('X', InputEvent.ALT_MASK));
		
		
		about = new JMenuItem("About", KeyEvent.VK_A);
		about.setAccelerator(KeyStroke
				.getKeyStroke('H', InputEvent.ALT_MASK));
		
		
		
		
		

		//add action listener to menu items
		exitItem.addActionListener(controller);
		intiLibrary.addActionListener(controller);
		resetLibrary.addActionListener(controller);
		
		addBook.addActionListener(controller);
		addVideo.addActionListener(controller);
		deleteBook.addActionListener(controller);
		deleteVideo.addActionListener(controller);
		about.addActionListener(controller);
		
		
		
		

		// add MenuItems to the Menu
		fileMenu.add(intiLibrary);
		fileMenu.add(resetLibrary);
		fileMenu.add(exitItem);
		
		holdingMenu.add(addBook);
		holdingMenu.add(addVideo);
		holdingMenu.add(deleteBook);
		holdingMenu.add(deleteVideo);
		
		helpMenu.add(about);
		
		
		
		

		//add menu to MenuBar
		this.add(fileMenu);
		this.add(holdingMenu);
		this.add(helpMenu);		
		
		
	}
	
	public MainView getMainVew(){
		return mainView;
	}
}
