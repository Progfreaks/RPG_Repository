package gui;

import gui.EventCreator.EVENT_TYPE;
import gui.objects.BackFrame;
import gui.objects.ButtonPanel;
import gui.objects.MenuPanel;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GuiManager {

	private static BackFrame backFrame;
	private MapHandler mapHdr;

	/**
	 * Konstruktor dieser Klasse.
	 * 
	 * @param centralGui
	 */
	public GuiManager() {
		
		mapHdr = new MapHandler();
	}

	
	
	
	public void paintButtonPanel(){
		mapHdr.paintButtonPanel();
	}
	
	public void setButtonPanel(ButtonPanel panel) {
		mapHdr.setButtonPanel(panel); 
	}
	
	public ButtonPanel getButtonPanel() {
		return mapHdr.getButtonPanel();
	}

	public void setButtonMatrix(JButton[][] buttons){
		mapHdr.setButtonMatrix(buttons);
	}
	
	public JButton[][] getButtonMatrix(){
		return mapHdr.getButtonMatrix();
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
	
	public void setNewEvent(JButton button){
		button.addActionListener(new EventCreator(EVENT_TYPE.NEW_GAME));
	}
	
	
}
