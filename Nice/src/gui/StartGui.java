package gui;

import java.awt.BorderLayout;

import javax.swing.*;
import gui.EventCreator;
import domain.CentralSave;
import domain.DuD;
import gui.CommandoInput;
import gui.guiobjects.*;
import gui.guiobjects.sublayer.*;
import gui.GuiManager;

public class StartGui {
	private static DuD game;
	private JPanel newGame;
	private BackLayer backLayer;
	
	private GuiManager gui;
	private JButton startButton;
	private EventCreator creator = new EventCreator(0);
	public StartGui(){
		game = DuD.getGame();
		backLayer = new BackLayer();
		gui = new GuiManager(backLayer);
		game.setGuiMgr(gui);
		game.addPanel(newGamePanel(), 0, "");
	}
	public JPanel newGamePanel(){
		IntroScreen startPanel = new IntroScreen();
		return startPanel.getIntroScreen();
	}
	public JPanel getnewGamePanel(){
		return this.newGame;
	}
	public static void main(String[] args){
		game = new DuD();
		game.setGame(game);
		new StartGui();
	}

}
