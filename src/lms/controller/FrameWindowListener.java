package lms.controller;

import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import lms.view.MainView;

public class FrameWindowListener implements WindowListener {

	private MainView mainView;

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.exit(0);

	}

	@Override
	public void windowClosing(WindowEvent e) {

		// functionaliy for closing the application, prompts user to make sure
		// the want to exit
		int returnValue = JOptionPane
				.showConfirmDialog(mainView, "Are You Sure You Want To Exit?",
						"", JOptionPane.YES_NO_OPTION);
		if (returnValue == JOptionPane.YES_OPTION) {
			Window w = e.getWindow();
			w.dispose();
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
