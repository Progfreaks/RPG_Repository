//package gui.events;
//
//import gui.creater.GuiEventCreator;
//import gui.creater.GuiGameConsole;
//import gui.creater.GuiManager;
//import gui.objects.sublayer.ButtonPanel;
//import gui.objects.sublayer.MenuPanel;
//import domain.DuD;
//import domain.exceptions.InvalidNumberException;
//
///**
// * Diese Klasse dient zu
// * TODO dies hat nicht mit map zu tun, deshalb sollte  dies Event nicht brauchen??
// * 
// * @author YOU_HEY
// *
// */
//public class GuiStartEvent extends GuiGameEvent   {
//
//	private DuD game;
//	private GuiManager guiMgr;
//	private GuiEventCreator eCreator;
//
//	public  GuiStartEvent(GuiEventCreator e) {
//		guiMgr = GuiManager.getInstance();
//		eCreator = e;
//	}
//
//	@Override
//	public void process() {
//
//		game = DuD.getGame();
//		guiMgr.createCharSelectPanel();
//		
//		boolean loop = true;
//		while(loop){
//		try {
//			game.addPlayer(game.createPlayer(GuiGameConsole.getInstance().selectCharacter()));
//			loop = false;
//		} catch (NumberFormatException  | InvalidNumberException e) {
//			e.printStackTrace();
//			
//			GuiGameConsole.getInstance().errorMsg(e.getMessage());
//			GuiGameConsole.getInstance().errorMsg("ungueltiges Zeichen! bitte nochmal eingeben");
//		}
//		}
//		
//		
//		setUpNewGame();
//	}
//
//
//	@Override
//	public void run() {
//		process();
//
//	}
//
//	private void setUpNewGame() {
//
//
////		guiMgr.removeFromMainPanel(0);                    
////		guiMgr.paintButtonPanel();
////		eCreator.setActionCalls(guiMgr.getButtonPanel().getButtonMatrix());
////		ButtonPanel buttonPanel  = guiMgr.getButtonPanel();
////		MenuPanel menuPanel = new MenuPanel();
////		eCreator.setRollCall(menuPanel.getRollButton());
////		guiMgr.createMainPanel(buttonPanel,menuPanel);
//
//	}
//
//
//}
