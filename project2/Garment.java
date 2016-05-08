// Steve Comer
// Project 2
// Garment
// 25 February 2013

public class Garment {
	
	public int xPos;
	public int yPos;
	public int width;
	public int height;
	public int value;
	public String name;
	
	public Garment(int x, int y, Pattern p) {
		xPos = x;
		yPos = y;
		width = p.width;
		height = p.height;
		value = p.value;
		name = p.name;
	}
	
	public Garment(int x, int y, int w, int h, int v, String n) {
		xPos = x;
		yPos = y;
		width = w;
		height = h;
		value = v;
		name = n;
	}
	
	public String toString() {
		return "(" + name + "," + xPos + "," + yPos + ")";
	}
	
}
