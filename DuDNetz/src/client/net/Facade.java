package client.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;

import javax.swing.JOptionPane;

import client.gui.manager.GuiGameCycle;
import client.gui.manager.GuiGameCycle.PHASE;
import common.valueobject.Character;
import common.valueobject.ICharacterDefs;
import client.gui.manager.GuiManager;
import server.domain.DuD;
import server.persistence.characterdata.CharacterData.Skill;
public class Facade extends Thread implements  ICharacterDefs{ //implements DuDInterface{

	private Socket socket;
	private	BufferedReader in;
	private PrintStream out;
	private GuiManager guiManager;
	Object key = new Object();
	private String result;
	int receivedInt;

	public  Facade(Socket s,GuiManager g){//fuer multiple kommunikation
		socket = s;
		guiManager = g;
	}

	public Facade(Socket s){//fuer einzel komminikation
		socket = s;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void run() {


		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}


		while (true) {
			System.out.println("ready to read!!\n");

			String inputString = null;
			try {
				inputString = in.readLine();
				System.out.println("Received Title!  ((Client)) -> received string is  "+inputString+"\n");
			} catch (IOException e) {
				e.printStackTrace();
			}



			if(inputString != null){
				switch (inputString) {
				case "panel":
					guiManager.createPanel(receiveString());
					break;

				case "addPlayer":
					result = receiveString();
					wake();
					break;

				case "phase":
					processPhase(receiveString());
					break;


				case "getXCharCoord":
					result = receiveString();
					wake();
					break;

				case "getYCharCoord":
					result = receiveString();
					wake();

					break;	


				default:
					break;


				}
			}else{
				System.out.println("Received!  ((Client)) -> received string is  "+inputString+" ends the loop...\n");
				break;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	//-----------for sending------------------

	public void sendString(String s){
		print("Send ((Client))  ->> "+s+(new Date().getTime()));
		out.println(s);
		out.flush();
	}

	public void sendPanelName(String name){
		sendString("panel");
		sendString(name);
	}

	public void sendPhase(String phase){
		sendString("phase");
		sendString(phase);
	}


	public String getPlayerName(){
		sendString("getPlayerName");
		String pname = receiveString();
		pname = receiveString();
		return pname;
	}
	
	public Character getPlayer(){//fuer fight event 
		sendString("getPlayer");
		String name = receiveString();
		int life = getIntFromString(receiveString());
		int mp = getIntFromString(receiveString());
		boolean isPlayer = getBooleanFromString(receiveString());
		int maLlife = getIntFromString(receiveString());
		int maxMp = getIntFromString(receiveString());
		Skill[] skills = getSkills(name);
		Character player = new Character(name, life, mp, isPlayer, skills, 0, 0);
		player.setMaxLife(maLlife);
		player.setMaxMP(maxMp);
		return player;
	}
	
	public Character getPlayerByIndex(int index){//fuer fight event 
		sendString("getPlayerByIndex");
		sendString(getStringFromInteger(index));
		String name = receiveString();
		int life = getIntFromString(receiveString());
		int mp = getIntFromString(receiveString());
		boolean isPlayer = getBooleanFromString(receiveString());
		int maLlife = getIntFromString(receiveString());
		int maxMp = getIntFromString(receiveString());
		Skill[] skills = getSkills(name);
		Character player = new Character(name, life, mp, isPlayer, skills, 0, 0);
		player.setMaxLife(maLlife);
		player.setMaxMP(maxMp);
		return player;
	}
	
	public Character getNextPlayer(){
		sendString("getNextPlayer");
		String name = receiveString();
		int life = getIntFromString(receiveString());
		int mp = getIntFromString(receiveString());
		boolean isPlayer = getBooleanFromString(receiveString());
		int maLlife = getIntFromString(receiveString());
		int maxMp = getIntFromString(receiveString());
		Skill[] skills = getSkills(name);
		Character player = new Character(name, life, mp, isPlayer, skills, 0, 0);
		player.setMaxLife(maLlife);
		player.setMaxMP(maxMp);
		return player;
	}
	
	public Character getEnemy(String name){//fuer fight event 
		sendString("getEnemy");
		sendString(name);
		
		int life = getIntFromString(receiveString());
		int mp = getIntFromString(receiveString());
		boolean isPlayer = getBooleanFromString(receiveString());
		Skill[] skills = getSkills(name);
		Character enemy = new Character(name, life, mp, isPlayer, null, 0, 0);
		return enemy;
	}
	
	public void setLife(String name,int life){
		sendString("setLife");
		sendString(name);
		sendString(getStringFromInteger(life));
	}
	
	public void setLifeToEnemy(String name,int life){
		sendString("setLifeToEnemy");
		sendString(name);
		sendString(getStringFromInteger(life));
	}
	
	public void setMP(String name,int mp){
		sendString("setMP");
		sendString(name);
		sendString(getStringFromInteger(mp));
	}
	
	private Skill[] getSkills(String name){//hilfs methode fuer getPlayer()
		int characterIndex = 0;
		switch (name) {
		case "Held":
			 characterIndex = HELD;
			break;
		case "magier":
			 characterIndex = MAGIER;
			break;
		case "Kobold":
			 characterIndex = KOBOLD;
			break;
		case "Spiderman":
			 characterIndex = SPIDERMAN;
			break;
		case "Ninja":
			 characterIndex = NINJA;
			break;
		

		default:
			break;
		}
		DuD game = DuD.getGame();//braucht nur um character skill zu bekommen 
		return game.createCharacter(game.getCharacterData(characterIndex)).getSkills();
	}
	
	public Character[] getPlayers(){//bei battle panel ausgefuehrt
		sendString("getPlayerCount");
		int size = getIntFromString(receiveString());
		Character[] players = new Character[size];
		for(int i=0;i<size;i++){
			players[i] = getPlayerByIndex(i);//TODO
		}
		return players;
	}
 	private Boolean getBooleanFromString(String param){
		return Boolean.parseBoolean(param);
	}
 	
 	


	public String addPlayer(int index){

		sendString("addPlayer");
		out.println(index);
		out.flush();
		String aString = receiveString();
		if(aString.equals("no")) {
			String msg = receiveString();
			JOptionPane.showMessageDialog(null, msg, "", JOptionPane.ERROR_MESSAGE);

		}
		return aString;


	}
	
	public void addEnemy(Character enemy){
		sendString("addEnemy");
		String isAdded = receiveString();
		if(isAdded.equals("notyet")){
			
			sendString(enemy.getName());
			sendString(getStringFromInteger(enemy.getLife()));
			sendString(getStringFromInteger(enemy.getMP()));
			sendString(getStringFromBoolean(enemy.isPlayer()));
			
		}
		//DuD.getGame().addEnemy(enemy);
	}
	
	public boolean isAllEnemyDead(){
		sendString("isAllEnemyDead");
		boolean received = getBooleanFromString(receiveString());
		return received;
	}
	
	public boolean isAllPlayerDead(){//bei guiconsole
		sendString("isAllPlayerDead");
		boolean received = getBooleanFromString(receiveString());
		return received;
	}
	
	
	
	public Character createEnemy(int characterIndex){//bei Guimaphandler
		DuD game = DuD.getGame();//braucht nur um enemy zu bekommen 
		Character enemy = game.createCharacter(game.getCharacterData(characterIndex));
		setEnemyStrength(enemy);
		return enemy;
	}
	
	private void setEnemyStrength(Character enemy){//hilfs methode fuer createEnemy()
		sendString("getPlayerCount");
		int playerCount = getIntFromString(receiveString());
		int strength = (playerCount-1);
		enemy.setLife(enemy.getLife()*strength);
		enemy.setMP(enemy.getMP()*strength);
		enemy.setMaxLife(enemy.getLife());
		enemy.setMaxMP(enemy.getMP());
	}


	public int getXCharCoord() {
		sendString("getXCharCoord");
		receiveString();
		return getIntFromString(receiveString());

	}


	public int getYCharCoord() {
		sendString("getYCharCoord");
		receiveString();
		return getIntFromString(receiveString());
	}

	public void setUpMapData() {
		sendString("setUpMapData");
	}

	public void setClickedCoords(int[] coords){
		sendString("clickedCoords");
		out.println(coords[0]);
		out.flush();
		out.println(coords[1]);
		out.flush();
	}

	public void setCoords(int[] coords){
		sendString("coords");
		out.println(coords[0]);
		out.flush();
		out.println(coords[1]);
		out.flush();
	}

	public void setPlayerXCoords(int x){
		sendString("setPlayerXCoords");
		out.println(x);
		out.flush();
	}

	public void setPlayerYCoords(int y){
		sendString("setPlayerYCoords");
		out.println(y);
		out.flush();
	}
	
	public void cureCharacter(){
		sendString("cureCharacter");
	}
	
	public String pullLever(){
		sendString("pullLever");
		return receiveString();
	}
	
	public boolean isAllLevePulled(){
		sendString("isAllLevePulled");
		return getBooleanFromString(receiveString());
	}
	public int getMyNumber(){
		sendString("myNumber");
		return getIntFromString(receiveString());
	}


	//------------after receiving---------------

	private void processPhase(String phase){
		switch (phase) {
		case "ROUND":
			GuiGameCycle.getInstance().setPhase(PHASE.ROUND);
			break;
		case "ROLL":
			GuiGameCycle.getInstance().setPhase(PHASE.ROLL);
			break;	
		case "MOVE":
			GuiGameCycle.getInstance().setPhase(PHASE.MOVE);
			GuiGameCycle.getInstance().setClickedXCoordinate(getIntFromString(receiveString()));
			GuiGameCycle.getInstance().setClickedYCoordinate(getIntFromString(receiveString()));
			break;	
		case "FIGHT":
			GuiGameCycle.getInstance().setPhase(PHASE.FIGHT);
			GuiGameCycle.getInstance().setClickedXCoordinate(getIntFromString(receiveString()));
			GuiGameCycle.getInstance().setClickedYCoordinate(getIntFromString(receiveString()));
			break;	
		case "FIGHT2":
			GuiGameCycle.getInstance().setPhase(PHASE.FIGHT2);
			break;	
			
		case "FIGHT3":
			GuiGameCycle.getInstance().setPhase(PHASE.FIGHT3);
			break;	
			
		case "CURE":
			GuiGameCycle.getInstance().setPhase(PHASE.CURE);
			GuiGameCycle.getInstance().setClickedXCoordinate(getIntFromString(receiveString()));
			GuiGameCycle.getInstance().setClickedYCoordinate(getIntFromString(receiveString()));
			break;	
			
		case "LEVER":
			GuiGameCycle.getInstance().setPhase(PHASE.LEVER);
			GuiGameCycle.getInstance().setClickedXCoordinate(getIntFromString(receiveString()));
			GuiGameCycle.getInstance().setClickedYCoordinate(getIntFromString(receiveString()));
			break;	
		default:
			break;
		}

		GuiGameCycle.getInstance().takeAction();
	}

	private String receiveString(){
		String getString = null;
		try {
			getString = in.readLine();
			if(getString.isEmpty() ) System.out.println("emptys  "+getString+"\n");

			System.out.println("Received!  ((Client)) -> received string is  "+getString+"\n");
			return getString;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	


	private int  getIntFromString(String param){
		return Integer.parseInt(param);
	}
	
	public String getStringFromInteger(Integer param){
		return Integer.toString(param);
	}
	
	private String getStringFromBoolean(boolean param){
		return Boolean.toString(param);
	}

	


	public void wake(){
		synchronized (key) {
			key.notify();
		}
	}

	public void waitReturnValue(){
		print("waiting for answer from  server...\n");

		synchronized (key) {
			try {
				key.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
		print("wake done!!");
	}



	public void print(String param){
		System.out.println(param+"\n");
	}
}

