package gui.objects.sublayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


/**
 * Diese Klasse repraesentiert das Buttonpanel, welches ein Buttonmatrix enthaelt.
 * @author YOU_HEY
 *
 */
@SuppressWarnings("serial")
public class ButtonPanel extends JPanel{

	public   JButton[][] buttons = new JButton[32][32];
	private Border moveBorder;

	/**
	 * Konstruktor dieser Klasse.
	 */
	public ButtonPanel(){

		setLayout(new GridLayout(32,32));


	}



	/**
	 * Setter-Methode fuer das Buttonmatrix.
	 * @param buttons
	 */
	public void setButtonMatrix(JButton[][] buttons){
		this.buttons = buttons;
	}

	/**
	 * Getter-Methode fuer das Buttonmatrix.
	 * @return
	 */
	public JButton[][] getButtonMatrix(){
		return buttons;
	}

	/**
	 * Getter-Methode fuer das Buttonpanel.
	 * @return
	 */
	public JPanel getButtonPanel(){
		return this;
	}


	/**
	 * Faerbt die Buttons anhand der Indexs.
	 * @param index
	 * @param i
	 * @param s
	 */
	public void paintButtons(int index, int i, int s){
		switch(index)
		{
		case 0: 
			setImageButton(i, s, "resource/images/m.png");
			//setColorButton(i, s, Color.BLACK);
			break;

		case 1: 

			setImageButton(i, s, "resource/images/road.png");
			//setColorButton(i, s, Color.LIGHT_GRAY);
			break;

		case 2: 
			setImageButton(i, s, "resource/images/box.png");
			//setColorButton(i, s, Color.CYAN);
			break;

		case 3: 
			setImageButton(i, s, "resource/images/skull.png");
			//setColorButton(i, s, Color.RED);
			break;

		case 4: setImageButton(i, s, "resource/images/star.png");
			//setColorButton(i, s, Color.BLUE);

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
		moveBorder = new LineBorder(Color.GRAY);
		button.setBorder(moveBorder);
		button.setBorderPainted(true);
	}


}