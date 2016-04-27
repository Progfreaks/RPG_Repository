package valueobject.guiobjects;
/**
 * Kann sp�ter genutz werden um alle Men�objekte zu verwalten
 */
import javax.swing.JButton;
import javax.swing.JMenuBar;
public class RollButton {
	JMenuBar menuBar;
	JButton rollButton;
	private int rounds;
	public RollButton(){
		menuBar = new JMenuBar();
		rollButton = new JButton("Please Roll");
		menuBar.add(rollButton);
		rounds = 0;
	}
	public JMenuBar getMenuBar(){
		return menuBar;
	}
	
	public JButton getRollButton(){
		return rollButton;
	}
	public int raiseIndex(){
		rounds++;
		return rounds;
	}
}
