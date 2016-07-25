package xyz.codingdaddy.texture.manipulation;

import java.awt.image.BufferedImage;

/**
 * Constructs normal map from height map.
 * 
 * @author sboychen
 */
public class HeightMapToNormalMap {
	/**
	 * Converts height map to normal map (with only four adjacent pixels).
	 * 
	 * @param image corresponding to height map.
	 * @param scale controlling the color transition smoothness (the lower the scale is the smoother transition will be).
	 * @return resulting normal map.
	 */
	public static BufferedImage convert(BufferedImage image, float scale) {
		if (image == null) {
			throw new IllegalArgumentException("Source image cannot be null!");
		}
		
		if (scale < 0) {
			throw new IllegalArgumentException("Scale must be positive!");
		}
		
		BufferedImage normalMap = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		
		float [][] pixelHeights = new float[image.getHeight()][image.getWidth()];
		for (int line = 0; line < image.getHeight(); line++) {
			for (int column = 0; column < image.getWidth(); column++) {
				pixelHeights[line][column] = pixelHeight(image.getRGB(column, line), scale);
			}
		}
		
		int left, right, up, down, normalColorRGB, normalColorRGBA;
		for (int line = 0; line < image.getHeight(); line++) {
			for (int column = 0; column < image.getWidth(); column++) {
				left = column - 1;
				right = column + 1;
				up = line - 1;
				down = line + 1;

				if( left < 0 ) {
					left = image.getWidth() - 1;
				} else if( right >= image.getWidth()) {
					right = 0;
				}

				if( up < 0 ) {
					up = image.getHeight() - 1;
				} else if( down >= image.getHeight() ) {
					down = 0;
				}

				normalColorRGB = getNormal(pixelHeights[line][left], pixelHeights[up][column], pixelHeights[line][right], pixelHeights[down][column]);
				normalColorRGBA = (((image.getRGB(column, line) >> 24) & 0xFF) << 24) + normalColorRGB;
				
				normalMap.setRGB(column, line, normalColorRGBA);
			}
		}
		
		return normalMap;
	}
	
	private static float pixelHeight(int color, float scale) {
		return (((color) & 0xFF) + ((color >> 8) & 0xFF) + ((color >> 16) & 0xFF)) / 765.0f * scale;
	}
	
	private static int getNormal(float left, float up, float right, float down) {
		float x = left - right;
		float y = down - up;
		float z = 1.0f;
		
		float val = (float) (1.0f / Math.sqrt( x * x + y * y + z * z ));
		
		x *= val;
		y *= val;
		z *= val;
		
		return ((int)((x + 1) * 127.5f) << 16) + ((int)((y + 1) * 127.5f) << 8) + (int) ((z + 1) * 127.5f);
	}
}
