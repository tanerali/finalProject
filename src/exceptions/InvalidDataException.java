package exceptions;

public class InvalidDataException extends Exception{

	@Override
	public String getMessage() {
		return "Invalid data entered";
	}
	
}
