// Steve Comer
// Project 2
// Cut
// 25 February 2013

public class Cut {
	
	public char orientation;
	public int x1, x2, y1, y2;
	public int location;
	
	public Cut(char o, int l) {
		orientation = o;
		location = l;
	}
	
	public Cut(int a, int b, int c, int d) {
		x1 = a;
		y1 = b;
		x2 = c;
		y2 = d;
	}
	
	public String toString() {
		return "(" + orientation + "," + location + ")";
	}
}
