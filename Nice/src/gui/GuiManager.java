package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;

import gui.objects.BackFrame;
import gui.objects.ButtonPanel;
import gui.objects.ImagePainter;
import gui.objects.BackgroundPanel;
import gui.objects.MenuPanel;
import gui.objects.MainPanel;

import javax.swing.JPanel;
import javax.swing.JButton;
import gui.objects.sublayer.*;
import net.miginfocom.swing.MigLayout;
import persistence.gamestate.GameStatePanel;

public class GuiManager {

	private BackFrame backFrame;
	private GuiMapHandler mapHdr;
	private static GuiManager singleton;
	
	private MainPanel mainPanel;
	private BackgroundPanel backgroundPanel;
	private HeroSelect charSel;
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
	public JButton[] getCharArray(){
		return charSel.getButtons();
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
	public void addToMainPanel(JPanel panel, int i, String in) {

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
	public void removeFromMainPanel(int i) {
		panelList.remove(i);
		mainPanel.remove(i);

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


	public void createCharSelectPanel(){
		charSel = new HeroSelect();
		removeBackgroundPanel();
		removeFromMainPanel(0);
		//JPanel consolePanel = GuiGameConsole.getInstance().getConsole();
		addToMainPanel(charSel.getHeroSelect(), 0 ,"grow");
		backgroundPanel = new BackgroundPanel("resource/images/CharSelImg.jpg");
		addToBackgroundPanel(mainPanel);
		addToBackFrame(backgroundPanel);
		refresh();
		
	}




	public void createMainPanel(ButtonPanel buttonLayer, MenuPanel menuLayer) {
		removeBackgroundPanel();
		removeFromMainPanel(0);
		addToMainPanel(buttonLayer.getButtonPanel(), 0, "push, height 550:550:550, width 550:550:550");
		addToMainPanel(menuLayer.getMenuPanel(), 1, "w 10%");
		addToMainPanel(GuiGameConsole.getInstance().getConsole(), 2, ("w 50%, wrap"));
		addToMainPanel(new GameStatePanel(), 3, ("w 50%, wrap"));
		backgroundPanel = new BackgroundPanel("resource/images/back.jpg");
		addToBackgroundPanel(mainPanel);
		addToBackFrame(backgroundPanel);
		refresh();
	}
	public void createIntroScreen(){
		removeBackgroundPanel();
		IntroScreen introPanel = new IntroScreen();
		addToMainPanel(introPanel.getIntroScreen(), 0, "grow, push, span");
		backgroundPanel = new BackgroundPanel("resource/images/introscreen.jpeg");
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




}
