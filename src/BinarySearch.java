
public class BinarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int array[] = {1, 5, 8, 9, 20, 100, 150, 200, 201, 305, 400};
		int found = binarySearch(array, 20);
		System.out.println(found);;

	}

	static int binarySearch(int array[], int number){
		if(array.length == 0)
			return -1;
	
		int leftIndex = 0;
		int rightIndex = array.length - 1;
		
		// <= is important to have, if we only < then we will not be able to find in an array with odd numbers.
		while (leftIndex <= rightIndex){ 
			int midIndex = leftIndex + (rightIndex - leftIndex)/2; // pay attention to it.
			
			if(array[midIndex] == number){
				return number;
			}
			
			if(number < array[midIndex]){
				rightIndex = midIndex - 1; // move right to new mid.
			} else {
				leftIndex = midIndex + 1; // move left to new mid.
			}
				
		}
		
		return -1;
	}
}
