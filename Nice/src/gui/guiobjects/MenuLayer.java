package gui.guiobjects;
/**
 * Kann später genutz werden um alle Menüobjekte zu verwalten
 */
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
public class MenuLayer {
	JPanel menuLayer;
	JMenuBar menuBar;
	JButton rollButton;
	private int rounds;
	public MenuLayer(){
		menuLayer = new JPanel();
		menuBar = new JMenuBar();
		rollButton = new JButton("Please Roll");
		menuBar.add(rollButton);
		menuLayer.add(menuBar);
		menuLayer.setSize(400, 200);
		menuLayer.setVisible(true);
		rounds = 0;
	}
	public JPanel getMenuLayer(){
		return menuLayer;
	}
	public JButton getRollButton(){
		return rollButton;
	}
	public int raiseIndex(){
		rounds++;
		return rounds;
	}
}
