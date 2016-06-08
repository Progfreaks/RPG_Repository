package domain.exceptions;

import gui.manager.GuiGameConsole;

@SuppressWarnings("serial")
public class SamePlayerSelectedException extends Exception{

	
	public SamePlayerSelectedException(String name){
		GuiGameConsole console =GuiGameConsole.getInstance();
		console.blankTextArea();
		console.appendln(name+" ist schon ausgewaehlt\nbitte ein anderer Character auswaehlen");
	}
}
