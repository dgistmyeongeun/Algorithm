package swExpertExam;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS_1953 {

	private static int N, M, R, C, L, time, move;
	private static int arr[][];
	private static boolean visited[][];
	private static int dir[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	private static void bfs(int row, int col) {
		Queue<Point> que = new LinkedList<>();
		Queue<Point> que2 = new LinkedList<>();
		que.add(new Point(row, col));
		visited[row][col] = true;
		int nextRow, nextCol, ndir1, ndir2, qcnt=0;
		move = 1;
		while (L >= time) {
			
			while (!que.isEmpty()) {
				Point now = que.poll();
				int val = arr[now.x][now.y];
				if (val == 1) {
					for (int i = 0; i < 4; i++) {
						nextRow = now.x + dir[i][0];
						nextCol = now.y + dir[i][1];

						if (!range(nextRow, nextCol))
							continue;
						if (moveChk(i + 1, arr[nextRow][nextCol]) && !visited[nextRow][nextCol]) {
							visited[nextRow][nextCol] = true;
							move++;
							que2.add(new Point(nextRow, nextCol));
						}
					}

				} else if (val == 2) {
					nextRow = now.x + dir[1][0];
					nextCol = now.y + dir[1][1];
					if (!range(nextRow, nextCol))
						continue;
					if (moveChk(2, arr[nextRow][nextCol]) && !visited[nextRow][nextCol]) {
						visited[nextRow][nextCol] = true;
						move++;
						que2.add(new Point(nextRow, nextCol));
					}
					nextRow = now.x + dir[3][0];
					nextCol = now.y + dir[3][1];
					if (!range(nextRow, nextCol))
						continue;
					if (moveChk(4, arr[nextRow][nextCol]) && !visited[nextRow][nextCol]) {
						visited[nextRow][nextCol] = true;
						move++;
						que2.add(new Point(nextRow, nextCol));
					}
				} else if (val == 3) {
					nextRow = now.x + dir[0][0];
					nextCol = now.y + dir[0][1];
					if (!range(nextRow, nextCol))
						continue;
					if (moveChk(1, arr[nextRow][nextCol]) && !visited[nextRow][nextCol]) {
						visited[nextRow][nextCol] = true;
						move++;
						que2.add(new Point(nextRow, nextCol));
					}

					nextRow = now.x + dir[2][0];
					nextCol = now.y + dir[2][1];
					if (!range(nextRow, nextCol))
						continue;
					if (moveChk(3, arr[nextRow][nextCol]) && !visited[nextRow][nextCol]) {
						visited[nextRow][nextCol] = true;
						move++;
						que2.add(new Point(nextRow, nextCol));
					}
				} else {
					ndir1 = val % 4;
					ndir2 = (val - 1) % 4;

					nextRow = now.x + dir[ndir1][0];
					nextCol = now.y + dir[ndir1][1];
					if (!range(nextRow, nextCol))
						continue;
					if (moveChk(ndir1+1, arr[nextRow][nextCol]) && !visited[nextRow][nextCol]) {
						visited[nextRow][nextCol] = true;
						move++;
						que2.add(new Point(nextRow, nextCol));
					}

					nextRow = now.x + dir[ndir2][0];
					nextCol = now.y + dir[ndir2][1];
					if (!range(nextRow, nextCol))
						continue;
					if (moveChk(ndir2+1, arr[nextRow][nextCol]) && !visited[nextRow][nextCol]) {
						visited[nextRow][nextCol] = true;
						move++;
						que2.add(new Point(nextRow, nextCol));
					}
				}
			}
			
			time++;
			while(!que2.isEmpty()) {
				que.add(que2.poll());
			}
		}

	}

	private static boolean range(int row, int col) {
		if (row < 0 || row >= N || col < 0 || col >= M)
			return false;
		return true;
	}

	private static boolean moveChk(int dir, int val) {
		switch (dir) {
		case 1:
			if (val == 1 || val == 3 || val == 6 || val == 7)
				return true;
			break;
		case 2:
			if (val == 1 || val == 2 || val == 4 || val == 7)
				return true;
			break;
		case 3:
			if (val == 1 || val == 3 || val == 4 || val == 5)
				return true;
			break;
		case 4:
			if (val == 1 || val == 2 || val == 5 || val == 6)
				return true;
			break;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int TestCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TestCase; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			arr = new int[N][M];
			visited = new boolean[N][M];
			time = 2;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bfs(R, C);
			System.out.println("#" + tc + " " + move);
		}
	}

}
