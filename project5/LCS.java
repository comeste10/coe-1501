// Steve Comer
// Project 5
// LCS
// 15 April 2013

public class LCS {
	
	public static String s1;
	public static String s2;
	public static int[][] memo;
	
	public static String findLCS(String x, String y) {
		
		s1 = x;
		s2 = y;
		memo = new int[s1.length()][s2.length()];
		for(int i=0; i<s1.length(); i++) {
			for(int j=0; j<s2.length(); j++) {
				memo[i][j] = -2;
			}
		}
		
		fillMemo(s1.length()-1,s2.length()-1);
		
		return buildLCS(s1.length()-1,s2.length()-1);
		
	}
	
	private static int fillMemo(int x, int y) {
				
		if(x < 0 || y < 0) return 0;
		
		if(memo[x][y] != -2) return memo[x][y];
		
		if(s1.charAt(x) == s2.charAt(y)) {
			memo[x][y] = fillMemo(x-1,y-1) + 1;
			return memo[x][y];
		}
		
		else {
			int temp1 = fillMemo(x-1,y);
			int temp2 = fillMemo(x,y-1);
			if(x > 0) memo[x-1][y] = temp1;
			if(y > 0) memo[x][y-1] = temp2;
			if(temp1 > temp2) return temp1;
			else 			  return temp2;
		}
		
	}
	
	private static String buildLCS(int x, int y) {
		
		if(x < 0 || y < 0) 					return "";
		
		if(s1.charAt(x) == s2.charAt(y)) 	return buildLCS(x-1,y-1) + s1.charAt(x);
		
		else {
			if(x == 0)						return buildLCS(x,y-1);
			if(y == 0)						return buildLCS(x-1,y);
			if(memo[x][y-1] > memo[x-1][y]) return buildLCS(x,y-1);
			else 							return buildLCS(x-1,y);
		}
		
	}

}
