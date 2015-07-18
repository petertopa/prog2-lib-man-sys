package lms.controller;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import lms.view.LibaryGrid;

public class GridCellController implements MouseInputListener {

	private String holdingID;
	private LibaryGrid gridDisplay;

	public GridCellController(String holdingID, LibaryGrid gridDisplay) {
		this.holdingID = holdingID;
		this.gridDisplay = gridDisplay;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		//calls method in ControllerActions class to delete a holding
			gridDisplay.getMainView().actions().rightClickDelete(holdingID);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

}
