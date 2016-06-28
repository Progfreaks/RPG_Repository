package client.gui.components.sub;

import java.awt.Dimension;
import java.awt.Toolkit;

public final class WindowSize {

	public static int getWindowWidth(double divide){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		
		return (int) (dimension.getWidth()/divide);
	}
	
	public static int getWindowHeight(double divide){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		return (int)(dimension.getHeight()/divide);
	}
	

}
