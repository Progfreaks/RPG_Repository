package gui;

import java.awt.BorderLayout;

import javax.swing.*;
import gui.EventCreator;
import domain.DuD;
import valueobject.guiobjects.*;

public class StartGui {
	private static DuD game;
	private JPanel newGame;
	private BackLayer backLayer;
	private JButton startButton;
	private EventCreator creator = new EventCreator(0);
	public StartGui(){
		game.setbackLayer(new BackLayer());
		game.addPanel(newGamePanel(), 0);
		
	}
	public JPanel newGamePanel(){
		this.newGame = new JPanel();
		startButton = new JButton("Ready for an adventure?");
		creator.getStartCall(startButton);
		newGame.setSize(200, 50);
		newGame.add(startButton);
		return newGame;
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
