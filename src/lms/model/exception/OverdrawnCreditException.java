package lms.model.exception;

@SuppressWarnings("serial")
public class OverdrawnCreditException extends LMSException{
	
	@SuppressWarnings("unused")
	private String message;
	
	public OverdrawnCreditException(String message){
		super(message);
		this.message = message;
	}

}
