package parsistence.character;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * Diese Klasse repraesentiert ein Panel, das jedes CaracterData enthaelt.
 * Die fuegt alle TextField vom Character in die List.
 * 
 * @author YOU_HEY
 *
 */
public class SubCharacterDataPanel extends JPanel implements ICharacterDefs {

	private static final long serialVersionUID = 8056902671150263793L;

	//Enthealt jedes JTextField.
	public List<JTextField> textFieldList = new ArrayList<JTextField>();

	private CharacterData characterData;

	/**
	 * Konstruktor. Bekommt CharacterData uebergeben.
	 * 
	 * @param pCharacterData
	 */
	public SubCharacterDataPanel(CharacterData pCharacterData) {

		characterData = pCharacterData;

		//Hier werden Jlabel und Jtext gespeichert.
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(10, 4));

		for (int i = 1; i <= 4; i++) {

			// Hier werden alle Charaker-Werte(Name,Hp,Mp,isPlayer)
			// hinzugefuegt.
			JTextField textField = new JTextField(10);
			
			textField.setText(characterData.getValue(i));



			// Schiebt nach links.
			textField.setHorizontalAlignment(JTextField.LEFT);

			JLabel label = null;

			switch (i) {//Fuegt jedes Label ins JLabel hinzu.
			case 1:
				label = new JLabel(" Charakter Name");

				break;
			case 2:
				label = new JLabel(" Lebenspunkte");

				break;
			case 3:
				label = new JLabel(" Mana Punkte");

				break;
			case 4:
				label = new JLabel(" isPlayer(true/false)");

				break;

			default:
				break;
			}

			

			//  wird das Label grau gefaerbt.
			
				label.setOpaque(true);
				label.setBackground(Color.LIGHT_GRAY);
			

			// Schiebt nach links.
			label.setHorizontalAlignment(JLabel.LEFT);

			panel.add(label);//label wird hinzugefuegt.
			panel.add(textField);//text wird hinzugefuegt.
			
			textFieldList.add(textField);//TextField wird in die List hinzugefuegt.
			
		}// < -Ende der for-Schleife

		//Dies Panel ist BorderLayout.
		setLayout(new BorderLayout());
		
		//jedes Panel wird in dies
		add(panel, BorderLayout.CENTER);
	}

	public String toString() {
		return characterData.toString();
	}

	public void storeData() {
		int i = 1;
		for (JTextField textField : textFieldList) {
			String value = textField.getText();

			characterData.setValue(i, value);
			i++;
		}
	}
}
