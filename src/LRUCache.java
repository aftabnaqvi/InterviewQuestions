import java.util.HashMap;

public class LRUCache {
	private class Node{
		int mData;
		Node mPrev;
		Node mNext;
		
		Node(int data){
			mData = data;
			mPrev= null;
			mNext = null;
		}
	}
	
	public static void main(String[] args) {
		LRUCache cache = new LRUCache(5);
		cache.set(10);
		cache.set(20);
		cache.set(30);
		cache.set(40);
		cache.set(50);
		
		
		cache.display();
		
		cache.get(10);
		cache.display();
		
		cache.set(20);
		cache.display();
		
		cache.set(20);
		cache.display();
	}
	

	// members
	private int mCapacity;
	private Node mHead;
	private Node mTail;
	private HashMap<Integer, Node> mMap;
	
	LRUCache(int capacity){
		mCapacity = capacity;
		mMap = new HashMap<>();
	}
	
	// helper method of LRU
	private void add(Node node){
		node.mNext = null;
		node.mPrev = null;
		
		if(mHead == null){
			mHead = node;
			mTail = node;
			
			return;
		}
		node.mNext = mHead;
		mHead.mPrev = node;
		mHead = node;
	}
	
	private void remove(Node node){
		if(mHead == null || node == null)
			return;
		
		//if we have only one node.
		if(mHead == mTail && mHead == node){
			mHead = null;
			mTail = null;
			return;
		}
			
		//remove from head
		if(mHead == node){
			mHead.mNext.mPrev = null;
			mHead = mHead.mNext;
			return;
		}
		
		// remove from last
		if(mTail == node){
			mTail = mTail.mPrev;
			mTail.mNext = null;
			return;
		}
		
		// remove in the middle
		node.mPrev.mNext = node.mNext;
		node.mNext.mPrev = node.mPrev;
	}
	
	private void moveToFirst(Node node){
		remove(node);
		add(node);
	}

	private void removeFromLast(){
		remove(mTail);
	}
	
	//LRU operations
	public int get(int data){
		// search in hashmap
		if(mMap.containsKey(data)){
			//if exists then remove it from that position and move it to head.
			Node node = mMap.get(data);
			moveToFirst(node);
			return node.mData;
		} 
		
		return -1;
	}
	
	public void set(int data){
		if(mMap.containsKey(data)){
			//if exists then remove it from that position and move it to head.
			Node node = mMap.get(data);
			moveToFirst(node);
			return;
		} 
		
		if(mMap.size() == mCapacity){
			int key = mTail.mData;
			removeFromLast();
			mMap.remove(key);
		}
		Node node = new Node(data);
		add(node);
		mMap.put(data, node);
	}
	
	public void display(){
		Node walker = mHead;
		while(walker != null){
			System.out.print(walker.mData+" ");
			walker = walker.mNext;
		}
		
		System.out.println();
	}
}

