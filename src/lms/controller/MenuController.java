package lms.controller;

import lms.view.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import lms.model.Book;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import lms.model.Holding;
import lms.model.LibraryCollection;
import lms.model.Video;
import lms.model.facade.LMSModel;
import lms.view.MainView;
import lms.view.StatusBar;
import lms.view.ToolBar;
import lms.view.formatCheck.IsInteger;
import lms.view.grid.cells.BookCell;
import lms.view.grid.cells.GridCell;
import lms.view.grid.cells.HoldingCell;
import lms.view.grid.cells.VideoCell;
import lms.view.tester.Tester;

public class MenuController implements ActionListener {

	private Menu menu;
	private LMSModel model;
	private String buttonPushed;
	private ControllerActions actions;

	public MenuController(Menu menu) {
		this.menu = menu;
		model = menu.getMainVew().getModel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buttonPushed = e.getActionCommand();

		// calls WINDOW CLOSING in the system to get it to call proper method in
		// FrameWindowListener class
		if (buttonPushed.equals("Exit")) {
			menu.getMainVew().dispatchEvent(
					new WindowEvent(menu.getMainVew(),
							WindowEvent.WINDOW_CLOSING));
		}

		// Calls relevent methods in the ControllerActions class

		if (buttonPushed.equals("Initialise Library")) {
			menu.getMainVew().actions().collection();
		}

		if (buttonPushed.equals("Reset Library")) {
			menu.getMainVew().actions().reset();
		}

		if (buttonPushed.equals("Add Book")) {
			menu.getMainVew().actions().bookAdd();
		}
		if (buttonPushed.equals("Add Video")) {
			menu.getMainVew().actions().videoAdd();
		}
		if (buttonPushed.equals("Delete Book")) {
			menu.getMainVew().actions().bookDelete();
		}
		if (buttonPushed.equals("Delete Video")) {
			menu.getMainVew().actions().videoDelete();
		}
		if (buttonPushed.equals("About")) {
			menu.getMainVew().actions().about();
		}

	}
}
