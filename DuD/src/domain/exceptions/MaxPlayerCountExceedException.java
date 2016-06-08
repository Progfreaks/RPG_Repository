package domain.exceptions;

import gui.manager.GuiGameConsole;

@SuppressWarnings("serial")
public class MaxPlayerCountExceedException extends Exception{

	public MaxPlayerCountExceedException(int max){
		GuiGameConsole console =GuiGameConsole.getInstance();
		console.blankTextArea();
		console.appendln("Maximal Player anzahl ist "+max);
	}
}
