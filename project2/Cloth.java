// Steve Comer
// Project 2
// Cloth (JPanel)
// 24 February 2013

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Cloth extends JPanel {
	
	public int height;
	public int width;
	public int scale;
	
	public Color cBackground = Color.black;
	public Color cCut = Color.green.darker();
	public Color cGarment = Color.green.brighter();
	public Color cBorder = Color.black;
	
	public Cloth(int w, int h, int s) {
		width = w;
		height = h;
		scale = s;
		setPreferredSize(new Dimension(width*scale,height*scale));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(cBackground);
		g.fillRect(0, 0, width*scale, height*scale);
	}
	
	public void drawGarment(Garment gar) {
		Graphics g = getGraphics();
		g.setColor(cGarment);
		g.fillRect(gar.xPos*scale, gar.yPos*scale, gar.width*scale, gar.height*scale);
		g.setColor(cBorder);
		g.drawRect(gar.xPos*scale, gar.yPos*scale, gar.width*scale, gar.height*scale);
		g.drawString(gar.name, gar.xPos*scale+8, gar.yPos*scale+20);
	}
	
	public void drawCut(Cut c) {
		Graphics g = getGraphics();
		g.setColor(cCut);
		g.drawLine(c.x1*scale, c.y1*scale, c.x2*scale, c.y2*scale);
	}
}