package lms.view.grid.cells;

import java.awt.Color;

import lms.view.LibaryGrid;

public class EmptyCell extends GridCell {
	
	private LibaryGrid gridDisplay;
	
	public EmptyCell(LibaryGrid gridDisplay) {
		super(gridDisplay);
		this.setBackground(Color.WHITE);

	}

	
	public void getString(){
		System.out.println("Got to emptycell");
	}
	
}
