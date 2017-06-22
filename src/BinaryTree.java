import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
		
		Node node = bTree.search(bTree.getRoot(), 40);
		if(node != null)
			System.out.println("data found in search: " + node.mData);
		
		node = bTree.searchItertative(bTree.getRoot(), 160);
		if(node != null)
			System.out.println("data found in search: " + node.mData);
		
		node = bTree.lcaRecursive(bTree.getRoot(), 40, 70);
		if(node != null)
			System.out.println("LCA found: " + node.mData);
		
		node = bTree.lcaIterative(bTree.getRoot(), 40, 70);
		if(node != null)
			System.out.println("LCA found: " + node.mData);
		
		List<Integer> items = new ArrayList<Integer>();
		bTree.nodesSum(bTree.getRoot(), 200, items);
		
		System.out.println("nodesSum: "+ items.toString() );
		
		node = bTree.lca(bTree.getRoot(), 40, 70);
		if(node != null)
			System.out.println("LCA found: " + node.mData);
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
	
	// Search
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
	
	public Node searchItertative(Node node, int data){
		if(node == null)
			return null;
		
		while(node != null){
			if(node.mData == data)
				return node;
			
			if(data < node.mData)
				node = node.mLeft;
			else
				node = node.mRight;
		}
		
		return null;
	}
	
	// ASSUMPTION: both nodes exists in the tree
	// Least common ancestor 
	public Node lcaRecursive(Node root, int first, int second){
		if(root == null)
			return null;
		
		if(root.mData < first && root.mData < second)
			return lcaRecursive(root.mRight, first, second);
		else if (root.mData > first && root.mData > second)
			return lcaRecursive(root.mLeft, first, second);
		
		return root;
	}
	// ASSUMPTION: both nodes exists in the tree
	public Node lcaIterative(Node root, int first, int second){
		if(root == null)
			return null;
		
		Node walker = root;
		while(walker != null){
			if(walker.mData > first && walker.mData > second){
				walker = walker.mLeft;
			} else if(walker.mData < first && walker.mData < second){
				walker = walker.mRight;
			} else 
				return walker;
		}
		
		return null;
	}
	
	//nodesSum of a Tree.
	public void nodesSum(Node node, int sum, List<Integer> items){
		if(node == null || items.size() == 2){
			return;
		}

		Node second = search(mRoot, sum-node.mData);  // make sure to pass mRoot for every search
		if(second != null && node != second){
			System.out.println("node.data: " + node.mData + " - second.data: " + second.mData);
			items.add(node.mData);
			items.add(second.mData);
		} else {
			nodesSum(node.mLeft, sum, items);
			nodesSum(node.mRight, sum, items);
		}
	}
	
	public boolean isSubTree(Node first, Node second){
		if(first == null){
			return false;
		}
		
		if(second == null){
			return true;
		}
		
		if(isEqual(first, second))
			return true;
		
		return isSubTree(first.mLeft, second) || 
			   isSubTree(first.mRight, second);
	}
	
	private boolean isEqual(Node one, Node two){
		if(one == null && two == null){
			return true;
		}
			
		if(one == null || two == null){
			return false;
		}
		
		return one.mData == two.mData &&
			   isEqual(one.mLeft, two.mLeft) && 
			   isEqual(one.mRight,  two.mRight);
	}
	
	// ASSUMPTION: both nodes exists in the tree
	public Node lca(Node node, int one, int two){
		if(node == null)
			return null;
		
		if(node.mData > one && node.mData > two)
			return lca(node.mLeft, one, two);
		else if(node.mData < one && node.mData < two)
			return lca(node.mRight, one, two);
		else 
			return node;
	}
	
//	1. How to uniquely identify the repeating strings from the array.
//	2. How to search in Skiplist
//	2nd round:
//	1. BinarySearch
//	2. How to write Customized Comparator
//	3. Anagrams
//	3rd round:
//	Lunch with tech manager and find maximum sub array
//	4th round:
//	1. Binary Search in sorted matrix
//	2. Different ways of writing Singleton pattern
//	3. Resume go through
//	5th round:
//	1. How HashMap works
//	2. Get sum using minimum combination of numbers provided
}



//     /* This function traverse the skewed binary tree and
//        stores its nodes pointers in vector nodes[] */
//     void storeBSTNodes(Node root, Vector<Node> nodes) 
//     {
//         // Base case
//         if (root == null)
//             return;
 
//         // Store nodes in Inorder (which is sorted
//         // order for BST)
//         storeBSTNodes(root.left, nodes);
//         nodes.add(root);
//         storeBSTNodes(root.right, nodes);
//     }
 
//     /* Recursive function to construct binary tree */
//     Node buildTreeUtil(Vector<Node> nodes, int start,
//             int end) 
//     {
//         // base case
//         if (start > end)
//             return null;
 
//         /* Get the middle element and make it root */
//         int mid = (start + end) / 2;
//         Node node = nodes.get(mid);
 
//         /* Using index in Inorder traversal, construct
//            left and right subtress */
//         node.left = buildTreeUtil(nodes, start, mid - 1);
//         node.right = buildTreeUtil(nodes, mid + 1, end);
 
//         return node;
//     }
 
//     // This functions converts an unbalanced BST to
//     // a balanced BST
//     Node buildTree(Node root) 
//     {
//         // Store nodes of given BST in sorted order
//         Vector<Node> nodes = new Vector<Node>();
//         storeBSTNodes(root, nodes);
 
//         // Constucts BST from nodes[]
//         int n = nodes.size();
//         return buildTreeUtil(nodes, 0, n - 1);
//     }
 
//     /* Function to do preorder traversal of tree */
//     void preOrder(Node node) 
//     {
//         if (node == null)
//             return;
//         System.out.print(node.data + " ");
//         preOrder(node.left);
//         preOrder(node.right);
//     }
 
//     // Driver program to test the above functions
//     public static void main(String[] args) 
//     {
//          /* Constructed skewed binary tree is
//                 10
//                /
//               8
//              /
//             7
//            /
//           6
//          /
//         5   */
//         BinaryTree tree = new BinaryTree();
//         tree.root = new Node(10);
//         tree.root.left = new Node(8);
//         tree.root.left.left = new Node(7);
//         tree.root.left.left.left = new Node(6);
//         tree.root.left.left.left.left = new Node(5);
 
//         tree.root = tree.buildTree(tree.root);
//         System.out.println("Preorder traversal of balanced BST is :");
//         tree.preOrder(tree.root);
//     }
