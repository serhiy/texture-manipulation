package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

// http://docs.oracle.com/javase/7/docs/api/java/awt/Color.html#getRGB%28%29
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedImage image = ImageIO.read(new File("test.png"));
		BufferedImage heightMap = ImageToHeightMap.convert(image);
		ImageIO.write(heightMap, "PNG", new File("test-greyscale.png"));
		BufferedImage normalMap = HeightMapToNormalMap.convert(heightMap, 1.0f);
		ImageIO.write(normalMap, "PNG", new File("test-normal.png"));
		BufferedImage dudvMap = NormalMapToDuDvMap.convert(normalMap);
		ImageIO.write(dudvMap, "PNG", new File("test-dudv.png"));
	}
}
