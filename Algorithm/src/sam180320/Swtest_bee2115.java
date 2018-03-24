package sam180320;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Swtest_bee2115 {
	
	private static int N,M,C;
	private static int[][] map;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int TestCase = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TestCase; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			int temp1=0;
			int temp2=0;
			int max = Integer.MIN_VALUE;
			int max2 = Integer.MIN_VALUE;
			PriorityQueue<Integer> pque = new PriorityQueue<>();
			Point maxpos= null;
			Point pos = null;
			boolean visited[][] = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int cmax =0;
					cmax = 0;
					temp1 = 0;
					pos = new Point(i, j);
					for(int m=j; m<j+M && m<N; m++) {
						cmax += map[i][m];
						pque.offer(map[i][m]);
						while(cmax>C) {
							int del = pque.poll();
							cmax -= del;
							temp1 -= Math.pow(del, 2);
						}
						temp1 += Math.pow(map[i][m],2);
					}
					pque.clear();
					if(max<=temp1) {
						maxpos = pos;
						max = temp1;
					}
					
				}
			}
			
			for(int i=0; i<M; i++) {
				if(maxpos.y+i>=N)
					break;
				visited[maxpos.x][maxpos.y+i] = true;
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					
					if(visited[i][j])
						continue;
					
					int cmax =0;
					cmax = 0;
					temp1 = 0;
					pos = new Point(i, j);
					for(int m=j; m<j+M && m<N; m++) {
						cmax += map[i][m];
						pque.offer(map[i][m]);
						visited[i][m] = true;
						while(cmax>C) {
							int del = pque.poll();
							cmax -= del;
							temp1 -= Math.pow(del, 2);
						}
						temp1 += Math.pow(map[i][m],2);
					}
					pque.clear();
					if(max2<=temp1) {
						max2 = temp1;
					}
				}
				
			}
			
			System.out.println(max+max2);
		}
	}
}
