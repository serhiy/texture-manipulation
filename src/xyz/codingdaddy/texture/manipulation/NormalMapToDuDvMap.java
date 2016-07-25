package xyz.codingdaddy.texture.manipulation;

import java.awt.image.BufferedImage;

/**
 * Constructs a DuDv map from the normal map.
 * 
 * @author sboychen
 */
public class NormalMapToDuDvMap {
	
	/**
	 * Constructs DuDv map from source image.
	 * 
	 * @param image corresponding to normal map.
	 * @return resulting DuDv map.
	 */
	public static BufferedImage construct(BufferedImage image) {
		BufferedImage duDvMap = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		for (int line = 0; line < image.getHeight(); line++) {
			for (int column = 0; column < image.getWidth(); column++) {
				int color = image.getRGB(column, line);
				int dudv = (((color >> 24) & 0xFF) << 24) + ((int)((((color) & 0xFF) / 255.0f * 2.0f - 1.0f) * 127.0f) << 16) + ((int)((((color >> 8) & 0xFF) / 255.0f * 2.0f - 1.0f) * 127.0f) << 8) + 0;
				duDvMap.setRGB(column, line, dudv);
			}
		}
		return duDvMap;
	}
}
