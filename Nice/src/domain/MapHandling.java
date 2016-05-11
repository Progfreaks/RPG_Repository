package domain;
import valueobject.Board;
import domain.DuD;
import javax.swing.JButton;
import java.awt.Color;
import valueobject.guiobjects.*;

import valueobject.fieldobjects.*;
/** Betreut alles was mit dem Board zu tun hat.
 *  - Laden der Map aus Valueobject Board
 *  - Recolourn der Squares bei Events (Stand pre Pictures)
 *  - Verwaltung der begehbaren Feldern
 * @author T
 *
 */
public class MapHandling {
	private DuD game;
	Board map = null;
	ButtonLayer buttonLayer = null;
	private int[][] intArray = null; //das int array vom board
	//public JButton[][] fieldSquares = new JButton[12][12]; // Neues Map-Array mit allen Feldern des int-arrays als JButton
	//public FieldObjects[][] fieldObjects = new FieldObjects[12][12]; //Collection der FreeSquares -> Valueobject FreeSquare
	private int xf, yf, xp, yp; // Koordinaten der FieldObjects
	private int diceNum;
	public MapHandling(DuD game){
		this.game = game;
		this.map = new Board();
		
	}
	/**
	 * Erstellt den buttonLayer, disabled alle Buttons und leitet dies weiter an die Gui über game
	 */
	public void render(){
		this.intArray = map.getBoard();
		for(int i = 0; i < intArray.length; i++){
			for(int s = 0; s < intArray[i].length; s++){
				if(intArray[i][s] == 4){
					game.setCharCoords(s, i); //x und y des Char setzen
				}
				game.fillLayer(intArray[i][s], i, s);
				}
				
			}
		}

	public int[][] getBoardArray(){
		return map.getBoard();
	}

	/**
	 * params = Position des geclickten Buttons
	 * settet außerderm neue charposi
	 * @param xf 
	 * @param yf
	 */
	public void recolour(int xf, int yf){
		this.xf = xf;
		this.yf = yf;
		this.xp = game.getXCharCoord();
		this.yp = game.getYCharCoord();
		buttonLayer.fieldSquares[yp][xp].setBackground(Color.LIGHT_GRAY);
		buttonLayer.fieldSquares[yf][xf].setBackground(Color.BLUE);
		undoCallForAction();
		game.setCharCoords(xf, yf);
		
	}
	public void renderChest(){
		buttonLayer.fieldSquares[22][30].setBackground(Color.YELLOW);
		buttonLayer.fieldSquares[21][29].setBackground(Color.YELLOW);
		buttonLayer.fieldSquares[22][29].setBackground(Color.YELLOW);
		buttonLayer.fieldSquares[21][30].setBackground(Color.YELLOW);
	}
	
	/**
	 * Enabled die Buttons anhand der Würfelaugenzahl und gibt den begehbaren Buttons ne schnieke grüne Border
	 * Beachtet out of bounds und nicht passierbare walls
	 * refresh der gui
	 * @param diceNum
	 */
	public void callForAction(int diceNum){
		this.diceNum = diceNum;
		this.xp = game.getXCharCoord();
		this.yp = game.getYCharCoord();
		intArray = map.getBoard();
		for(int i = 1; i <= diceNum; i++){
			if((xp + i)  < buttonLayer.fieldSquares[yp].length && (xp+i) >= 0){
				if(intArray[yp][xp+i] != 0){
					buttonLayer.setMoveBorder(buttonLayer.fieldSquares[yp][xp+i]);
					buttonLayer.enable(buttonLayer.fieldSquares[yp][xp+i]);
				}
			}
			
			if((yp + i) < buttonLayer.fieldSquares.length && (yp + i) >= 0){
				if(intArray[yp+i][xp] != 0){
					buttonLayer.setMoveBorder(buttonLayer.fieldSquares[yp+i][xp]);
					buttonLayer.enable(buttonLayer.fieldSquares[yp+i][xp]);
				}	
			}
			
			if((xp-i)  < buttonLayer.fieldSquares[yp].length && (xp-i) >= 0){
				if(intArray[yp][xp-i] != 0){
					buttonLayer.setMoveBorder(buttonLayer.fieldSquares[yp][xp-i]);
					buttonLayer.enable(buttonLayer.fieldSquares[yp][xp-i]);
				}
			}
			
			if((yp-i)  < buttonLayer.fieldSquares.length && (yp-i) >= 0){
				if(intArray[yp-i][xp] != 0){
					buttonLayer.setMoveBorder(buttonLayer.fieldSquares[yp-i][xp]);
					buttonLayer.enable(buttonLayer.fieldSquares[yp-i][xp]);
				}
			
			}
			if(i>1){
				if((yp+(i-1)) < buttonLayer.fieldSquares.length){
					
					for(int s = 1; s < diceNum; s++){
						if((xp+(diceNum-s)) < buttonLayer.fieldSquares[yp].length && (xp+(diceNum-(s))) >= 0){
							if(intArray[(yp+(i-1))][(xp+(diceNum-s))] != 0){
								
								buttonLayer.setMoveBorder(buttonLayer.fieldSquares[yp+(i-1)][xp+(diceNum-(s))]);
								buttonLayer.enable(buttonLayer.fieldSquares[yp+(i-1)][xp+(diceNum-(s))]);
							}
						}
						if((xp-(diceNum-s)) < buttonLayer.fieldSquares[yp+(i-1)].length && (xp-(diceNum-(s))) >= 0){
							if(intArray[(yp+(i-1))][(xp-(diceNum-s))] != 0){
						
								buttonLayer.setMoveBorder(buttonLayer.fieldSquares[yp+(i-1)][xp-(diceNum-(s))]);
								buttonLayer.enable(buttonLayer.fieldSquares[yp+(i-1)][xp-(diceNum-(s))]);
							}
						}
						
					}
				}
				if((yp-(i-1)) < buttonLayer.fieldSquares.length){
					
						for(int s = 1; s < diceNum; s++){
							
							if((xp+(diceNum-s)) < buttonLayer.fieldSquares[yp-(i-1)].length && (xp+(diceNum-(s))) >= 0){
								if(intArray[(yp-(i-1))][(xp+(diceNum-s))] != 0){
									buttonLayer.setMoveBorder(buttonLayer.fieldSquares[yp-(i-1)][(xp+(diceNum-(s)))]);
									buttonLayer.enable(buttonLayer.fieldSquares[yp-(i-1)][(xp+(diceNum-(s)))]);
								}
							}
							if((xp-(diceNum-s)) < buttonLayer.fieldSquares[yp-(i-1)].length && (xp-(diceNum-(s))) >= 0){
								if(intArray[(yp-(i-1))][(xp-(diceNum-s))] != 0){
									buttonLayer.setMoveBorder(buttonLayer.fieldSquares[yp-(i-1)][(xp-(diceNum-(s)))]);
									buttonLayer.enable(buttonLayer.fieldSquares[yp-(i-1)][(xp-(diceNum-(s)))]);
								}
							}
						
						}
					
				}
				
			}	
		}
		game.refreshGUI();
		
	}
	public void undoCallForAction(){
		this.xp = game.getXCharCoord();
		this.yp = game.getYCharCoord();
		intArray = map.getBoard();
		for(int i = 1; i <= diceNum; i++){
			if((xp + i)  < buttonLayer.fieldSquares[yp].length && (xp+i) >= 0){
				if(intArray[yp][xp+i] != 0){
					buttonLayer.removeMoveBorder(buttonLayer.fieldSquares[yp][xp+i]);
					buttonLayer.disable(buttonLayer.fieldSquares[yp][xp+i]);
				}
			}
			
			if((yp + i) < buttonLayer.fieldSquares.length && (yp + i) >= 0){
				if(intArray[yp+i][xp] != 0){
					buttonLayer.removeMoveBorder(buttonLayer.fieldSquares[yp+i][xp]);
					buttonLayer.disable(buttonLayer.fieldSquares[yp+i][xp]);
				}	
			}
			
			if((xp-i)  < buttonLayer.fieldSquares[yp].length && (xp-i) >= 0){
				if(intArray[yp][xp-i] != 0){
					buttonLayer.removeMoveBorder(buttonLayer.fieldSquares[yp][xp-i]);
					buttonLayer.disable(buttonLayer.fieldSquares[yp][xp-i]);
				}
			}
			
			if((yp-i)  < buttonLayer.fieldSquares.length && (yp-i) >= 0){
				if(intArray[yp-i][xp] != 0){
					buttonLayer.removeMoveBorder(buttonLayer.fieldSquares[yp-i][xp]);
					buttonLayer.disable(buttonLayer.fieldSquares[yp-i][xp]);
				}
			
			}
			if(i>1){
				if((yp+(i-1)) < buttonLayer.fieldSquares.length){
					
					for(int s = 1; s < diceNum; s++){
						if((xp+(diceNum-s)) < buttonLayer.fieldSquares[yp].length && (xp+(diceNum-(s))) >= 0){
							if(intArray[(yp+(i-1))][(xp+(diceNum-s))] != 0){
		
								buttonLayer.removeMoveBorder(buttonLayer.fieldSquares[yp+(i-1)][xp+(diceNum-(s))]);
								buttonLayer.disable(buttonLayer.fieldSquares[yp+(i-1)][xp+(diceNum-(s))]);
							}
						}
						if((xp-(diceNum-s)) < buttonLayer.fieldSquares[yp+(i-1)].length && (xp-(diceNum-(s))) >= 0){
							if(intArray[(yp+(i-1))][(xp-(diceNum-s))] != 0){
								
								buttonLayer.removeMoveBorder(buttonLayer.fieldSquares[yp+(i-1)][xp-(diceNum-(s))]);
								buttonLayer.disable(buttonLayer.fieldSquares[yp+(i-1)][xp-(diceNum-(s))]);
							}
						}
						
					}
				}
				if((yp-(i-1)) < buttonLayer.fieldSquares.length){
					
						for(int s = 1; s < diceNum; s++){
							
							if((xp+(diceNum-s)) < buttonLayer.fieldSquares[yp-(i-1)].length && (xp+(diceNum-(s))) >= 0){
								if(intArray[(yp-(i-1))][(xp+(diceNum-s))] != 0){
									buttonLayer.removeMoveBorder(buttonLayer.fieldSquares[yp-(i-1)][(xp+(diceNum-(s)))]);
									buttonLayer.disable(buttonLayer.fieldSquares[yp-(i-1)][(xp+(diceNum-(s)))]);
								}
							}
							if((xp-(diceNum-s)) < buttonLayer.fieldSquares[yp-(i-1)].length && (xp-(diceNum-(s))) >= 0){
								if(intArray[(yp-(i-1))][(xp-(diceNum-s))] != 0){
									buttonLayer.removeMoveBorder(buttonLayer.fieldSquares[yp-(i-1)][(xp-(diceNum-(s)))]);
									buttonLayer.disable(buttonLayer.fieldSquares[yp-(i-1)][(xp-(diceNum-(s)))]);
								}
							}
						
						}
					
				}
				
			}	
		}
		game.refreshGUI();
		
	}

}
