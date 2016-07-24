package test;

import java.awt.image.BufferedImage;

public class ImageToHeightMap {
	public static BufferedImage convert(BufferedImage image) {
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
