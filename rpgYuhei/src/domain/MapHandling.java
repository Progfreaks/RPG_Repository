package domain;

import valueobject.Board;
import domain.DuD;
import javax.swing.JButton;
import java.awt.Color;
import valueobject.guiobjects.*;

import valueobject.fieldobjects.*;

/**
 * Betreut alles was mit dem Board zu tun hat. - Laden der Map aus Valueobject
 * Board - Recolourn der Squares bei Events (Stand pre Pictures)
 * 
 * @author T
 *
 */
public class MapHandling {

	private DuD game;
	Board map = null;
	ButtonLayer buttonLayer = null;
	private int[][] intArray = null; // das int array vom board
	// public JButton[][] fieldSquares = new JButton[12][12]; // Neues Map-Array
	// mit allen Feldern des int-arrays als JButton
	// public FieldObjects[][] fieldObjects = new FieldObjects[12][12];
	// //Collection der FreeSquares -> Valueobject FreeSquare
	private int xf, yf, xp, yp; // Koordinaten der FieldObjects

	public MapHandling(DuD game) {

		this.game = game;
		this.map = new Board();

	}

	public void render() {
		buttonLayer = new ButtonLayer();
		this.intArray = map.getBoard();
		for (int i = 0; i < intArray.length; i++) {
			for (int s = 0; s < intArray[i].length; s++) {
				if (intArray[i][s] == 4) {
					game.setCharCoords(s, i); // x und y des Char setzen
				}
				buttonLayer.fillLayer(intArray[i][s], i, s);
				buttonLayer.disable(buttonLayer.fieldSquares[i][s]);
			}

		}
		game.newMaprender(game, game.getbackLayer(), buttonLayer);
	}

	public int[][] getBoardArray() {
		return map.getBoard();
	}

	/**
	 * public FieldObjects[][] getFieldObjects(){ return this.fieldObjects; }
	 */

	public JButton[][] getButtonArray() {
		return buttonLayer.getFieldSquares();
	}

	public void recolour(int xf, int yf) {
		this.xf = xf;
		this.yf = yf;
		this.xp = game.getXCharCoord();
		this.yp = game.getYCharCoord();
		buttonLayer.fieldSquares[yp][xp].setBackground(Color.YELLOW);
		buttonLayer.fieldSquares[yf][xf].setBackground(Color.BLUE);
		game.setCharCoords(xf, yf);

	}

	public void callForAction(int diceNum) {
		this.xp = game.getXCharCoord();
		this.yp = game.getYCharCoord();
		intArray = map.getBoard();
		for (int i = 1; i <= diceNum; i++) {
			if ((xp + i) < buttonLayer.fieldSquares[yp].length) {
				if (intArray[yp][xp + i] != 0) {
					buttonLayer.fieldSquares[yp][xp + i]
							.setBackground(Color.GREEN);
					buttonLayer.enable(buttonLayer.fieldSquares[yp][xp + i]);
				}
			}

			if ((yp + i) < buttonLayer.fieldSquares.length) {
				if (intArray[yp + i][xp] != 0) {
					buttonLayer.fieldSquares[yp + i][xp]
							.setBackground(Color.GREEN);
					buttonLayer.enable(buttonLayer.fieldSquares[yp + i][xp]);
				}
			}

			if ((xp - i) < buttonLayer.fieldSquares[yp].length) {
				if (intArray[yp][xp - i] != 0) {
					buttonLayer.fieldSquares[yp][xp - i]
							.setBackground(Color.GREEN);
					buttonLayer.enable(buttonLayer.fieldSquares[yp][xp - i]);
				}
			}

			if ((yp - i) < buttonLayer.fieldSquares.length) {
				if (intArray[yp - i][xp] != 0) {
					buttonLayer.fieldSquares[yp - i][xp]
							.setBackground(Color.GREEN);
					buttonLayer.enable(buttonLayer.fieldSquares[yp - i][xp]);
				}
			}

		}
		game.refreshGUI();

	}

}
