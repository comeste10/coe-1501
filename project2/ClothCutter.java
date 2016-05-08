// Steve Comer
// Project 2
// ClothCutter
// 24 February 2013

import java.util.ArrayList;

public class ClothCutter {
	
	public int width;
	public int height;
	public ArrayList<Pattern> patterns;
	public Rectangle[][] memo;
	public ArrayList<Cut> cuts;
	public ArrayList<Rectangle> leaves;
	public ArrayList<Garment> garments;
	
	public ClothCutter(int w, int h, ArrayList<Pattern> p) {
		width = w;
		height = h;
		patterns = p;
		memo = new Rectangle[width+1][height+1];
		for(int i=0; i<=width; i++) {
			for(int j=0; j<=height; j++) {
				memo[i][j] = null;
			}
		}
		cuts = new ArrayList<Cut>();
		leaves = new ArrayList<Rectangle>();
		garments = new ArrayList<Garment>();
	}
	
	public Rectangle optimize(int w, int h) {
		
		int bestValue = 0;
		Pattern bestPattern = null;
		Rectangle bestRectangle = new Rectangle(w,h);
		Rectangle rtemp1;
		Rectangle rtemp2;
		boolean nothingFits = true;
		boolean exactMatch = false;
		
		// has it already been done?
		if(memo[w][h] != null) return memo[w][h];
		
		// instantiate new rectangle
		memo[w][h] = new Rectangle(w,h);
		
		// try all patterns
		for(Pattern p : patterns) {
			if(p.width <= w && p.height <= h) {
				nothingFits = false;
				if(p.value > bestValue) {
					bestValue = p.value;
					bestPattern = p;
					exactMatch = false;
					if(p.width == w && p.height == h) {
						exactMatch = true;
					}
				}
			}
		}
		
		if(nothingFits) {
			memo[w][h].pattern = new Pattern(w,h,0,"unused");
			memo[w][h].child1 = null;
			memo[w][h].child2 = null;
			return memo[w][h];
		}
		
		// pattern is either null or best exact match
		if(exactMatch) {
			memo[w][h].pattern = bestPattern;
			return memo[w][h];
		}
		
		// set bestRectangle with child1 = bestPattern and child2 = remaining and val 0
		bestRectangle.child1 = new Rectangle(bestPattern.width,bestPattern.height);
		bestRectangle.child1.pattern = bestPattern;
		bestRectangle.child2 = new Rectangle(w-bestPattern.width,h-bestPattern.height);
		
		// try all vertical cuts
		for(int i=1; i<w; i++) {
			rtemp1 = optimize(i,h);
			rtemp2 = optimize(w-i,h);
			if((rtemp1.getValue() + rtemp2.getValue()) > bestRectangle.getValue()) {
				bestValue = rtemp1.getValue() + rtemp2.getValue();
				bestRectangle.pattern = null;
				bestRectangle.orient = 'v';
				bestRectangle.child1 = rtemp1;
				bestRectangle.child2 = rtemp2;
			}
		}
		
		// try all horizontal cuts
		for(int i=1; i<h; i++) {
			rtemp1 = optimize(w,i);
			rtemp2 = optimize(w,h-i);
			if((rtemp1.getValue() + rtemp2.getValue()) > bestRectangle.getValue()) {
				bestValue = rtemp1.getValue() + rtemp2.getValue();
				bestRectangle.pattern = null;
				bestRectangle.orient = 'h';
				bestRectangle.child1 = rtemp1;
				bestRectangle.child2 = rtemp2;
			}
		}
		
		memo[w][h] = bestRectangle;
		return memo[w][h];
	}
	
	public void getLeaves(Rectangle r) {
		if(r.isLeafNode()) {
			leaves.add(r);
			return;
		}
		if(r.child1 != null) getLeaves(r.child1);
		if(r.child2 != null) getLeaves(r.child2);
	}
	
	public void getGarments(int x, int y, Rectangle r) {
		if(r.pattern != null) {
			garments.add(new Garment(x,y,r.pattern));
		}
		if(r.child1 != null) {
			getGarments(x,y,r.child1);
		}
		if(r.child2 != null) {
			x += (r.child1.width != r.width) ? r.child1.width : 0;
			y += (r.child1.height != r.height) ? r.child1.height : 0;
			getGarments(x,y,r.child2);
		}
	}
	
	public void getCuts(int x, int y, Rectangle r) {
		
		// no cuts
		if(r.child1 == null) return;
		
		// if at least one cut
		if(r.child1.width < r. width) {
			cuts.add(new Cut(x+r.child1.width, y, x+r.child1.width, y+r.height));
			getCuts(x, y, r.child1);
			getCuts(x+r.child1.width, y, r.child2);
			return;
		}
		
		// if at least one cut
		if(r.child1.height < r.height) {
			cuts.add(new Cut(x, y+r.child1.height, x+r.width, y + r.child1.height));
			getCuts(x, y, r.child1);
			getCuts(x, y+r.child1.height, r.child2);
			return;
		}
	}

}