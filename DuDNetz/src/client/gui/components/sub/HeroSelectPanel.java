package client.gui.components.sub;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import server.domain.DuD;
import server.domain.exceptions.MaxPlayerCountExceedException;
import server.domain.exceptions.NotAllPlayerSelectedException;
import server.domain.exceptions.SamePlayerSelectedException;
import server.persistence.characterdata.CharacterData;
import server.persistence.characterdata.CharacterData.STATUS;
import client.gui.manager.GuiGameConsole;
import client.gui.manager.GuiManager;
import client.net.Facade;
import common.valueobject.Character;


@SuppressWarnings("serial")
public class HeroSelectPanel extends JPanel implements ActionListener{

	private JPanel  characterButtonPanel, confirmPanel;
	private JButton[] characterButtons;
	private DuD game;
	private Facade facade;
	private Facade single;
	private GuiGameConsole console;
	private CharacterData characterData;
	private GuiManager guiManager;
	private int playerIndex;
	
	public HeroSelectPanel(GuiManager g){
		guiManager = g;
		facade = g.getFacede();
		single = g.getSingle();
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
		characterButtonPanel = new JPanel(new MigLayout("fill"));

		characterButtonPanel.setOpaque(false);
		game = DuD.getGame();

		CharacterData status = null;


		for(int i = 0; i < characterButtons.length; i++){
			status = game.getCharacterData(i);
			String characterName = status.getValue(STATUS.NAME);
			System.out.println("(Heropanel) charactername "+characterName+"\n");
			if(status.getValue(STATUS.HP).equals("0")) JOptionPane.showMessageDialog(null,"keine character data existieren \n bitte bei [persistence -> SetUp] starten und einstellen ", "", JOptionPane.ERROR_MESSAGE);

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
		characterButtonPanel.setOpaque(false);
		return characterButtonPanel;
	}

	private void setCharacterButton(JPanel parentPanel,int index, int buttonCount,ImageIcon characterIcon){
		characterButtons[index] = new JButton();
		characterButtons[index].setOpaque(false);
		characterButtons[index].setIcon(resizeImage(characterIcon, characterButtons[index]));
		characterButtons[index].setMargin(new Insets(0, 0, 0, 0));
		characterButtons[index].setDisabledIcon(characterIcon); 
		characterButtons[index].addActionListener(this);
		if(buttonCount == 0){
			characterButtonPanel.add(characterButtons[index],"gap 0");
		}else if(buttonCount == 1){
			characterButtonPanel.add(characterButtons[index],"gap 0,cell 2 0,wrap");//, buttonCount);
		}else{
			characterButtonPanel.add(characterButtons[index]);
		}
		
	}

	private JPanel createConfirmPanel(){
		confirmPanel = new JPanel(new BorderLayout());
		JButton playerAddButton = new JButton("Add Player");
		JButton conirmButton = new JButton("Confirm");
		playerAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(characterData != null){
						if(addPlayer().equals("no")){
//							JOptionPane.showMessageDialog(null, "ex (Hero panel) (createconfirmpanel)", "", JOptionPane.ERROR_MESSAGE);

							
						}else{
							console.blankTextArea();
							//console.appendln(characterData.getValue(STATUS.NAME)+" ist addiert");
							JOptionPane.showMessageDialog(null, characterData.getValue(STATUS.NAME)+" ist addiert \n Bitte auf Confirm Button druecken", "", JOptionPane.PLAIN_MESSAGE);


							guiManager.setClientName(characterData.getValue(STATUS.NAME));
							guiManager.setClientIndex(playerIndex);
						}
					
				}
			}
		});

		conirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GuiManager.getInstance().actionPerformed(e);
//				if(facade.getPlayerCount() != facade.getMaxPlayerCount()){
//					try {
//						throw new NotAllPlayerSelectedException(facade.getMaxPlayerCount(), facade.getPlayerCount());
//
//					} catch (NotAllPlayerSelectedException e1) {
//						JOptionPane.showMessageDialog(null, e1.getMessage(), "not all player selected", JOptionPane.ERROR_MESSAGE);
//
//					}
//				}else{
//					GuiManager.getInstance().actionPerformed(e);
//				}


			}
		});
		confirmPanel.add(playerAddButton,BorderLayout.NORTH);
		confirmPanel.add(conirmButton, BorderLayout.CENTER);
		confirmPanel.setBackground(Color.BLACK);
		confirmPanel.setVisible(true);
		confirmPanel.setOpaque(false);
		return confirmPanel;
	}

	/**
	 * Image resizer fuer die Buttons
	 * @param img
	 * @param b
	 */
	private ImageIcon resizeImage(ImageIcon img, JButton b){
		Image image = img.getImage();  
		Image newimg = image.getScaledInstance( WindowSize.getWindowWidth(9), WindowSize.getWindowWidth(7),  java.awt.Image.SCALE_SMOOTH ) ;  
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
		playerIndex = characterIndex;

	}

	private String addPlayer() {
//		return guiManager.getFacede().addPlayer(playerIndex);
		System.out.println("singe "+single);
		return single.addPlayer(playerIndex);
		
	}

}
