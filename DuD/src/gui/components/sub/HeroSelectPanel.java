package gui.components.sub;

import gui.manager.GuiGameConsole;
import gui.manager.GuiManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import persistence.character.CharacterData;
import persistence.character.CharacterData.STATUS;
import valueobject.Character;
import domain.DuD;
import domain.exceptions.MaxPlayerCountExceedException;
import domain.exceptions.NotAllPlayerSelectedException;
import domain.exceptions.SamePlayerSelectedException;


@SuppressWarnings("serial")
public class HeroSelectPanel extends JPanel implements ActionListener{

	private JPanel  characterButtonPanel, confirmPanel;
	private JButton[] characterButtons;
	private DuD game;
	private GuiGameConsole console;
	private CharacterData characterData;

	public HeroSelectPanel(){
		setUpPanel();
	}

	private void setUpPanel(){

		setLayout(new MigLayout("fill"));
		console = GuiGameConsole.getInstance();
		characterButtons = new JButton[5];
		createConfirmPanel();
		createCharacterButtonPanel();
		add(characterButtonPanel, "h 80%, span");
		add(console.getConsolePanel(),"h 20%, wrap");
		add(confirmPanel, "h 20%, wrap");
		setOpaque(false);
	}

	/**
	 * Erstellen des ButtonPanels
	 */
	private JPanel createCharacterButtonPanel(){
		characterButtonPanel = new JPanel(new GridLayout(1, 5));

		game = DuD.getGame();
		
		CharacterData status = null;

		characterButtonPanel.setPreferredSize(new Dimension(1024, 456));

		for(int i = 0; i < characterButtons.length; i++){
			status = game.getCharacterData(i);
			String characterName = status.getValue(STATUS.NAME);
			int buttonCount = characterButtonPanel.getComponentCount();
			System.out.println(buttonCount);
			switch(characterName){
			case "Held": 
				ImageIcon himage = new ImageIcon("resource/images/Hero.jpg"); 
				setCharacterButton(this, i, buttonCount, himage);
				break;

			case "Magier": 
				ImageIcon mimage = new ImageIcon("resource/images/Mage.jpeg");
				setCharacterButton(this, i, buttonCount, mimage);
				break;

			case "Kobold": 
				ImageIcon gimage = new ImageIcon("resource/images/Goblin.jpg");
				setCharacterButton(this, i, buttonCount, gimage);
				break;

			case "Ninja":
				ImageIcon nimage = new ImageIcon("resource/images/Ninja.jpg");
				setCharacterButton(this, i, buttonCount, nimage);
				break;

			case "Spiderman": 
				ImageIcon simage = new ImageIcon("resource/images/Spiderman.jpg"); 
				setCharacterButton(this, i, buttonCount, simage);
				break;

			}
		}

		characterButtonPanel.revalidate();
		return characterButtonPanel;
	}

	private void setCharacterButton(JPanel parentPanel,int index, int buttonCount,ImageIcon characterIcon){
		characterButtons[index] = new JButton();
		characterButtons[index].setPreferredSize(new Dimension(152, 456));
		characterButtons[index].setOpaque(false);
		characterButtons[index].setIcon(resizeImage(characterIcon, characterButtons[index]));
		characterButtons[index].setMargin(new Insets(0, 0, 0, 0));
		characterButtons[index].setDisabledIcon(characterIcon); 
		characterButtons[index].addActionListener(this);
		characterButtonPanel.add(characterButtons[index], buttonCount);
	}

	private JPanel createConfirmPanel(){
		confirmPanel = new JPanel(new BorderLayout());
		JButton playerAddButton = new JButton("Add Player");
		JButton conirmButton = new JButton("Confirm");
		playerAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(characterData != null){
					if(game.getPlayerCount() < game.getMaxPlayerCount()){
					try {
						addPlayer();
					} catch (SamePlayerSelectedException e1) {
						e1.printStackTrace();
						return;
					}
					console.blankTextArea();
					console.appendln(characterData.getValue(STATUS.NAME)+" ist addiert");
					}else{
						try {
							throw new MaxPlayerCountExceedException(game.getMaxPlayerCount());
						} catch (MaxPlayerCountExceedException e1) {
							e1.printStackTrace();
							return;
						}
						
					}
				}
			}
		});
		
		conirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(game.getPlayerCount() != game.getMaxPlayerCount()){
					try {
						throw new NotAllPlayerSelectedException(game.getMaxPlayerCount(), game.getPlayerCount());
					} catch (NotAllPlayerSelectedException e1) {
						e1.getMessage();
						e1.printStackTrace();
					}
				}else{
					GuiManager.getInstance().actionPerformed(e);
				}
				
				
			}
		});
		confirmPanel.add(playerAddButton,BorderLayout.NORTH);
		confirmPanel.add(conirmButton, BorderLayout.CENTER);
		confirmPanel.setBackground(Color.BLACK);
		confirmPanel.setVisible(true);
		return confirmPanel;
	}

	/**
	 * Image resizer fuer die Buttons
	 * @param img
	 * @param b
	 */
	private ImageIcon resizeImage(ImageIcon img, JButton b){
		Image image = img.getImage();  
		Image newimg = image.getScaledInstance( 162, 456,  java.awt.Image.SCALE_SMOOTH ) ;  
		ImageIcon icon = new ImageIcon( newimg );
		return icon;
	}

	/**
	 * Layer getter
	 */
	public JPanel getHeroSelectPanel(){
		return this;
	}

	public JButton[] getCharacterButtons(){
		return characterButtons;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		showPlayerDetail(e);
	}
	
	
	private void showPlayerDetail(ActionEvent e){
		int characterIndex = 0;
		for(int i = 0; i < characterButtons.length; i++){
			if(characterButtons[i] == e.getSource()){
				characterIndex = i;
			}
		}
		game = DuD.getGame();
	    characterData = game.getCharacterData(characterIndex);
		console.showCharacterDetail(characterData);
		
	}
	
	private void addPlayer() throws SamePlayerSelectedException{
		Character selectedPlayer = game.createPlayer(characterData);
		for(Character player : game.getPlayers()){
			if(player.getName().equals(selectedPlayer.getName())){
				throw new SamePlayerSelectedException(player.getName());
				
			}
		}
		System.out.println("nooooos");
		game.addPlayer(selectedPlayer);
	}

}
