// Steve Comer
// Project 2
// Rectangle
// 24 February 2013

public class Rectangle {
	
	public int width = 0;
	public int height = 0;
	public Pattern pattern = null;
	public char orient = 'a';
	public Rectangle child1 = null;
	public Rectangle child2 = null;
	public int xoff;
	public int yoff;
	public Cut cut;

	public Rectangle(int w, int h) {
		width = w;
		height = h;
	}
	
	public boolean isLeafNode() {
		if(this.child1 == null && this.child2 == null) return true;
		else return false;
	}
	
	public int getValue() {
		if(isLeafNode()) {
			if(pattern != null) return pattern.value;
			else return 0;
		}
		else if(child1 == null) return child2.getValue();
		else if(child2 == null) return child1.getValue();
		else return child1.getValue() + child2.getValue();
	}
	
	public String toString() {
		if(pattern != null) return pattern.name;
		String root = "(" + width + "," + height + ")";
		String c1 = (child1 != null) ? child1.toString() : "null";
		String c2 = (child2 != null) ? child2.toString() : "null";
		return root + "--> ( " + c1 + "\t" + c2 + " )";
	}
	
}
