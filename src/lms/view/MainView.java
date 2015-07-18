package lms.view;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import lms.controller.ControllerActions;
import lms.controller.FrameWindowListener;
import lms.model.facade.LMSModel;
import lms.view.grid.cells.GridCell;

public class MainView extends JFrame {

	private LMSModel model;
	private ToolBar toolBar;// sub-view
	private LibaryGrid gridDisplay;// sub-view
	private StatusBar statusBar;
	private Menu menu;
	private ControllerActions actions;

	public MainView(LMSModel model) throws HeadlessException {
		super("LMS");
		this.setSize(800, 600);

		this.model = model;

		this.setLayout(new BorderLayout());

		// initialise sub views
		toolBar = new ToolBar(this);
		gridDisplay = new LibaryGrid(this);
		statusBar = new StatusBar(this);
		menu = new Menu(this);

		// display subviews
		add(toolBar, BorderLayout.NORTH);
		add(gridDisplay, BorderLayout.CENTER);
		add(statusBar, BorderLayout.SOUTH);

		this.setJMenuBar(menu);
		
		actions = new ControllerActions(model, this);

	}


	public ToolBar getToolBar() {
		return toolBar;
	}

	public LibaryGrid getGridDisplay() {
		return gridDisplay;
	}

	public StatusBar getStatusBar() {
		return statusBar;
	}

	public LMSModel getModel() {
		return model;
	}
	
	public ControllerActions actions(){
		return actions;
	}

	public void updateGridDisplay(GridCell[] cells) {
		gridDisplay.update(cells);
		this.validate();
	}
	
	public void updateEmptyGridDisplay (){
		gridDisplay.updateEmpty();
		this.validate();
	}

	public void updateStatusBar(String code, int bookCount, int videoCount) {
		statusBar.fillInfo(code, bookCount, videoCount);
		this.validate();
	}

}
