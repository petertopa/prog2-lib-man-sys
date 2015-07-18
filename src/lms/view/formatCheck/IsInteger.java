package lms.view.formatCheck;

public class IsInteger {

	// checks string to make sure its an integer and that is has 7 chararcters
	public boolean holdingIDCheck(String s) {

		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		if (s.length() == 7)
			return true;
		else
			return false;
	}

}
