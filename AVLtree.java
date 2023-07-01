package project;

public class AVLtree {
	Node root;
	class Node{
		int voterID;
		int aadharNo;
		int mobNo;
		boolean voted;
		int height;
		Node right;
		Node left;
		Node(int val,int aadharNo,int mobNo){
			this.voterID=val;
			this.aadharNo=aadharNo;
			this.mobNo=mobNo;
			this.right=null;
			this.left=null;
			voted=false;
			height=0;
		}
	}
	void insert(int val,int aadharNo,int mobNo){
		root = insert1(root,val,aadharNo,mobNo);
	}
	private static int max(int a,int b) {
		return (a>b)?a:b;
	}
	Node insert1(Node root,int val,int aadharNo,int mobNo) {
		if(root==null) return new Node(val,aadharNo,mobNo);
		if(root.voterID<val) root.right=insert1(root.right,val,aadharNo,mobNo);
		else if(root.voterID>val)
			root.left=insert1(root.left,val,aadharNo,mobNo);
		else 
			return root;
		root.height = 1+max(getHeight(root.left),getHeight(root.right));
		
		int balFactor=getHeight(root.left)-getHeight(root.right);
		if(val==40) {
			System.out.println("The height of "+root.voterID+" is "+root.height);
		}
		
			if(balFactor>1 && val<root.left.voterID) {
				return RR(root);
			}
			if(balFactor>1 && val>root.left.voterID) {
				root.left=LR(root.left);
				return RR(root);
			}
			if(balFactor<-1 && val<root.right.voterID) {
				root.right=RR(root.right);
				return LR(root);
			}
			if(balFactor<-1 && val>root.right.voterID) {
				return LR(root);
			}
		
		return root;
	}
	private Node RR(Node x) {
		Node y=x.left;
		Node t1=y.right;
		y.right=x;
		x.left=t1;
		
		x.height=max(getHeight(x.left),getHeight(x.right))+1;
		y.height=max(getHeight(y.left),getHeight(y.right))+1;
		return y;
	}
	private Node LR(Node x) {
		Node y=x.right;
		Node t1=y.left;
		y.left=x;
		x.right=t1;
		
		x.height=max(getHeight(x.left),getHeight(x.right))+1;
		y.height=max(getHeight(y.left),getHeight(y.right))+1;
		
		return y;
	}
	void inOrder() {
		inOrder1(root);
	}
	private void inOrder1(Node root) {
		
		if(root!=null) {
		inOrder1(root.left);
		System.out.println("	Voter ID "+root.voterID+" ");
		System.out.println("	Aadhar Number "+root.aadharNo);
		System.out.println("	Mobile Number "+root.mobNo+"\n\n\t");
		inOrder1(root.right);
		}
	}
	Node searchVoter(int voterID) {
		return search(voterID,root);
	}
	private Node search(int voterID, Node root) {
		if(root==null || root.voterID==voterID) 
			return root;
		if(voterID<root.voterID)
			return search(voterID,root.left);
		else
			return search(voterID,root.right);
	}
	private static int getHeight(Node root) {
		if(root==null)
			return -1;
		else
		return root.height;
	}
	
}
