package sam180309;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Back_bridge2146 {

	static int[][] arr;
	static int[][] check;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int n, count = 0, min=Integer.MAX_VALUE;

	static Queue<Info> que = new LinkedList<>();

	public static void find() {
		while(!que.isEmpty()) {
			Info current = que.poll();
			
			for(int i=0; i<4; i++) {
				int nextrow = current.x + dx[i];
				int nextcol = current.y + dy[i];
				
				if(range(nextrow, nextcol)) {
					if(arr[nextrow][nextcol] == 0) {
						que.offer(new Info(nextrow, nextcol, current.res+1));
						arr[nextrow][nextcol] = arr[current.x][current.y];
						check[nextrow][nextcol] = current.res+1;
						
					}else if(arr[nextrow][nextcol] != arr[current.x][current.y]) {
						int div = check[nextrow][nextcol]+ check[current.x][current.y];
						min = min>div?div:min;
					}
				}
			}
		}
	}
	
	public static void bfs(int row, int col, int group) {
		arr[row][col] = group;

		for (int i = 0; i < 4; i++) {
			int nowrow = row+dx[i];
			int nowcol = col+dy[i];
			if(range(nowrow, nowcol) && arr[nowrow][nowcol]==1) {
				arr[nowrow][nowcol] = group;
				bfs(nowrow, nowcol, group);
			}
		}
	}

	public static boolean range(int row, int col) {
		if (row >= 0 && row < n && col >= 0 && col < n)
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n][n];
		check = new int[n][n];
		
		sc.nextLine();
		for (int i = 0; i < n; i++) {
			String temp[] = sc.nextLine().split(" ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(temp[j]);
				if (arr[i][j] == 1)
					que.offer(new Info(i, j, 0));
			}
		}
		
	/*	print();
		System.out.println();*/
		
		int group = 2;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (arr[i][j] == 1) {
					bfs(i, j, group);
					group++;
				}
		//print();
		
		find();
		System.out.println(min);
	}
	public static void print() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++)
				System.out.print(arr[i][j]+" ");
			System.out.println();
		}
	}

}

class Info {
	int x;
	int y;
	int res;

	public Info(int x, int y, int res) {
		this.x = x;
		this.y = y;
		this.res = res;
	}

}
