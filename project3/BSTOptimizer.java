// Steve Comer
// Project 3
// BSTOptimizer
// 13 March 2013

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Collections;

public class BSTOptimizer {
	
	public ArrayList<String> keys;
	public Hashtable<String,Integer> freaks;
	public BinaryTree[][] btmemo;
	public int[][] costs;
	public boolean MEMOIZE;
	public int CALLS;
	
	public BSTOptimizer() {
		keys = new ArrayList<String>();
		freaks = new Hashtable<String,Integer>();
		MEMOIZE = true;
		CALLS = 0;
	}
	
	// called by test file
	// assumes keys created previously
	public BinaryTree optimize() {
		Collections.sort(keys);
		btmemo = new BinaryTree[keys.size()+1][keys.size()+1];
		costs = new int[keys.size()+1][keys.size()+1];
		for(int i=0; i<=keys.size(); i++) {
			for(int j=0; j<=keys.size(); j++) {
				// special case
				if(i == j) {
					btmemo[i][j] = new BinaryTree();
					costs[i][j] = 0; 
				}
				// normal case
				else {
					btmemo[i][j] = null;
					costs[i][j] = -1;
				}
			}
		}
		return optimize(0,keys.size());
	}
	
	// real computation
	// assumes sorted keys
	public BinaryTree optimize(int leftIndex, int rightIndex) {
		
		CALLS++;
		
		// no work required
		if(MEMOIZE && btmemo[leftIndex][rightIndex] != null) return btmemo[leftIndex][rightIndex];
		if(leftIndex == rightIndex) return btmemo[leftIndex][rightIndex];
		
		// local variables
		int bestCost = Integer.MAX_VALUE;
		int tempCost = 0;
		int sumCost = 0;
		int bestRootIndex = -1;
		BinaryTree bestbt = new BinaryTree();
		BinaryTree bt1;
		BinaryTree bt2;
		
		// try all possible BSTs recursively
		for(int i=leftIndex; i<rightIndex; i++) {
			bt1 = optimize(leftIndex,i);
			bt2 = optimize(i+1,rightIndex);
			tempCost = costs[leftIndex][i] + costs[i+1][rightIndex];
			if(tempCost < bestCost) {
				bestbt = new BinaryTree(keys.get(i),bt1,bt2);
				bestCost = tempCost;
				bestRootIndex = i;
			}
		}
		
		// calculate cost
		sumCost = 0;
		sumCost = costs[leftIndex][bestRootIndex] + costs[bestRootIndex+1][rightIndex];
		for(int i=leftIndex; i<rightIndex; i++) {
			sumCost += this.freaks.get(keys.get(i));
		}
		bestbt.cost = sumCost;
		
		// set memos
		costs[leftIndex][rightIndex] = sumCost;
		btmemo[leftIndex][rightIndex] = bestbt;
		
		return bestbt;
	}
	
	// keys stored in ArrayList
	// frequencies stored in hash table
	public void addKey(String k, int f) {
		keys.add(k);
		freaks.put(k,f);
	}

}
