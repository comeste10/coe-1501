// Steve Comer
// Project 2
// Pattern
// 24 February 2013

public class Pattern {
	
	public int width;
	public int height;
	public int value;
	public String name;
	
	public Pattern(int w, int h, int v, String s) {
		width = w;
		height = h;
		value = v;
		name = s;
	}
	
	public String toString() {
		return name;
	}

}
