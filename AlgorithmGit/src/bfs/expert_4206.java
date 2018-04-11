package bfs;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class expert_4206 {

	static int N, M, time;
	static int[][] map;
	static boolean[][] visited;
	static Queue<Point> virus, person;
	static Point myposit;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static boolean flag, flag2;

	public static void bfs() {
		Queue<Point> virus2 = new LinkedList<>();
		int nextR, nextC;
		flag = false;
		flag2 = false;

		while (true) {
			time++;
			three();
			
			if(time>=N*M)
				return;
			if(person.isEmpty()) {
				flag2= true;
				return;
			}
			
			while (!person.isEmpty()) {
				Point now = person.poll();

				for (int i = 0; i < 4; i++) {
					nextR = now.x + dir[i][0];
					nextC = now.y + dir[i][1];

					if (nextR >= N || nextC >= M || nextR < 0 || nextC < 0) {
						flag = true;
						return;
					}

					if (map[nextR][nextC] == 1 || map[nextR][nextC] == 2)
						continue;

					map[nextR][nextC] = 3;
				}
			}

			while (!virus.isEmpty()) {
				Point now = virus.poll();
				visited[now.x][now.y] = true;
				
				for (int i = 0; i < 4; i++) {
					nextR = now.x + dir[i][0];
					nextC = now.y + dir[i][1];
					
					if (nextR >= N || nextC >= M || nextR < 0 || nextC < 0)
						continue;
					
					if(visited[nextR][nextC])
						continue;
					
					if (map[nextR][nextC] == 1)
						continue;

					map[nextR][nextC] = 2;
					virus2.offer(new Point(nextR, nextC));
				}
			}

			/*if (virus2.isEmpty())
				return;*/

			while (!virus2.isEmpty())
				virus.offer(virus2.poll());

		}
	}

	public static void three() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				if (map[i][j] == 3) {
					person.offer(new Point(i, j));
				}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int TestCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TestCase; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited= new boolean[N][M];
			virus = new LinkedList<>();
			person = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 2)
						virus.add(new Point(i, j));
				}
			}

			time = 0;
			bfs();
			if (flag) {
				System.out.println("#"+tc+" "+time);
			} else if(flag2){
				System.out.println("#"+tc+" ZOMBIE");
			}else {
				System.out.println("#"+tc+" CANNOT ESCAPE");
			}
		}
	}

}
