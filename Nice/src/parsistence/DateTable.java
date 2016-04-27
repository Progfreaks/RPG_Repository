package parsistence;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

public class DateTable implements Serializable, IStatusDefs {

	
	

	private static final long serialVersionUID = 7935540778515823936L;

	//Speichert Level und entsprechende Werte.
	private Map<Integer, Integer> dataMap = new HashMap<Integer, Integer>();
	
	//Speichert Status als Integer
	private int status;
	
	/**
	 * Konstruktor. Bekommt Status als Paramerter uebergeben.
	 * @param pStatus
	 */
	public DateTable(final int pStatus){
		status = pStatus;
	}
	
	/**
	 * Bekommt entsprechende Wert anhand des uebergebenen Levels.
	 * @param pLevel
	 * @return
	 */
	public int getData(int pLevel){
     if(!dataMap.containsKey(pLevel))
    	 return 0;
     
     return dataMap.get(pLevel);
	
	}
	
	/**
	 * Setzt Level und Wert in HashMap.
	 * @param pLevel
	 * @param pData
	 */
	public void putData(int pLevel, int pData){
		dataMap.put(pLevel, pData);
	}
	
	/**
	 * Ueberprueft, ob Level groesser als Max-Level ist.
	 * @param pLevel
	 * @return
	 */
	public boolean isMaxLevel(int pLevel){
		return pLevel >= MAX_LEVEL;
	}
	
	@Override
	public String toString(){
		return STATUS_NAME_TABLE[status];
	}
}
