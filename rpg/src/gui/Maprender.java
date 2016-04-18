package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.EventCreator;
import domain.DuD;

public class Maprender {
	
	//public static DuD game = new DuD();
	
	private static DuD game;
	private EventCreator creator = new EventCreator(0);
	private JButton[][] fieldSquares = new JButton[12][12]; //JButton-Array (Spielfeld)
	
	public Maprender(){
		JFrame backLayer = new JFrame("Bestes RPG"); // Haupthintergrundebene
		JPanel buttonLayer = new JPanel(new GridLayout(12,12));//Extraebene für die Buttons mit GridLayout(boardarray.length, boardarray[].length)
		//backLayer.setLayout(new GridLayout(12,12)); //Gridlayout für das JButton-Array
		backLayer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		backLayer.setSize(800, 600);
		backLayer.add(buttonLayer);//Hinzufügen des Buttonlayer
		//render(this.fieldSquares);//Aufrufen der render Methode
		
		this.fieldSquares = game.getMap();
		creator.getActionCalls(fieldSquares);
		for(int i = 0; i < fieldSquares.length; i++){        //Einfügen der Buttons aufs Buttonlayer
			for(int s = 0; s < fieldSquares[i].length; s++){
				buttonLayer.add(fieldSquares[i][s]);
			}
		}
		JPanel menue = new JPanel();//Menue Layer
		menue.setSize(300, 600); //Groesse des Menue Layers
		backLayer.add(menue, BorderLayout.SOUTH);// Hinzufügen des Menuelayer, Layout unterer Bildschirmrand	
		backLayer.setVisible(true); //True damit das JFrame angezeigt wird	
	}
	
	public static void main(String[] args){
		game = new DuD();
		game.setGame(game);
		Maprender test = new Maprender();
	}
}
