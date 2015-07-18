package lms.view.grid.cells;

import java.awt.Color;

import javax.swing.BorderFactory;

import lms.model.Holding;
import lms.view.LibaryGrid;

public class BookCell extends HoldingCell{
	
	private LibaryGrid gridDisplay;
	
	public BookCell(String string, LibaryGrid gridDisplay){
		
		super(string,gridDisplay);
		this.setBorder((BorderFactory.createLineBorder(new Color(126,149,252),3)));
	}

}
