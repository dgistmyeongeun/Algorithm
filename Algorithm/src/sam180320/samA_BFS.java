package sam180320;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class samA_BFS {

	static int N;
	static int map[][];
	static int Success[] = { 3, 4, 1, 2 };
	static int dir[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static boolean visited[][];

	public static void main(String... msg) {
		Scanner sc = new Scanner(System.in);
		int TestCase = sc.nextInt();

		for (int T = 1; T <= TestCase; T++) {
			N = sc.nextInt();
			map = new int[N][N];
			visited = new boolean[N][N];
			Point start = new Point(sc.nextInt() - 1, sc.nextInt() - 1);
			Point end = new Point(sc.nextInt() - 1, sc.nextInt() - 1);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			System.out.println("#" + T + " " + BFS(start, end));
		}
	}

	public static int BFS(Point start,Point end) {

		int time = 0;
		Queue<Point> q = new LinkedList<Point>();
		q.add(start);
		while(!q.isEmpty()) {
			int size = q.size();

			for(int i = 0; i < size; i++) {
				Point cur = q.poll();
			
				if(cur.y == end.y && cur.x == end.x)
					return time;

				for(int d = 0 ; d < 4; d++) {
					int ny = cur.y + dir[d][0];
					int nx = cur.x + dir[d][1];

					if( ny < 0 || nx < 0 || ny >= N || nx >= N)
						continue;

					if(visited[ny][nx])
						continue;

					if(map[cur.y][cur.x]!=d+1)
						continue;

					if(map[ny][nx] != Success[d])
						continue;

					visited[ny][nx] = true;
					q.add(new Point(nx,ny));
				}
			}
			time++;
			UpdateMap();
		}
		return -1;
	}

	public static void UpdateMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] += 1;
				if (map[i][j] > 4)
					map[i][j] = 1;
			}
		}
	}

}