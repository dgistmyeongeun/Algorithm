package swExpert;

import java.util.Scanner;

public class IntegerSeq3809 {

	private static int[] arr;
	private static int N;
	private static boolean visited[];
	
	private static int find(int pow) {
		
		if(pow==4)
			return -1;
		
		for(int i=0; i+pow<N; i++) {
			String s = "";
			s += arr[i];
			
			for(int j=1; j<=pow; j++) {
				s+=arr[j+i];
			}
			
			visited[Integer.parseInt(s)] = true;
		}
		
		int idx = (int) Math.pow(10, pow+1);
		for(int i=0; i<idx; i++) {
			if(!visited[i])
				return i;
		}
		
		return find(pow+1);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TestCase = sc.nextInt();
		
		for(int tc=1; tc<=TestCase; tc++) {
			N = sc.nextInt();
			arr = new int[N];
			visited = new boolean[1001];
			
			for(int i=0; i<N; i++) {
				arr[i] = sc.nextInt();
			}
			
			int ans = find(0);
			
			System.out.println("#"+tc+" "+ans);
		}
	}

}
