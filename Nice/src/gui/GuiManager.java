package gui;

import gui.objects.BackFrame;
import gui.objects.ButtonPanel;
import gui.objects.MenuPanel;

import javax.swing.JPanel;

public class GuiManager {

	private BackFrame backFrame;
	private MapHandler mapHdr;
	private static GuiManager singleton;

	// Vermeidet dass die Instanz dieser Klasse von anderen Klassen erzeugt wird.
	private GuiManager() {
		
		mapHdr = MapHandler.getInstance();
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
	public void addPanel(JPanel panel, int i, String in) {

		backFrame.addPanel(panel, i, in);

	}

	/**
	 * Entfernt ein Panel aus Backlayer.
	 * 
	 * @param i
	 */
	public void removePanel(int i) {

		backFrame.removePanel(i);

	}



	public void createMainPanel(ButtonPanel buttonLayer,MenuPanel menuLayer) {
		backFrame.createMainPanel(buttonLayer,  menuLayer);
	}

	public void createOpeningPanel(){
		backFrame.createOpeningPanel();
	}
	

	public void paintMoveRange(int num){

		mapHdr.paintMoveRange(num);
		removePanel(0);
		addPanel(mapHdr.getButtonPanel(), 0,"push, height 400:400:400, width 400:400:400");
		BackFrame backLayer = getBackFrame();
		backLayer.refresh();
	}
	
	
	
	public void repaintButton(int xf, int yf){
		mapHdr.repaintButton(xf, yf);
		
		removePanel(0);
		addPanel(mapHdr.getButtonPanel(), 0,
				"push, height 400:400:400, width 400:400:400");
		BackFrame backLayer = getBackFrame();
		backLayer.refresh();
	}

	public void refresh() {

		backFrame.refresh();

	}
	
	
	
	
}
