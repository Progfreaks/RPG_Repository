package domain.exceptions;

public class InvalidNumberException extends Exception{
	
	
	public InvalidNumberException(int num){
		super("Die Nummer "+num+" ist ungueltig\n\n");
	}

	
	
	
}
