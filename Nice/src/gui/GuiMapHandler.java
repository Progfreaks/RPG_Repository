package gui;

import gui.objects.ButtonPanel;
import gui.objects.ImagePainter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
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
public class GuiMapHandler {

	private DuD game;
	private Board board ;
	public  ButtonPanel buttonPanel;
	private int[][] boardMatrix ;
	private int  xPlayer, yPlayer; 
	public static int diceNum;
	public final int CHARACTER = 4;
	
	
	// Singleton Objekt. Gewaehrleistet dass es nur eine Instanz exsistiert.
	private static GuiMapHandler  singleton;

	// Vermeidet dass die Instanz dieser Klasse von anderen Klassen erzeugt wird.
	private GuiMapHandler() {

		board = new Board();
		game = DuD.getGame();

	}
	
	/**
	 * Wenn es keine Instanz exsistiert, dann wird eine Instanz erzeugt. Wenn
	 * existiert, dann gibt die zurück.
	 * 
	 * @return
	 */
	public static GuiMapHandler getInstance() {

		if (singleton == null) singleton = new GuiMapHandler();
			 
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
		this.diceNum = diceNum;
		//this.diceNum = diceNum;
		this.xPlayer = game.getXCharCoord();
		this.yPlayer = game.getYCharCoord();
		boardMatrix = board.getBoardMatrix();

		for (int i = 1; i <= diceNum; i++) {

			if ((xPlayer + i) < buttonPanel.buttons[yPlayer].length && (xPlayer + i) >= 0) {

				setBorder(yPlayer, xPlayer + i);

			}

			if ((yPlayer + i) < buttonPanel.buttons.length && (yPlayer + i) >= 0) {

				setBorder(yPlayer + i, xPlayer);

			}

			if ((xPlayer - i) < buttonPanel.buttons[yPlayer].length && (xPlayer - i) >= 0) {
				setBorder(yPlayer, xPlayer - i);

			}

			if ((yPlayer - i) < buttonPanel.buttons.length && (yPlayer - i) >= 0) {
				setBorder(yPlayer - i, xPlayer);

			}

			if (i > 1) {

				if ((yPlayer + (i - 1)) < buttonPanel.buttons.length) {

					for (int s = 1; s < diceNum; s++) {

						if ((xPlayer + (diceNum - s)) < buttonPanel.buttons[yPlayer].length&& (xPlayer + (diceNum - (s))) >= 0) {

							setBorder(yPlayer + (i - 1), xPlayer + (diceNum - s));

						}

						if ((xPlayer - (diceNum - s)) < buttonPanel.buttons[yPlayer+ (i - 1)].length&& (xPlayer - (diceNum - (s))) >= 0) {
							setBorder(yPlayer + (i - 1), xPlayer - (diceNum - s));

						}

					}
				}

				if ((yPlayer - (i - 1)) < buttonPanel.buttons.length) {

					for (int s = 1; s < diceNum; s++) {

						if ((xPlayer + (diceNum - s)) < buttonPanel.buttons[yPlayer- (i - 1)].length&& (xPlayer + (diceNum - (s))) >= 0) {
							setBorder(yPlayer - (i - 1), xPlayer + (diceNum - s));

						}

						if ((xPlayer - (diceNum - s)) < buttonPanel.buttons[yPlayer- (i - 1)].length&& (xPlayer - (diceNum - (s))) >= 0) {
							setBorder(yPlayer - (i - 1), xPlayer - (diceNum - s));

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
	 * @param xClicked
	 * @param yClicked
	 */
	public void repaintButton(int xClicked, int yClicked) {

		this.xPlayer = game.getXCharCoord();
		this.yPlayer = game.getYCharCoord();

		setImage(yPlayer,xPlayer,"resource/images/road.png");
		setImage(yClicked, xClicked, "resource/images/star.png");

		undoMoveRange(diceNum);
		game.setCharCoords(xClicked, yClicked);

	}
	
	private void setImage(int y, Integer x,String path){
		ImageIcon icon = new ImageIcon(path);
		buttonPanel.buttons[y][x].setMargin(new Insets(0, 0, 0, 0));
		buttonPanel.buttons[y][x].setIcon(icon);
		buttonPanel.buttons[y][x].setDisabledIcon(icon);
	}




	private void undoMoveRange(int diceNum) {

		this.xPlayer = game.getXCharCoord();
		this.yPlayer = game.getYCharCoord();
		boardMatrix = board.getBoardMatrix();

		System.out.println("xp-> " + xPlayer + " yp-> " + yPlayer + " diceNum-> "
				+ diceNum);

		for (int i = 1; i <= diceNum; i++) {

			if ((xPlayer + i) < buttonPanel.buttons[yPlayer].length && (xPlayer + i) >= 0) {
				removeBorder(yPlayer, xPlayer+i);
			}

			if ((yPlayer + i) < buttonPanel.buttons.length && (yPlayer + i) >= 0) {
				removeBorder(yPlayer+i, xPlayer);
			}

			if ((xPlayer - i) < buttonPanel.buttons[yPlayer].length && (xPlayer - i) >= 0) {
				removeBorder(yPlayer, xPlayer-i);
			}

			if ((yPlayer - i) < buttonPanel.buttons.length && (yPlayer - i) >= 0) {
				removeBorder(yPlayer-i, xPlayer);
			}

			if (i > 1) {
				if ((yPlayer + (i - 1)) < buttonPanel.buttons.length) {

					for (int s = 1; s < diceNum; s++) {

						if ((xPlayer + (diceNum - s)) < buttonPanel.buttons[yPlayer].length && (xPlayer + (diceNum - (s))) >= 0) {
							removeBorder(yPlayer+(i-1), xPlayer+(diceNum-s));
						}

						if ((xPlayer - (diceNum - s)) < buttonPanel.buttons[yPlayer+ (i - 1)].length && (xPlayer - (diceNum - (s))) >= 0) {
							removeBorder(yPlayer+(i-1), xPlayer-(diceNum-s));
						}

					}
				}

				if ((yPlayer - (i - 1)) < buttonPanel.buttons.length) {

					for (int s = 1; s < diceNum; s++) {

						if ((xPlayer + (diceNum - s)) < buttonPanel.buttons[yPlayer- (i - 1)].length && (xPlayer + (diceNum - (s))) >= 0) {
							removeBorder(yPlayer-(i-1), xPlayer+(diceNum-s));
						}

						if ((xPlayer - (diceNum - s)) < buttonPanel.buttons[yPlayer- (i - 1)].length && (xPlayer - (diceNum - (s))) >= 0) {
							removeBorder(yPlayer-(i-1), xPlayer-(diceNum-s));
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
