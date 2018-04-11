package backtracking;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sudoku_2580 {
	static Queue<Point> zero;
	static int[][] arr;
	static int curR, curC;
	
	static boolean fill() {
		if(zeroChk())
			return true;
		
		int r= curR;
		int c = curC;
		for(int i=1; i<=9; i++) {
			if(Check1(r, i) && Check2(c,i)&&Check3(r,c,i)) {
				arr[r][c] = i;
				if(fill()) {
					return true;
				}
				arr[r][c] = 0;
			}
		}
		return false;
	}
	
	static boolean zeroChk() {
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(arr[i][j]==0) {
					curR = i;
					curC = j;
					return false;
				}
			}
		}
		return true;
	}
	static boolean Check1(int r, int n) {
		for (int i = 0; i < 9; i++) 
			if (arr[r][i]==n) 
				return false;
		return true;
	}
	
	static boolean Check2(int c, int n) {
		for (int i = 0; i < 9; i++) 
			if (arr[i][c]==n) 
				return false;
		return true;
	}
		
	static boolean Check3(int r, int c, int n) {
		for (int i = (r / 3)*3; i < (r / 3)*3 + 3; i++) {
			for (int j = (c / 3)*3; j < (c / 3)*3 + 3; j++) {
				if (arr[i][j]==n) 
					return false;
			}
		}
		return true;
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		arr = new int[9][9];
		zero = new LinkedList<>();

		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 0)
					zero.add(new Point(i, j));
			}
		}
		fill();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				System.out.print(arr[i][j] + " ");
			System.out.println();
		}
	}

}
