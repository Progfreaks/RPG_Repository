package server.persistence.characterdata;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.net.ssl.SSLEngineResult.Status;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import common.valueobject.ICharacterDefs;



/**
 * Dies Klasse enthaelt SelectBox, alle CharacterData und Save-Button.
 * @author YOU_HEY
 *
 */
public class MainCharacterDataPanel extends JPanel implements ICharacterDefs,ActionListener{

	
	
	private static final long serialVersionUID = -828153967471956355L;

	//SelectBox. JComboBox zeigt alle Chharakter-Name, man kann jeden Name auswaehlen.
	@SuppressWarnings("rawtypes")
	private JComboBox selectCharacterBox = new JComboBox();
	
	private SubCharacterDataPanel selected;
	
	private CharacterDataMap map;
	
	/**
	 * Konstruktor
	 */
	@SuppressWarnings("unchecked")
	public  MainCharacterDataPanel() {
	    
		map = CharacterDataMap.getInstance();
		
		//Fuegt alle CharacterData in ComboBox hinzu.
		for(int characterId : CharakterID){
			
			
			selectCharacterBox.addItem(map.getCharacterData(characterId));
			
		}
		
		selectCharacterBox.addActionListener(this);
		
		setLayout(new BorderLayout());
		
		add(selectCharacterBox,BorderLayout.NORTH);
		
		JButton saveButton = new JButton("Speichern");
		
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				selected.storeData();
				//selected.storeDataForSkill();
				
				map.saveFile();
				
			}
		});
		
		add(saveButton,BorderLayout.SOUTH);
		
		displayCharacterData();
	}
	
	/**
	 * Wenn SelectBox draufgeklickt wird, wird diese Methode aufgerufen.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
	selected.storeData();
	//selected.storeDataForSkill();
	selected.setVisible(false);
	remove(selected);
	displayCharacterData();
		
	}
	
	public void displayCharacterData(){
		selected = new SubCharacterDataPanel((CharacterData)selectCharacterBox.getSelectedItem());
		add(selected,BorderLayout.CENTER);
	}

}
