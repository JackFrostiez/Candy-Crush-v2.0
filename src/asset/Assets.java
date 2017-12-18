package asset;

import java.awt.image.BufferedImage;

import graphics.ImageLoader;

public class Assets {
	
	public static BufferedImage green,red,blue,yellow,purple;

	
	public Assets(){
		
	}
	
	public static void loadFiles(){
		green = ImageLoader.loadImage("/green.png");   // 1
		red = ImageLoader.loadImage("/red.png");       // 2
		blue = ImageLoader.loadImage("/blue.png"); 	   // 3
		yellow = ImageLoader.loadImage("/yellow.png"); // 4
		purple = ImageLoader.loadImage("/purple.png"); // 5
	}
	
}

