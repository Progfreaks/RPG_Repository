package server.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;

import server.domain.exceptions.MaxPlayerCountExceedException;
import server.domain.exceptions.SamePlayerSelectedException;
import server.persistence.characterdata.CharacterData;
import server.persistence.characterdata.CharacterData.Skill;
import client.gui.manager.GuiGameConsole;
import client.gui.manager.GuiGameCycle.PHASE;
import common.interfaces.DuDInterface;
import common.valueobject.Character;
//JOptionPane.showMessageDialog(null,"getcharacter!", "", JOptionPane.ERROR_MESSAGE);
public class ClientRequestProcess extends Thread{

	private int playerNumber;//è‡ªåˆ†ã�®ç•ªå�·
	private Socket socket;
	private String myName;//æŽ¥ç¶šè€…ã�®å��å‰�
	private DuDInterface game;
	private BufferedReader in;
	private PrintStream out;


	public  ClientRequestProcess(int n, Socket i, BufferedReader in, PrintStream out,DuDInterface g) {
		playerNumber = n;
		socket = i;
		this.in = in;
		this.out = out;
		game = g;
	}

	String inputString;
	public void run() {
		

		while(true){

			inputString = receiveTitle();

			System.out.println("Title received ((Server)) from "+playerNumber+" String is: "+inputString+"->>>>>\n");

			if(inputString != null){

				switch (inputString) {
				case "panel":
					String panelName = receiveString();
					Server.SendPanelNameToAll(panelName);
					if(panelName.equals("Main"))Server.SendPhaseToOne(playerNumber+2, "ROUND");

					break;
					
				case "phase":
					sendBackPhase(receiveString());
					break;

				case "addPlayer":
					addPlayer(receiveString());
					break;
				case "getPlayerName":
					getPlayerName();
					break;
					
				case "getPlayer"://bei fight event
					getPlayer();
					break;
				case "getPlayerByIndex"://bei battle panel
					getPlayerByIndex();
					break;
					
				case "getNextPlayer"://bei fight event
					getNextPlayer();
					break;
					
				case "getPlayerCount"://bei battle panel
					getPlayerCount();
					break;
				

				case "getXCharCoord":
					getXCharCoord();
					break;

				case "getYCharCoord":
					getYCharCoord();
					break;		
				case "setUpMapData":	
					setUpMapData(); 
					break;



				case "clickedCoords":	
					int[] coords =new int[2];
					coords[0] = toIntegerFronString(receiveString());
					coords[1] = toIntegerFronString(receiveString());		
					setClickedCoords(coords);
					break;

				case "coords":	
					int[] c =new int[2];
					c[0] = toIntegerFronString(receiveString());
					c[1] = toIntegerFronString(receiveString());		
					setCoords(c);
					break;
					
				case "addEnemy":	
					addEnemy();
					break;
					
				case "isAllEnemyDead":	//fuer fight event
					isAllEnemyDead();
					break;
					
				case "isAllPlayerDead":
					isAllPlayerDead();
					break;
					
				case "getEnemy":	//fuer fight event
					getEnemy();
					break;
					
				case "setLife":	//fuer fight event
					setLife();
					break;
					
				case "setLifeToEnemy":	//fuer fight event
					setLifeToEnemy();
					break;
					
				case "setMP":	//fuer fight event
					setMP();
					break;
					
				case "cureCharacter":
				cureCharacter();
				break;
				
				case "pullLever":
					pullLever();
					break;
					
				case "isAllLevePulled":
					isAllLevePulled();
					break;
					
				case "myNumber":
					int myNum = playerNumber-2;
					if(myNum < 0) myNum = 0;
					sendString(getStringFromInteger(myNum));
					break;
					
					
					
				default:
					break;
				}
			}else{
				System.out.println("Received!  ((Server)) -> received string is  "+inputString+" ends the loop...\n");
				break;
			}
		}
	}


	//-------for sending------------
	
	public void sendString(String msg){
		
		out.println(msg);
		out.flush();
		print("Send ((Server)) "+msg+" "+(new Date().getTime()));
	}
	
	boolean isEnemyAdded = false;//called from is allenemy dead
	private void addEnemy(){
		if(isEnemyAdded){
			sendString("added");
		}{
			sendString("notyet");
			String name = receiveString();
			int life = toIntegerFronString(receiveString());
			int mp = toIntegerFronString(receiveString());
			boolean isPlayer = toBooleanFromString(receiveString());
			Skill[] skills = null;
			Character enemy = new Character(name, life, mp, isPlayer, skills, 0, 0);
			game.addEnemy(enemy);
		}
	}

	private void addPlayer(String index){
		CharacterData data = game.getCharacterData(Integer.parseInt(index));
		Character player = game.createCharacter(data);
		try {
			game.addPlayer(player);
			sendString(player.getName());
			out.flush();
		} catch (MaxPlayerCountExceedException | SamePlayerSelectedException e) {
			
			sendString("no");
			sendString(e.getMessage());

			e.printStackTrace();
		}
	}

	
	public void sendBackPhase(String phase){
		
		switch (phase) {
		case "MOVE":
			Server.SendPhaseToAll(phase);
			print("Phase is MOVE!!");
			Server.SendToAll(getStringFromInteger(game.getCX()));
			Server.SendToAll(getStringFromInteger(game.getCY()));
			break;
		case "NEXT":
			print("Phase is NEXT!!");
			Server.SendPhaseToOne(playerNumber+2, "ROUND");
		break;
		case "FIGHT":
			print("Phase is FIGHT!!");
		//	Server.SendPanelNameToAll("Battle");
			Server.SendPhaseToAll(phase);
			Server.SendToAll(getStringFromInteger(game.getCX()));
			Server.SendToAll(getStringFromInteger(game.getCY()));
		break;
		
		case "FIGHT2":
			print("Phase is FIGHT2!!");
			Server.SendPhaseToOne(playerNumber, "FIGHT2");
		break;
		
		case "FIGHT3":
			print("Phase is FIGHT3!!");
			Server.SendPhaseToOne(playerNumber+2, "FIGHT3");
		break;
		
		case "CURE":
			print("Phase is CURE!!");
			Server.SendPhaseToAll(phase);
			Server.SendToAll(getStringFromInteger(game.getCX()));
			Server.SendToAll(getStringFromInteger(game.getCY()));
		break;
		
		case "LEVER":
			print("Phase is LEVER!!");
			Server.SendPhaseToAll(phase);
			Server.SendToAll(getStringFromInteger(game.getCX()));
			Server.SendToAll(getStringFromInteger(game.getCY()));
		break;



		default:
			sendString("phase");
			sendString(phase);
			
			break;
		}

	}
	
	private void getPlayerName(){
		sendString("getPlayerName");
		Character character = game.getPlayer();
		sendString(character.getName());
		out.flush();
	}
	
	private void getPlayer(){//bei fight
		Character player = game.getPlayer();
		sendString(player.getName());
		sendString(getStringFromInteger(player.getLife()));
		sendString(getStringFromInteger(player.getMP()));
		sendString(getStringFromBoolean(player.isPlayer()));
		
		sendString(getStringFromInteger(player.getMaxLife()));
		sendString(getStringFromInteger(player.getMaxMP()));
		
		
		
	}
	
	private void getPlayerByIndex(){//bei battle panel
		
		int index = toIntegerFronString(receiveString());
		Character player = game.getPlayerByIndex(index);
		sendString(player.getName());
		sendString(getStringFromInteger(player.getLife()));
		sendString(getStringFromInteger(player.getMP()));
		sendString(getStringFromBoolean(player.isPlayer()));
		
		sendString(getStringFromInteger(player.getMaxLife()));
		sendString(getStringFromInteger(player.getMaxMP()));
		
		
		
	}
	
	
	private void getNextPlayer(){
		print("GetNextPlayer excecuted!! !!");
		Character player = game.getNextPlayer();
		sendString(player.getName());
		sendString(getStringFromInteger(player.getLife()));
		sendString(getStringFromInteger(player.getMP()));
		sendString(getStringFromBoolean(player.isPlayer()));
		
		sendString(getStringFromInteger(player.getMaxLife()));
		sendString(getStringFromInteger(player.getMaxMP()));
		
	}
	
	private void getEnemy(){//bei fight
		String name = receiveString();
		Character enemy = game.getEnemy(name);
		
		sendString(getStringFromInteger(enemy.getLife()));
		sendString(getStringFromInteger(enemy.getMP()));
		sendString(getStringFromBoolean(enemy.isPlayer()));
		
		
		
	}
	
	private void getPlayerCount(){//bei battle panel benoetigt
		sendString(getStringFromInteger(game.getPlayerCount()));
	}
	
	
	private void getXCharCoord(){
		sendString("getXCharCoord");
		sendString(Integer.toString((game.getXCharCoord())));
		out.flush();
	}

	private void getYCharCoord(){
		sendString("getYCharCoord");
		sendString(Integer.toString((game.getYCharCoord())));
		out.flush();
	}
	
	private void isAllEnemyDead(){//fuer fight event
		isEnemyAdded = true;
		System.out.println("iaAllEnemyDead(Server)) "+isEnemyAdded);
		sendString(getStringFromBoolean(game.isAllEnemyDead()));
		
	}
	
	private void isAllPlayerDead(){//fuer fight event
		isEnemyAdded = true;
		sendString(getStringFromBoolean(game.isAllPlayerDead()));
		
	}
	
	//--------setter------------
	
	private void cureCharacter(){
		game.cureCharacter();
		

	}
	
	private void pullLever(){
		   game.pullLever();
		   sendString( game.getCountPulledLever());
	}
	
	private void isAllLevePulled(){
		boolean result = game.isAllLevePulled();
		sendString(getStringFromBoolean(result));
	}

	private void setUpMapData(){
		game.setUpMapData();
	}

	private void setClickedCoords(int[] coords){
		game.setClickedCoords(coords);
	}

	private void setCoords(int[] coords){
		game.setCoords(coords);
	}
	
	private void setLife(){
		String name = receiveString();
		int life = toIntegerFronString(receiveString());
		game.setLifeToPlayer(name,life);
	}
	
	private void setLifeToEnemy(){
		String name = receiveString();
		int life = toIntegerFronString(receiveString());
		game.setLifeToEnemy(name,life);
	}
	
	private void setMP(){
		String name = receiveString();
		int mp = toIntegerFronString(receiveString());
		game.setMPToCharacter(name,mp);
	}
	
	//-------sub----------
	
	private String receiveString(){
		String receive = null;
		try {
			receive = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("------>>>>>> after title [["+inputString+"]] ((Server)) from "+playerNumber+" String is: "+receive+"\n");
		return receive;
	}
	
	private String receiveTitle(){
		try {
			return  in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int toIntegerFronString(String param){
		return Integer.parseInt(param);
	}
	
	public boolean toBooleanFromString(String param){
		return Boolean.parseBoolean(param);
	}



	public String getStringFromInteger(Integer param){
		return Integer.toString(param);
	}
	
	private String getStringFromBoolean(boolean param){
		return Boolean.toString(param);
	}

	private int getNextPlayerIndex(){
		int index = playerNumber+1;
		if(index > Server.getPlayerCount()) index = 1;
		return index;
	}

	private int getPlayerIndex(){
		return playerNumber;
	}
	
	private void getMaxPlayerCount(){
		sendData("getMaxPlayerCount", game.getMaxPlayerCount());
	}

	private void setCharCoords(int[] coordinates){
		game.setCharCoords(coordinates[0], coordinates[1]);
	}
	private void getBoardMatrix(){
		sendData("getBoardMatrix", game.getBoardMatrix());
	}

	

	private void sendData(String messsage,Object data){
		System.out.println("data is sent back! from CRP >"+messsage);
		System.out.println("data is sent back! from CRP >"+data);
		System.out.println("data is sent back! from CRP > complete");
	}

	public void print(String param){
		System.out.println(param+"\n");
	}

}
