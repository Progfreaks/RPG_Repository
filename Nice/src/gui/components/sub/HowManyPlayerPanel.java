package gui.components.sub;

import gui.manager.GuiManager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.DuD;
import net.miginfocom.swing.MigLayout;

public class HowManyPlayerPanel extends JPanel implements ActionListener{

	//private Integer[] count = {2,3,4};
	private String[] count = {"-","2","3","4"};
	private Integer selectedCount;
	private JComboBox countBox;


	public HowManyPlayerPanel(){
		setUpPanel();
	}
	
	private void setUpPanel(){
		setLayout(new MigLayout("fill"));
		JLabel countPlayerLabel = new JLabel(" Die Anzahl von Spieler ");
		countPlayerLabel.setOpaque(true);
		countBox = new JComboBox(count);
		countBox.addActionListener(this);
		JButton confirmButton = new JButton("OK");
		confirmButton.addActionListener(GuiManager.getInstance());
		add(countPlayerLabel,"wrap");
		add(countBox,"wrap");
		add(confirmButton);
		setOpaque(false);
		setVisible(true);
		revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Integer selectedCount = Integer.parseInt((String)countBox.getSelectedItem());
		
		DuD.getGame().setMaxPlayerCount(selectedCount);
		
	}
}
