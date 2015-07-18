package lms.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import lms.model.facade.LMSModel;
import lms.view.grid.cells.EmptyCell;
import lms.view.grid.cells.GridCell;

@SuppressWarnings("serial")
public class LibaryGrid extends JPanel {

	private MainView mainView;

	private LMSModel model;

	GridCell[] cells;

	public LibaryGrid(MainView mainView) {

		this.mainView = mainView;
		this.setBackground(Color.WHITE);
	}

	public void update(GridCell[] cells) {

		this.cells = cells;

		// reset library grid
		removeAll();

		// used for calculating empty cell requirments
		float holdingCount = cells.length;

		if (holdingCount >= 4) {

			// gets how many collums there are in the grid
			double collCount = holdingCount / 4;

			// rounds the collum cound up
			double collCountRounded = Math.ceil(collCount);

			// calculates how many empty cells are need to fill all the space.
			double emptyCells = (collCountRounded * 4) - cells.length;

			// array of grid cells to add to grid
			GridCell[] empty = new EmptyCell[(int) emptyCells];

			setLayout(new GridLayout((int) collCountRounded, 4, 5, 5));

			// helper method for adding cells to grid
			addCells();

			// add empty cells to GridCell array empty
			for (int i = 0; i < ((int) emptyCells); i++) {
				empty[i] = new EmptyCell(this);
			}

			// adds empty gridcells from the array to the grid
			for (int i = 0; i < ((int) emptyCells); i++) {
				this.add(empty[i]);
			}

		} else if (holdingCount == 3) {
			//layout for 3 grid cells
			setLayout(new GridLayout(1, 3, 5, 5));

			addCells();

		} else if (holdingCount == 2) {
			//layout for 2 grid cells
			setLayout(new GridLayout(1, 2, 5, 5));

			addCells();

		} else if (holdingCount == 1) {
			//layout for 1 grid cells
			setLayout(new GridLayout(1, 1, 5, 5));

			addCells();
		}
	}

	//helper method to add cells to grid
	public void addCells() {
		for (int i = 0; i < cells.length; i++) {
			this.add(cells[i]);
		}
	}

	//method to empty the grid when resetting
	public void updateEmpty() {
		removeAll();
		this.setBackground(Color.WHITE);
	}

	public MainView getMainView() {
		return mainView;
	}

}
