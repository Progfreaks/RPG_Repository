package server.domain.exceptions;


@SuppressWarnings("serial")
public class SamePlayerSelectedException extends Exception{
	public SamePlayerSelectedException(String name){
		super(name+" ist schon ausgewaehlt\nbitte ein anderer Character auswaehlen");
	}
}
