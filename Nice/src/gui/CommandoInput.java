package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import domain.BattleManager;
import domain.CentralSave;
import domain.CharacterManager;
import domain.DuD;
import domain.exceptions.InvalidNumberException;
import persistence.character.CharacterData;
import persistence.character.CharacterDataMap;
import valueobject.Dice;
import valueobject.PlayerArray;
import valueobject.character.Character;

public class CommandoInput extends JFrame implements ActionListener {

	// ----------------------
	static Character player = null;
	static Character enemy = null;
	static CharacterManager cm;
	private static CharacterDataMap map = CharacterDataMap.getInstance();

	int useMP;

	String skillName;

	double multi;

	// Zaehler. wie viel mal Wuerfel geworfen ist.
	private int count = 1;

	// ----------------------

	JTextArea area = new JTextArea();
	JTextField field = new JTextField();

	public CommandoInput(String titel) {
		super(titel);
		initialize();
	}

	private void initialize() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);

		area.setEditable(false);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1, 3));// 1 Zeile, 3 Spalte
		JLabel inputLabel = new JLabel("Eingabe ->  ");
		inputLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(inputLabel);
		panel1.add(field);
		JButton enterButton = new JButton("Enter");
		enterButton.addActionListener(this);
		panel1.add(enterButton);

		JPanel panel2 = new JPanel();

		// ScrollPane hinzufuegen
		JScrollPane scrollPane = new JScrollPane(area);// Scroll Kanten
		scrollPane.setPreferredSize(new Dimension(400, 400));

		panel2.add(scrollPane);

		// Inhalt des Frames zusammenbauen
		setLayout(new BorderLayout());
		add(panel1, BorderLayout.CENTER);
		add(panel2, BorderLayout.NORTH);
		setVisible(true);

	}

	private boolean stop = true;

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("tipped");
		changeValueOfStop();

	}

	private void appendln(String pText) {
		area.append(pText + "\n");

	}
	
	private void append(String pText){
		area.append(pText);
	}

	private int getNumber() {
		return Integer.valueOf(field.getText());
	}

	private void changeValueOfStop() {
		stop = !stop;
	}

	private void waitForClick() {
		while (stop) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
		}
		changeValueOfStop();
	}

	public CharacterData selectCharacter() {

		appendln("Willkommen zu unserem Duengeon!! (ENTER)\n");

		waitForClick();

		appendln("Charakter auswaehlen (ENTER)\n");

		int characterId = 0;

		waitForClick();

		CharacterData status = null;
		int count = 1;
		for (int id : persistence.character.ICharacterDefs.CharakterID) {

			status = map.getCharacterData(id);

			if (Boolean.valueOf(status.getValue(status.ISPLAYER)))
				appendln(((count++) + " :" + status.getValue(status.NAME)));
		}

		waitForClick();

		characterId = getNumber();

		status = map.getCharacterData(characterId - 1);

		appendln("\nLos geht's mit dem " + status.getValue(status.NAME) + "!!\n");

		return status;
	}

	/**
	 * 
	 * Waehlt eine Faehigkeit aus und gibt entsprechender Schaden zurueck.
	 * 
	 * @param character
	 *            Charakter, der die auszuwaehlenden Faaehigkeiten besitzt.
	 * @return Schaden
	 */
	public int selectCommandMessage(final Character ch) {// TODO Exception

		// Pruefer fuer die while Schleife
		boolean check = true;

		// zurueckzugebende Schaden
		int damage = 0;

		// solange es genug MP bleibt
		while (check) {

			appendln("Kommando auswaehlen\n");

			// Bekommt die Faehigkeiten vom Charakter
			persistence.character.CharacterData.Skill[] skills = ch.getSkills();

			int i = 1;
			for (persistence.character.CharacterData.Skill skill : skills) {// Alle
																			// Faehigkeiten
																			// werden
																			// angezeigt

				appendln((i++) + " : " + skill.getName() + " /" + skill.getMP()
						+ "MP/Schaden*" + skill.getDamage());
			}
			appendln("4 : Aufladen\n");

			// Bekommt das Kommando
			int command = getNumber();

			switch (command) {
			case 1:
			case 2:
			case 3:

				// Bekommt entsprechendes MP
				useMP = skills[command - 1].getMP();

				// Bekommt entsprechenden SkillName
				skillName = skills[command - 1].getName();

				// Bekommt entsprechendes Mehrfach
				multi = skills[command - 1].getDamage();

				if (checkMP(ch, useMP)) {

					check = false;
					damage = subSelectCommand(ch);
					ch.setMP(-useMP);

				}

				break;

			case 4:
				appendln("Aufladen\n");
				ch.setMP(Dice.diceForMP(ch));
				check = false;
				break;
			default:
				appendln("false Eingabe. bitte noch mal Kommando auswaehlen");
				break;
			}// <- Ende switch

		}// <- Ende der while Schleife

		resetValue();
		return damage;

	}

	/**
	 * Hilfsmethode fuer selectCommandMessage() Prueft, ob der Charakter genug
	 * MP hat.
	 * 
	 * @param ch
	 */
	public boolean checkMP(final Character ch, final int usedMP) {

		if (ch.getMP() < usedMP) {

			appendln("Keinen MP.....\n");
			return false;
		}
		return true;
	}

	/**
	 * Hilfsmethode von selectCommandMessage()
	 * 
	 * @param skill
	 */
	private int subSelectCommand(final Character ch) {
		appendln(skillName + " \n");
		int damage = diceForAtk(ch);
		if (damage != 0) {
			appendln("Angreifen? (ENTER)");
			waitForClick();
		}

		return damage;
	}

	/**
	 * Gibt den Verlauf des Kampfs aus.
	 * 
	 * @param c01
	 * @param c02
	 */
	public void roundMessage(final Character c01, final Character c02) {

		// Zaehler fuer die Runde
		int round = 0;

		// Schaden
		int damage;

		// Solange beide Charakter noch am Leben ist
		while (c01.isAlive() && c02.isAlive()) {

			appendln("-----Runde " + (++round) + " ----- (ENTER)\n");
			waitForClick(); // ---------Runde fuer Spieler-------------
			appendln(c01.toString());

			// damage = c01.attack();

			damage = c01.isPlayer() ? selectCommandMessage(c01)
					: diceForAtk(c01);
			
			

			if (damage != 0) {

				roundMessageShowStatus(damage, c02);

				if (!c02.isAlive()) {

					appendln("Du" + " hast gewonnen!!");

					return;
				}
			}

			// -------------Runde fuer Gegner-----------------
			appendln(c02.toString());

			damage = c02.isPlayer() ? selectCommandMessage(c02)
					: diceForAtk(c02);

			if (damage != 0) {

				roundMessageShowStatus(damage, c01);

			}

			appendln(c01.toString());
			appendln("Naechste Runde (ENTER) > \n");
			waitForClick();
		}

		String result = c01.isAlive() ? "You" : "ENEMY";
		appendln(result + " hat gewonnen!!");

	}

	/**
	 * Hilfs Methode fuer RoundMessage() Zeigt, wie viel Schaden der Charakter
	 * bekommt.
	 * 
	 * @param damage
	 * @param character
	 */
	private void roundMessageShowStatus(final int damage,
			final Character character) {

		append(character.getName() + " kriegt " + damage
				+ " Schaden! " + character.getLife() + "/"
				+ character.getMaxLife() + " -> ");

		character.setLife(-damage);

		appendln(character.getLife() + "/" + character.getMaxLife()
				+ " (ENTER)\n");

		waitForClick();
	}

	public static int diceForAtk(final Character ch) {

		int totalDamage = 0;

		CentralSave.console.diceMessage(ch);

		int diceNum01 = Dice.getDiceNummer();

		int damage01 = CentralSave.console.validDiceNumMessage(diceNum01);
		if (damage01 == 0)
			return 0;

		CentralSave.console.diceMessage(ch);

		int diceNum02 = Dice.getDiceNummer();

		int damage02 = CentralSave.console.validDiceNumMessage(diceNum02);
		if (damage02 == 0)
			return 0;

		totalDamage = CentralSave.console.totalDamageMessage(damage01,
				damage02, ch);

		return totalDamage;
	}

	/**
	 * Nachricht für den Wuerfeln.
	 * 
	 * @param ch
	 */
	public void diceMessage(final Character ch) {

		String message = ch.isPlayer() ? "Wuerfeln (" + (count++)
				+ "/2) (ENTER) >" : ch.getName() + " wuerfelt (" + (count++)
				+ "/2) (ENTER)) >";
		appendln(message);
		waitForClick();
		if (count == 3)
			count = 1;

	}

	/**
	 * Gibt aus, ob die Augenzahl gueltig ist.
	 * 
	 * @param num
	 * @return
	 */
	public int validDiceNumMessage(final int num) {
		if (num == 1) {

			append("\nDie Nummer ist 1. Der Angriff ist fehlgeschlagen ..... (ENTER)\n");
			waitForClick();
			count = 1;

			// failed = true;
			return 0;

		} else {
			appendln("\nDie Nummer ist " + num + " (ENTER)\n");
			waitForClick();
			return num;
		}

	}

	/**
	 * Gibt die Augenzahl aus.
	 * 
	 * @param num
	 * @return
	 */
	public int showNumMessage(final int num) {
		appendln("\nDie Nummer ist " + num + " (ENTER)\n");
		waitForClick();
		return num;
	}

	/**
	 * Zeigt wie viel MP aufgeladen ist.
	 * 
	 * @param mp01
	 * @param mp02
	 * @return
	 */
	public int chargeMPMessage(final int mp01, final int mp02) {
		int totalMP = (mp01 * mp02);

		appendln(mp01 + " * " + mp02 + " = " + totalMP + " MP ist aufgeladen\n");

		return totalMP;
	}

	/**
	 * Gibt den Schaden aus.
	 * 
	 * @param d01
	 * @param d02
	 * @param d
	 */
	public int totalDamageMessage(final int d01, final int d02,
			final Character ch) {
		double dTotalDamage = ((double) (d01 * d02)) * multi;
		int iTotalDamage = (int) dTotalDamage;
		appendln("Der Schaden ist (" + d01 + " * " + d02 + ") * " + multi + " = "
				+ iTotalDamage + "\n");
		multi = 1.0;
		return iTotalDamage;
	}

	/**
	 * Wenn aud GUI Roll-Button angeklickt wird, dann dies Methode aufgerufen.
	 * 
	 * @param ch
	 * @param rounds
	 */
	public void roundDiceMessage(final Character ch, int rounds) {
		if (rounds <= 1) {
			String msg = ch.getName() + ": Rundenbeginn! Bitte wuerfel (ENTER)";
			appendln(msg);
			waitForClick();
		} else {
			String msg = ch.getName() + ": Runde: " + rounds
					+ ". Bitte wuerfel (ENTER)";
			appendln(msg);
			waitForClick();
		}

	}

	/**
	 * Setzt alle static value in default value zurueck
	 */
	public void resetValue() {

		multi = 1.0;
		skillName = "";
		useMP = 0;

	}

	public void endGoalMessage(final Character ch) {
		String msg = ch.getName() + ": Congratulations! Claim your Treasure!";
		appendln(msg);
	}

	public static void main(String[] args) {

	}

}
