package com.sun.method;

public class TreeNode {
	public float val;
	public int id;
	TreeNode left;
	TreeNode right;
	
	TreeNode(){}
	
	public TreeNode(int id, float similarity){
		this.id=id;
		this.val=similarity;
	}

}
