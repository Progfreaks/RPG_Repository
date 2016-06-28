package server.domain.exceptions;


@SuppressWarnings("serial")
public class MaxPlayerCountExceedException extends Exception{
	public MaxPlayerCountExceedException(int max){
		super("Maximal Player Anzahl ist " + max + ".");
	}
}
