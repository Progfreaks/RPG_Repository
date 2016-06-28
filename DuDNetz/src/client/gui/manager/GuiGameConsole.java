package client.gui.manager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.net.Facade;
import common.valueobject.Character;
import common.valueobject.Dice;
import net.miginfocom.swing.MigLayout;
import server.domain.DuD;
import server.domain.exceptions.InvalidNumberException;
import server.persistence.characterdata.CharacterData;
import server.persistence.characterdata.CharacterData.STATUS;

public class GuiGameConsole implements ActionListener {


	static Character player;
	static Character enemy ;
	private JPanel consolePanel;


	private int useMP;

	private String skillName;

	private double multi;

	// Zaehler. wie viel mal Wuerfel geworfen ist.
	private int count = 1;

	private static GuiGameConsole singleton;

	// ----------------------

	private JTextArea area = new JTextArea();
	private JTextField textField = new JTextField();
	private JScrollPane scrollPane;
	private JButton enterButton;
	private static boolean stop = true;
	private int num = 0;
	Facade single;


	private GuiGameConsole() {
		this.consolePanel = new JPanel();

		initialize();
	}

	public static GuiGameConsole getInstance(){
		if(singleton == null) singleton = new GuiGameConsole();

		return singleton;
	}

	public void setSingle(Facade single){
		this.single = single;
	}

	/**
	 * Gibt das Enterbutton zurueck.
	 * @return
	 */
	public JButton getButton() {
		return enterButton;
	}

	/**
	 * Initialisiert dies Console.
	 */
	private void initialize() {

		consolePanel.setSize(400, 200);
		area.setEditable(false);
		JPanel labelAndInputPanel = new JPanel();
		//		labelAndInputPanel.setLayout(new GridLayout(3, 1));// 3 Zeile, 1 Spalte
		labelAndInputPanel.setLayout(new MigLayout("fill"));// 3 Zeile, 1 Spalte


		//JLabel inputLabel = new JLabel("Eingabe ->  ");

		//inputLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		//labelAndInputPanel.add(inputLabel);
		labelAndInputPanel.add(textField,"gap 140,w 10%,wrap");
		enterButton = new JButton("Enter");
		labelAndInputPanel.setOpaque(false);
		enterButton.addActionListener(this);
		labelAndInputPanel.add(enterButton,"gap 120,w 20%");

		JPanel textPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(area);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		textPanel.add(scrollPane);
		textPanel.setOpaque(false);

		consolePanel.setLayout(new BorderLayout());// Inhalt des Frames zusammenbauen

		consolePanel.add(textPanel, BorderLayout.NORTH);
		consolePanel.add(labelAndInputPanel, BorderLayout.CENTER);
		consolePanel.setOpaque(false);
		consolePanel.setVisible(true);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		changeValueOfStop();

		autoScroll();

	}



	private void autoScroll() {
		area.setCaretPosition(area.getDocument().getLength());
	}

	public void appendln(String pText) {

		area.append(pText + "\n");
		autoScroll();
	}

	public void errorMsg(String msg){
		area.append("\n "+msg+" \n");
		autoScroll();
	}

	public void append(String pText) {
		area.append(pText);
		autoScroll();
	}

	private int getNumber() throws NumberFormatException { 


		int num = Integer.valueOf(textField.getText());
		textField.setText("");
		return num;
	}

	private void changeValueOfStop() {
		stop = !stop;
	}


	Object key = new Object();
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


	public void waitFewSecond(int ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void blankTextArea(){
		area.setText("");
	}

	//	public void showCharacterDetail(Character character){
	//		blankTextArea();
	//		appendln(character.getName()+"\nHP : "+character.getLife()+"\nMP : "+character.getMP());
	//	}

	public CharacterData selectCharacter() throws NumberFormatException, InvalidNumberException{

		appendln("\nWillkommen zu unserem Duengeon!! (enter)\n");
		waitForClick();

		appendln("bitte Charakter auswaehlen (enter)\n");

		int characterId = 0;

		waitForClick();

		CharacterData status = null;
		int count = 1;
		for (int id : common.valueobject.ICharacterDefs.CharakterID) {

			status = DuD.getGame().getCharacterData(id);

			if (Boolean.valueOf(status.getValue(STATUS.ISPLAYER)))
				appendln(((count++) + " :" + status.getValue(STATUS.NAME)));
		}

		waitForClick();


		characterId = getNumber();
		if(characterId > 5 || characterId < 1) throw new InvalidNumberException(characterId);


		status = DuD.getGame().getCharacterData(characterId - 1);

		appendln("\nLos geht's mit dem " + status.getValue(STATUS.NAME)
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
	 * @throws InvalidNumberException, NumberFormatException 
	 */
	public int selectCommandMessage(final Character ch) throws InvalidNumberException, NumberFormatException {// TODO Exception

		// Pruefer fuer die while Schleife
		boolean check = true;

		// zurueckzugebende Schaden
		int damage = 0;

		// solange es genug MP bleibt
		while (check) {

			//appendln("\nbitte Kommando auswaehlen\n");
			JOptionPane.showMessageDialog(null,"bitte Kommando auswaehlen!", "", JOptionPane.PLAIN_MESSAGE);

			//waitFewSecond(1000);

			// Bekommt die Faehigkeiten vom Charakter
			server.persistence.characterdata.CharacterData.Skill[] skills = ch.getSkills();

			int i = 1;
			for (server.persistence.characterdata.CharacterData.Skill skill : skills) {// Alle Faehigkeiten werden angezeigt

				appendln((i++) + " : " + skill.getName() + " /" + skill.getMP()
				+ "MP/Schaden*" + skill.getDamage());
			}
			appendln("4 : Aufladen\n");
			autoScroll();
			waitForClick();

			// Bekommt das Kommando


			//------TODO-------------
			int command = getNumber();
			if(command > 4 || command < 1) throw new InvalidNumberException(command);


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
					single.setMP(ch.getName(),-useMP);

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
			//appendln("Angreifen? (ENTER)\n");
			JOptionPane.showMessageDialog(null,"Angreifen!!", "", JOptionPane.PLAIN_MESSAGE);

			//waitForClick();
		}

		return damage;
	}

	public void battleMsg(Character player, Character enemy){

		int damage;
		blankTextArea();
		appendln("Kampf gegen "+enemy.getName()+"\n");
		GuiManager.getInstance().removeBorderByBattlePanel();
		GuiManager.getInstance().setBorderByBattlePanel(player.getName());
		appendln(player.getName()+" ist dran (enter)\n");
		JOptionPane.showMessageDialog(null,player.getName()+" ist dran", "", JOptionPane.PLAIN_MESSAGE);

		autoScroll();
		//waitForClick();
		appendln(player.toString());
		damage = getDamage(player);
		if (damage != 0) {

			roundMessageShowStatus(damage, enemy);
			if (!enemy.isAlive()) {
				DuD.getGame().removeEnemy(enemy.getName());
				//appendln(enemy.getName()+" ist besiegt!! (enter)\n");
				JOptionPane.showMessageDialog(null,enemy.getName()+" ist besiegt!", "", JOptionPane.PLAIN_MESSAGE);

				autoScroll();
				//waitForClick();
				return;
			}
		}
		// -------------Runde fuer Gegner-----------------
		appendln(enemy.toStringEnemy());

		GuiManager.getInstance().removeBorderByBattlePanel();
		GuiManager.getInstance().setBorderByBattlePanel(enemy.getName());
		damage = getDamage(enemy);
		if (damage != 0) {
			roundMessageShowStatus(damage, player);
		}
		appendln(player.toString());

		if(!player.isAlive()) {
			DuD.getGame().removePlayer(player);
			appendln(player.getName()+" ist gestorben...(enter)");
			autoScroll();
			waitForClick();
			return;
		}
		//appendln("Naechste Runde (enter) > \n");
		JOptionPane.showMessageDialog(null,"Naechste Runde", "", JOptionPane.PLAIN_MESSAGE);
		autoScroll();
		//waitForClick();



	}







	/**
	 * Hilfs Methode fuer roundMessage. Gibt den Schaden zurueck.
	 * @param c
	 * @return
	 */
	private int getDamage(Character c){
		boolean loop = true;
		int damage = 0;
		while(loop)
			try {
				damage = c.isPlayer() ? selectCommandMessage(c)
						: diceForAtk(c);
				loop = false;
			} catch (NumberFormatException | InvalidNumberException e) {
				GuiGameConsole.getInstance().errorMsg(e.getMessage());
				GuiGameConsole.getInstance().errorMsg("ungueltiges Zeichen! bitte nochmal eingeben");

			}

		return damage;
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
		if(character.isPlayer()){
		single.setLife(character.getName(),-damage);
		}else{
			single.setLifeToEnemy(character.getName(),-damage);
		}


		appendln(character.getLife() + "/" + character.getMaxLife()
		+ "\n");

		waitFewSecond(1500);
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
				+ "/2)  " : ch.getName() + " wuerfelt (" + (count++)
				+ "/2)  ";
		JOptionPane.showMessageDialog(null,message, "", JOptionPane.PLAIN_MESSAGE);

		//appendln(message);
		autoScroll();
		//waitForClick();
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

			//append("\nDie Nummer ist 1. Der Angriff ist fehlgeschlagen ..... (enter)\n");
			//waitForClick();
			JOptionPane.showMessageDialog(null,"Die Nummer ist 1. Der Angriff ging schief .....", "", JOptionPane.PLAIN_MESSAGE);

			count = 1;
			return 0;

		} else {

			return showNumMessage(num);
		}

	}

	/**
	 * Gibt die Augenzahl aus.
	 * 
	 * @param num
	 * @return
	 */
	public int showNumMessage(final int num) {
		//appendln("\nDie Nummer ist " + num + " \n");
		JOptionPane.showMessageDialog(null,"Die Nummer ist " + num, "", JOptionPane.INFORMATION_MESSAGE);
		autoScroll();
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


	public void showCharacterDetail(CharacterData data){
		blankTextArea();
		appendln(data.getValue(STATUS.NAME));
		appendln("HP "+data.getValue(STATUS.HP));
		appendln("MP "+data.getValue(STATUS.MP));
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
		//	appendln("Der Schaden ist (" + d01 + " * " + d02 + ") * " + multi
		//			+ " = " + iTotalDamage + "\n");
		JOptionPane.showMessageDialog(null,"Der Schaden ist (" + d01 + " * " + d02 + ") * " + multi
				+ " = " + iTotalDamage, "", JOptionPane.PLAIN_MESSAGE);

		multi = 1.0;
		return iTotalDamage;
	}



	//	/**
	//	 * Wuerfeln fuer die Bewergung.
	//	 * 
	//	 * @param ch
	//	 * @param rounds
	//	 * @return
	//	 */
	//	public void diceForRound(Character ch, int rounds) {
	//		
	//		roundDiceMessage(ch, rounds);
	//		
	//		
	//	}

	/**
	 * Wenn aud GUI Roll-Button angeklickt wird, dann dies Methode aufgerufen.
	 * 
	 * @param character
	 * @param rounds
	 */
	public void diceForMove(String name){//, int rounds) {
		blankTextArea();
		
			System.out.println("DiceForMove ((console))'n");
			blankTextArea();
			String msg ="Runde fuer "+ name + " \n Bitte wuerfel! ";
			appendln(msg);
			JOptionPane.showMessageDialog(null,msg, "", JOptionPane.PLAIN_MESSAGE);

			autoScroll();

	}

	public void encounterEnemy(){
		appendln("Gegner begegnet!!!!!\n");
		waitFewSecond(2000);
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

	public void gameLoadedMessage(){
		appendln("Weiter geht's mit unserem Dungeon!!");
	}

	public JPanel getConsolePanel() {

		return consolePanel;
	}



}
