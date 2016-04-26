package valueobject.guiobjects;
/**
 * Kann sp�ter genutz werden um alle Men�objekte zu verwalten
 */
import javax.swing.JButton;
import javax.swing.JPanel;
public class RollButton {
	JPanel rollLayer;
	JButton rollButton;
	private int rounds;
	public RollButton(){
		rollLayer = new JPanel();
		rollButton = new JButton("Please Roll");
		rollLayer.add(rollButton);
		rounds = 0;
	}
	public JPanel getRollLayer(){
		return rollLayer;
	}
	
	public JButton getRollButton(){
		return rollButton;
	}
	public int raiseIndex(){
		rounds++;
		return rounds;
	}
}
