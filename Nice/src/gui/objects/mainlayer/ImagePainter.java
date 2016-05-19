package gui.objects.mainlayer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Diese Klasse dient dazu, dass ein Bild ins Component malt.
 * @author YOU_HEY
 *
 */
public final class ImagePainter {

	/**
	 * Malt das Panel mit dem geladenen Image.
	 * @param g
	 * @param panel
	 * @param path
	 */
	public static void paintPanel(Graphics g, JPanel panel, String path){
		BufferedImage image = loadImage(path);
		AffineTransform af = getScale(panel, image);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(image, af, panel);
	}

	/**
	 * Malt den Button mit dem geladenen Image.
	 * @param g
	 * @param button
	 * @param path
	 */
	public static void paintButton(Graphics g, JButton button, String path){
		BufferedImage image = loadImage(path);
		AffineTransform af = getScale(button, image);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(image, af, button);
	}

	/**
	 * Laedt ein Image anhand des Sourcefiles.
	 * @param path
	 * @return
	 */
	public static BufferedImage loadImage(String path){
		try {
			File inputFile = new File(path);
			return ImageIO.read(inputFile);
		} catch (IOException e) {
			e.printStackTrace();
			return  null;
		}
	}

	/**
	 * Rechnet das Verhaeltnis vom uebergebenen Image.
	 * @param component
	 * @param image
	 * @return
	 */
	private static AffineTransform getScale(JComponent component, BufferedImage image){
		double imageWidth = image.getWidth();
		double imageHeight = image.getHeight();
		double panelWidth = component.getWidth();
		double panelHeight = component.getHeight();
		// Rechnet das Verhaeltnis, dass wie gross dies Image ist.
		double sx = (panelWidth / imageWidth);
		double sy = (panelHeight / imageHeight);
		// Scaling
		return AffineTransform.getScaleInstance(sx, sy);
	}


}
