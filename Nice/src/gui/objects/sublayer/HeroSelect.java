package gui.objects.sublayer;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Insets;

import persistence.character.CharacterData;
import persistence.character.CharacterDataMap;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.border.*;
import gui.EventCreator;
import gui.EventCreator.EVENT_TYPE;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import net.miginfocom.swing.MigLayout;


public class HeroSelect{
	private JPanel layerPanel, buttonLayer, confLayer;
	private JButton[] buttons;
	private JButton button;
	private EventCreator eCreator;
	private static CharacterDataMap map = CharacterDataMap.getInstance();

	public HeroSelect(){
		
		layerPanel = new JPanel(new MigLayout("fill"));
		confLayer = new JPanel(new BorderLayout());
		buttonLayer = new JPanel(new GridLayout(1, 5));
		buttons = new JButton[5];
		eCreator = new EventCreator(EVENT_TYPE.DEFAULT);
		setUp();
		
	}
	private void setUp(){
		layerPanel.add(createButtons(), "h 80%, span");
		layerPanel.add(confLayer(), "h 20%, wrap");
		layerPanel.setOpaque(false);
	}
	
	
	

	
	/**
	 * Erstellen des ButtonPanels
	 */
	private JPanel createButtons(){
		CharacterData status = null;
		buttonLayer.setPreferredSize(new Dimension(1024, 456));
		for(int i = 0; i < buttons.length; i++){
			buttons[i] = new JButton();
			buttons[i].setPreferredSize(new Dimension(152, 456));
			status = map.getCharacterData(i);
			String name = status.getValue(status.NAME);
			switch(name){
			case "Held": 
				ImageIcon himage = new ImageIcon("resource/images/Hero.jpg"); buttons[i].setOpaque(false);
						 
			    buttons[i].setIcon(resizeImg(himage, buttons[i])); buttons[i].setDisabledIcon(himage); 
						 
			    eCreator.setCharCalls(buttons[i]); buttonLayer.add(buttons[i], 0); break;
			    
			case "Magier": 
				ImageIcon mimage = new ImageIcon("resource/images/Mage.jpeg"); buttons[i].setOpaque(false);
			 			   
				buttons[i].setIcon(resizeImg(mimage, buttons[i])); buttons[i].setDisabledIcon(mimage); 
			 			  
				eCreator.setCharCalls(buttons[i]); buttonLayer.add(buttons[i], 1); break;
				
			case "Kobold": 
				ImageIcon gimage = new ImageIcon("resource/images/Goblin.jpg"); buttons[i].setOpaque(false);
			   	 		   
				buttons[i].setIcon(resizeImg(gimage, buttons[i])); buttons[i].setDisabledIcon(gimage); 
			   	 		   
				eCreator.setCharCalls(buttons[i]); buttonLayer.add(buttons[i], 2); break;
				
			case "Ninja":
				ImageIcon nimage = new ImageIcon("resource/images/Ninja.jpg"); buttons[i].setOpaque(false);
			   		     
				buttons[i].setIcon(resizeImg(nimage, buttons[i])); buttons[i].setDisabledIcon(nimage); 
			   		     
				eCreator.setCharCalls(buttons[i]); buttonLayer.add(buttons[i], 3); break;
				
			case "Spiderman": 
				ImageIcon simage = new ImageIcon("resource/images/Spiderman.jpg"); buttons[i].setOpaque(false);
			   		         
				buttons[i].setIcon(resizeImg(simage, buttons[i])); buttons[i].setDisabledIcon(simage); 
			   		         
				eCreator.setCharCalls(buttons[i]); buttonLayer.add(buttons[i], 4); break;
				
			
			}
			
		}
		buttonLayer.revalidate();
		eCreator.setButtons(buttons);
		
		return buttonLayer;
	}
	private JPanel confLayer(){
		JButton confButton = new JButton("Confirm");
		eCreator.setStartCall(confButton);//StartEvent !GuiStartEvent
		confLayer.add(confButton, BorderLayout.CENTER);
		confLayer.setBackground(Color.BLACK);
		confLayer.setVisible(true);
		
		return confLayer;
	}
	/**
	 * Image resizer für die Buttons
	 * @param img
	 * @param b
	 */
	private ImageIcon resizeImg(ImageIcon img, JButton b){
		   
		   Image image = img.getImage();  
		   Image newimg = image.getScaledInstance( 152, 456,  java.awt.Image.SCALE_SMOOTH ) ;  
		   ImageIcon icon = new ImageIcon( newimg );
		   return icon;
	}
	/**
	 * Layer getter
	 */
	public JPanel getHeroSelect(){
		return layerPanel;
	}
	public JButton[] getButtons(){
		return buttons;
	}

}
