package sam180308;

import java.util.Scanner;

public class Back_Tumproject9466 {

	static int[] arr, check, startarr;
	
	static int dfs(int index, int cnt, int start) {
		System.out.println("Å½»ö"+cnt+"::::"+index);
		if(check[index] != 0) {
			if(start != startarr[index]) {
				System.out.println("LLLLLL"+cnt+"::::"+index+"::::"+startarr[index]);
				return 0;
			}
			System.out.println(cnt+"::::"+index+"::::"+check[index]);
			return cnt-check[index];
		}
		check[index] = cnt;
		startarr[index] = start;
		return dfs(arr[index], cnt+1, start);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		while(tc-- >0) {
			int n = sc.nextInt();
			arr = new int[n+1];
			check = new int[n+1];
			startarr = new int[n+1];
			
			sc.nextLine();
			String temp[] = sc.nextLine().split(" "); 
			
			for(int i=1; i<=n; i++) {
				arr[i]=Integer.parseInt(temp[i-1]);
			}
			
			int count=0;
			
			for(int i=1; i<=n; i++) {
				if(check[i] == 0) {
					count += dfs(i,1,i);
				}
			}
			
			System.out.println(n-count);
			System.out.println();
		}
	}

}
