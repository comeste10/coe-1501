// Steve Comer
// Project 4
// City
// 7 April 2013

public class City {
	
	double x;
	double y;
	String name;
	
	public City(double xPos, double yPos, String n) {
		x = xPos;
		y = yPos;
		name = n;
	}
	
	public String toString() {
		return "(" + name + "; " + x + ", " + y + ")";
	}

}
