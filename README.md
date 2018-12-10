# Java Texture Manipulation Utilities

## Disclaimer: 
This work is essentially a port of the existing C code: https://sourceforge.net/p/ssbumpgenerator/svn/HEAD/tree/Normal2DuDv/, so I might not be fully available to answer the questions about the algorithms and constants used in the implementation.

## Building

The project requires Maven to be built from sources. To build the JAR for your code, you can execute the following command in the root of the texture-manipulation project:
```
	mvn package
```

You can add this project as Maven dependency using jitpack:
```
	<dependency>
	    <groupId>com.github.serhiy</groupId>
	    <artifactId>texture-manipulation</artifactId>
	    <version>0.0.1</version>
	</dependency>
	
	<repositories>
	    <repository>
	        <id>jitpack.io</id>
	        <url>https://jitpack.io</url>
	    </repository>
	</repositories>
```

## Examples:

- Convert image to height map:
```
	BufferedImage image = ImageIO.read("<input image path>");
	BufferedImage heightMap = ImageToHeightMap.convert(image);
	ImageIO.write(heightMap, "PNG", "<output image path>");
```
	
- Convert height map to normal map:
```
	BufferedImage image = ImageIO.read("<input image path>"); // should be height map
	BufferedImage normalMap = HeightMapToNormalMap.convert(image, 5.0f);
	ImageIO.write(normalMap, "PNG", "<output image path>");
```
	
- Convert normal map to DuDv map:
```
	BufferedImage image = ImageIO.read("<input image path>"); // should be normal map
	BufferedImage dudvMap = NormalMapToDuDvMap.convert(image);
	ImageIO.write(dudvMap, "PNG", "<output image path>");
```
