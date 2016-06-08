package gui.manager;

import gui.components.main.BackFrame;
import gui.components.main.BackgroundPanel;
import gui.components.main.MainPanel;
import gui.components.sub.BattlePanel;
import gui.components.sub.GameStatePanel;
import gui.components.sub.HeroSelectPanel;
import gui.components.sub.HowManyPlayerPanel;
import gui.components.sub.MapButtonPanel;
import gui.components.sub.MenuPanel;
import gui.components.sub.NewOrLoadPanel;
import gui.components.sub.SingleOrMutliPanel;
import gui.manager.GuiGameCycle.PHASE;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import domain.DuD;

public class GuiManager implements ActionListener, Runnable{

	private static GuiManager singleton;
	private BackFrame backFrame;
	private BackgroundPanel backgroundPanel;
	private MainPanel mainPanel;
	private HeroSelectPanel heroSelectPanel;
	private java.util.List<JPanel> panelList ;
	private DuD game;
	private GuiMapHandler mapHdr;
	private GuiGameConsole console;
	private MapButtonPanel mapButtonPanel;
	private BattlePanel battlePanel;

	// Vermeidet dass die Instanz dieser Klasse von anderen Klassen erzeugt wird.
	private GuiManager() {
		mapHdr = GuiMapHandler.getInstance();
		console = GuiGameConsole.getInstance();
		game = DuD.getGame();
		initBackFrame();
	}

	// Singleton Objekt. Gewaehrleistet dass es nur eine Instanz exsistiert.
	public static GuiManager getInstance(){
		if(singleton == null) singleton = new GuiManager();
		return singleton;
	}

	/**
	 * initialisiert das Backframe.
	 */
	private void initBackFrame(){
		panelList = new ArrayList<JPanel>();
		backFrame = new BackFrame("Bests RPG");
		backgroundPanel = new BackgroundPanel("");
		mainPanel = new MainPanel();
		backFrame.setVisible(true);

	}

	public void createNewOrLoadPanel(){
		NewOrLoadPanel newOrLoadPanel = new NewOrLoadPanel();
		addToMainPanel(newOrLoadPanel, 0, "grow, push, span");
		backgroundPanel = new BackgroundPanel("resource/images/introscreen.jpeg");
		addToBackgroundPanel(mainPanel);
		addToBackFrame(backgroundPanel);
		refreshBackFrame();

	}

	private void createSingleOrMultiPanel(){
		removeBackgroundPanel();
		removeComponentFromMainPanel();
		SingleOrMutliPanel singleOrMutliPanel = new SingleOrMutliPanel();
		addToMainPanel(singleOrMutliPanel, 0, "w 100%, wrap");
		backgroundPanel = new BackgroundPanel("resource/images/introscreen.jpeg");
		addToBackgroundPanel(mainPanel);
		addToBackFrame(backgroundPanel);
		refreshBackFrame();


	}
	
	public void createHowManyPlayerPanel(){
		removeBackgroundPanel();
		removeComponentFromMainPanel();
		HowManyPlayerPanel howManyPlayerPanel = new HowManyPlayerPanel();
		addToMainPanel(howManyPlayerPanel, 0, "w 100%, wrap");
		backgroundPanel = new BackgroundPanel("resource/images/introscreen.jpeg");
		addToBackgroundPanel(mainPanel);
		addToBackFrame(backgroundPanel);
		refreshBackFrame();
		
	}


	public void createCharSelectPanel(){
		heroSelectPanel = new HeroSelectPanel();
		removeBackgroundPanel();
		removeComponentFromMainPanel();
		addToMainPanel(heroSelectPanel.getHeroSelectPanel(), 0 ,"grow");
		backgroundPanel = new BackgroundPanel("resource/images/CharSelImg.jpg");
		addToBackgroundPanel(mainPanel);
		addToBackFrame(backgroundPanel);
		refreshBackFrame();
	}



	
	public void createMainDungeonPanel() {
		removeBackgroundPanel();
		removeComponentFromMainPanel();
		if(mapHdr.getMapButtonPanel() == null) mapButtonPanel = setUpMapButtonPanel();
		addToMainPanel(mapButtonPanel, 0, "push, height 550:550:550, width 550:550:550");
		addToMainPanel(new MenuPanel(), 1, "w 10%");
		addToMainPanel(console.getConsolePanel(), 2, ("w 30%, wrap"));
		addToMainPanel(new GameStatePanel(), 3, ("w 50%, wrap"));
		backgroundPanel = new BackgroundPanel("resource/images/back.jpg");
		addToBackgroundPanel(mainPanel);
		addToBackFrame(backgroundPanel);
		refreshBackFrame();
//		new Thread(this).start();

	}
	
	
	public void createBattlePanel(valueobject.Character enemy){
		removeBackgroundPanel();
		removeComponentFromMainPanel();
		battlePanel = new BattlePanel(enemy);
		addToMainPanel(battlePanel, 0,  "h 100%,w 100%");
		backgroundPanel = new BackgroundPanel("resource/images/back.jpg");
		addToBackgroundPanel(mainPanel);
		addToBackFrame(backgroundPanel);
		refreshBackFrame();
	}

	
	public void setBorderByBattlePanel(String name){
		battlePanel.setBorder(name);
		battlePanel.repaint();
		battlePanel.revalidate();

	}
	
	public void removeBorderByBattlePanel(){
		battlePanel.removeBorder();
		battlePanel.repaint();
		battlePanel.revalidate();

	}



	/**
	 * Revalidiert und repaint das Backframe.
	 */
	public void refreshBackFrame() {
		backFrame.revalidate();
		backFrame.repaint();
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonText = ((JButton)e.getSource()).getText();
		System.out.println(buttonText);
		
		switch (buttonText) {

		case "New game":
			createSingleOrMultiPanel();
			break;

		case "Load game":
			loadGame();

			if(!checkExistSaveData()){
				System.out.println("no save data");
				JLabel alertLabel = new JLabel("keine Datei exsistiert");
				alertLabel.setForeground(Color.RED);
				JOptionPane.showMessageDialog(backFrame, alertLabel);
			}else{
				createMainDungeonPanel();
				startGame();

			}
			break;

		case "Singleplayer":
			createCharSelectPanel();
			game.setMaxPlayerCount(1);
			break;
			
		case "Multiplayer":
			createHowManyPlayerPanel();
			break;
			
		case "OK":
			createCharSelectPanel();
			break;


		case "Confirm":
			createMainDungeonPanel();
			startGame();

			break;

		case "save":
			saveGameState();
			break;
		default:
			break;
		}

	}

	//-------ganze hilfs Methoden-----------
	
	private void saveGameState(){
		game.saveState();
		console.appendln("\nDie Datei wurde gespeichert");
	}

	private MapButtonPanel setUpMapButtonPanel(){
		mapHdr.setUpMapButtonPanel();
		return mapHdr.getMapButtonPanel();
	}


	/**
	 * Fuegt das uebergebene Panel in Mainpanel ein.
	 * 
	 * @param panel
	 * @param i
	 */
	private void addToMainPanel(JPanel panel, int i, String in) {
		panelList.add(i, panel);
		mainPanel.add(panel, in, i);
		mainPanel.setVisible(true);
		backFrame.revalidate();
	}


	
	private void removeComponentFromMainPanel() {
		if(mainPanel.getComponentCount() != 0){
			mainPanel.removeAll();;
		}

	}


	private void addToBackFrame(JPanel panel){
		backFrame.add(panel);
		backFrame.setVisible(true);
	}

	private void removeBackgroundPanel(){
		if(backgroundPanel != null)
			backFrame.remove(backgroundPanel);
	}


	private void addToBackgroundPanel(JPanel panel){
		backgroundPanel.add(panel);
		backgroundPanel.setVisible(true);
	}


	private void loadGame(){
		game.loadState();
		
	}

	private boolean checkExistSaveData(){
		return game.getSavedBoardMatrix() != null;
	}

	@Override
	public void run() {
		GuiGameCycle.getInstance().setPhase(PHASE.DEFAULT);
		GuiGameCycle.getInstance().startGame();
		
	}
	
	public void startGame(){
		new Thread(this).start();
	}





	
}
