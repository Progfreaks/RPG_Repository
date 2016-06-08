package domain.exceptions;

import gui.manager.GuiGameConsole;

@SuppressWarnings("serial")
public class NotAllPlayerSelectedException extends Exception {

	public NotAllPlayerSelectedException(int max, int selected){
		GuiGameConsole console =GuiGameConsole.getInstance();
		console.blankTextArea();
		console.appendln("nicht alle Player ausgewaehlt!!\n"+"bitte noch "+(max-selected)+" Player auswaehlen");
		
	}
}
