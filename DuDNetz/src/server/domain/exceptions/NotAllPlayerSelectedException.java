package server.domain.exceptions;


@SuppressWarnings("serial")
public class NotAllPlayerSelectedException extends Exception {
	public NotAllPlayerSelectedException(int max, int selected){
		super("nicht alle Player ausgewaehlt!!\n"+"bitte noch "+(max-selected)+" Player auswaehlen");
	}
}
