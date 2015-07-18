package lms.view.formatCheck;

public class StringThree {
	
	//checks string to make sure it has at least 3 characters
	public boolean stringCheck(String s){
		
		if (s.length() >= 3)
		return true;
		else
			return false;
	}

}
