package edgy;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Panel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	static String file = "/tree.jpg";

	public Panel() {
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.LIGHT_GRAY);
		
		BufferedImage testImage = loadBufferedImage(file);
		
		BufferedImage image = EdgeFinder.findEdge(testImage);
		
		g.drawImage(testImage, 0, 0, image.getWidth(), image.getHeight(), null);
		
		g.drawImage(image.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_DEFAULT), image.getWidth(), 0, image.getWidth(), image.getHeight(), null);
		
	}
	
	public static BufferedImage loadBufferedImage(String filePath) {
		try {
			BufferedImage image = ImageIO.read(Main.class.getResource(filePath));
			return image;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}