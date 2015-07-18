package lms.model.exception;

@SuppressWarnings("serial")
public class InsufficientCreditException extends LMSException {
	
	@SuppressWarnings("unused")
	private String message;
	
	public InsufficientCreditException(String message){
		super(message);
		this.message  = message;
	}

}
