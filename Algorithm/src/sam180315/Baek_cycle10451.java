package sam180315;

import java.util.Scanner;

public class Baek_cycle10451 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TestCase = sc.nextInt();
		
		for(int tc=1; tc<=TestCase; tc++) {
			int n = sc.nextInt();
			sc.nextLine();
			int[] arr = new int[n+1];
			boolean check[] = new boolean[n+1];
			int cycle=0;
			
			String line[] = sc.nextLine().split(" ");
			for(int i=1; i<=n; i++)
				arr[i] = Integer.parseInt(line[i-1]);
			
			for(int i=1; i<=n; i++) {
				if(!check[i]) {
					check[i] = true;
					cycle++;
					int val = arr[i];
					while(val!=i) {
						check[val] = true;
						val = arr[val];
					}
				}
			}
			
			System.out.println(cycle);
		}
	}

}
