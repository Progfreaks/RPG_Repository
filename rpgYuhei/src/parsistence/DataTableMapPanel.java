package parsistence;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.net.ssl.SSLEngineResult.Status;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;


/**
 * Dies DataTablePanel enthaelt JCpmboBox, alle DataTable und Save-Button.
 * @author YOU_HEY
 *
 */
public class DataTableMapPanel extends JPanel implements IStatusDefs,ActionListener{

	
	
	private static final long serialVersionUID = -828153967471956355L;

	private JComboBox statusCombo = new JComboBox();
	
	private DataTablePanel selected;
	
	private DataTableMap map;
	
	/**
	 * Konstruktor
	 */
	public  DataTableMapPanel() {
	    
		map = DataTableMap.getInstance();
		
		//Fuegt alle DataTable in ComboBox hinzu.
		for(int status : STATUS_TABLE){
			

			statusCombo.addItem(map.getDateTable(status));
		}
		
		statusCombo.addActionListener(this);
		
		setLayout(new BorderLayout());
		
		add(statusCombo,BorderLayout.NORTH);
		
		JButton saveButton = new JButton("Speichern");
		
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selected.storeData();

				map.saveFile();
				
			}
		});
		
		add(saveButton,BorderLayout.SOUTH);
		
		displayDataTable();
	}
	
	/**
	 * Wenn JComboBox draufgeklickt wird, wird aufgerufen.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
	
	System.out.println(selected+" selected");
	selected.storeData();
	selected.setVisible(false);
	remove(selected);
	displayDataTable();
		
	}
	
	public void displayDataTable(){
		selected = new DataTablePanel((DateTable)statusCombo.getSelectedItem());
		add(selected,BorderLayout.CENTER);
	}

}
