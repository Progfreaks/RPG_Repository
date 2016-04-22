package parsistence;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ExperienceTablePanel extends JPanel implements ActionListener {

	
	private static final long serialVersionUID = 1220590933554803342L;

	public List<JTextField> panelList = new ArrayList<JTextField>();
	
	
	
	
	public ExperienceTablePanel(){
		
		ExperienceTable eTable = ExperienceTable.getInstance();

		JPanel listPanel = new JPanel();
		listPanel.setLayout(new GridLayout(10, 4));

		for(int i = 1; i <= ExperienceTable.MAX_LEVEL; i++){
			JTextField textField = new JTextField(10);
			

			
			if(eTable.getExperience(i) != 0){

				textField.setText(String.valueOf(eTable.getExperience(i)));
			}else{
				textField.setText("0");
			}
			

			textField.setHorizontalAlignment(JTextField.RIGHT);
			
			JLabel label = new JLabel(String.valueOf(i));
			
			if(i % 2 == 1){
				label.setOpaque(true);
				label.setBackground(Color.LIGHT_GRAY);
			}
			
			label.setHorizontalAlignment(JLabel.RIGHT);
			listPanel.add(label);
			listPanel.add(textField);
			panelList.add(textField);
		}
		
		setLayout(new BorderLayout());
		
		JButton button = new JButton("Save");
		
		button.addActionListener(this);
		
		add(listPanel,BorderLayout.CENTER);
		add(button,BorderLayout.SOUTH);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		saveFile();
	}
	
	public void saveFile(){
		ExperienceTable table = ExperienceTable.getInstance();
		int i = 1;
		for(JTextField textField : panelList){
			int intValue = 0;
			String value = textField.getText();
			if(value != null && value.length() != 0){
				try {
					intValue = Integer.parseInt(value);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			table.putExperience(i, intValue);
			i++;
		}
		table.saveFile();
	}
}
