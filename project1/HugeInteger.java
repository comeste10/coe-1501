// Steve Comer
// Project 1
// HugeInteger
// 23 January 2013

import java.util.*;

public class HugeInteger implements Comparable<HugeInteger> {
	
	public ArrayList<Integer> hiRev = new ArrayList<Integer>();
	public static int DIGIT_OPERATIONS;
	
	public HugeInteger(String s) {
		
		if(s == "");
		else {
			int temp;
			// stores number in "reverse" order
			temp = 0;
			for(int i=s.length()-1; i>=0; i--) {
				temp = Character.getNumericValue(s.charAt(i));
				hiRev.add(temp);
			}
		}
	}
	
	public int compareTo(HugeInteger h) {
		
		//this.trimZeros();
		//h.trimZeros();
		
		// returns -1 if this < h
		if(this.hiRev.size() < h.hiRev.size()) {
			DIGIT_OPERATIONS++;
			return -1;
		}
		
		// returns 1 if this > h
		else if(this.hiRev.size() > h.hiRev.size()) {
			DIGIT_OPERATIONS++;
			return 1;
		}
		
		// returns 0 if this == h
		else {
			int tempT = 0;
			int tempH = 0;
			for(int i=this.hiRev.size()-1; i>=0; i--) {
				tempT = this.hiRev.get(i);
				tempH = h.hiRev.get(i);
				DIGIT_OPERATIONS++;
				if(tempT != tempH) return (tempT < tempH) ? -1 : 1;
			}
			return 0;
		}
	}
	
	public HugeInteger add(HugeInteger h) {
		HugeInteger tempHI = new HugeInteger("");
		int carry = 0;
		int temp = 0;
		int tempT = 0;
		int tempH = 0;
		int biggerSize = (this.hiRev.size() >= h.hiRev.size()) ? this.hiRev.size() : h.hiRev.size();
		for(int i=0; i<biggerSize; i++) {
			tempT = (i<this.hiRev.size()) ? this.hiRev.get(i) : 0;
			tempH = (i<h.hiRev.size()) ? h.hiRev.get(i) : 0;
			temp = carry + tempT + tempH;
			DIGIT_OPERATIONS++;
			tempHI.hiRev.add(temp%10);
			carry = temp/10;
		}
		if(carry == 1) tempHI.hiRev.add(1);
		tempHI.trimZeros();
		return tempHI;
	}
	
	public HugeInteger subtract(HugeInteger h) {
		if(h.isZero()) return this;
		if(this.compareTo(h) == 0) return new HugeInteger("0");
		this.trimZeros();
		h.trimZeros();
		HugeInteger tempHI = new HugeInteger("");
		int carry = 1; // for fixing super carry operation 999 + 1 = 1000
		int temp = 0;
		int tempT = 0;
		int tempH = 0;
		// "subtraction" with super carry in the form of + constant 9
		// super carry accounted for with initial carry of 1
		for(int i=0; i<this.hiRev.size(); i++) {
			tempT = this.hiRev.get(i);
			tempH = (i<h.hiRev.size()) ? h.hiRev.get(i) : 0;
			temp = tempT - tempH + carry + 9;
			if(i == this.hiRev.size()-1) temp -= 10;
			DIGIT_OPERATIONS++;
			tempHI.hiRev.add(temp%10);
			carry = temp/10;
		}
		if(carry == 1) tempHI.hiRev.add(1);
		tempHI.trimZeros();
		return tempHI;		
	}
	
	public HugeInteger multiply(HugeInteger h) {
		if(this.isZero() || h.isZero()) return new HugeInteger("0");
		HugeInteger result = new HugeInteger("0");
		HugeInteger temp = new HugeInteger("0");
		int tempH = 0;
		for(int i=0; i<h.hiRev.size(); i++) {
			tempH = h.hiRev.get(i);
			temp = new HugeInteger("0");
			for(int j=0; j<tempH; j++) {
				temp = temp.add(this);
			}
			for(int k=0; k<i; k++) {
				temp.hiRev.add(0,0);
			}
			result = result.add(temp);
		}
		return result;
	}
	
	public HugeInteger fastMultiply(HugeInteger h) {
		if(this.isZero() || h.isZero()) return new HugeInteger("0");
		
		int sizeT = this.hiRev.size();
		int sizeH = h.hiRev.size();
		
//		// pad the front of the smaller number with logical zeros
//		if(sizeT != sizeH) {
//			if (sizeT > sizeH)
//				for (int i = sizeH - 1; i < sizeT; i++)
//					h.hiRev.add(0);
//			else
//				for (int i = sizeT - 1; i < sizeH; i++)
//					this.hiRev.add(0);
//		}

		int sizeN = sizeT;
		if(sizeN == 1) return this.multiply(h);
		int halfSizeN = (int)Math.floor(sizeT/2);

		
		HugeInteger a = new HugeInteger("");
		HugeInteger b = new HugeInteger("");
		HugeInteger c = new HugeInteger("");
		HugeInteger d = new HugeInteger("");
		
		HugeInteger ac = new HugeInteger("");
		HugeInteger bd  = new HugeInteger("");
		HugeInteger tempab = new HugeInteger("");
		HugeInteger tempcd = new HugeInteger("");
		HugeInteger abcd = new HugeInteger("");
				
//		// split this into 'a' and 'b'
//		b.hiRev = (ArrayList<Integer>) this.hiRev.subList(0, halfSizeN);
//		a.hiRev = (ArrayList<Integer>) this.hiRev.subList(halfSizeN, sizeN);
//		
//		// split h into 'c' and 'd'
//		d.hiRev = (ArrayList<Integer>)h.hiRev.subList(0, halfSizeN);
//		c.hiRev = (ArrayList<Integer>)h.hiRev.subList(halfSizeN, sizeN);
		
		// manually assign digits for 'b' and 'd'
		for(int i=0; i<halfSizeN; i++) {
			b.hiRev.add(this.hiRev.get(i));
			d.hiRev.add(h.hiRev.get(i));
		}
		
		// manually assign digits for 'a' and 'c'
		for(int i=halfSizeN; i<sizeN; i++) {
			a.hiRev.add(this.hiRev.get(i));
			c.hiRev.add(h.hiRev.get(i));
		}
		
		// optimization
		a.trimZeros();
		c.trimZeros();
		
		ac = a.multiply(c);
		bd = b.multiply(d);
		tempab = a.add(b);
		tempcd = c.add(d);
		abcd = tempab.multiply(tempcd);
		
		abcd = abcd.subtract(ac);
		abcd = abcd.subtract(bd);
		
		// three terms are ac, abcd, bd
		for(int i=0; i<2*halfSizeN; i++)
			ac.hiRev.add(0,0);
		
		for(int i=0; i<halfSizeN; i++)
			abcd.hiRev.add(0,0);
		
		System.out.println(ac);
		System.out.println(abcd);
		System.out.println(bd);
	
		return ac.add(abcd.add(bd));
	}
	
	public String toString() {
		String temp = "";
		for(int i=hiRev.size()-1; i>=0; i--)
			temp += (hiRev.get(i)).toString();
		return temp;
	}
	
	public void trimZeros() {
		if(this.hiRev.size() <= 1) return;
		int lastNonZero = -1;
		boolean noZero = true;
		for(int i=0; i<this.hiRev.size(); i++) {
			if(this.hiRev.get(i) == 0) noZero = false;
			if(this.hiRev.get(i) != 0) lastNonZero = i;
		}
		if(lastNonZero == -1 || (lastNonZero == this.hiRev.size()-1) || noZero) return;
		this.hiRev.subList(lastNonZero+1,this.hiRev.size()).clear();
	}
	
	public boolean isZero() {
		if (this.compareTo(new HugeInteger("0")) == 0) return true;
		else return false;
	}
	
}
