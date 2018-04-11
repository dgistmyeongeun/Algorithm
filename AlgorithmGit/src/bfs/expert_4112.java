package bfs;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class expert_4112 {
	
	static int A,B, time, max;
	static Point start,end;
	static int[][] map;
	static int[][] dir = {{0,1},{1,1},{1,0},{0,-1},{-1,-1},{-1,0}};
	
	static void bfs() {
		boolean[][] visited = new boolean[max+1][max+1];
		
		Queue<Point> que = new LinkedList<>();
		que.offer(start);
		visited[start.x][start.y] = true;
		
		if(start.x == end.x && start.y==end.y)
			return;
		
		int nextR, nextC;
		
		while(!que.isEmpty()) {
			Point now = que.poll();
			
			for(int d=0; d<6; d++) {
				nextR = now.x+dir[d][0];
				nextC = now.y+dir[d][1];
				
				if(nextR<nextC || nextR<0 || nextC<0 || nextR>max || nextC>max)
					continue;
				
				if(visited[nextR][nextC])
					continue;
				
				visited[nextR][nextC] = true;
				map[nextR][nextC] = map[now.x][now.y]+1;
				que.offer(new Point(nextR,nextC));
				
				if(nextR == end.x && nextC==end.y) {
					que.clear();
					return;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TestCase = sc.nextInt();
		
		for(int tc=1; tc<=TestCase; tc++) {
			A = sc.nextInt();
			B = sc.nextInt();
			start = calc(A);
			end = calc(B);
			
			if(start.x>end.x) {
				max= start.x;
			}else
				max= end.x;
			
			map = new int[max+1][max+1];
			
			bfs();
			
			System.out.println(map[end.x][end.y]);
		}
	}
	
	static Point calc(int v) {
		double val = 1*1-4*1*(-v+1)*2;
		int x1=-1, x2=-1;
		
		if(val>0) {
			 x1 = (int)((-1+Math.sqrt(val))/(2.0*1));
			 x2 = (int)((-1-Math.sqrt(val))/(2.0*1));
		}else if(val==0) {
			 x1 = -1/(2*1);
		}else {
			 x1 = (int)(-val/(2.0*1));
			 x2 = (int)(Math.sqrt(val)/(2.0*1));
		}
		int row, col;
		
		if(x1>0) {
			row = x1;
		}else {
			row = x2;
		}
		
		int nextR = (row+1)*(row+2)/2+1;
		col = (row+1)-(nextR-v);
		
		return new Point(row,col);
	}
}
