package gui.components.sub;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import valueobject.IGameElements;


/**
 * Diese Klasse repraesentiert das Buttonpanel, welches ein Buttonmatrix enthaelt.
 * @author YOU_HEY
 *
 */
public class MapButtonPanel extends JPanel implements IGameElements{

	private static final long serialVersionUID = 7582907266671964634L;
	private   JButton[][] buttons = new JButton[32][32];
	private Border moveBorder;

	public MapButtonPanel(){
		setLayout(new GridLayout(32,32));

	}

	public int getMapButtonMatrixRowLength(){
		return buttons.length;
	}

	public int getMapButtonMatrixColumnLength(int row){
		return buttons[row].length;
	}

	public JButton getButton(int y, int x){
		return buttons[y][x];
	}


	
	/**
	 * Getter-Methode fuer das Buttonmatrix.
	 * @return
	 */
	public JButton[][] getMapButtonMatrix(){
		return buttons;
	}

	
	/**
	 * Faerbt die Buttons anhand der Indexs.
	 * @param element
	 * @param i
	 * @param s
	 */
	public void paintButtons(int element, int i, int s){

		switch(element)
		{
		case WALL_ELEMENT: 
			setImageButton(i, s, "resource/images/m.png");
			break;
		case FLOOR_ELEMENT: 
			setImageButton(i, s, "resource/images/road.png");
			break;
		case PICKUP_ELEMENT:
			setImageButton(i, s, "resource/images/box.png");
			break;
		case ZOMBIE_ELEMENT:
			setImageButton(i, s, "resource/images/skull.png");
			break;
		case PLAYER_ELEMENT: 
			setImageButton(i, s, "resource/images/star.png");
			break;

		case GHOST_ELEMENT:
			setImageButton(i, s, "resource/images//enemy/ghost.png");
			break;
		case MUMMY_ELEMENT:
			setImageButton(i, s, "resource/images//enemy/mummy.png");
			break;
		case POT_ELEMENT:
			setImageButton(i, s, "resource/images//enemy/pot.png");
			break;
		case SLEIM_ELEMENT:
			setImageButton(i, s, "resource/images//enemy/slime.png");
			break;
			
		case DEMON_ELEMENT:
			setBossImageToButton(i, s, "resource/images//enemy/slime.png");
			break;
		}
	}

	/**
	 * Hilfs Methode fuer paintButtons(int index, int i, int s)
	 * @param i
	 * @param s
	 * @param c
	 */
	private  void setColorButton(int i, int s, Color c){
		buttons[i][s] = new JButton(); 
		buttons[i][s].setBackground(c); 
		buttons[i][s].setOpaque(true); 
		add(buttons[i][s]); 
	}

	/**
	 * Setzt ein Image in den Button und fuegt ihn in dies Panel ein.
	 * @param i
	 * @param s
	 * @param path
	 */
	private void setImageButton(int i, int s, String path){
		ImageIcon imageIcon = new ImageIcon(path);
		buttons[i][s] = new JButton(); 
		buttons[i][s].setMargin(new Insets(0, 0, 0, 0));
		buttons[i][s].setIcon(imageIcon);
		buttons[i][s].setDisabledIcon(imageIcon);
		add(buttons[i][s]); 
	}
	
	private void setBossImageToButton(int i, int s, String path){
		ImageIcon imageIcon = new ImageIcon(path);
		buttons[i][s].setMargin(new Insets(0, 0, 0, 0));
		buttons[i][s].setIcon(imageIcon);
		buttons[i][s].setDisabledIcon(imageIcon);
	}

	/**
	 * Aktiviert das uebergebene Button.
	 * @param button
	 */
	public void enable(JButton button){
		button.setEnabled(true);
	}

	/**
	 * Deaktiviert das uebergebene Button.
	 * @param button
	 */
	public void disable(JButton button){
		button.setEnabled(false);
	}


	/**
	 * Setzt das Border in das uebergebene Button.
	 * @param button
	 */
	public void setBorder(JButton button){
		moveBorder = new LineBorder(Color.GREEN, 1);
		button.setBorder(moveBorder);
		button.setBorderPainted(true);
	}

	/**
	 * Entfernt das Border vonm uebergebenen Button.
	 * @param button
	 */
	public void removeBorder(JButton button){
		moveBorder = new LineBorder(Color.GRAY,0,false);
		button.setBorder(moveBorder);
		button.setBorderPainted(true);
	}
	
	public void refresh(){
		repaint();
		revalidate();
	}


}
