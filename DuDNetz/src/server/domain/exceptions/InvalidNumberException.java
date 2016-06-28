package server.domain.exceptions;


@SuppressWarnings("serial")
public class InvalidNumberException extends Exception{
	public InvalidNumberException(int num){
		super("Die Nummer "+num+" ist ungueltig");
	}
}
