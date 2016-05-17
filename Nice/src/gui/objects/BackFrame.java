package gui.objects;

import gui.GameConsole;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import domain.DuD;

/**
 * Diese Klasse repraesentiert das Backframe.
 * @author YOU_HEY
 *
 */
public class BackFrame {

	private JFrame backFrame;
	private JPanel mainPanel;
	private ArrayList<JPanel> panelList;

	public BackFrame() {

		init();

//		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		 int w = screenSize.width; int h = screenSize.height;
//		 backLayer.setBounds(w / 4, h / 4, w / 2, h / 2);
//		 backLayer.setSize(500, 500);
//		 BoxLayout boxlayout = new BoxLayout(backLayer.getContentPane(),BoxLayout.Y_AXIS);

	}
	
	/**
	 * Initialisiert das Backframe.
	 */
	private void init(){
		this.backFrame = new JFrame("Bestes RPG");
		backFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		backFrame.setSize(1100, 1000);
		backFrame.setLayout(new BorderLayout());
		panelList = new ArrayList<JPanel>();
		mainPanel = new JPanel();
		backFrame.add(mainPanel, BorderLayout.CENTER);
		backFrame.setBackground(Color.BLACK);
		backFrame.setVisible(true);
	}


	/**
	 * Entfernt ein Panel anhand des uebergebenen Index.
	 * @param i
	 */
	public void removePanel(int i) {

		backFrame.remove(panelList.get(i));

		this.panelList.remove(i);

		mainPanel.remove(i);

	}



	/**
	 * Fuegt ein Panel anhand des Index ein.
	 * @param panel
	 * @param i
	 * @param in
	 */
	public void addPanel(JPanel panel, int i,String in) {

		this.panelList.add(i, panel);

		mainPanel.add(panel, in, i);

		backFrame.revalidate();

	}


	


	/**
	 * Erzeugt das Mainpanel.
	 * @param buttonLayer
	 * @param menuLayer
	 */
	public void createMainPanel(ButtonPanel buttonLayer, MenuPanel menuLayer) {


		mainPanel.setLayout(new MigLayout("fill"));
		mainPanel.setSize(1024, 768);
		mainPanel.setBackground(Color.black);
		addPanel(buttonLayer.getButtonPanel(), 0, "push, height 400:400:400, width 400:400:400");
		addPanel(menuLayer.getMenuPanel(), 1, "w 20%");
		addPanel(GameConsole.getInstance().getConsole(), 2, ("w 50%, wrap"));
		mainPanel.setVisible(true);
		backFrame.add(mainPanel);
		refresh();

	}

	/**
	 * Erzeugt das Openingpanel.
	 */
	public void createOpeningPanel(){

		mainPanel.setLayout(new MigLayout("fill"));
		mainPanel.setSize(1024, 768);
		mainPanel.setBackground(Color.black);
		//System.out.println(DuD.getGame());
		addPanel(GameConsole.getInstance().getConsole(),0, ("w 100%, wrap"));
		mainPanel.setVisible(true);
		backFrame.add(mainPanel);
		refresh();
	}

	
	public void refresh() {

		backFrame.revalidate();
		backFrame.repaint();

	}


}



