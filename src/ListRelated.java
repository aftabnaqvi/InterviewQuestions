import java.util.ArrayList;
import java.util.List;

public class ListRelated {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(5);
		list1.add(15);
		list1.add(25);
		list1.add(35);
		list1.add(45);
		
		
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(5);
		//list2.add(10);
		list2.add(15);
		//list2.add(20);
		list2.add(25);
		
		System.out.println("Res: " + intersection(list1, list2));
		
		int [] a = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println("Maximum contiguous sum is " +
                                       maxSubArraySum(a));
	}

	// intersection of two lists/arrays
	public static List<Integer> intersection(List<Integer> list1, List<Integer> list2){
		if(list1 == null && list2 == null){
			return null;
		}
		List<Integer> res = new ArrayList<Integer>();
		int count1 = list1.size();
		int count2 = list2.size();
		int index1 = 0;
		int index2 = 0;
		while(index1<count1 && index2<count2){
			if(list1.get(index1)<list2.get(index2))
				index1++;
			else if(list2.get(index2)<list1.get(index1))
				index2++;
			else {
				res.add(list1.get(index1));
				index1++; index2++;
			}
		}
		
		return res;
	}
	
	static int maxSubArraySum(int a[])
    {
        int size = a.length;
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;
 
        for (int i = 0; i < size; i++)
        {
            max_ending_here = max_ending_here + a[i];
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return max_so_far;
    }
}
