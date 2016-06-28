package server.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import client.gui.manager.GuiGameCycle.PHASE;
import common.interfaces.DuDInterface;
import server.domain.DuD;

public class Server {


	private static int maxConnection=100;//
	private static Socket[] sockets;//socket fuer empfang
	private static boolean[] isConnected;//ob es connected
	private static ClientRequestProcess[] processes;
	private static int streamIndex;//player anzahl, die verbunden ist
	private DuDInterface game;
	private static int  confirmCount = 0;
	private static BufferedReader[] in;
	private static PrintStream[] out;	// nicht Writer, damit auch telnet-Client mit Server kommunizieren kann
	public static int pnum =0;
	public static int ClientCount;

	public static void main(String[] args) {
		Server server = new Server();
	}


	public Server(){
		setUpConnection();
	}

	private void setUpConnection(){
		game = DuD.getGame();
		sockets = new Socket[maxConnection];
		isConnected = new boolean[maxConnection];
		processes = new ClientRequestProcess[maxConnection];
		in = new BufferedReader[maxConnection];
		out = new PrintStream[maxConnection];
		int n = 1;
		streamIndex = 0;//zuerst no player, so = 0
		try {
			System.out.println("The server has launched!\n");
			ServerSocket socketForMulti = new ServerSocket(10000);//10000ç•ªãƒ�ãƒ¼ãƒˆã‚’åˆ©ç”¨ã�™ã‚‹
			ServerSocket socketForSingle = new ServerSocket(9999);//10000ç•ªãƒ�ãƒ¼ãƒˆã‚’åˆ©ç”¨ã�™ã‚‹

			while (true) {
				Socket socket = socketForSingle.accept();
				sockets[n] = socketForMulti.accept();
				pnum++;
				game.increaseMaxPlayerCount();
				ClientCount++;
				System.out.println("max player count is "+game.getMaxPlayerCount()+"\n");
				isConnected[n] = true;
				System.out.println("Accept client No." + n+"\n");
				out[n] = new PrintStream(sockets[n].getOutputStream());
				in[n] = new BufferedReader(new InputStreamReader(sockets[n].getInputStream()));
				out[n+1] = new PrintStream(socket.getOutputStream());
				in[n+1] = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				processes[n] = new ClientRequestProcess(pnum, sockets[n], in[n], out[n],game);
				processes[n] .start();
				processes[n+1] = new ClientRequestProcess(n, socket, in[n+1], out[n+1],game);
				processes[n+1] .start();
				streamIndex= n;//erneut player count
				n=n+2;
			}
		} catch (Exception e) {
			System.err.println("ã‚½ã‚±ãƒƒãƒˆä½œæˆ�æ™‚ã�«ã‚¨ãƒ©ãƒ¼ã�Œç™ºç”Ÿã�—ã�¾ã�—ã�Ÿ: " + e);

		}
	}


	public static int getPlayerCount(){
		return streamIndex;
	}

	public static void SendToAll(String param){
		for(int i=1;i<=streamIndex;i=i+2){
			if(isConnected[i] == true){
				out[i].println(param);
				out[i].flush();
				System.out.println("Send ALL to client No."+i+"\n");
			}
		}	
	}


	public static void SendPanelNameToAll(String panel){
		if(panel.equals("Confirm")){
			confirmCount++;
			if(confirmCount == Server.ClientCount){
				SendToAll("panel");
				SendToAll(panel);
				Server.SendPhaseToOne(1,"ROUND");
			}
		}else if(panel.equals("Main")){
			SendToAll("panel");
			SendToAll(panel);
			
		}

		else{
			SendToAll("panel");
			SendToAll(panel);
		}
	}

	public static void SendPhaseToOne(int clientIndex,String phase){
		if(clientIndex > streamIndex) clientIndex = 1;
		if(isConnected[clientIndex] == true){
			out[clientIndex].println("phase");
			out[clientIndex].println(phase);
			out[clientIndex].flush();
			System.out.println("Send phase "+phase+"to client No."+clientIndex+"\n");
		}
	}

	public static void SendPhaseToAll(String phase){
		for(int i=1;i<=streamIndex;i=i+2){
			out[i].println("phase");
			out[i].println(phase);
			out[i].flush();
			System.out.println("Send Phase To All ((Server)) to client No."+i+"\n");
		}
	}

	private static void confirmCountUp(String inputString){
		if(inputString.equals("Confirm")) confirmCount++;
	}

	public static void SetFlag(int n, boolean value){	
		isConnected[n] = value;
	}

	public static void setPhase(PHASE p){
		//LOOP.phase = p;
	}

}


