package parsistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Hier werden alle DataTable gespeichert.
 * @author YOU_HEY
 *
 */
public class DataTableMap implements Serializable,IStatusDefs{

	
    /**
	 * 
	 */
	private static final long serialVersionUID = -9181586459859931536L;

	//Speichert DataTable und entsprechende Status.
	private Map<Integer, DateTable> dataTableMap = new HashMap<Integer, DateTable>();
	
	// Singleton Objekt. Gewaehrleistet dass es nur eine Instanz exsistiert.
	private static DataTableMap singleton;
	
	// Vermeidet dass die Instanz dieser Klasse von anderen Klassen erzeugt wird.
	private DataTableMap(){}
	
	/**
	 * Wenn es keine Instanz exsistiert, dann wird eine Instanz erzeugt. Wenn
	 * existiert, dann gibt die zurück.
	 * 
	 * @return
	 */
	public static DataTableMap getInstance() {

		if (singleton == null)
			 loadFile();

		return singleton;
	}

	
	/**
	 * Bekommt die entsprechende Tabelle anhand Status. 
	 * @param pLevel
	 * @return
	 */
	public DateTable getDateTable(int pStatus) {


		//Wenn DataTableMap kein entsprechende DataTable hat, 
		//wird ein DateTable mit entsprechendem Status im DataTableMap gespeichert.
		if (!dataTableMap.containsKey(pStatus)) {
			dataTableMap.put(pStatus, new DateTable(pStatus));
		}
			return dataTableMap.get(pStatus);
	
	}

	/**
	 * Liest die Datei.
	 */
	private static void loadFile() {
		try {

			FileInputStream fIS = new FileInputStream("./datatable01.dat");
			ObjectInputStream oIS = new ObjectInputStream(fIS);
		
			singleton = (DataTableMap) oIS.readObject();
			
			oIS.close();
			System.out.println(" loaded");

		} catch (Exception e) {
          //to do
			System.out.println(" exceptin ausgloest...");
			singleton = new DataTableMap();

		}
	}

	/**
	 * Schreibt in die Datei.
	 */
	public void saveFile() {
		try {
			FileOutputStream fOS = new FileOutputStream("./datatable01.dat");
			ObjectOutputStream oOS = new ObjectOutputStream(fOS);
			oOS.writeObject(this);
			oOS.close();
			System.out.println(" saved");

		} catch (Exception e) {
			System.out.println(" exception ausgeloest...");

			e.printStackTrace();
		}
	}

}
