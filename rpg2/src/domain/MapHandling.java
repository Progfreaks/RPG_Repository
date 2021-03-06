package domain;
import valueobject.Board;
import domain.DuD;
import javax.swing.JButton;
import java.awt.Color;
import valueobject.TestFigure;
import valueobject.FreeSquare;
public class MapHandling {
	TestFigure player = null;
	Board map = null;
	private int[][] intArray = null;
	public JButton[][] fieldSquares = new JButton[12][12];
	
	public MapHandling(){
		this.map = new Board();
	}
	
	public JButton[][] render(){
		this.intArray = map.getBoard();
		for(int i = 0; i < intArray.length; i++){
			for(int s = 0; s < intArray[i].length; s++){
				
			
				 //Coloring the Buttons
				 
				switch(intArray[i][s]){
				case 0: fieldSquares[i][s] = new JButton(); fieldSquares[i][s].setBackground(Color.BLACK); 
				fieldSquares[i][s].setOpaque(true); break;
				
				case 1: fieldSquares[i][s] = new JButton(); fieldSquares[i][s].setBackground(Color.YELLOW); 
				fieldSquares[i][s].setOpaque(true); break;
				
				case 2: fieldSquares[i][s] = new JButton(); fieldSquares[i][s].setBackground(Color.GREEN); 
				fieldSquares[i][s].setOpaque(true); break;
//				case 3: fieldSquares[i][s] = new JButton(); fieldSquares[i][s].setBackground(Color.RED); 
				//fieldSquares[i][s].setOpaque(true); fieldSquares[i][s].addActionListener(new FightEvent()); break;
				
				case 3: fieldSquares[i][s] = new JButton(); fieldSquares[i][s].setBackground(Color.RED); 
				fieldSquares[i][s].setOpaque(true); break;
				
				case 4: fieldSquares[i][s] = new JButton(); fieldSquares[i][s].setBackground(Color.BLUE); 
				player = new TestFigure(s,i); fieldSquares[i][s].setOpaque(true); break;
				}
				
			}
		}
		return this.fieldSquares;
	}
	public int[][] getBoardArray(){
		return map.getBoard();
	}

}
