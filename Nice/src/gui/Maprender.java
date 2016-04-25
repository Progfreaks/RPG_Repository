package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import valueobject.guiobjects.*;
import gui.EventCreator;
import domain.DuD;

public class Maprender {
	
	//public static DuD game = new DuD();
	
	private static DuD game;
	private EventCreator creator = new EventCreator(0); //JButton-Array (Spielfeld)
	private BackLayer backLayer;
	private RollButton rollButton;
	private ButtonLayer buttonLayer;
	
	public Maprender(DuD game, BackLayer backLayer, ButtonLayer buttonLayer){
		this.game = game;
		this.backLayer = backLayer;  // Haupthintergrundebene
		this.buttonLayer = buttonLayer;
		game.removePanel(0);
		game.setRollButton(new RollButton());
		
		creator.getRollCall(game.getRollButton());
		creator.getActionCalls(game.getButtonArray());
		/**
		JPanel menue = new JPanel();//Menue Layer
		menue.setSize(300, 600); //Groesse des Menue Layers
		backLayer.add(menue, BorderLayout.SOUTH);// Hinzufügen des Menuelayer, Layout unterer Bildschirmrand
		*/
		backLayer.addPanel(buttonLayer.getButtonLayer(), 0);//Hinzufügen des Buttonlayer
		backLayer.addPanel(game.getRollLayer(), 1);
		backLayer.setPanelVisible(0, true);
		backLayer.setPanelVisible(1, true);
		refreshGUI();
		
	}
	public void refreshGUI(){
		backLayer.refresh();
	}
		
	
	/**
	public static void main(String[] args){
		game = new DuD();
		game.setGame(game);
		Maprender test = new Maprender();
	}
	*/
}
