package lms.model.exception;

@SuppressWarnings("serial")
public class MultipleBorrowingException extends LMSException {
	
	
	@SuppressWarnings("unused")
	private String message;
	
	public MultipleBorrowingException(String message){
		super(message);
		this.message = message;
	}

}
