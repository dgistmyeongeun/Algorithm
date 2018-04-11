package backtracking;

import java.util.Scanner;

public class Goodnum2661 {

	static int N;
	static boolean flag = false;
	
	static void dfs(int idx, String s) {
		if(flag)
			return;
		
		if(idx==N) {
			flag = true;
			System.out.println(s);
		}else {
			for(int i=1; i<=3; i++) {
				if(chk(s+i)) {
					dfs(idx+1, s+i);
				}
			}
		}
	}
	
	static boolean chk(String s) {
		int hf = s.length()/2;
		int start = s.length()-1;
		int end = s.length();
		
		for(int i=1; i<=hf; i++) {
			if(s.substring(start-i, end-i).equals(s.substring(start,end))) {
				return false;
			}
			start--;
		}
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dfs(1, "1");
	}

}
