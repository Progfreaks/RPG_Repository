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
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import domain.CharacterManager;
import domain.DuD;
import domain.exceptions.InvalidNumberException;
import persistence.character.CharacterData;
import persistence.character.CharacterDataMap;
import valueobject.Dice;
import valueobject.character.Character;

public class MyConsole implements ActionListener {

	// ----------------------
	private static DuD game;

	static Character player = null;
	static Character enemy = null;
	private JPanel console = null;

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
	JScrollPane scrollPane;
	JButton enterButton;
	String title;

	public MyConsole() {

		game = DuD.getGame();
		this.console = new JPanel();
		initialize();

	}

	public JButton getButton() {
		return enterButton;
	}

	public String getName() {
		return title;
	}
	
	public void printMsg(String s){
		appendln(s);
	}



	

	private void initialize() {

		console.setSize(800, 400);
		area.setEditable(false);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1, 3));// 1 Zeile, 3 Spalte
		JLabel inputLabel = new JLabel("Eingabe ->  ");
		inputLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(inputLabel);
		panel1.add(field);
		enterButton = new JButton("Enter");
		enterButton.addActionListener(this);
		panel1.add(enterButton);

		JPanel panel2 = new JPanel();

		// ScrollPane hinzufuegen
		JScrollPane scrollPane = new JScrollPane(area);// Scroll Kanten
		scrollPane.setPreferredSize(new Dimension(400, 200));

		panel2.add(scrollPane);

		// Inhalt des Frames zusammenbauen
		console.setLayout(new BorderLayout());
		console.add(panel1, BorderLayout.CENTER);
		console.add(panel2, BorderLayout.NORTH);
		console.setVisible(true);

	}

	public static boolean stop = true;

	int num = 0;

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("tipped");
		changeValueOfStop();
		autoScroll();

	}

	private void autoScroll() {
		// scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum()+3);
		// scrollPane.getVerticalScrollBar().setValue(0);
		// scrollPane.getViewport().scrollRectToVisible(new Rectangle(0,
		// Integer.MAX_VALUE - 1, 1, 1));

		area.setCaretPosition(area.getDocument().getLength());
	}

	private void appendln(String pText) {

		area.append(pText + "\n");
		autoScroll();
	}

	private void append(String pText) {
		area.append(pText);
		autoScroll();
	}

	private int getNumber() {// TODO Exception
		

		int num = Integer.valueOf(field.getText());
		field.setText("");
		return num;
	}

	public void changeValueOfStop() {
		stop = !stop;
	}

	/**
	 * Wartet bis Enter angeklickt wird.
	 */
	private void waitForClick() {
		while (stop) {
			try {
				Thread.sleep(1000);
		} catch (InterruptedException e) {
			}
		}
			
		
			
		changeValueOfStop();
		
	}

	public CharacterData selectCharacter() {

		appendln("Willkommen zu unserem Duengeon!! (enter)\n");
		waitForClick();

		appendln("Charakter auswaehlen (enter)\n");

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

		appendln("\nLos geht's mit dem " + status.getValue(status.NAME)
				+ "!!\n");
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
			autoScroll();
			waitForClick();

			// Bekommt das Kommando
			
			
			//------TODO-------------
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
				System.out.println(ch.getName());
				System.out.println(checkMP(ch, useMP));

				if (checkMP(ch, useMP)) {

					check = false;
					damage = subSelectCommand(ch);
					ch.setMP(-useMP);

				}

				break;

			case 4:
				appendln("Aufladen\n");
				ch.setMP(diceForMP(ch));
				check = false;
				break;
			default:
				appendln("false Eingabe. bitte noch mal Kommando auswaehlen");
				check = false;
				break;
			}// <- Ende switch

		}// <- Ende der while Schleife

		resetValue();
		autoScroll();
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
			System.out.println(c01.getMP());

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
			autoScroll();
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

		append(character.getName() + " kriegt " + damage + " Schaden! "
				+ character.getLife() + "/" + character.getMaxLife() + " -> ");

		character.setLife(-damage);

		appendln(character.getLife() + "/" + character.getMaxLife()
				+ " (ENTER)\n");

		waitForClick();
	}

	/**
	 * Wuerfeln fuer MP Aufladen
	 * 
	 * @param ch
	 * @return
	 */
	public int diceForMP(final Character ch) {

		diceMessage(ch);
		int diceNum01 = Dice.getDiceNummer();
		int mp01 = showNumMessage(diceNum01);

		diceMessage(ch);
		int diceNum02 = Dice.getDiceNummer();
		int mp02 = showNumMessage(diceNum02);

		int totalMP = chargeMPMessage(mp01, mp02);

		return totalMP;

	}

	public int diceForAtk(final Character ch) {

		int totalDamage = 0;

		diceMessage(ch);

		int diceNum01 = Dice.getDiceNummer();

		int damage01 = validDiceNumMessage(diceNum01);
		if (damage01 == 0)
			return 0;

		diceMessage(ch);

		int diceNum02 = Dice.getDiceNummer();

		int damage02 = validDiceNumMessage(diceNum02);
		if (damage02 == 0)
			return 0;

		totalDamage = totalDamageMessage(damage01, damage02, ch);

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
		autoScroll();
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
		autoScroll();
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
		appendln("Der Schaden ist (" + d01 + " * " + d02 + ") * " + multi
				+ " = " + iTotalDamage + "\n");
		multi = 1.0;
		return iTotalDamage;
	}

	

	/**
	 * Wuerfeln fuer die Bewergung.
	 * 
	 * @param ch
	 * @param rounds
	 * @return
	 */
	public int diceForRound(Character ch, int rounds) {
		
		roundDiceMessage(ch, rounds);
		Random random = new Random();
		int diceNumRound = random.nextInt(5) + 1;
		showNumMessage(diceNumRound);
		return diceNumRound;
		
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
			autoScroll();

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

	public JPanel getConsole() {
		return console;
	}

	public static void main(String[] args) {

	}

}
