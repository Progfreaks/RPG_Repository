package valueobject.guiobjects;
/**
 * Kann später genutz werden um alle Menüobjekte zu verwalten
 */
import javax.swing.JButton;
import javax.swing.JPanel;
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
