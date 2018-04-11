package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Flower_14620 {

	static int N, minval;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static boolean[][] visited;
	static int[][] map;

	public static void back(int cnt, int price) {
		int cr, cc, p = 0;
		boolean flag = false;
		if (cnt== 3) {
			minval = Math.min(minval, price);
			return;
		}

		for (int i = 2; i < N; i++) {
			for (int j = 2; j < N; j++) {
				for (int d = 0; d < 4; d++) {
					cr = i + dir[d][0];
					cc = j + dir[d][1];
					if (visited[cr][cc]) {
						flag = true;
						break;
					}
				}
				if (flag) {
					flag=false;
					continue;
				}
				else {
					visited[i][j] = true;
					p = map[i][j];
					for (int d = 0; d < 4; d++) {
						cr = i + dir[d][0];
						cc = j + dir[d][1];
						p += map[cr][cc];
						visited[cr][cc] = true;
					}
					back(cnt + 1, price + p);
				}
				p=map[i][j];
				visited[i][j]= false;
				for (int d = 0; d < 4; d++) {
					cr = i + dir[d][0];
					cc = j + dir[d][1];
					p+=map[cr][cc];
					visited[cr][cc] = false;
				}
			}
		}
		


	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		minval = Integer.MAX_VALUE;
		back(0, 0);
		System.out.println(minval);

	}

}
