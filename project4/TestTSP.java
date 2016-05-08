// Steve Comer
// Project 4
// TestTSP
// 7 April 2013

import java.util.ArrayList;
import java.util.Random;
import java.util.Date;
import javax.swing.JFrame;


public class TestTSP {
	
	public static int MAPWIDTH;
	public static int MAPHEIGHT;
	public static int CITYCOUNT;

	public static void main(String[] args) {
		
		// constants
		MAPWIDTH = 500;
		MAPHEIGHT = 500;
		CITYCOUNT = Integer.parseInt(args[0]);
		
		// variables
		ArrayList<City> cities;
		ArrayList<Edge> mst;
		ArrayList<City> tour;
		double mstWeight;
		double tourLength;
		
		// setup
		cities = makeCities(CITYCOUNT,MAPWIDTH,MAPHEIGHT);
		//System.out.println("Cities: ");
		//printCities(cities);
		//newline();
		
		// find an MST and display weight
		mst = TSP.getMST(cities);
		mstWeight = TSP.getMSTWeight(mst);
		System.out.println("MST weight: " + mstWeight);
		//System.out.println("Edges in MST: ");
		//printEdges(mst);
		//newline();
		
		// extract a tour and display length
		tour = TSP.getTour(mst);
		tourLength = TSP.getTourLength(tour);
		System.out.println("Tour length: " + tourLength);
		//System.out.println("Cities in tour: ");		
		//printCities(tour);
		//newline();
		
		// setup graphics
		JFrame tspFrame = new JFrame("MST-Walk Tour");
		Roadmap tspMap = new Roadmap(MAPWIDTH,MAPHEIGHT);
		tspFrame.getContentPane().add(tspMap);
		tspFrame.pack();
		tspFrame.setVisible(true);
		tspFrame.repaint();
		sleep(1000);
		
		// graphically display MST and tour
		for(int i=0; i<tour.size()-1; i++) {
			tspMap.paintEdge(new Edge(tour.get(i),tour.get(i+1)));
		}
		sleep(2000);
		for(City c : cities) {
			tspMap.paintCity(c);
		}
	}
	
	public static ArrayList<City> makeCities(int count, int width, int height) {
		Random r = new Random();
		ArrayList<City> atlas = new ArrayList<City>();
		for(int i=0; i<count; i++) {
			atlas.add(new City(r.nextDouble()*width,r.nextDouble()*height,"city_"+i));
		}
		return atlas;
	}
	
	public static void printCities(ArrayList<City> cities) {
		for(City c : cities) {
			System.out.println(c);
		}
	}
	
	public static void printEdges(ArrayList<Edge> edges) {
		for(Edge e : edges) {
			System.out.println(e.c1.name + "--" + e.c2.name);
		}
	}
	
	public static void newline() {
		System.out.println();
	}
	
	public static void sleep(long milliseconds) {
	    Thread thread = new Thread() ;
	    try { thread.sleep(milliseconds) ; }
	    catch (Exception e) {}
	}

}
