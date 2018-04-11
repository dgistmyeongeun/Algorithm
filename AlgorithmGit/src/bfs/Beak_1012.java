package bfs;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Beak_1012 {

	static int M,N,K,cnt;
	static LinkedList<Point> cabbage;
	static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
	static boolean[][] visited;
	static int[][] map;
	
	private static void bfs() {
		Queue<Point> que = new LinkedList<>();
		int nextR, nextC;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==1) {
					cnt++;
					que.offer(new Point(i,j));
					
					while(!que.isEmpty()) {
						Point now = que.poll();
						
						for(int d=0; d<4; d++) {
							nextR = now.x+dir[d][0];
							nextC = now.y+dir[d][1];
							
							if(nextR>=N || nextC>=M || nextC<0 || nextR<0)
								continue;
							
							if(visited[nextR][nextC])
								continue;
							
							if(map[nextR][nextC]==1) {
								map[nextR][nextC] = 0;
								que.add(new Point(nextR, nextC));
							}
						}
					}
				}
			}
		}
		
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int TestCase = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TestCase; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			int row, col;
			visited = new boolean[N][M];
			map = new int[N][M];
			cabbage = new LinkedList<>();
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				col = Integer.parseInt(st.nextToken());
				row = Integer.parseInt(st.nextToken());
				map[row][col] = 1;
			}
			cnt=0;
			bfs();
			System.out.println(cnt);
		}
	}

}
