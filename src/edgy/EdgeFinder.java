package edgy;

import java.awt.image.BufferedImage;

public class EdgeFinder {
	static int[][] pixels;
	static int compareValue = 100;
	
	public static BufferedImage findEdge(BufferedImage image) {
		pixels = new int[image.getWidth()][image.getHeight()];
		for(int rows = 0; rows < image.getWidth(); rows++) {
			for(int cols = 0; cols < image.getHeight(); cols++) {
				pixels[rows][cols] = image.getRGB(rows, cols);
			}
		}
		
		BufferedImage output = new BufferedImage(pixels.length, pixels[0].length, BufferedImage.TYPE_INT_ARGB);
		
		for(int rows = 0; rows < pixels.length; rows++) {
			for(int cols = 0; cols < pixels[0].length; cols++) {
				if (compareAdjacentPixels(rows, cols)) {
					output.setRGB(rows, cols, 0xFFFFFFFF);
				}
			}
		}
		return output;
	}
	
	/**
	 * Returns true if any of the adjacent pixels are vastly different in color
	 * @return
	 */
	private static boolean compareAdjacentPixels(int x, int y) {
		int[] colors, tmpcolors;
		colors = setRGBColors(pixels[x][y]);
		
		if(x != pixels.length-1) {
			tmpcolors = setRGBColors(pixels[x+1][y]);
			
			for (int i = 0; i < 3; i++) {
				if (Math.abs(colors[i]-tmpcolors[i]) > compareValue) {
					return true;
				}
			}
		}
		
		if (x != 0) {
			tmpcolors = setRGBColors(pixels[x-1][y]);
			
			for (int i = 0; i < 3; i++) {
				if (Math.abs(colors[i]-tmpcolors[i]) > compareValue) {
					return true;
				}
			}
		}
		
		if (y != pixels[0].length-1) {
			tmpcolors = setRGBColors(pixels[x][y+1]);
			
			for (int i = 0; i < 3; i++) {
				if (Math.abs(colors[i]-tmpcolors[i]) > compareValue) {
					return true;
				}
			}
		}
		
		if (y != 0) {
			tmpcolors = setRGBColors(pixels[x][y-1]);
			
			for (int i = 0; i < 3; i++) {
				if (Math.abs(colors[i]-tmpcolors[i]) > compareValue) {
					return true;
				}
			}
		}
		return false;
	}

	private static int[] setRGBColors(int rgb) {
		int[] temp = new int[3];
		for (int i = 2; i >= 0; i--) {
			temp[i] = (rgb >> i*8) & 0xFF;
		}
		return temp;
	}
		

}
