package bfs;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Mirosearch_2178 {

	public static int[][] map;
	public static int N, M, time;
	public static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	public static boolean[][] visited;

	public static void bfs() {
		Queue<Point> que = new LinkedList<>();
		Queue<Point> que1 = new LinkedList<>();
		int nextR, nextC;
		que.offer(new Point(1, 1));
		visited[1][1] = true;

		while (true) {
			time++;
			while (!que.isEmpty()) {
				Point now = que.poll();
				if (now.x == N && now.y == M) {
					return;
				}

				for (int i = 0; i < 4; i++) {
					nextR = now.x + dir[i][0];
					nextC = now.y + dir[i][1];

					if (nextR > N || nextC > M|| nextR <= 0 || nextC <= 0)
						continue;

					if (visited[nextR][nextC])
						continue;

					if (map[nextR][nextC] == 1) {
						que1.offer(new Point(nextR, nextC));
						visited[nextR][nextC] = true;
					}
				}
			}
			if (que1.isEmpty())
				return;
			while (!que1.isEmpty())
				que.offer(que1.poll());
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			String[] line = br.readLine().split("");
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(line[j - 1]);
			}
		}
		time = 0;
		bfs();
		System.out.println(time);
	}

}
