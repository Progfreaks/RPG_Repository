package persistence.character;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import persistence.character.CharacterData.STATUS;


/**
 * 
 * Diese Klasse repraesentiert ein Panel, das jedes CaracterData enthaelt. 
 * Charaktereigenschaften wird als JTextField in der ArrayList gespeichert.
 * 
 * @author YOU_HEY
 *
 */
public class SubCharacterDataPanel extends JPanel implements ICharacterDefs {

	private static final long serialVersionUID = 8056902671150263793L;

	// Enthealt jedes JTextField(Character)
	public List<JTextField> textFieldList = new ArrayList<JTextField>();

	// Enthealt jedes JTextField(Character)
	public List<JTextField> sTextFieldList = new ArrayList<JTextField>();

	private CharacterData characterData;

	/**
	 * Konstruktor. Bekommt CharacterData uebergeben.
	 * 
	 * @param pCharacterData
	 */
	public SubCharacterDataPanel(CharacterData pCharacterData) {

		characterData = pCharacterData;

		// Hier werden Jlabel und Jtext gespeichert.
		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(10, 4));

		setStatusText(panel);

		JPanel skillPanel = new JPanel();
		skillPanel.setLayout(new GridLayout(3, 3));// 3 * 3 Grid

		setSkillText(skillPanel);

		// Dies Panel ist BorderLayout.
		setLayout(new BorderLayout());

		// jedes Panel wird in dies
		add(panel, BorderLayout.CENTER);

		// Dies Panel ist BorderLayout.
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// jedes Panel wird in dies
		add(skillPanel);

	}

	/**
	 * Hilfsmethode fuer die Konstruktor dieser Klasse. Setzt Panel und Text von
	 * CharakterStatus ins JPanel.
	 * 
	 * @param pPanel
	 */
	private void setStatusText(JPanel pPanel) {
		
		
		for(STATUS status : STATUS.values()){
			// Hier werden alle Charaker-Werte(Name,Hp,Mp,isPlayer)
						// hinzugefuegt.
						JTextField textField = new JTextField(10);

						textField.setText(characterData.getValue(status));

						// Schiebt nach links.
						textField.setHorizontalAlignment(JTextField.LEFT);

						JLabel label = null;

						switch (status) {// Fuegt jedes Label ins JLabel hinzu.
						case NAME:
							label = new JLabel(" Charakter Name");

							break;
						case HP:
							label = new JLabel(" Lebenspunkte");

							break;
						case MP:
							label = new JLabel(" Mana Punkte");

							break;
						case ISPLAYER:
							label = new JLabel(" isPlayer(true/false)");

							break;

						default:
							break;
						}

						// wird das Label grau gefaerbt.

						label.setOpaque(true);
						label.setBackground(Color.LIGHT_GRAY);

						// Schiebt nach links.
						label.setHorizontalAlignment(JLabel.LEFT);

						pPanel.add(label);// label wird hinzugefuegt.
						pPanel.add(textField);// text wird hinzugefuegt.

						textFieldList.add(textField);// TextField wird in die List
														// hinzugefuegt.
		}

		for (int i = 1; i <= 4; i++) {

//			// Hier werden alle Charaker-Werte(Name,Hp,Mp,isPlayer)
//			// hinzugefuegt.
//			JTextField textField = new JTextField(10);
//
//			textField.setText(characterData.getValue(i));
//
//			// Schiebt nach links.
//			textField.setHorizontalAlignment(JTextField.LEFT);
//
//			JLabel label = null;
//
//			switch (i) {// Fuegt jedes Label ins JLabel hinzu.
//			case 1:
//				label = new JLabel(" Charakter Name");
//
//				break;
//			case 2:
//				label = new JLabel(" Lebenspunkte");
//
//				break;
//			case 3:
//				label = new JLabel(" Mana Punkte");
//
//				break;
//			case 4:
//				label = new JLabel(" isPlayer(true/false)");
//
//				break;
//
//			default:
//				break;
//			}
//
//			// wird das Label grau gefaerbt.
//
//			label.setOpaque(true);
//			label.setBackground(Color.LIGHT_GRAY);
//
//			// Schiebt nach links.
//			label.setHorizontalAlignment(JLabel.LEFT);
//
//			pPanel.add(label);// label wird hinzugefuegt.
//			pPanel.add(textField);// text wird hinzugefuegt.
//
//			textFieldList.add(textField);// TextField wird in die List
//											// hinzugefuegt.

		}// < -Ende der for-Schleife

	}

	/**
	 * Hilfsmethode fuer die Konstruktor dieser Klasse. Setzt Panel und Text von
	 * CharakterSkill ins JPanel.
	 * 
	 * @param pPanel
	 */
	private void setSkillText(JPanel pPanel) {

		for (int skillIndex = 1; skillIndex <= 3; skillIndex++) {

			JLabel label = null;

			// Hier werden Jlabel und Jtext gespeichert.

			label = new JLabel("Faehigkeit:" + skillIndex);

			decolateLabel(label, Color.PINK);// Faerbt das Label und den Rand
												// vom Label.

			pPanel.add(label);// label wird hinzugefuegt.

			for (int i = 1; i <= 3; i++) {// 1:Name,2:MP,3:Schaden

				// Hier werden alle Faehigkeiten-Werte(Name,Mp,Schaden)
				// hinzugefuegt.

				JTextField textField = new JTextField(10);// fuer 10 Zeichen

				setText(i, skillIndex, textField);// Setzt Text in JTextField.

				// Schiebt nach links.
				textField.setHorizontalAlignment(JTextField.LEFT);

				switch (i) {// Fuegt jedes Label ins JLabel hinzu.
				case 1:
					label = new JLabel(" Name");

					break;
				case 2:
					label = new JLabel(" MP");

					break;
				case 3:
					label = new JLabel(" *Schaden");

					break;

				default:
					break;
				}

				// wird das Label grau gefaerbt.

				decolateLabel(label, Color.LIGHT_GRAY);// Faerbt das Label und
														// den Rand vom Label.

				textField.setBorder(new LineBorder(Color.BLACK, 1, true));

				// Schiebt nach links.
				// label.setHorizontalAlignment(JLabel.LEFT);

				pPanel.add(label);// label wird hinzugefuegt.
				pPanel.add(textField);// text wird hinzugefuegt.

				sTextFieldList.add(textField);// TextField wird in die List
												// hinzugefuegt.

			}// < -Ende der for-Schleife

		}// < -Ende der for-Schleife

	}

	/**
	 * Hilfsmethode fuer die Konstruktor dieser Klasse. Setzt Text in
	 * JTextField.
	 * 
	 * @param pTextType
	 * @param pIndex
	 * @param pTextField
	 */
	private void setText(int pTextType, int pIndex, JTextField pTextField) {

		switch (pTextType) {
		case 1:// name
			pTextField.setText(characterData.getSkill()[pIndex-1].getName());
			
			break;

		case 2:// mp

			pTextField.setText(String.valueOf(characterData.getSkill()[pIndex-1].getMP()));

			break;
		case 3:// schaden

			pTextField.setText(String.valueOf((characterData.getSkill()[pIndex-1].getDamage())));

			break;
		default:
			break;
		}

	}

	/**
	 * Hilfsmethode fuer die Konstruktor dieser Klasse. Faerbt das Label und den
	 * Rand vom Label.
	 * 
	 * @param pLabel
	 * @param pColor
	 */
	private void decolateLabel(JLabel pLabel, Color pColor) {

		pLabel.setOpaque(true);
		pLabel.setBackground(pColor);
		LineBorder border = new LineBorder(Color.BLACK, 1, true);
		pLabel.setBorder(border);
		pLabel.setHorizontalAlignment(JLabel.LEFT);
	}

	public String toString() {
		return characterData.toString();
	}

	/**
	 * Der von User eingegebene Text wird in CharackterData gespeicert.
	 */
	public void storeData() {

		storeStatus();
		storeSkill();

	}

	/**
	 * Hilfsmethode fuer storeData() Speichert Charakter
	 * Status(Name,HP,MP,IsPlayer).
	 */
	private void storeStatus() {
		int i = 1;
		for (JTextField textField : textFieldList) {
			String value = textField.getText();

			characterData.setValue(i, value);
			i++;
		}
	}

	/**
	 * Hilfsmethode fuer storeData() Speichert Charakter Faehigkeiten
	 */
	private void storeSkill() {
		int i = 1;
		String[] names = new String[3];
		int[] mp = new int[3];
		double[] damage = new double[3];

		int index01 = 0;
		int index02 = 0;
		int index03 = 0;

		for (JTextField textField : sTextFieldList) {
			String value = textField.getText();

			if (i % 3 == 1) {
				names[index01++] = value;
			} else if (i % 3 == 2) {
				mp[index02++] = Integer.valueOf(value);
			} else {
				damage[index03++] = Double.valueOf(value);
			}

			i++;
		}// ende der for Schleife

		characterData.setSkillName(names);
		characterData.setSkillMP(mp);
		characterData.setSkillDamage(damage);
	}

}
