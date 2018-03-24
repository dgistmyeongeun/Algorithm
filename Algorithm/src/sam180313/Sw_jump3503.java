package sam180313;

import java.util.Arrays;
import java.util.Scanner;

public class Sw_jump3503 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testcase = sc.nextInt();

		for (int tc = 1; tc <= testcase; tc++) {
			int n = sc.nextInt();
			
			int[] arr = new int[n];
			int[] tarr = new int[n+1];
			sc.nextLine();
			
			String[] temp = sc.nextLine().split(" ");
			for(int i=0; i<n; i++) {
				arr[i] = Integer.parseInt(temp[i]);
			}
			
			Arrays.sort(arr);
			
			int j=0;
			for(int i=0; i<n; i+=2) {
				tarr[j++] = arr[i];
			}
			j=n-1;
			for(int i=1; i<n; i+=2) {
				tarr[j--] = arr[i];
			}
			tarr[n] = tarr[0];
			int max = 0;
			for(int i=0; i<n; i++) {
				int diff = Math.abs(tarr[i]-tarr[i+1]);
				if(max<diff)
					max = diff;
			}
			
			System.out.println("#"+tc+" " +max);
		}
	}
}
