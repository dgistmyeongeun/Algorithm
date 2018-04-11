package backtracking;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class WordMath_1339 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		ArrayList<Integer> list = new ArrayList<>();
		int[] arr = new int['Z'-'A'+1];
		
		for (int i = 0; i < N; i++) {
			String s = sc.next();
			for (int j = 0; j < s.length(); j++)
				arr[s.charAt(j)-'A'] += Math.pow(10, s.length()-j-1);
		}
		
		Arrays.sort(arr);
		int num=9;
		int tot = 0;
		for(int i=arr.length-1; i>=0; i--) {
			if(arr[i]==0)
				break;
			tot += arr[i]*num--;
		}
		System.out.println(tot);
	}

}
