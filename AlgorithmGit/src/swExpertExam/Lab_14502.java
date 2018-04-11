package swExpertExam;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Lab_14502 {

	private static int[][] arr, tmp;
	private static int N, M, max;
	private static int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	private static List<Point> two;

	private static int[][] bfs(int[] idx, int[][] map) {
		int nextrow, nextcol;
		boolean visited[][] = new boolean[N][M];
		int w, wrow, wcol, cnt=0;
		int map2[][] = new int[N][M];
		Queue<Point> que = new LinkedList<>();
		for(int i=0; i<two.size(); i++) {
			que.offer(two.get(i));
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++)
				map2[i][j] = map[i][j];
		}
		
		for(int i=0; i<3; i++) {
			w = idx[i];
				wrow = w/M;
				wcol = w%M;
			
			if(map2[wrow][wcol]==0) {
				map2[wrow][wcol] =  1;
				cnt++;
			}
		}
		if(cnt!=3)
			return null;
		
		while (!que.isEmpty()) {
			Point pos = que.poll();

			for (int i = 0; i < 4; i++) {
				nextrow = pos.x + dir[i][0];
				nextcol = pos.y + dir[i][1];

				if (nextrow >= N || nextcol >= M || nextrow < 0 || nextcol < 0)
					continue;

				if (map2[nextrow][nextcol] == 0 && !visited[nextrow][nextcol]) {
					map2[nextrow][nextcol] = 2;
					visited[nextrow][nextcol] = true;
					que.add(new Point(nextrow, nextcol));
				}
			}
		}
		return map2;
	}

	private static int zeroarea(int[][] map) {
		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				if (map[i][j] == 0)
					ans++;
		}

		return ans;
	}
	
	
	public static void combination(int[] arr, int index, int n, int r, int target) {
		if (r == 0) {
			int[][] subarr = bfs(arr, tmp);
			if(subarr!=null) {
				int sizetmp = zeroarea(subarr);
				if(sizetmp>max) {
					max=sizetmp;
				
					
				}
			}
		}
		else if (target == n) 
			return; 
		else { 
			arr[index] = target; 
			combination(arr, index + 1, n, r - 1, target + 1); 
			combination(arr, index, n, r, target + 1); 
			} 
		}

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		two = new LinkedList<>();

		arr = new int[N][M];
		tmp = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				tmp[i][j] = arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2)
					two.add(new Point(i, j));
			}
		}
		boolean visited[][] = new boolean[N][M];
		
		max = 0;
		int tot = N*M;
		int[] c = new int[3];
		combination(c, 0, tot, 3, 0);
		System.out.println(max);
	}

}
