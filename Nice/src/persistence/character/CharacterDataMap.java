package persistence.character;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Hier werden alle CharakterData in HashMap gespeichert.
 * Das Objekt dieser klasse wird in die Datei gespeichert. 
 * @author YOU_HEY
 *
 */
public class CharacterDataMap implements Serializable,ICharacterDefs{

	
    
	private static final long serialVersionUID = -9181586459859931536L;

	//Speichert CharacterData und entsprechenden CharakterID.
	private Map<Integer, CharacterData> characterDataMap = new HashMap<Integer, CharacterData>();
	//private Map<Integer, CharacterData> characterDataMap;

	
	// Singleton Objekt. Gewaehrleistet dass es nur eine Instanz exsistiert.
	private static CharacterDataMap singleton;
	
	// Vermeidet dass die Instanz dieser Klasse von anderen Klassen erzeugt wird.
	private CharacterDataMap(){}
	
	/**
	 * Wenn es keine Instanz exsistiert, dann wird eine Instanz erzeugt. Wenn
	 * existiert, dann gibt die zurück.
	 * 
	 * @return
	 */
	public static CharacterDataMap getInstance() {

		if (singleton == null)
			 loadFile();

		return singleton;
	}

	
	
	/**
	 * Bekommt die entsprechende Tabelle anhand des Charactername. 
	 * @param pLevel
	 * @return
	 */
	public CharacterData getCharacterData(int pCharacterId) {
		
	

		


		//Wenn CharacterDataMap kein entsprechende CharacterData hat, 
		//wird ein CharacterData im CharacterMap gespeichert.
		if (!characterDataMap.containsKey(pCharacterId)) {
			

			characterDataMap.put(pCharacterId, new CharacterData(pCharacterId));
			
		}

			return characterDataMap.get(pCharacterId);
	
	}

	/**
	 * Liest die Datei.
	 */
	private static void loadFile() {
		try {

			FileInputStream fIS = new FileInputStream("./ctable.dat");
			ObjectInputStream oIS = new ObjectInputStream(fIS);
		
			singleton = (CharacterDataMap) oIS.readObject();
			
			oIS.close();
			System.out.println("loaded");

		} catch (Exception e) {

			System.out.println(" keine Datei exsistiert. wird 'ctable.dat' erzeugt");
			singleton = new CharacterDataMap();

		}
	}

	/**
	 * Schreibt in die Datei.
	 */
	public void saveFile() {
		try {
			FileOutputStream fOS = new FileOutputStream("./ctable.dat");
			ObjectOutputStream oOS = new ObjectOutputStream(fOS);
			oOS.writeObject(this);
			oOS.close();
			System.out.println("saved");

		} catch (Exception e) {
			System.out.println(" exceptin saveFile");

			e.printStackTrace();
		}
	}

}
