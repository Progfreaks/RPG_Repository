package client.gui.manager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import server.domain.DuD;
import client.gui.components.main.BackFrame;
import client.gui.components.main.BackgroundPanel;
import client.gui.components.main.MainPanel;
import client.gui.components.sub.BattlePanel;
import client.gui.components.sub.GameStatePanel;
import client.gui.components.sub.HeroSelectPanel;
import client.gui.components.sub.HowManyPlayerPanel;
import client.gui.components.sub.MapButtonPanel;
import client.gui.components.sub.NewOrLoadPanel;
import client.gui.components.sub.RollPanel;
import client.gui.components.sub.SingleOrMutliPanel;
import client.gui.components.sub.WindowSize;
import client.gui.manager.GuiGameCycle.PHASE;
import client.net.Facade;

import common.interfaces.DuDInterface;

public class GuiManager implements ActionListener, Runnable{

	private static GuiManager singleton;
	private BackFrame backFrame;
	private BackgroundPanel backgroundPanel;
	private MainPanel mainPanel;
	private HeroSelectPanel heroSelectPanel;
	private java.util.List<JPanel> panelList ;
	private DuDInterface game;
	private GuiMapHandler mapHdr;
	private GuiGameConsole console;
	private MapButtonPanel mapButtonPanel;
	private BattlePanel battlePanel;
	private Facade multi;
	private Facade single;
	private ObjectOutputStream objectOutputStream;



	// Vermeidet dass die Instanz dieser Klasse von anderen Klassen erzeugt wird.
	private GuiManager() {
		createSocket();
		//		cycle = new GuiGameCycle();
		mapHdr = GuiMapHandler.getInstance();
		//		mapHdr.setCycle(cycle);
		mapHdr.setFacede(multi);
		mapHdr.setSingle(single);
		console = GuiGameConsole.getInstance();
		game = DuD.getGame();
		initBackFrame();

	}

	//	public GuiGameCycle getCycle(){
	//		return cycle;
	//	}

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

		//createSocket();


	}

	private void createSocket(){
		Socket socket = null;
		Socket socket2 = null;
		try {
			socket = new Socket("localhost", 10000);
			socket2 = new Socket("localhost", 9999);
		} catch (UnknownHostException e) {
			System.err.println("cannot recognize host: " + e);
		} catch (IOException e) {
			System.err.println("exception by MyChatClient " + e);
		}
		single = new Facade(socket2);
		multi =new Facade(socket, this);
		multi.start();
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
		heroSelectPanel = new HeroSelectPanel(this);
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


		addToMainPanel(mapButtonPanel, 0, getProperSize());

		addToMainPanel(console.getConsolePanel(), 1, ("gap 45,w 85%, wrap"));
		//		addToMainPanel(new RollPanel(), 2, "w 10%,wrap");
		addToMainPanel(new RollPanel(multi), 2, "gap 160,cell 0 2");
		//		addToMainPanel(new GameStatePanel(), 3, ("w 10%, wrap"));
		addToMainPanel(new GameStatePanel(), 3, ("cell 0 2"));
		backgroundPanel = new BackgroundPanel("resource/images/back.jpg");
		addToBackgroundPanel(mainPanel);
		addToBackFrame(backgroundPanel);
		refreshBackFrame();
		//		new Thread(this).start();

	}


	public void createBattlePanel(common.valueobject.Character enemy){
		removeBackgroundPanel();
		removeComponentFromMainPanel();
		battlePanel = new BattlePanel(this,enemy);
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

	public void createPanel(String panelName){
		System.out.println("guimanager got panel string: "+panelName);

		switch (panelName) {

		case "New game":
			createCharSelectPanel();
			break;

		case "Confirm":
			createCharSelectPanel();
			createMainDungeonPanel();
			GuiGameCycle.getInstance().setFacade(multi);
			GuiGameCycle.getInstance().setFacadeSingle(single);

			startGame();
			break;
			
		case "Battle":
			//createBattlePanel(null);
			break;
			
		case "Main":
			createMainDungeonPanel();
			break;

		default:
			break;
		}
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonText = ((JButton)e.getSource()).getText();

		switch (buttonText) {

		case "New game":
			multi.sendPanelName(buttonText);
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
		case "Confirm":
			multi.sendPanelName(buttonText);
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

	private String getProperSize(){
		String size = Integer.toString(WindowSize.getWindowWidth(2.8));
		String size2 = Integer.toString(WindowSize.getWindowWidth(3));

		return "gap 50, height "+size+":"+size+":"+size+", width "+size+":"+size+":"+size+",wrap";
	}

	@Override
	public void run() {
		//GuiGameCycle.getInstance().setPhase(PHASE.DEFAULT);

		GuiGameCycle.getInstance().startGame();


	}

	public void startGame(){
		new Thread(this).start();
	}

	public Facade getFacede(){
		return multi;
	}

	public Facade getSingle(){
		return single;
	}
	
	static String  ClientName;
	public  void setClientName(String name){
		ClientName = name;
	}
	
	static int ClientIndex;
	public void setClientIndex(int index){
		ClientIndex = index;
	}
	
	






}
