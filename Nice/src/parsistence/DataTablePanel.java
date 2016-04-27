package parsistence;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * Panel fuer jedes DataPanel.
 * @author YOU_HEY
 *
 */
public class DataTablePanel extends JPanel implements IStatusDefs {

	
	private static final long serialVersionUID = 8056902671150263793L;


	public List<JTextField> panelList = new ArrayList<JTextField>();
	
	
	private DateTable dataTable;
	
	/**
	 * Konstruktor. Bekommt DataTable uebergeben.
	 * @param pDateTable
	 */
	public DataTablePanel(DateTable pDateTable){
		
		dataTable = pDateTable;
		
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new GridLayout(10,4));
		
		for(int i = 1; i <= MAX_LEVEL; i++){
			JTextField text = new JTextField(10);
			if(dataTable != null){
				text.setText(String.valueOf(dataTable.getData(i)));
			}else{
				text.setText("0");
			}
			
			text.setHorizontalAlignment(JTextField.RIGHT);
			JLabel label = new JLabel(String.valueOf(i));
			
			//Damit es ueberschabar wird.
			if(i % 2 == 1){
				label.setOpaque(true);
				label.setBackground(Color.LIGHT_GRAY);
			}
			
			label.setHorizontalAlignment(JLabel.RIGHT);
			listPanel.add(label);
			listPanel.add(text);
			panelList.add(text);
		}
		
		setLayout(new BorderLayout());
		add(listPanel,BorderLayout.CENTER);
	}
	
	public String toString(){
		return dataTable.toString();
	}
	
	public void storeData(){
		int i = 1;
		for(JTextField textField : panelList){
			int intValue = 0;
			String value = textField.getText();
			if(value != null && value.length() != 0){
				try{
					intValue = Integer.parseInt(value);
					System.out.println(value);
				}catch(Exception e){
					
				}
			}
			dataTable.putData(i, intValue);
			i++;
		}
	}
}
