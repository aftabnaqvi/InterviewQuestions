
public class StringRelated {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String data = "Aftab Hassan Naqvi";
		char dataBuffer[] = data.toCharArray();
		System.out.println("isPalindrome: " + isPalindrome(dataBuffer));
		reverse(dataBuffer);
		for(int i=0; i<dataBuffer.length; i++){
			System.out.print(dataBuffer[i]);
		}
		System.out.println();
		
		reverseWordsInString(dataBuffer);
		System.out.println(dataBuffer);
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
}
