import java.util.LinkedList;
import java.util.Queue;

class Node{
	int mData;
	Node mLeft;
	Node mRight;
	
	Node(int data){
		mData = data;
		mLeft = mRight = null;
	}
}

public class BinaryTree {
	//Driver Program
	public static void main(String[] args) {
		BinaryTree bTree = new BinaryTree(100);
		bTree.insert(50);
		bTree.insert(150);
		bTree.insert(70);
		bTree.insert(160);
		bTree.insert(155);
		bTree.insert(40);
		bTree.insert(200);
		bTree.insert(300);
		
		System.out.println("isBST: " + bTree.isBST());
		System.out.println("findMin: " + bTree.findMin());
		System.out.println("findMinR: " + bTree.findMinR());
		System.out.println("findMinRec: " + bTree.findMinRec(bTree.getRoot()).mData);
		
		System.out.println("findMax: " + bTree.findMax());
		System.out.println("findMaxR: " + bTree.findMaxR());
		
		System.out.println("findHeight: " + bTree.findHeight(bTree.getRoot()));
		System.out.println("findDepth: " + bTree.findDepth(bTree.getRoot()));
		
		bTree.display(bTree.getRoot());
		System.out.println();
		
		System.out.print("LevelOrder: ");
		bTree.displayLevelOrder();
		
		bTree.mirror(bTree.getRoot());
		bTree.display(bTree.getRoot());
		System.out.println();
		System.out.println("isBST: " + bTree.isBST());
		
		bTree.mirror(bTree.getRoot());
		bTree.display(bTree.getRoot());
		System.out.println();
		System.out.println("isBST: " + bTree.isBST());
		
		bTree.displayLevelOrder();
		
		Node node = bTree.search(bTree.getRoot(), 300);
		if(node != null)
			System.out.println("data found in search: " + node.mData);
	}

	public Node getRoot(){
		return mRoot;
	}
	public void display(Node node){
		if(node == null){
			return;
		}
		
		System.out.print(node.mData + " ");
		display(node.mLeft);
		display(node.mRight);
	}
	
	// Data members
	private Node mRoot = null;
	
	BinaryTree(int data){
		mRoot = new Node(data);
	}
	
	void insert(int data){
		insert(mRoot, data);
	}
	
	private void insert(Node node, int data){
		if(data < node.mData){
			if(node.mLeft == null){
				node.mLeft = new Node(data);
			} else {
				insert(node.mLeft, data);
			}
		} else if(data > node.mData){
			if(node.mRight == null){
				node.mRight = new Node(data);
			} else {
				insert(node.mRight, data);
			}
		}
	}
	
	// Method to check if tree is BST
	boolean isBST(){
		return isBST(mRoot);
	}
	
	private boolean isBST(Node node){
		if(node == null)
			return true;
		
		if(node.mLeft != null &&  node.mData < node.mLeft.mData ){
			return false;
		}
		
		if(node.mRight != null &&  node.mData > node.mRight.mData ){
			return false;
		}
		
		if( !isBST(node.mLeft) && !isBST(node.mRight) ){
			return false;
		}
		
		return true;
	}

	// find minimum value  within a tree.
	public int findMin(){
		Node node = mRoot;
		if(node == null){
			System.out.println("Error: Tree is empty");
			return -1;
		}
		
		while(node.mLeft != null){
			node = node.mLeft;
		}
		
		return node.mData;
	}
	
	public int findMinR(){
		if(mRoot == null){
			System.out.println("Error: Tree is empty");
			return -1;
		}
		
		return findMinR(mRoot);
	}
	
	public int findMinR(Node node){
		if(node.mLeft == null){
			return node.mData;
		}
		
		while(node.mLeft != null)
			return findMinR(node.mLeft);
		
		return -1;
	}
	
	public Node findMinRec(Node node){
		if(node.mLeft == null){
			return node;
		}
		
		while(node.mLeft != null)
			return findMinRec(node.mLeft);
		
		return null;
	}
	
	// find maximum value  within a tree.
	public int findMax(){
		Node node = mRoot;
		if(node == null){
			System.out.println("Error: Tree is empty");
			return -1;
		}
		
		while(node.mRight != null){
			node = node.mRight;
		}
		
		return node.mData;
	}
	
	public int findMaxR(){
		if(mRoot == null){
			System.out.println("Error: Tree is empty");
			return -1;
		}
		
		return findMaxR(mRoot);
	}
	
//	public int findMaxR(Node node){
//		if(node.mRight == null){
//			return node.mData;
//		}
//		
//		return findMaxR(node.mRight);
//	}
	
	public int findMaxR(Node node){
		if(node == null)
			return -1;
		else if (node.mRight == null)
			return node.mData;
		
		return findMaxR(node.mRight);
	}
	
	
	// find depth and height of the tree
	public int findHeight(Node node){
		if(node == null)
			return -1;
		
		int leftHeight = findHeight(node.mLeft);
		int rightHeight = findHeight(node.mRight);
		
		
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	public int findDepth(Node node){
		if(node == null)
			return -1;
		
		return Math.max(findDepth(node.mLeft), findDepth(node.mRight)) + 1;
	}
	
	//Mirror the tree nodes
	public void mirror(Node node){
		if(node == null){
			return;
		}
		
		mirror(node.mLeft);
		mirror(node.mRight);
		swapChildNodes(node);
	}
	
	private void swapChildNodes(Node parent){
		if(parent == null){
			System.out.println("Error: Node is null. can't swap childs");
			return;
		}
		
		Node temp = parent.mLeft;
		parent.mLeft = parent.mRight;
		parent.mRight = temp;
	}
	
	//print nodes in level order
	public void displayLevelOrder(){
		if(mRoot == null){
			System.out.println("Error: Tree is empty.");
		}
		
		Queue<Node> queue = new LinkedList<Node>();
		
		queue.add(mRoot);
		while(!queue.isEmpty()){
			Node node = queue.peek();
			System.out.print(node.mData + " ");
			queue.remove();
			
			if(node.mLeft != null)
				queue.add(node.mLeft);
			
			if(node.mRight != null)
				queue.add(node.mRight);
		}
		
		System.out.println();
	}
	
	public Node search(Node node, int data){
		if(node == null)
			return null;
		
		if(node.mData == data)
			return node;
		
		if(data < node.mData)
			return search(node.mLeft, data);
		else
			return search(node.mRight, data);
	}
	
}
