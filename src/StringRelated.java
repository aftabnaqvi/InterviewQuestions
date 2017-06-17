import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class StringRelated {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String data = "Aftab Hassan Naqvi";
		char dataBuffer[] = data.toCharArray();
		System.out.println("isPalindrome: " + isPalindrome(dataBuffer));
		
		System.out.println(dataBuffer);
		reverse(dataBuffer);
		for(int i=0; i<dataBuffer.length; i++){
			System.out.print(dataBuffer[i]);
		}
		System.out.println();
		
		reverseWordsInString(dataBuffer);
		System.out.println(dataBuffer);
		
//		reverseWordsInString(dataBuffer);
//		System.out.println(dataBuffer);
//		
//		reverseWordsInString(dataBuffer);
//		System.out.println(dataBuffer);
		
		System.out.println("isAnagram: " + isAnagram("Aftab", "ttaAf"));
		System.out.println("isAnagram: " + isAnagram("School MASTER", "The ClassROOM"));
		
		String s = "AA  A";
		String m = "AAA";
		System.out.println("isAnagram: " + isAnagram2(s, m));
		System.out.println(s);
		removeDuplicates(data);
	}

	public static boolean isPalindrome(char data[]){
		for(int i=0; i<data.length/2; i++){
			if(data[i] != data[data.length-i-1])
				return false;
		}
		return true;
	}
	
	public static void reverseWordsInString(char data[]){
		int index = 0;
		int start = 0;
		int size = data.length;
		while(index <= size){
			if(data[index==size?index-1:index] == ' ' || index == size){
				reverse(data, start, index-1);
				start = index+1;
			}
			index++;
		}
	}
	
	public static void reverse(char data[], int start, int end){
			for(; start < end; start++, end--){
				char temp = data[start];
				data[start] = data[end];
				data[end] = temp;
			}
	}
	
	public static void reverse(char data[]){
		for(int i=0; i<data.length/2; i++){
			char temp = data[i];
			data[i] = data[data.length-i-1];
			data[data.length-i-1] = temp;
		}
	}
	
	public static void reverse1(char data[]){
		int start = 0;
		int end = data.length-1;
		while(start<end){
			char temp = data[start];
			data[start++] = data[end];
			data[end++] = temp;
		}
	}
	
	/* Function removes duplicate characters from the string
    This function work in-place */
    static void removeDuplicates(String str){
    	str = str.toLowerCase();
        LinkedHashSet<Character> lhs = new LinkedHashSet<>();
        for(int i=0;i<str.length();i++)
            lhs.add(str.charAt(i));
         
        // print string after deleting duplicate elements
        for(Character ch : lhs)
            System.out.print(ch);
    }
    
    // Anagram using hashmap.
    static boolean isAnagram(String data1, String data2){
    	HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    	
    	int size = data1.length();
    	int size2 = data2.length();
    	
    	if(size != size2){
    		return false;
    	}
    	
    	data1 = data1.toLowerCase();
    	data2 = data2.toLowerCase();
    	for(int i=0; i<size; i++){
    		char key = data1.charAt(i);
    		int value = 0;
    		if(map.containsKey(key)){
    			value = map.get(key);
    		} 
    		map.put(key, ++value);
    		
    		value = 0;
    		key = data2.charAt(i);
    		if(map.containsKey(key)){
    			value = map.get(key);
    		} 
    		map.put(key, --value);
    	}
    	    	
    	for(int value : map.values()){
    		if(value != 0)
    			return false;
    	}
    	
    	return true;
    }
    
    
    //Anagram with sort and equal
    public static boolean isAnagram2(String one, String two){
    	//Removing all white spaces from s1 and s2
        one = one.replaceAll("\\s", "");
        two = two.replaceAll("\\s", "");
        
        if(one.length() != two.length()){
        	return false;
        }
        
        char array1[] = one.toLowerCase().toCharArray();
        char array2[] = two.toLowerCase().toCharArray();
        
        Arrays.sort(array1);
        Arrays.sort(array2);
        
        return Arrays.equals(array1, array2);
    }
}
