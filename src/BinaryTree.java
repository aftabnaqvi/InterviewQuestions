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
	public static void main(String[] args) {
		BinaryTree bTree = new BinaryTree(100);
		bTree.insert(50);
		bTree.insert(150);
		bTree.insert(70);
		bTree.insert(160);
		bTree.insert(150);
		bTree.insert(40);
		
		System.out.println("isBST: " + bTree.isBST());
	}

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
	
	boolean isBST(){
		
		return isBST(mRoot);
	}
	
	private boolean isBST(Node node){
		if(node == null)
			return true;
		
		if(node.mLeft != null &&  node.mData > node.mLeft.mData ){
			return true;
		}
		
		if(node.mRight != null &&  node.mData < node.mRight.mData ){
			return true;
		}
		
		if( isBST(node.mLeft) && isBST(node.mRight) ){
			return true;
		}
		
		return false;
	}
}
