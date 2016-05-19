package gui.creater;

import gui.creater.GuiEventCreator.EVENT_TYPE;
import gui.objects.mainlayer.BackFrame;
import gui.objects.mainlayer.BackgroundPanel;
import gui.objects.mainlayer.MainPanel;
import gui.objects.sublayer.ButtonPanel;
import gui.objects.sublayer.MenuPanel;
import gui.objects.sublayer.NewOrLoadPanel;
import gui.objects.sublayer.singleOrMutliPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import persistence.gamestate.GameStatePanel;
import domain.DuD;
import domain.exceptions.InvalidNumberException;

public class GuiManager implements ActionListener, Runnable{

	private BackFrame backFrame;
	private GuiMapHandler mapHdr;
	private static GuiManager singleton;

	private MainPanel mainPanel;
	private BackgroundPanel backgroundPanel;
	private java.util.List<JPanel> panelList ;

	// Vermeidet dass die Instanz dieser Klasse von anderen Klassen erzeugt wird.
	private GuiManager() {

		mapHdr = GuiMapHandler.getInstance();
	}

	// Singleton Objekt. Gewaehrleistet dass es nur eine Instanz exsistiert.
	public static GuiManager getInstance(){
		if(singleton == null) singleton = new GuiManager();
		return singleton;
	}


	/**
	 * Erstellt das Buttonpanel, disabled alle Buttons.
	 */
	public void paintButtonPanel(){
		mapHdr.paintButtonPanel();
	}

	/**
	 * Getter-Methode feur das Buttonpanel.
	 * @return
	 */
	public ButtonPanel getButtonPanel() {
		return mapHdr.getButtonPanel();
	}





	public int[][] getBoardMatrix(){
		return mapHdr.getBoardMatrix();
	}


	/**
	 * Setzt Backlayer.
	 * 
	 * @param backLayer
	 */
	public void setBackFrame(BackFrame backLayer) {

		this.backFrame = backLayer;

	}




	public void initBackFrame(){
		panelList = new ArrayList<JPanel>();
		backFrame = new BackFrame("Bests RPG");
		backgroundPanel = new BackgroundPanel("");
		mainPanel = new MainPanel();
		backgroundPanel.add(mainPanel);
		backFrame.add(backgroundPanel, BorderLayout.CENTER);
		backFrame.setVisible(true);

	}

	/**
	 * Gibt Backlayer zurueck.
	 * 
	 * @return
	 */
	public BackFrame getBackFrame() {

		return this.backFrame;

	}

	/**
	 * Fuegt ein Panel in Backlayer.
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

	/**
	 * Entfernt ein Panel aus Backlayer.
	 * 
	 * @param i
	 */
	private void removeFromMainPanel(int i) {
		if(panelList.size() != 0){
			panelList.remove(i);
			mainPanel.remove(i);
		}

	}


	private void addToBackFrame(JPanel panel){
		backFrame.add(panel);
		backFrame.setVisible(true);
	}

	private void removeBackgroundPanel(){
		backFrame.remove(backgroundPanel);
	}


	private void addToBackgroundPanel(JPanel panel){
		backgroundPanel.add(panel);
		backgroundPanel.setVisible(true);
	}



	enum PANEL_STATE{
		LOAD,SINGLE,SELECT;
	}
	PANEL_STATE panelState;


	public void createNewOrLoadPanel(){

		panelState = PANEL_STATE.LOAD;
		removeBackgroundPanel();
		removeFromMainPanel(0);
		NewOrLoadPanel loadPanel = new NewOrLoadPanel();
		addToMainPanel(loadPanel, 0, "w 100%, wrap");
		backgroundPanel = new BackgroundPanel("resource/images/introscreen.jpeg");
		addToBackgroundPanel(mainPanel);
		addToBackFrame(backgroundPanel);
		refresh();

	}

	private void createSingleOrMultiPanel(){
		panelState = PANEL_STATE.SINGLE;
		removeBackgroundPanel();
		removeFromMainPanel(0);
		singleOrMutliPanel singleOrMutliPanel = new singleOrMutliPanel();
		addToMainPanel(singleOrMutliPanel, 0, "w 100%, wrap");
		backgroundPanel = new BackgroundPanel("resource/images/introscreen.jpeg");
		addToBackgroundPanel(mainPanel);
		addToBackFrame(backgroundPanel);
		refresh();


	}

	private void createCharSelectPanel(){

		removeBackgroundPanel();
		removeFromMainPanel(0);
		JPanel consolePanel = GuiGameConsole.getInstance().getConsole();
		addToMainPanel(consolePanel, 0 ,"w 100%, wrap");
		backgroundPanel = new BackgroundPanel("resource/images/character.jpg");
		addToBackgroundPanel(mainPanel);
		addToBackFrame(backgroundPanel);
		refresh();
		panelState = PANEL_STATE.SELECT;
	}




	public void createMainPanel() {
		removeBackgroundPanel();
		removeFromMainPanel(0);

		paintButtonPanel();
		GuiEventCreator eCreator = new GuiEventCreator(EVENT_TYPE.DEFAULT);
		eCreator.setActionCalls(getButtonPanel().getButtonMatrix());
		ButtonPanel buttonPanel  = getButtonPanel();
		MenuPanel menuPanel = new MenuPanel();
		eCreator.setRollCall(menuPanel.getRollButton());
		addToMainPanel(buttonPanel.getButtonPanel(), 0, "push, height 550:550:550, width 550:550:550");
		addToMainPanel(menuPanel.getMenuPanel(), 1, "w 10%");
		addToMainPanel(GuiGameConsole.getInstance().getConsole(), 2, ("w 50%, wrap"));
		addToMainPanel(new GameStatePanel(), 3, ("w 50%, wrap"));
		backgroundPanel = new BackgroundPanel("resource/images/back.jpg");
		addToBackgroundPanel(mainPanel);
		addToBackFrame(backgroundPanel);
		refresh();
	}





	public void paintMoveRange(int num){

		mapHdr.paintMoveRange(num);
		//Entfernt das alte Buttonpanel
		removeFromMainPanel(0);
		//Fuegt das neue buttonpanel ein
		addToMainPanel(mapHdr.getButtonPanel(), 0,"push, height 550:550:550, width 550:550:550");
		refresh();
	}



	/**
	 * Faerbt das geclickte Button erneut und das Buttonpanel wieder.
	 * @param xf Position des geclickten Buttons 
	 * @param yf Position des geclickten Buttons 
	 */
	public void repaintButton(int xf, int yf){
		mapHdr.repaintButton(xf, yf);
		//Entfernt das alte Buttonpanel
		removeFromMainPanel(0);
		//Fuegt das neue buttonpanel ein
		addToMainPanel(mapHdr.getButtonPanel(), 0, "push, height 550:550:550, width 550:550:550");
		refresh();
	}

	/**
	 * Revalidiert und repaint das Backframe.
	 */
	public void refresh() {
		backFrame.revalidate();
		backFrame.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {


		Thread thread = new Thread(this);
		thread.start();


	}

	@Override
	public void run() {

		switch (panelState) {
		case LOAD:
			createSingleOrMultiPanel();
			break;

		case SINGLE:
			createCharSelectPanel();
			System.out.println("dd");
			DuD game = DuD.getGame();
			boolean loop = true;
			while(loop){
				try {
					game.addPlayer(game.createPlayer(GuiGameConsole.getInstance().selectCharacter()));
					loop = false;
				} catch (NumberFormatException  | InvalidNumberException e) {
					e.printStackTrace();

					GuiGameConsole.getInstance().errorMsg(e.getMessage());
					GuiGameConsole.getInstance().errorMsg("ungueltiges Zeichen! bitte nochmal eingeben");
				}
			}
			System.out.println("end");


			createMainPanel();


			break;

		case SELECT:



			break;


		default:
			break;
		}





	}




}
