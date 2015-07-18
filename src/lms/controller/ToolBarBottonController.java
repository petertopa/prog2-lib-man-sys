package lms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class ToolBarBottonController implements ActionListener {

	private ToolBar toolBar;
	private String buttonPushed;

	public ToolBarBottonController(ToolBar toolBar) {
		this.toolBar = toolBar;
	}

	
	//Calls relevent methods in the ControllerActions class
	
	@Override
	public void actionPerformed(ActionEvent e) {

		buttonPushed = e.getActionCommand();

		if (buttonPushed == "Collection") {

			toolBar.getMainVew().actions().collection();
		}

		if (buttonPushed == "bookAdd") {

			toolBar.getMainVew().actions().bookAdd();
		}
		if (buttonPushed == "videoAdd") {

			toolBar.getMainVew().actions().videoAdd();

		}
		if (buttonPushed == "bookDelete") {

			toolBar.getMainVew().actions().bookDelete();
		}

		if (buttonPushed == "videoDelete") {

			toolBar.getMainVew().actions().videoDelete();
		}

	}
}
