// Steve Comer
// Project 3
// BinaryTree
// 13 March 2013

public class BinaryTree {
	
	public Node root;
	public int cost;
	
	// default
	public BinaryTree() {
		root = null;
	}
	
	// singleton tree
	public BinaryTree(String r) {
		root = new Node(r,null,null);
	}
	
	// tree with node children
	public BinaryTree(String r, Node L, Node R) {
		root = new Node(r,L,R);
	}
	
	// tree with tree children
	public BinaryTree(String r, BinaryTree L, BinaryTree R) {
		root = new Node(r,L.root,R.root);
	}
	
	public String toString() {
		if(root == null) return "nonexistent tree";
		return printNode(root);
	}
	
	public String printNode(Node n) {
		if(n == null) return "-";
		return n.key + "  [ " + printNode(n.left) + " " + printNode(n.right) + " ]";
	}

}
