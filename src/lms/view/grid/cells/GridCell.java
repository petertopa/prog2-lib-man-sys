package lms.view.grid.cells;

import java.awt.Color;

import javax.swing.JPanel;

import lms.view.LibaryGrid;

@SuppressWarnings("serial")
public abstract class GridCell extends JPanel{
	
	private LibaryGrid gridDisplay;

	//grid cell only need white background
	public GridCell(LibaryGrid gridDisplay) {
		this.setBackground(Color.WHITE);
		this.gridDisplay = gridDisplay;
		
	}
}
