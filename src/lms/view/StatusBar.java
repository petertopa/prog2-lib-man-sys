package lms.view;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class StatusBar extends JPanel {

	private JLabel statusLabel;
	private String code;
	private int bookCount;
	private int videoCount;
	private MainView mainView;

	public StatusBar(MainView mainView) {

		this.mainView = mainView;
		addInfo();
	}
	
	//add empty info to status bar
	public void addInfo(){
		statusLabel = new JLabel("Collection Code: [" + code
				+ "] | Total Books: [" + bookCount + "] | Total Videoa: ["
				+ videoCount + "]");
		add(statusLabel);
	}

	//updates the statusbar with library information
	public void fillInfo(String code, int bookCount, int videoCount) {
		removeAll();
		this.code = code;
		this.bookCount = bookCount;
		this.videoCount = videoCount;
		addInfo();

	}

}
