package lms.view.grid.cells;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.ScrollPane;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import lms.controller.GridCellController;
import lms.view.LibaryGrid;

public class HoldingCell extends GridCell {

	private String holdingInfo;

	private JLabel code;
	private JLabel title;
	private JLabel loanFee;
	private JLabel loanPeriod;
	private GridCellController gridControl;
	
	private JPopupMenu menuPopup;
	private JMenuItem delete;

	private JPanel panel;

	private ScrollPane scroll;
	
	private LibaryGrid gridDisplay;

	public HoldingCell(String holdingInfo, LibaryGrid gridDisplay) {

		super(gridDisplay);
		

		this.holdingInfo = holdingInfo;

		setLayout(new GridLayout(1, 1));

		String[] split = holdingInfo.split(":");

		scroll = new ScrollPane();
		add(scroll);

		panel = new JPanel();
		scroll.add(panel);
		panel.setBackground(new Color(209,209,209));
		panel.setLayout(new GridLayout(5, 1));
		panel.setBorder((BorderFactory.createLineBorder(new Color(209,209,209),15)));

		code = new JLabel("Holding Code: " + split[0]);
		title = new JLabel("Title: " + split[1]);
		loanFee = new JLabel("Fee: " + split[2]);
		loanPeriod = new JLabel("Loand Period: " + split[3]);

		
		gridControl = new GridCellController(split[0],gridDisplay);
		panel.addMouseListener(gridControl);
		
		
		panel.add(code);
		panel.add(title);
		panel.add(loanFee);
		panel.add(loanPeriod);

	}

}
