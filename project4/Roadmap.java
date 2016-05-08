// Steve Comer
// Project 4
// Roadmap
// 7 April 2013

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Roadmap extends JPanel {
	
	public int width;
	public int height;
	public double xmax;
	public double ymax;
	
	public Roadmap(int w, int h) {
		width = w;
		height = h;
		xmax = (double)w;
		ymax = (double)h;
		setPreferredSize(new Dimension(w,h));
	}
	
	public void paintCity(City c) {
		Graphics g = getGraphics();
		g.setColor(Color.blue);
		g.fillOval((int)(c.x)-3,(int)(c.y)-3,6,6);
	}
	
	public void paintEdge(Edge e) {
		Graphics g = getGraphics();
		g.setColor(Color.black);
		g.drawLine((int)e.c1.x,(int)e.c1.y,(int)e.c2.x,(int)e.c2.y);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
