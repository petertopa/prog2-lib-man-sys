/*
 * Controller actions class is used to perform various functions within the 
 * main GUI. It is resposible for most majoy GUI functions like adding and 
 * deleting holdings, error handling and input validation 
 */

package lms.controller;

import java.awt.Dimension;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lms.model.Book;
import lms.model.Holding;
import lms.model.LibraryCollection;
import lms.model.Video;
import lms.model.facade.LMSModel;
import lms.view.MainView;
import lms.view.Menu;
import lms.view.ToolBar;
import lms.view.formatCheck.IsInteger;
import lms.view.formatCheck.StringThree;
import lms.view.grid.cells.BookCell;
import lms.view.grid.cells.GridCell;
import lms.view.grid.cells.HoldingCell;
import lms.view.grid.cells.VideoCell;
import lms.view.tester.Tester;

public class ControllerActions {

	private LMSModel model;
	private MainView mainView;
	private Menu menu;
	private ToolBar toolBar;
	private IsInteger checkInteger = new IsInteger();
	private StringThree checkString = new StringThree();
	private Book tempBook;
	private Video tempVideo;
	private String sort = "NONE";
	private JTextArea textArea;
	private JScrollPane jScrollPane1;
	private JLabel jLabel1;

	// Class is contructed by Main View which passes itself along
	// with the LMS Model
	public ControllerActions(LMSModel model, MainView mainView) {
		this.model = model;
		this.mainView = mainView;
	}

	// Used to set the sort order of the grid
	public void setSortOrder(String sort) {
		this.sort = sort;
		// once the sort order is set, resets the the grid to
		// update
		processHoldings(sort);
	}

	// Functionality for initialising a libray collection
	public void collection() {

		// Used in dialog pane to into collecting code and name
		JTextField code = new JTextField();
		JTextField name = new JTextField();

		// Array of Ojects used to add multiple questions to the dialog box
		Object[] questions = { "Library Collection Code", code,
				"Library Collection Name", name, };

		// stores the output of dialog box to be used later for checking user
		// input, also adds the questions array to the dialog box
		int option = JOptionPane.showConfirmDialog(mainView, questions,
				"Create New Library", JOptionPane.OK_CANCEL_OPTION);

		// cheking what option was pushed by the user and that the user enter
		// text for collection code and name
		if (option == JOptionPane.OK_OPTION && !code.getText().equals("")
				&& !name.getText().equals("")) {
			String libCode = code.getText();
			String libName = name.getText();

			// initialises a new library collection with the user input
			model.addCollection(new LibraryCollection(libCode, libName));

			// initialises the tester class to add various holdings to the
			// collection
			Tester tester = new Tester();
			tester.setupTestData(model);

			// updates the status bar by calling helper method
			refreshStatusBar();

			// checks that the collection has holdings
			if (model.getAllHoldings() != null) {

				// calls helper method to sort holdings and add appropiate book
				// or video cells
				processHoldings(sort);
			}
		} else {
			// if user has not enterest any text into one or both of the dialog
			// fields, system produces an alert message
			JOptionPane.showConfirmDialog(mainView,
					"You Must Enter Text In All Feilds!", "",
					JOptionPane.OK_CANCEL_OPTION);
		}
	}

	// functionality for adding holding to the collection
	public void bookAdd() {

		// checks that there is a collection
		if (model.getCollection() != null) {

			// used for a dialog to collect holding name and ID
			JTextField holdingID = new JTextField();
			JTextField holdingName = new JTextField();

			// stores the questions in an Object array to be passed to dialog
			Object[] questions = { "Holding ID (Must Be 7 digits) : ",
					holdingID, "Holding Name (at least 3 characters): ",
					holdingName, };

			// stores the output of dialog box to be used later for checking
			// user input, also adds the questions array to the dialog box
			int option = JOptionPane.showConfirmDialog(mainView, questions,
					"Add Book", JOptionPane.OK_CANCEL_OPTION);

			// checks user input for null inputs and OK button being pushed
			if (option == JOptionPane.OK_OPTION
					&& !holdingID.getText().equals("")
					&& !holdingName.getText().equals("")) {

				// calls holdingIDCheck from class IsInteger to check the
				// holding ID was an integer and was 7 characters long
				if (checkInteger.holdingIDCheck(holdingID.getText())) {
					// calls stringCheck from class StringThree to check that
					// user entered a minimum of 3 charcaters as a holding name
					if (checkString.stringCheck(holdingName.getText())) {

						// converts user input for holding ID into Integer and
						// stores it in bookCode
						int bookCode = Integer.parseInt(holdingID.getText());
						// Stores user input for holdingName in bookName
						String bookName = holdingName.getText();

						// creats instance of a book and stores it in tempBook
						tempBook = new Book(bookCode, bookName);

						// calls addHolding to add tempBook to collection
						model.addHolding(tempBook);

						// updates the grid with helper method
						processHoldings(sort);
					} else {
						// message dialog if name was not 3 characters or more
						JOptionPane.showConfirmDialog(mainView,
								"Book Name must be at least 3 characters!", "",
								JOptionPane.OK_CANCEL_OPTION);
					}

				} else {
					// message dialog if ID was not 7 numbers
					JOptionPane.showConfirmDialog(mainView,
							"Book ID must be 7 numbers!", "",
							JOptionPane.OK_CANCEL_OPTION);
				}

			} else {
				// message dialog if text wasnt entered for either or both
				// fields
				JOptionPane.showConfirmDialog(mainView,
						"You Must Enter Text In All Feilds!", "",
						JOptionPane.OK_CANCEL_OPTION);
			}
		} else {
			// message dialog if user tries to add a book before initialising
			// collection
			JOptionPane.showConfirmDialog(mainView,
					"You must add a Library Collection before adding books!",
					"", JOptionPane.OK_CANCEL_OPTION);
		}

	}

	public void about() {

		// Help menu string
		String text = "Wlcome to the LMS: Please start by initialising a Library, this"
				+ "can be done by either clicking the first icon in the toolbar or clicking file "
				+ "the initialise library. From there you can add and remove holdings (Books or Videos) "
				+ "but make sure to pay atentions to the format you need to enter :)";

		// text area to add the help string
		textArea = new JTextArea(text, 20, 40);

		textArea.setColumns(40);
		textArea.setLineWrap(true);
		textArea.setRows(5);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);

		// adding scroll bar functionality to the text area
		jScrollPane1 = new JScrollPane(textArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// show help menu with text area
		JOptionPane.showMessageDialog(mainView, jScrollPane1, "Help Menu",
				JOptionPane.OK_CANCEL_OPTION);
	}

	public void videoAdd() {

		// checks that there is a collection
		if (model.getCollection() != null) {

			// used for a dialog to collect holding name and ID and fee
			JTextField holdingID = new JTextField();
			JTextField holdingName = new JTextField();
			JComboBox holdingFee = new JComboBox();
			holdingFee.addItem("$4");
			holdingFee.addItem("$6");

			// stores the questions in an Object array to be passed to dialog
			Object[] questions = { "Holding ID (Must Be 7 digits) : ",
					holdingID, "Holding Name (at least 3 characters): ",
					holdingName, "Video Fee :", holdingFee };

			// stores the output of dialog box to be used later for checking
			// user input, also adds the questions array to the dialog box
			int option = JOptionPane.showConfirmDialog(mainView, questions,
					"Add Video", JOptionPane.OK_CANCEL_OPTION);

			// checks user input for null inputs and OK button being pushed
			if (option == JOptionPane.OK_OPTION
					&& !holdingID.getText().equals("")
					&& !holdingName.getText().equals("")) {

				// calls holdingIDCheck from class IsInteger to check the
				// holding ID was an integer and was 7 characters long
				if (checkInteger.holdingIDCheck(holdingID.getText())) {
					// calls stringCheck from class StringThree to check that
					// user entered a minimum of 3 charcaters as a holding name
					if (checkString.stringCheck(holdingName.getText())) {

						// converts user input for holding ID into Integer and
						// stores it in videoCode
						int videoCode = Integer.parseInt(holdingID.getText());
						// Stores user input for holdingName in videoName
						String videoName = holdingName.getText();

						// used to check user input for video fee
						int videoFee;

						// gets user input for video fee and stores it in
						// videoFee
						if (holdingFee.getSelectedItem().equals("$4"))
							videoFee = 4;
						else
							videoFee = 6;

						// creates new video and stores it in tempVIdeo
						tempVideo = new Video(videoCode, videoName, videoFee);

						// add the holding to the collection
						model.addHolding(tempVideo);

						// updates the grid for display
						processHoldings(sort);

					} else {
						// message dialog if name was not 3 characters or more
						JOptionPane.showConfirmDialog(mainView,
								"Video Name must be at least 3 characters!",
								"", JOptionPane.OK_CANCEL_OPTION);
					}
				} else {
					// message dialog if ID was not 7 numbers
					JOptionPane.showConfirmDialog(mainView,
							"Video ID must be 7 numbers!", "",
							JOptionPane.OK_CANCEL_OPTION);
				}

			} else {
				// message dialog if text wasnt entered for either or both
				// fields
				JOptionPane.showConfirmDialog(mainView,
						"You Must Enter Text In All Feilds!", "",
						JOptionPane.OK_CANCEL_OPTION);
			}

		} else {
			// message dialog if user tries to add a book before initialising
			// collection
			JOptionPane.showConfirmDialog(mainView,
					"You must add a Library Collection before adding videos!",
					"", JOptionPane.OK_CANCEL_OPTION);
		}

	}

	public void bookDelete() {

		// checks the collection has been inititiated
		if (model.countBooks() != 0) {
			// adds a combo box to display all the books in the library so user
			// can select which one to delete
			JComboBox books = new JComboBox();

			// holding array with all holding in the collection
			Holding[] temp = model.getAllHoldings();

			// finds all the holdings in the collection that are books and adds
			// an option to the combo box with the holdings ID
			for (int i = 0; i < temp.length; i++) {
				if (temp[i].getClass().getSimpleName().equals("Book")) {
					books.addItem(Integer.toString(temp[i].getCode()));
				}
			}

			// shows dialog with all the combo box and asks user to delete a
			// specified holding
			int option = JOptionPane.showConfirmDialog(mainView, books,
					"Choose Book to delete", JOptionPane.OK_CANCEL_OPTION);

			// checks if user has selected ok
			if (option == JOptionPane.OK_OPTION) {
				// gets the selected holding and stores it in the String
				// bookToDelete
				String bookToDelete = (String) books.getSelectedItem();

				// gets the holdings ID and stores it in integer
				int holdingIDDelete = Integer.parseInt(bookToDelete);

				// calls model to remove the holding
				model.removeHolding(holdingIDDelete);

				// updates the grid
				processHoldings(sort);
				// updates the statusbar
				refreshStatusBar();

			}

		} else {
			// if no books were found in the collection, shows dialog box to
			// prompt user to add some
			JOptionPane.showConfirmDialog(mainView,
					"No Books Found! Try Addoing Some.", "",
					JOptionPane.OK_CANCEL_OPTION);
		}

	}

	public void videoDelete() {

		// checks the collection has been inititiated
		if (model.countVideos() != 0) {
			// adds a combo box to display all the videos in the library so user
			// can select which one to delete
			JComboBox videos = new JComboBox();

			// holding array with all holding in the collection
			Holding[] temp = model.getAllHoldings();

			// finds all the holdings in the collection that are videos and adds
			// an option to the combo box with the holdings ID
			for (int i = 0; i < temp.length; i++) {
				if (temp[i].getClass().getSimpleName().equals("Video")) {
					videos.addItem(Integer.toString(temp[i].getCode()));
				}
			}

			// shows dialog with all the combo box and asks user to delete a
			// specified holding
			int option = JOptionPane.showConfirmDialog(mainView, videos,
					"Choose Video to delete", JOptionPane.OK_CANCEL_OPTION);

			// checks if user has selected ok
			if (option == JOptionPane.OK_OPTION) {
				// gets the selected holding and stores it in the String
				// videoToDelete
				String videoToDelete = (String) videos.getSelectedItem();

				// gets the holdings ID and stores it in integer
				int holdingIDDelete = Integer.parseInt(videoToDelete);

				// calls model to remove the holding
				model.removeHolding(holdingIDDelete);

				// updates the grid
				processHoldings(sort);
				// updates the statusbar
				refreshStatusBar();

			}

		} else {
			// if no videos were found in the collection, shows dialog box to
			// prompt user to add some
			JOptionPane.showConfirmDialog(mainView,
					"No Videos Found! Try Addoing Some.", "",
					JOptionPane.OK_CANCEL_OPTION);
		}

	}

	public void reset() {

		try {
			// checks that there are holdings in the collection
			if (model.getAllHoldings() != null) {
				// confirm dialog to make sure user would like to reset
				int option = JOptionPane
						.showConfirmDialog(
								mainView,
								"This Will Delete All Holdings In Your Collection. Are You Sure?",
								"", JOptionPane.YES_NO_CANCEL_OPTION);
				if (option == JOptionPane.YES_OPTION) {

					// temp array to store curent holdings
					Holding[] temp = model.getAllHoldings();

					// iterates through holdings and removes them from the
					// library collection
					for (int i = 0; i < temp.length; i++) {
						model.removeHolding(temp[i].getCode());
					}
					// updates grid and statusbar
					processHoldings(sort);
					refreshStatusBar();
				}
			} else {
				// shows dialog if user tries to rest with no holdings
				JOptionPane
						.showConfirmDialog(
								mainView,
								"You must add holding to the Library Collection before resetting!",
								"", JOptionPane.OK_CANCEL_OPTION);
			}
		} catch (NullPointerException e) {
			// catchs null pointer if user tries to reset with no collection
			JOptionPane.showConfirmDialog(mainView,
					"You must add a Library Collection before resetting!", "",
					JOptionPane.OK_CANCEL_OPTION);
		}

	}

	public void processHoldings(String sort) {

		try {

			// new holdings array
			Holding[] temp = new Holding[model.getAllHoldings().length];

			if (sort == "NONE") {

				// if sort is set to none, stores all the holdings in their
				// original order in temp array
				temp = model.getAllHoldings();
			}

			if (sort == "CODE") {

				// new holding array for sorting
				Holding[] holdings = model.getAllHoldings();

				// new TreeMap used for sorting holdings
				Map<Integer, Holding> codeSort = new TreeMap<Integer, Holding>();

				// adds holding to TreeMap to sort by holdingID
				for (int i = 0; i < holdings.length; i++) {
					codeSort.put(holdings[i].getCode(), holdings[i]);
				}

				// adds the holdings to temp array in correct order
				codeSort.values().toArray(temp);
			}

			if (sort == "TYPE") {
				// new holding array for sorting
				Holding[] holdings = model.getAllHoldings();
				// new TreeMap used for sorting holdings
				Map<String, Holding> codeSort = new TreeMap<String, Holding>();

				// adds holding to TreeMap to sort by holdingID
				for (int i = 0; i < holdings.length; i++) {
					codeSort.put(holdings[i].getClass().getSimpleName() + i,
							holdings[i]);
				}
				// adds the holdings to temp array in correct order
				codeSort.values().toArray(temp);

			}

			if (model.getAllHoldings() != null) {

				// creats new gridcell array for adding books and videos
				GridCell[] cells = new HoldingCell[temp.length];

				// extract data from holdings and create appropriate grid
				// cells to be later passed to library grid for display

				for (int i = 0; i < temp.length; i++) {

					if (temp[i].getClass().getSimpleName().equals("Book"))
						cells[i] = new BookCell(temp[i].toString(),
								mainView.getGridDisplay());

					else if (temp[i].getClass().getSimpleName().equals("Video"))
						cells[i] = new VideoCell(temp[i].toString(),
								mainView.getGridDisplay());

				}

				// pass array of gridcells to the libraygrid for display

				mainView.updateGridDisplay(cells);
			} else {
				// if there ar no holding, call empty grid cell
				mainView.updateEmptyGridDisplay();
			}
			refreshStatusBar();
		} catch (NullPointerException e) {
			mainView.updateEmptyGridDisplay();
		}

	}

	public void refreshStatusBar() {
		// passes libray information to update the status bar
		mainView.updateStatusBar(model.getCollectionCode(), model.countBooks(),
				model.countVideos());
	}

	public void rightClickDelete(String holdingID) {

		// functionality for deleting holding by clicking on them, impimented in
		// the gridCellController class
		JLabel code = new JLabel("Holding ID: " + holdingID);
		int option = JOptionPane.showConfirmDialog(mainView, code,
				"Delete This Holding?", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			model.removeHolding(Integer.parseInt(holdingID));
			processHoldings(sort);
			refreshStatusBar();
		}
	}

}
