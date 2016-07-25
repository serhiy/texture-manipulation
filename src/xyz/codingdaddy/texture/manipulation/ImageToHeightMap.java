package xyz.codingdaddy.texture.manipulation;

import java.awt.image.BufferedImage;

/**
 * Constructs a height map from the source image.
 * 
 * @author sboychen
 */
public class ImageToHeightMap {
	
	/**
	 * Converts source image to height map.
	 * 
	 * @param image to be converted to height map.
	 * @return resulting height map.
	 */
	public static BufferedImage convert(BufferedImage image) {
		if (image == null) {
			throw new IllegalArgumentException("Source image cannot be null!");
		}
		
		BufferedImage greyscaleImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		for (int line = 0; line < image.getHeight(); line++) {
			for (int column = 0; column < image.getWidth(); column++) {
				int color = image.getRGB(column, line);
				int grey = (int) ((((color) & 0xFF) * .299f) + (((color >> 8) & 0xFF) * .587f) + ((color >> 16) & 0xFF) * .114f);
				int greyscaleColor = (((color >> 24) & 0xFF) << 24) + (grey << 16) + (grey << 8) + grey;
				greyscaleImage.setRGB(column, line, greyscaleColor);
			}
		}
		return greyscaleImage;
	}
}
