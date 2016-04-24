package parsistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Das Objekt dieser Klasse kann sowhl gespeichert als auch weiderhergestellt
 * werden. Um das zu realiseren, ist diese Klasse mit Serializable
 * implementiert.
 * 
 * @author YOU_HEY
 *
 */
public class ExperienceTable implements Serializable {

	private static final long serialVersionUID = 8389067946119182035L;

	public static int MAX_LEVEL = 50;

	private Map<Integer, Integer> expMap = new HashMap<Integer, Integer>();

	// Singleton Objekt. Gewaehrleistet dass es nur eine Instanz exsistiert.
	private static ExperienceTable singleton;

	// Vermeidet dass die Instanz dieser Klasse von anderen Klassen erzeugt
	// wird.
	private ExperienceTable() {
	};

	/**
	 * Wenn es keine Instanz exsistiert, dann wird eine Instanz erzeugt. Wenn
	 * existiert, dann gibt die zurück.
	 * 
	 * @return
	 */
	public static ExperienceTable getInstance() {

		if (singleton == null)
			loadFile();

		return singleton;
	}

	
	/**
	 * Bekommt Erfahrungspunkte je nach dem Level.
	 * @param pLevel
	 * @return
	 */
	public int getExperience(int pLevel) {

		if (expMap.get(pLevel) != null) {
			return expMap.get(pLevel);
		} else {
			return 0;
		}
	}

	/**
	 * Bekommt Level je nach  Erfahrungspunkte.
	 * @param pExperience
	 * @return
	 */
	public int getLevel(int pExperience) {

		int _return = 1;
		for (Map.Entry<Integer, Integer> exp : expMap.entrySet()) {
			if (exp.getValue() < pExperience) {
				_return = exp.getKey();
			} else {
				break;
			}
		}

		return _return;

	}

	/**
	 * Setzt Erfahrungsppunkte und Level.
	 * @param pLevel
	 * @param pExperience
	 */
	public void putExperience(int pLevel, int pExperience) {
		expMap.put(pLevel, pExperience);
	}

	/**
	 * Gibt Maxmal Level zurueck.
	 * @param pLevel
	 * @return
	 */
	public boolean isMaxLevel(int pLevel) {
		return pLevel >= MAX_LEVEL;
	}

	/**
	 * Liest das File.
	 */
	private static void loadFile() {
		try {

			FileInputStream fIS = new FileInputStream("./exptable.dat");
			ObjectInputStream oIS = new ObjectInputStream(fIS);
			singleton = (ExperienceTable) oIS.readObject();
			oIS.close();
		} catch (Exception e) {
			singleton = new ExperienceTable();
		}
	}

	/**
	 * Schreibt ins File.
	 */
	public void saveFile() {
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
