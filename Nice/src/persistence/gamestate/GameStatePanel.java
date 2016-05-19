package persistence.gamestate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import domain.DuD;

@SuppressWarnings("serial")
public class GameStatePanel extends JPanel implements ActionListener{



	public GameStatePanel(){
		JButton saveButton = new JButton("Speichern");
		saveButton.addActionListener(this);
		add(saveButton);


	}



	@Override
	public void actionPerformed(ActionEvent e) {
		DuD.getGame().setCharToState(0);
	}

}
