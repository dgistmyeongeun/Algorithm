package sam180307;

import java.util.Scanner;

public class back_Bakery3109 {

	static int col, row, count=0;
	static String[][] arr;
	static int[] dx = {-1, 0, 1};
	static int[] dy = {1, 1, 1};
	
	public static int dfs(int r, int c) {
		int nowx, nowy;
		arr[r][c] = "x";
		if(c == col-1)
			return 1;
		
		for(int i=0; i<3; i++) {
			nowx = r+dx[i];
			nowy = c+dy[i];
			
			if(idxAble(nowx, nowy))
				if(dfs(nowx, nowy) != 0) {
					return 1;
				}
		}
		return 0;
	}
	
	public static boolean idxAble(int x, int y) {
		if(x>=0 && x<row && y>=0 && y<col && !arr[x][y].equals("x"))
			return true;
		else
			return false;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		row = sc.nextInt();
		col = sc.nextInt();
		arr = new String[row][col];
		
		sc.nextLine();
		for(int i=0; i<row; i++) {
			arr[i] = sc.nextLine().split("");
		}
		
		for(int i=0; i<row; i++) {
			count += dfs(i,0);
		}
		
		System.out.println(count);
	}
}

class XY{
	int x;
	int y;
	public XY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}
