package domain;

import gui.CommandoInput;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;



public final class CentralSave {
	

	
	public static CommandoInput console = null;
	
	public static void setConsole(CommandoInput pConsole){
		console = pConsole;
	}
	
	public BufferedImage loadImg(String path){
		
		try{
			File input = new File(path);
			BufferedImage image = ImageIO.read(input);;
			return image;
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
		
	}
}
