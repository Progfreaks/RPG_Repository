package gui.manager;

import gui.components.sub.MapButtonPanel;
import gui.manager.GuiGameCycle.PHASE;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import persistence.character.ICharacterDefs;
import valueobject.IGameElements;
import domain.DuD;

/**
 * Betreut alles was mit dem Board zu tun hat. - Laden der Map aus Valueobject
 * Board - Recolourn der Squares bei Events (Stand pre Pictures) - Verwaltung
 * der begehbaren Feldern
 * 
 * @author T
 *
 */
public class GuiMapHandler implements IGameElements, ICharacterDefs{

	private DuD game;
	public  MapButtonPanel mapButtonPanel;
	private int[][] boardMatrix ;
	public  int diceNum;
	private JButton[][] buttonMatrix;
	private static GuiMapHandler singleton;// Singleton Objekt. Gewaehrleistet dass es nur eine Instanz exsistiert.
	private ActionListener fightListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			GuiGameCycle.getInstance().setClickedCoordinate(e);
			GuiGameCycle.getInstance().setPhase(PHASE.FIGHT);
			GuiGameCycle.getInstance().wake();
			
		}
	};

	private ActionListener moveListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			GuiGameCycle.getInstance().setClickedCoordinate(e);
			GuiGameCycle.getInstance().setPhase(PHASE.MOVE);
			GuiGameCycle.getInstance().wake();
		

		}
	};

	private ActionListener pickListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			GuiGameCycle.getInstance().setClickedCoordinate(e);
			GuiGameCycle.getInstance().setPhase(PHASE.PICK);
			GuiGameCycle.getInstance().wake();
			


		}
	};

	// Vermeidet dass die Instanz dieser Klasse von anderen Klassen erzeugt wird.
	private GuiMapHandler() {
		game = DuD.getGame();
	}

	/**
	 * Wenn es keine Instanz exsistiert, dann wird eine Instanz erzeugt. Wenn
	 * existiert, dann gibt die zurück.
	 * 
	 * @return
	 */
	public static GuiMapHandler getInstance() {
		if (singleton == null) singleton = new GuiMapHandler();
		return singleton;
	}

	public void setUpMapButtonPanel(){
		paintMapButtonPanel();
		game.setUpMapData();
		setActionCalls();
	}

	/**
	 * Erstellt den buttonLayer, disabled alle Buttons und leitet dies weiter an
	 * die Gui ueber game
	 */
	private void paintMapButtonPanel() {

		// Panel fuer MapButton. Das Panel ist zuerst leer.
		if(mapButtonPanel == null) {
			mapButtonPanel = new MapButtonPanel();
		}
		if(boardMatrix == null) {
			boardMatrix = game.getBoardMatrix();
		}
		// Setzt Button ins Panel
		for (int y = 0; y < boardMatrix.length; y++) {
			for (int x = 0; x < boardMatrix[y].length; x++) {
				mapButtonPanel.paintButtons(boardMatrix[y][x], y, x);// je nachdem welche Wert das Boardarray hat.
				mapButtonPanel.disable(mapButtonPanel.getButton(y, x));
			}
		}
	}


	/**
	 * Setzt die Events in die Buttons.
	 * 
	 * @param pButtons
	 */
	private void setActionCalls() {
		int[][] boardMatrix = game.getBoardMatrix();
		buttonMatrix = mapButtonPanel.getMapButtonMatrix();
		for (int i = 0; i < buttonMatrix.length; i++) {
			for (int s = 0; s < buttonMatrix[i].length; s++) {
				switch (boardMatrix[i][s]) {
				case WALL_ELEMENT:
					break; // Wall. No ActionEvente 
				case FLOOR_ELEMENT:// Freies Feld. MoveEvent
					buttonMatrix[i][s].addActionListener(moveListener);
					break; 
				case PICKUP_ELEMENT:// Item. MoveEvent + PickUpEvent
					buttonMatrix[i][s].addActionListener(pickListener);
					break; 
				case ZOMBIE_ELEMENT :
					game.addEnemy(game.createEnemy(ZOMBIE));
					buttonMatrix[i][s].addActionListener(fightListener);
					break;
				case GHOST_ELEMENT:
					game.addEnemy(game.createEnemy(GHOST));				
					buttonMatrix[i][s].addActionListener(fightListener);
					break;
				case POT_ELEMENT:
					game.addEnemy(game.createEnemy(POT));					
					buttonMatrix[i][s].addActionListener(fightListener);
					break;
				case SLEIM_ELEMENT:
					game.addEnemy(game.createEnemy(SLEIM));					
					buttonMatrix[i][s].addActionListener(fightListener);
					break;
				case MUMMY_ELEMENT:
					game.addEnemy(game.createEnemy(MUMMY));					
					buttonMatrix[i][s].addActionListener(fightListener);
					break;
				}
			}// <-- ende der for Schleife
		}// <- ende der for Schleife

	}


	public void placeBoss(){
		mapButtonPanel.paintButtons(10, 30, 4);
		game.setMapElement(10, 30, 11);
		
		buttonMatrix[30][4].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GuiGameCycle.getInstance().setPhase(PHASE.FIGHT);
				GuiGameCycle.getInstance().wake();
				GuiGameCycle.getInstance().setClickedCoordinate(e);				
			}
		});
		mapButtonPanel.refresh();
		//refreshBackFrame();
	}


	/**
	 * Getter-Methode feur das Buttonpanel.
	 * @return
	 */
	public MapButtonPanel getMapButtonPanel() {
		return mapButtonPanel;
	}






	/**
	 * Enabled die Buttons anhand der Wuerfelaugenzahl und gibt den begehbaren
	 * Buttons ne schnieke gruene Border Beachtet out of bounds und nicht
	 * passierbare walls refresh der gui
	 * 
	 * @param diceNum
	 */
	public void paintMoveRange(int diceNum) {
		this.diceNum = diceNum;
		int xPlayerCoord = game.getXCharCoord();
		int yPlayerCoord = game.getYCharCoord();
		boardMatrix = game.getBoardMatrix();

		for (int i = 1; i <= diceNum; i++) {

			if ((xPlayerCoord + i) < mapButtonPanel.getMapButtonMatrixColumnLength(yPlayerCoord) && (xPlayerCoord + i) >= 0) {

				setBorder(yPlayerCoord, xPlayerCoord + i);

			}

			if ((yPlayerCoord + i) < mapButtonPanel.getMapButtonMatrixRowLength() && (yPlayerCoord + i) >= 0) {

				setBorder(yPlayerCoord + i, xPlayerCoord);

			}

			if ((xPlayerCoord - i) < mapButtonPanel.getMapButtonMatrixColumnLength(yPlayerCoord) && (xPlayerCoord - i) >= 0) {
				setBorder(yPlayerCoord, xPlayerCoord - i);

			}

			if ((yPlayerCoord - i) < mapButtonPanel.getMapButtonMatrixRowLength() && (yPlayerCoord - i) >= 0) {
				setBorder(yPlayerCoord - i, xPlayerCoord);

			}

			if (i > 1) {

				if ((yPlayerCoord + (i - 1)) < mapButtonPanel.getMapButtonMatrixRowLength()) {

					for (int s = 1; s < diceNum; s++) {

						if ((xPlayerCoord + (diceNum - s)) < mapButtonPanel.getMapButtonMatrixColumnLength(yPlayerCoord) && (xPlayerCoord + (diceNum - (s))) >= 0) {

							setBorder(yPlayerCoord + (i - 1), xPlayerCoord + (diceNum - s));

						}

						if ((xPlayerCoord - (diceNum - s)) < mapButtonPanel.getMapButtonMatrixColumnLength(yPlayerCoord+ (i - 1))&& (xPlayerCoord - (diceNum - (s))) >= 0) {
							setBorder(yPlayerCoord + (i - 1), xPlayerCoord - (diceNum - s));

						}

					}
				}

				if ((yPlayerCoord - (i - 1)) < mapButtonPanel.getMapButtonMatrixRowLength()) {

					for (int s = 1; s < diceNum; s++) {

						if ((xPlayerCoord + (diceNum - s)) < mapButtonPanel.getMapButtonMatrixColumnLength(yPlayerCoord- (i - 1) )&& (xPlayerCoord + (diceNum - (s))) >= 0) {
							setBorder(yPlayerCoord - (i - 1), xPlayerCoord + (diceNum - s));

						}

						if ((xPlayerCoord - (diceNum - s)) < mapButtonPanel.getMapButtonMatrixColumnLength(yPlayerCoord- (i - 1)) && (xPlayerCoord - (diceNum - (s))) >= 0) {
							setBorder(yPlayerCoord - (i - 1), xPlayerCoord - (diceNum - s));

						}
					}
				}
			}// ende if statement
		}
	}


	private void undoMoveRange(int diceNum) {
		int xPlayerCoord = game.getXCharCoord();
		int yPlayerCoord = game.getYCharCoord();
		boardMatrix = game.getBoardMatrix();

		for (int i = 1; i <= diceNum; i++) {

			if ((xPlayerCoord + i) < mapButtonPanel.getMapButtonMatrixColumnLength(yPlayerCoord) && (xPlayerCoord + i) >= 0) {
				removeBorder(yPlayerCoord, xPlayerCoord+i);
			}

			if ((yPlayerCoord + i) < mapButtonPanel.getMapButtonMatrixRowLength() && (yPlayerCoord + i) >= 0) {
				removeBorder(yPlayerCoord+i, xPlayerCoord);
			}

			if ((xPlayerCoord - i) < mapButtonPanel.getMapButtonMatrixColumnLength(yPlayerCoord) && (xPlayerCoord - i) >= 0) {
				removeBorder(yPlayerCoord, xPlayerCoord-i);
			}

			if ((yPlayerCoord - i) < mapButtonPanel.getMapButtonMatrixRowLength() && (yPlayerCoord - i) >= 0) {
				removeBorder(yPlayerCoord-i, xPlayerCoord);
			}

			if (i > 1) {
				if ((yPlayerCoord + (i - 1)) < mapButtonPanel.getMapButtonMatrixRowLength()) {

					for (int s = 1; s < diceNum; s++) {

						if ((xPlayerCoord + (diceNum - s)) < mapButtonPanel.getMapButtonMatrixColumnLength(yPlayerCoord) && (xPlayerCoord + (diceNum - (s))) >= 0) {
							removeBorder(yPlayerCoord+(i-1), xPlayerCoord+(diceNum-s));
						}

						if ((xPlayerCoord - (diceNum - s)) < mapButtonPanel.getMapButtonMatrixColumnLength(yPlayerCoord+ (i - 1)) && (xPlayerCoord - (diceNum - (s))) >= 0) {
							removeBorder(yPlayerCoord+(i-1), xPlayerCoord-(diceNum-s));
						}

					}
				}

				if ((yPlayerCoord - (i - 1)) < mapButtonPanel.getMapButtonMatrixRowLength()) {

					for (int s = 1; s < diceNum; s++) {

						if ((xPlayerCoord + (diceNum - s)) < mapButtonPanel.getMapButtonMatrixColumnLength(yPlayerCoord- (i - 1)) && (xPlayerCoord + (diceNum - (s))) >= 0) {
							removeBorder(yPlayerCoord-(i-1), xPlayerCoord+(diceNum-s));
						}

						if ((xPlayerCoord - (diceNum - s)) < mapButtonPanel.getMapButtonMatrixColumnLength(yPlayerCoord- (i - 1)) && (xPlayerCoord - (diceNum - (s))) >= 0) {
							removeBorder(yPlayerCoord-(i-1), xPlayerCoord-(diceNum-s));
						}
					}
				}
			}
		}
	}



	/**
	 * Hilfs Methode 
	 * @param y
	 * @param x
	 */
	private void setBorder(int y, int x) {
		if (boardMatrix[y][x] != 0) {
			JButton button = mapButtonPanel.getButton(y, x);
			mapButtonPanel.setBorder(button);
			mapButtonPanel.enable(button);
		}
	}

	private void removeBorder(int y, int x){
		if(boardMatrix[y][x] != 0){
			JButton button = mapButtonPanel.getButton(y, x);
			mapButtonPanel.removeBorder(button);
			mapButtonPanel.disable(button);
		}
	}


	public void removeFightActionCall(int x, int y){
		if(buttonMatrix != null)  buttonMatrix[y][x].removeActionListener(fightListener);
	}

	public void setMoveActionCall(int x, int y){
		if(buttonMatrix != null){
			buttonMatrix[y][x].addActionListener(moveListener);
			boardMatrix[y][x] = FLOOR_ELEMENT;
		}
	}




	/**
	 * params = Position des geclickten Buttons settet ausserderm neue charposi
	 * 
	 * @param xClicked
	 * @param yClicked
	 */
	public void repaintButton(int xClicked, int yClicked) {
		int xPlayerCoord = game.getXCharCoord();
		int yPlayerCoord = game.getYCharCoord();
		setImage(yPlayerCoord,xPlayerCoord,"resource/images/road.png");
		setImage(yClicked, xClicked, "resource/images/star.png");
		undoMoveRange(diceNum);
		game.setCharCoords(xClicked, yClicked);
		System.out.println("clicked x "+xClicked);
		System.out.println("clicked y "+yClicked);
		refreshMapButtonPanel();

	}

	private void setImage(int y, Integer x,String path){
		ImageIcon icon = new ImageIcon(path);
		mapButtonPanel.getButton(y, x).setMargin(new Insets(0, 0, 0, 0));
		mapButtonPanel.getButton(y, x).setIcon(icon);
		mapButtonPanel.getButton(y, x).setDisabledIcon(icon);
	}




	public void refreshMapButtonPanel(){
		mapButtonPanel.refresh();
	}



}
