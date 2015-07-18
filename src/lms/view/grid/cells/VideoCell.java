package lms.view.grid.cells;

import java.awt.Color;

import javax.swing.BorderFactory;

import lms.model.Holding;
import lms.view.LibaryGrid;

public class VideoCell extends HoldingCell{
	
	private LibaryGrid gridDisplay;
	
	public VideoCell(String string, LibaryGrid gridDisplay){
		
		super(string,gridDisplay);
		this.setBorder((BorderFactory.createLineBorder(new Color(252,126,126),3)));
	}

}
