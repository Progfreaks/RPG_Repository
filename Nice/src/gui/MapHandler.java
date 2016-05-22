package gui;

import gui.objects.ButtonPanel;

import java.awt.Color;

import javax.swing.JButton;

import persistence.character.CharacterDataMap;
import valueobject.Board;
import domain.DuD;

/**
 * Betreut alles was mit dem Board zu tun hat. - Laden der Map aus Valueobject
 * Board - Recolourn der Squares bei Events (Stand pre Pictures) - Verwaltung
 * der begehbaren Feldern
 * 
 * @author T
 *
 */
public class MapHandler {

	private DuD game;
	private Board board ;
	public  ButtonPanel buttonPanel;
	private int[][] boardMatrix ;
	private int  xp, yp; 
	public static int num;
	public final int CHARACTER = 4;
	
	
	// Singleton Objekt. Gewaehrleistet dass es nur eine Instanz exsistiert.
	private static MapHandler  singleton;

	// Vermeidet dass die Instanz dieser Klasse von anderen Klassen erzeugt wird.
	private MapHandler() {

		board = new Board();
		game = DuD.getGame();

	}
	
	/**
	 * Wenn es keine Instanz exsistiert, dann wird eine Instanz erzeugt. Wenn
	 * existiert, dann gibt die zurück.
	 * 
	 * @return
	 */
	public static MapHandler getInstance() {

		if (singleton == null) singleton = new MapHandler();
			 
		return singleton;
	}

	/**
	 * Erstellt den buttonLayer, disabled alle Buttons und leitet dies weiter an
	 * die Gui ueber game
	 */
	public void paintButtonPanel() {

		// Panel fuer Button. Das Feld ist zuerst leer.
		buttonPanel = new ButtonPanel();

		// Bekommt ein Matrix von int
		boardMatrix = getBoardMatrix();

		// Setzt Button ins Feld
		for (int y = 0; y < boardMatrix.length; y++) {
			for (int x = 0; x < boardMatrix[y].length; x++) {
				if (boardMatrix[y][x] == CHARACTER) {
					game.setCharCoords(x, y); // x und y des Char setzen
				}
				buttonPanel.paintButtons(boardMatrix[y][x], y, x);// je nachdem welche Wert das Boardarray hat.

				buttonPanel.disable(buttonPanel.buttons[y][x]);
			}// <- ende der for Schleife
		}// <- ende der for Schleife
	}


	/**
	 * Getter-Methode feur das Buttonpanel.
	 * @return
	 */
	public ButtonPanel getButtonPanel() {
		return buttonPanel;
	}

	/**
	 * Gibt ein Matrix von JButton zurueck.
	 * 
	 * @return
	 */
//	public JButton[][] getButtonMatrix() {
//
//		return buttonPanel.getButtonMatrix();
//
//	}

	public void setButtonMatrix(JButton[][] buttons) {
		buttonPanel.setButtonMatrix(buttons);
	}

	public int[][] getBoardMatrix() {
		return board.getBoardMatrix();
	}



	public void renderChest() {

		buttonPanel.buttons[22][30].setBackground(Color.YELLOW);
		buttonPanel.buttons[21][29].setBackground(Color.YELLOW);
		buttonPanel.buttons[22][29].setBackground(Color.YELLOW);
		buttonPanel.buttons[21][30].setBackground(Color.YELLOW);

	}



	/**
	 * Enabled die Buttons anhand der Wuerfelaugenzahl und gibt den begehbaren
	 * Buttons ne schnieke gruene Border Beachtet out of bounds und nicht
	 * passierbare walls refresh der gui
	 * 
	 * @param diceNum
	 */
	public void paintMoveRange(int diceNum) {
		num = diceNum;
		//this.diceNum = diceNum;
		this.xp = game.getXCharCoord();
		this.yp = game.getYCharCoord();
		boardMatrix = board.getBoardMatrix();

		for (int i = 1; i <= diceNum; i++) {

			if ((xp + i) < buttonPanel.buttons[yp].length && (xp + i) >= 0) {

				setBorder(yp, xp + i);

			}

			if ((yp + i) < buttonPanel.buttons.length && (yp + i) >= 0) {

				setBorder(yp + i, xp);

			}

			if ((xp - i) < buttonPanel.buttons[yp].length && (xp - i) >= 0) {
				setBorder(yp, xp - i);

			}

			if ((yp - i) < buttonPanel.buttons.length && (yp - i) >= 0) {
				setBorder(yp - i, xp);

			}

			if (i > 1) {

				if ((yp + (i - 1)) < buttonPanel.buttons.length) {

					for (int s = 1; s < diceNum; s++) {

						if ((xp + (diceNum - s)) < buttonPanel.buttons[yp].length&& (xp + (diceNum - (s))) >= 0) {

							setBorder(yp + (i - 1), xp + (diceNum - s));

						}

						if ((xp - (diceNum - s)) < buttonPanel.buttons[yp+ (i - 1)].length&& (xp - (diceNum - (s))) >= 0) {
							setBorder(yp + (i - 1), xp - (diceNum - s));

						}

					}
				}

				if ((yp - (i - 1)) < buttonPanel.buttons.length) {

					for (int s = 1; s < diceNum; s++) {

						if ((xp + (diceNum - s)) < buttonPanel.buttons[yp- (i - 1)].length&& (xp + (diceNum - (s))) >= 0) {
							setBorder(yp - (i - 1), xp + (diceNum - s));

						}

						if ((xp - (diceNum - s)) < buttonPanel.buttons[yp- (i - 1)].length&& (xp - (diceNum - (s))) >= 0) {
							setBorder(yp - (i - 1), xp - (diceNum - s));

						}

					}

				}

			}// ende if statement
		}


	}

	/**
	 * Hilfs Methode 
	 * @param y
	 * @param x
	 */
	private void setBorder(int y, int x) {

		if (boardMatrix[y][x] != 0) {
			JButton button = buttonPanel.buttons[y][x];
			buttonPanel.setBorder(button);
			buttonPanel.enable(button);
		}

	}

	/**
	 * params = Position des geclickten Buttons settet ausserderm neue charposi
	 * 
	 * @param xf
	 * @param yf
	 */
	public void repaintButton(int xf, int yf) {


		this.xp = game.getXCharCoord();
		this.yp = game.getYCharCoord();
		buttonPanel.buttons[yp][xp].setBackground(Color.LIGHT_GRAY);
		buttonPanel.buttons[yf][xf].setBackground(Color.BLUE);
		undoMoveRange(num);
		game.setCharCoords(xf, yf);




	}




	private void undoMoveRange(int diceNum) {

		this.xp = game.getXCharCoord();
		this.yp = game.getYCharCoord();
		boardMatrix = board.getBoardMatrix();

		System.out.println("xp-> " + xp + " yp-> " + yp + " diceNum-> "
				+ diceNum);

		for (int i = 1; i <= diceNum; i++) {

			if ((xp + i) < buttonPanel.buttons[yp].length && (xp + i) >= 0) {
				removeBorder(yp, xp+i);
			}

			if ((yp + i) < buttonPanel.buttons.length && (yp + i) >= 0) {
				removeBorder(yp+i, xp);
			}

			if ((xp - i) < buttonPanel.buttons[yp].length && (xp - i) >= 0) {
				removeBorder(yp, xp-i);
			}

			if ((yp - i) < buttonPanel.buttons.length && (yp - i) >= 0) {
				removeBorder(yp-i, xp);
			}

			if (i > 1) {
				if ((yp + (i - 1)) < buttonPanel.buttons.length) {

					for (int s = 1; s < diceNum; s++) {

						if ((xp + (diceNum - s)) < buttonPanel.buttons[yp].length && (xp + (diceNum - (s))) >= 0) {
							removeBorder(yp+(i-1), xp+(diceNum-s));
						}

						if ((xp - (diceNum - s)) < buttonPanel.buttons[yp+ (i - 1)].length && (xp - (diceNum - (s))) >= 0) {
							removeBorder(yp+(i-1), xp-(diceNum-s));
						}

					}
				}

				if ((yp - (i - 1)) < buttonPanel.buttons.length) {

					for (int s = 1; s < diceNum; s++) {

						if ((xp + (diceNum - s)) < buttonPanel.buttons[yp- (i - 1)].length && (xp + (diceNum - (s))) >= 0) {
							removeBorder(yp-(i-1), xp+(diceNum-s));
						}

						if ((xp - (diceNum - s)) < buttonPanel.buttons[yp- (i - 1)].length && (xp - (diceNum - (s))) >= 0) {
							removeBorder(yp-(i-1), xp-(diceNum-s));
						}

					}

				}

			}
		}



	}

	private void removeBorder(int y, int x){
		if(boardMatrix[y][x] != 0){
			JButton button = buttonPanel.buttons[y][x];
			buttonPanel.removeBorder(button);
			buttonPanel.disable(button);
		}
	}


}
