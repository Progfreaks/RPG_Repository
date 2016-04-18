package valueobject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputConsole {
	
	
	BufferedReader in;
    String select = null;
    
   
    public String getString(){
    	in = new BufferedReader(new InputStreamReader(System.in));
    	try {
    		select = in.readLine();
    	} catch (IOException e) {
    		
    		e.printStackTrace();
    	}
    	
    	return select;
    }
}

