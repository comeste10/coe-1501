// Steve Comer
// Project 4
// TSP
// 7 April 2013

import java.util.ArrayList;

public class TSP {
	
	public static ArrayList<Edge> getMST(ArrayList<City> cities) {
		
		// variables
		ArrayList<Edge> edges = new ArrayList<Edge>();
		int[] neighborIndex = new int[cities.size()];
		boolean[] marked = new boolean[cities.size()];
		double[] distToTree = new double[cities.size()];
		City home;
		
		// initialize
		home = cities.get(0);
		distToTree[0] = 0;
		marked[0] = true;
		for(int i=1; i<cities.size(); i++) {
			neighborIndex[i] = 0;
			distToTree[i] = getDistance(home,cities.get(i));
			marked[i] = false;
		}
		
		// find MST
		for(int i=1; i<cities.size(); i++) {
			
			// get nearest neighbor
			int bestIndex = 0;
			double bestDist = Double.MAX_VALUE;
			City bestCity = null;
			for(int j=0; j<cities.size(); j++) {
				if(!marked[j] && distToTree[j] < bestDist) {
					bestDist = distToTree[j];
					bestIndex = j;
				}
			}
			bestCity = cities.get(bestIndex);
			
			// mark nearest neighbor and add edge
			marked[bestIndex] = true;
			edges.add(new Edge(cities.get(neighborIndex[bestIndex]),bestCity));
			
			// update distances to tree
			double tempDist;
			for(int k=0; k<cities.size(); k++) {
				if(!marked[k]) {
					tempDist = getDistance(bestCity,cities.get(k));
					if(tempDist < distToTree[k]) {
						distToTree[k] = tempDist;
						neighborIndex[k] = bestIndex;
					}
				}
			}
		}
		
		return edges;
	}
	
	public static double getMSTWeight(ArrayList<Edge> edges) {
		double weight = 0;
		for(Edge e : edges) weight += e.weight;
		return weight;
	}
	
	public static ArrayList<City> getTour(ArrayList<Edge> mst) {
		
		// setup
		ArrayList<City> tour = new ArrayList<City>();
		City home = mst.get(0).c1;
		
		// tree walk
		walkTree(home,tour,mst);
		
		// complete tour by returning home
		tour.add(home);
		return tour;
	}
	
	public static void walkTree(City c, ArrayList<City> tour, ArrayList<Edge> mst) {
		tour.add(c);
		for(Edge e : mst) {
			if(e.c1 == c) {
				walkTree(e.c2,tour,mst);
			}
		}
	}
	
	public static double getTourLength(ArrayList<City> tour) {
		double length = 0;
		for(int i=0; i<tour.size()-1; i++) {
			length += getDistance(tour.get(i),tour.get(i+1));
		}
		return length;
	}
	
	public static City getNearestNeighbor(City c, ArrayList<City> cities) {
		City tempCity = null;
		City bestCity = null;
		double tempDistance = 0.0;
		double bestDistance = Double.MAX_VALUE;
		for(int i=0; i<cities.size(); i++) {
			tempCity = cities.get(i);
			if(tempCity.name != c.name) {
				tempDistance = getDistance(c,tempCity);
				if(tempDistance < bestDistance) {
					bestDistance = tempDistance;
					bestCity = tempCity;
				}
			}
		}
		return bestCity;
	}
	
	public static double getDistance(City c1, City c2) {
		return Math.sqrt(Math.pow(c1.x-c2.x,2)+Math.pow(c1.y-c2.y,2));
	}

}
