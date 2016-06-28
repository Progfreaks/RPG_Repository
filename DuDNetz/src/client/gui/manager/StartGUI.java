package client.gui.manager;



public class StartGUI {

	/**
	 * Konstruktor dieser Klasse.
	 */
	public StartGUI() {
		GuiManager  guiMgr = GuiManager.getInstance();
		guiMgr.createNewOrLoadPanel();
	}

	public static void main(String[] args) {
		new StartGUI();
	}



}
