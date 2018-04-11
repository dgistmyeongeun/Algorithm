package backtracking;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Night2025 {

	private static int N;
	private static boolean[][] visited;
	private static int[][] dir = { { 0, 1 },{ 1, 0 }, { 0, -1 },{ -1, 0 } };
	static List<String> stack;
	static boolean flag = false;

	static void go(int r, int c, int cnt) {

		if (cnt == N * N) {
			if(stack.size()==N*N)
				flag = true;
			return;
		}
		
		for (int i = 0; i < 4; i+=2) {
			int nextR = r + dir[i][0];
			int nextC = c + dir[i][1];

			if (nextR >= N || nextC >= N || nextR < 0 || nextC < 0)
				continue;

			// µÎ¹ø ²©±â1
			int tmpR = nextR + dir[i][0] + dir[(i + 3) % 4][0]; // --|
			int tmpC = nextC + dir[i][1] + dir[(i + 3) % 4][1];

			if (tmpR < N && tmpC < N && tmpR >= 0 && tmpC >= 0) {
				if (!visited[tmpR][tmpC]) {
					visited[tmpR][tmpC] = true;
					stack.add((tmpR + 1) + " " + (tmpC + 1));
					go(tmpR, tmpC, cnt + 1);
					if(flag)
						return;
				}
			}
			// µÎ¹ø ²©±â2
			tmpR = (nextR + dir[i][0]) + dir[(i + 1) % 4][0]; // --|
			tmpC = (nextC + dir[i][1]) + dir[(i + 1) % 4][1];

			if (tmpR < N && tmpC < N && tmpR >= 0 && tmpC >= 0) {
				if (!visited[tmpR][tmpC]) {
					visited[tmpR][tmpC] = true;
					stack.add((tmpR + 1) + " " + (tmpC + 1));
					go(tmpR, tmpC, cnt + 1);
					if(flag)
						return;
				}
			}

			// ÇÑ¹ø ²©±â1
			tmpR = nextR + dir[(i + 3) % 4][0]*2; // --|
			tmpC = nextC + dir[(i + 3) % 4][1]*2;

			if (tmpR < N && tmpC < N && tmpR >= 0 && tmpC >= 0) {
				if (!visited[tmpR][tmpC]) {
					visited[tmpR][tmpC] = true;
					stack.add((tmpR + 1) + " " + (tmpC + 1));
					go(tmpR, tmpC, cnt + 1);
					if(flag)
						return;
				}
			}

			// µÎ¹ø ²©±â2
			tmpR = nextR + dir[(i + 1) % 4][0]*2; // --|
			tmpC = nextC + dir[(i + 1) % 4][1]*2;

			if (tmpR < N && tmpC < N && tmpR >= 0 && tmpC >= 0) {
				if (!visited[tmpR][tmpC]) {
					visited[tmpR][tmpC] = true;
					stack.add((tmpR + 1) + " " + (tmpC + 1));
					go(tmpR, tmpC, cnt + 1);
					if(flag)
						return;
				}
			}
		}
		visited[r][c] = false;
		stack.remove((r+1)+" "+(c+1));
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int R = sc.nextInt();
		int C = sc.nextInt();
		visited = new boolean[N][N];
		visited[R - 1][C - 1] = true;
		stack = new Stack<>();
		stack.add(R + " " + C);

		go(R - 1, C - 1, 1);
		if(flag) {
		for (String i : stack)
			System.out.println(i);
		}else
			System.out.println(-1);
	}

}
