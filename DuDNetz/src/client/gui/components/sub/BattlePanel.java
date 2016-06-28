package client.gui.components.sub;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;
import server.domain.DuD;
import client.gui.manager.GuiGameConsole;
import client.gui.manager.GuiManager;
import client.net.Facade;
import common.valueobject.Character;

public class BattlePanel extends JPanel{

	private GuiGameConsole console;
	private LineBorder border = new LineBorder(Color.RED, 5, true);
	private JLabel characterImageLabel;
	private Character enemy;
	private Facade single;
	

	public BattlePanel(GuiManager g,Character enemy){
		single = g.getSingle();
		console = GuiGameConsole.getInstance();
		this.enemy = enemy;
		setUpPanel();
		
	}

	private void setUpPanel(){
		setLayout(new MigLayout("fill"));
		//add(console.getConsolePanel(),"h 150%,w 150%, wrap");
		add(console.getConsolePanel(),"span 3, wrap");
		setPlayerImageToButton();
		setEnemyImageToButton();
		//setHealthBar();
		//setOpaque(true);
		setOpaque(false);
		setVisible(true);
	}
	
	
private void setPlayerImageToButton(){
		
		for(Character player:single.getPlayers()){
			System.out.println("Player name ((battle panel)) "+player.getName());
			switch(player.getName()){
			case "Held": 
				ImageIcon himage = new ImageIcon("resource/images/Hero.jpg"); 
				setUpButton(himage,"Held ");
				break;

			case "Magier": 
				ImageIcon mimage = new ImageIcon("resource/images/Mage.jpeg");
				setUpButton(mimage,"Magier ");
				break;

			case "Kobold": 
				ImageIcon gimage = new ImageIcon("resource/images/Goblin.jpg");
				setUpButton(gimage,"Kobold ");
				break;

			case "Ninja":
				ImageIcon nimage = new ImageIcon("resource/images/Ninja.jpg");
				setUpButton(nimage,"Ninja ");
				break;

			case "Spiderman": 
				ImageIcon simage = new ImageIcon("resource/images/Spiderman.jpg"); 
				setUpButton(simage,"Spiderman ");
				break;
			}
		}

	}

	
//	private void setPlayerImageToButton(){
//		
//		for(Character player:DuD.getGame().getPlayers()){
//			switch(player.getName()){
//			case "Held": 
//				ImageIcon himage = new ImageIcon("resource/images/Hero.jpg"); 
//				setUpButton(himage,"Held ");
//				break;
//
//			case "Magier": 
//				ImageIcon mimage = new ImageIcon("resource/images/Mage.jpeg");
//				setUpButton(mimage,"Magier ");
//				break;
//
//			case "Kobold": 
//				ImageIcon gimage = new ImageIcon("resource/images/Goblin.jpg");
//				setUpButton(gimage,"Kobold ");
//				break;
//
//			case "Ninja":
//				ImageIcon nimage = new ImageIcon("resource/images/Ninja.jpg");
//				setUpButton(nimage,"Ninja ");
//				break;
//
//			case "Spiderman": 
//				ImageIcon simage = new ImageIcon("resource/images/Spiderman.jpg"); 
//				setUpButton(simage,"Spiderman ");
//				break;
//			}
//		}
//
//	}

	public void setEnemyImageToButton(){
		System.out.println(enemy.getName());
		switch(enemy.getName()){
		case "Zombie": 
			ImageIcon zImage = new ImageIcon("resource/images/enemy/zombie_image.jpg"); 
			setUpButton(zImage,"Zombie ");
			break;
		case "Ghost": 
			ImageIcon mimage = new ImageIcon("resource/images/enemy/ghost.png"); 
			setUpButton(mimage,"Ghost ");
			break;
		case "Mummy": 
			ImageIcon gimage = new ImageIcon("resource/images/enemy/mummy.png"); 
			setUpButton(gimage,"Mummy ");
			break;
		case "Pot":
			ImageIcon nimage = new ImageIcon("resource/images/enemy/pot.png"); 
			setUpButton(nimage,"Pot ");
			break;
		case "Sleim": 
			ImageIcon simage = new ImageIcon("resource/images/enemy/slime.png"); 
			setUpButton(simage,"Sleim ");
			break;
		case "Demon": 
			ImageIcon dimage = new ImageIcon("resource/images/enemy/demon.jpg"); 
			setUpButton(dimage,"Demon ");
			break;
		}
	}

	
	private void setUpButton(ImageIcon image,String characterName){
		characterImageLabel = new JLabel(characterName);
		characterImageLabel.setVerticalAlignment(JLabel.TOP);//funktioniert net
		characterImageLabel.setPreferredSize(new Dimension(182, 286));
		characterImageLabel.setOpaque(true);
		characterImageLabel.setIcon(resizeImage(image, characterImageLabel));
		characterImageLabel.setDisabledIcon(image); 
		add(characterImageLabel,"h 15%,w 15%");
	}
	
	public void setHealthBar(){
		JLabel dummy =new JLabel();
		dummy.setOpaque(false);
		add(dummy,"wrap");
		JPanel panel =new JPanel(new BorderLayout());
		
		panel.setPreferredSize(new Dimension(90, 2));
		panel.setOpaque(true);
		panel.setBorder(new LineBorder(Color.black,2));
		JLabel healthRedLabel = new JLabel();
		healthRedLabel.setPreferredSize(new Dimension(180, 2));
		healthRedLabel.setBackground(Color.RED);
		healthRedLabel.setOpaque(true);
		healthRedLabel.setVisible(true);
		panel.add(healthRedLabel,BorderLayout.WEST);
		add(panel,"h 5%,w 15%");
	}


	private ImageIcon resizeImage(ImageIcon img, JLabel b){
		Image image = img.getImage();  
		Image newimg = image.getScaledInstance( 162, 256,  java.awt.Image.SCALE_SMOOTH ) ;  
		ImageIcon icon = new ImageIcon( newimg );
		return icon;
	}

	public void setBorder(String characterName){
		switch(characterName){
		case "Zombie":
			checkName("Zombie ");			
			break;
		case "Ghost": 
			checkName("Ghost ");
			break;
		case "Mummy": 
			checkName("Mummy ");
			break;
		case "Pot":
			checkName("Pot ");
			break;
		case "Sleim": 
			checkName("Sleim ");
			break;
		case "Demon": 
			checkName("Demon ");
			break;
		case "Held": 
			checkName("Held ");
			break;
		case "Magier": 
			checkName("Magier ");
			break;
		case "Kobold": 
			checkName("Kobold ");
			break;
		case "Ninja":
			checkName("Ninja ");
			break;
		case "Spiderman": 
			checkName("Spiderman ");
			break;
		}
	}
	
	private void checkName(String characterName){
		for(int i = 0; i < getComponentCount() ; i++){
			if (getComponent(i) instanceof JLabel) {
				JLabel label = (JLabel)getComponent(i);
				String name = label.getText();
				if(name == characterName){
					label.setBorder(border);
				}
			}
		}
		repaint();
		revalidate();
	}

	public void removeBorder(){
		for(int i = 0; i < getComponentCount() ; i++){
			if (getComponent(i) instanceof JLabel) {
				JLabel label = (JLabel)getComponent(i);
				label.setBorder(new LineBorder(Color.BLACK, 0, false));

				}
			}
		}

	


}
