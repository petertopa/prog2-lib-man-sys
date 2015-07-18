package lms.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import lms.controller.ToolBarBottonController;
import lms.controller.ToolBarSortController;
import lms.model.facade.LMSModel;

@SuppressWarnings("serial")
public class ToolBar extends JPanel {

	//adds an icons for use in toolbar buttons
	Icon collectIcon = new ImageIcon(getClass().getResource("/images/collection.png"));
	Icon addBookIcon = new ImageIcon(getClass().getResource("/images/book_add.png"));
	Icon addVideoIcon = new ImageIcon(getClass().getResource("/images/film_add.png"));
	Icon deleteBookIcon = new ImageIcon(getClass().getResource("/images/book_delete.png"));
	Icon deleteVideoIcon = new ImageIcon(getClass().getResource("/images/film_delete.png"));
	

	private JButton collection = new JButton(collectIcon);

	private JButton bookAdd = new JButton(addBookIcon);

	private JButton videoAdd = new JButton(addVideoIcon);

	private JButton bookDelete = new JButton(deleteBookIcon);

	private JButton videoDelete = new JButton(deleteVideoIcon);
	
	private ToolBarBottonController buttonControl;
	
	private ToolBarSortController sortControl;
	
	private JLabel sortLabel = new JLabel("SORT ORDER: ");
	
	MainView mainView;

	public ToolBar(MainView mainView) {
		
		
		//set action command for all button to identify in controller class
		collection.setActionCommand("Collection");
		bookAdd.setActionCommand("bookAdd");
		videoAdd.setActionCommand("videoAdd");
		bookDelete.setActionCommand("bookDelete");
		videoDelete.setActionCommand("videoDelete");
	
		
		
		this.mainView = mainView;
		buttonControl = new ToolBarBottonController(this);
		sortControl = new ToolBarSortController(this);
		
		//add action listener to all buttons
		collection.addActionListener(buttonControl);
		bookAdd.addActionListener(buttonControl);
		videoAdd.addActionListener(buttonControl);
		bookDelete.addActionListener(buttonControl);
		videoDelete.addActionListener(buttonControl);
		
		this.setLayout(new GridLayout(0,2));

		JPanel topLeftPanel = new JPanel();
		topLeftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(topLeftPanel);

		topLeftPanel.add(collection);
		topLeftPanel.add(bookAdd);
		topLeftPanel.add(videoAdd);
		topLeftPanel.add(bookDelete);
		topLeftPanel.add(videoDelete);
		
		
		
		sortLabel = new JLabel("SORT ORDER: ");
		JRadioButton none = new JRadioButton("NONE", true);
		JRadioButton code = new JRadioButton("CODE");
		JRadioButton type = new JRadioButton("TYPE");
		ButtonGroup group = new ButtonGroup();
		group.add(none);
		group.add(code);
		group.add(type);
		
		none.addActionListener(sortControl);
		code.addActionListener(sortControl);
		type.addActionListener(sortControl);
		
		
		JPanel topRightPanel = new JPanel();
		topRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(topRightPanel);
		
		topRightPanel.add(sortLabel);
		topRightPanel.add(none);
		topRightPanel.add(code);
		topRightPanel.add(type);
		
		
		
	}
	
	public MainView getMainVew(){
		return mainView;
	}

}
