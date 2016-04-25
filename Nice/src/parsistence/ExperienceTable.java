package parsistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ExperienceTable implements Serializable {

	
	private static final long serialVersionUID = 8389067946119182035L;

	public static int MAX_LEVEL = 50;
	
	private Map<Integer, Integer> expMap = new HashMap<Integer, Integer>();
	
	private static ExperienceTable singleton;
	
	private ExperienceTable(){};
	
	public static ExperienceTable getInstance(){
		
		if(singleton == null) loadFile();
		
		return singleton;
	}
	
	public int getExperience(int pLevel)  {
		
		


if(expMap.get(pLevel) != null){
		return expMap.get(pLevel);
	}else{
		return 0;
	}
		}
	
	public int getLevel(int pExperience){
		
		int _return  = 1;
		for(Map.Entry<Integer, Integer> exp : expMap.entrySet()){
			if(exp.getValue() < pExperience){
				_return = exp.getKey();
			}else{
				break;
			}
		}
		
		return _return;
		
	}
	
	public void putExperience(int pLevel, int pExperience){
		expMap.put(pLevel, pExperience);
	}
	
	public boolean isMaxLevel(int pLevel){
		return pLevel >= MAX_LEVEL;
	}
	
	private static void loadFile(){
		try {
			
			FileInputStream fIS = new FileInputStream("./exptable.dat");
			ObjectInputStream oIS = new ObjectInputStream(fIS);
			singleton = (ExperienceTable) oIS.readObject();
			oIS.close();
		} catch (Exception e) {
			singleton = new ExperienceTable();
		}
	}
	
	public void saveFile(){
		try {
			FileOutputStream fOS = new FileOutputStream("./exptable.dat");
			ObjectOutputStream oOS = new ObjectOutputStream(fOS);
			oOS.writeObject(this);
			oOS.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
