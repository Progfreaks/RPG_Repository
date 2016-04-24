package parsistence;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ExperienceTablePanel extends JPanel implements ActionListener {

	
	private static final long serialVersionUID = 1220590933554803342L;

	
	//Eine Liste, damit man das eingegebene Experience sehen kann.
	public List<JTextField> panelList = new ArrayList<JTextField>();
	
	
	
	//Konstruktor dieser Klasse.
	public ExperienceTablePanel(){
		
		//Laedet das ExperienceTable-Objekt.
		ExperienceTable eTable = ExperienceTable.getInstance();

		//Panel fuer die List.
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new GridLayout(10, 4));

		//Schreibt den Inhalt vom ExperienceTable ins TextField.
		for(int i = 1; i <= ExperienceTable.MAX_LEVEL; i++){
			JTextField textField = new JTextField(10);
			
			if(eTable.getExperience(i) != 0){

				textField.setText(String.valueOf(eTable.getExperience(i)));
			}else{
				textField.setText("0");
			}
			
            //Schiebt nach rechts.
			textField.setHorizontalAlignment(JTextField.RIGHT);
			
			//Wenn Level ungerade ist, wird es grau gefaerbt.
			JLabel label = new JLabel(String.valueOf(i));
			
			if(i % 2 == 1){
				label.setOpaque(true);
				label.setBackground(Color.LIGHT_GRAY);
			}
			
			//nach rechts.
			label.setHorizontalAlignment(JLabel.RIGHT);
			listPanel.add(label);
			listPanel.add(textField);
			panelList.add(textField);
		}
		
		//Erstellt ein Save-Button
		setLayout(new BorderLayout());
		
		JButton button = new JButton("Speichern");
		
		//Definiert ein Listner.
		button.addActionListener(this);
		
		add(listPanel,BorderLayout.CENTER);
		add(button,BorderLayout.SOUTH);

	}

	/**
	 * Wenn den Button geklickt wird, wird es aufgerufen.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		saveFile();
	}
	
	/**
	 * Die Methode fuer die File-Speichrung.
	 */
	public void saveFile(){
		ExperienceTable table = ExperienceTable.getInstance();
		int i = 1;
		for(JTextField textField : panelList){
			int intValue = 0;
			String value = textField.getText();
			if(value != null && value.length() != 0){
				try {
					intValue = Integer.parseInt(value);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			table.putExperience(i, intValue);
			i++;
		}
		table.saveFile();
	}
}
