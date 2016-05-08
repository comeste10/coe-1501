// Steve Comer
// Project 4
// City
// 7 April 2013

public class Edge {

	public City c1;
	public City c2;
	public double weight;
	
	public Edge(City city1, City city2) {
		c1 = city1;
		c2 = city2;
		weight = TSP.getDistance(c1,c2);
	}
	
	public String toString() {
		return c1 + " --> " + c2 + "\t" + weight;
	}
	
}
