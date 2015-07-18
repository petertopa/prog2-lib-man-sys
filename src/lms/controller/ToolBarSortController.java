package lms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lms.view.ToolBar;

public class ToolBarSortController implements ActionListener {
	
	private ToolBar toolBar;

	public ToolBarSortController(ToolBar toolBar) {
		this.toolBar = toolBar;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String buttonPushed = e.getActionCommand();
		
		if(buttonPushed == "NONE"){
			toolBar.getMainVew().actions().setSortOrder("NONE");
		}
		
		if(buttonPushed == "CODE"){
			toolBar.getMainVew().actions().setSortOrder("CODE");
		}
		
		if(buttonPushed == "TYPE"){
			toolBar.getMainVew().actions().setSortOrder("TYPE");
		}

	}

}
