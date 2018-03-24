package sam180309;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Back_virus2606 {

	static boolean[][] arr;
	static boolean[] check;
	static int comnum;

	public static int bfs(int num) {
		int count = 0;
		Queue<Integer> que = new LinkedList<>();
		que.offer(1);
		check[1] = true;

		while (!que.isEmpty()) {
			int row = que.poll();
			
			for (int i = 1; i <= comnum; i++) {
				if (arr[row][i] && !check[i]) {
					que.offer(i);
					check[i] = true;
					count++;
				}
			}
		}
		
		return count;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		comnum = sc.nextInt();
		arr = new boolean[comnum + 1][comnum + 1];
		check = new boolean[comnum + 1];

		int connect = sc.nextInt();
		for (int i = 1; i <= connect; i++) {
			int row = sc.nextInt();
			int col = sc.nextInt();
			arr[row][col] = true;
			arr[col][row] = true;
		}


		System.out.println(bfs(comnum));
	}

	public static void print() {
		for (int i = 1; i <= comnum; i++) {
			for (int j = 1; j <= comnum; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void print2() {
		for (int j = 1; j <= comnum; j++) {
			System.out.print(check[j] + " ");

			System.out.println();
		}
	}

}
