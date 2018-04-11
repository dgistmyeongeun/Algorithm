package backtracking;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bishop {

	static int[][] map;
	static int[][] dir = {{1,1},{1,-1},{-1,-1},{-1,1}};
	static int nextR, nextC, N, max;
	
	static void back(int cnt) {
		if(!one()) {
			max = Math.max(max, cnt);
			return;
		}
		
		int r = nextR;
		int c = nextC;
		map[r][c] = 2;
		int tmpR, tmpC;
		Queue<Point> list = new LinkedList<>();
		
		for(int i=0; i<4; i++) {
			tmpR = r+dir[i][0];
			tmpC = c+dir[i][1];
			while(chk(tmpR,tmpC)) {
				list.add(new Point(tmpR, tmpC));
				if(map[tmpR][tmpC]==1)
					map[tmpR][tmpC] = 0;
				tmpR +=dir[i][0];
				tmpC += dir[i][1];
			}
		}
		back(cnt+1);
		map[r][c] = 1;
		while(!list.isEmpty()) {
			Point p = list.poll();
			map[p.x][p.y] = 1;
		}
		//back(cnt);
	}
	
	static boolean chk(int r, int c) {
		if(r>=N || c>=N || r<0 || c<0)
			return false;
		return true;
	}
	
	static boolean one() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1) {
					nextR = i;
					nextC = j;
					return true;
				}
			}
		}
		return false;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map =new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		max = Integer.MIN_VALUE;
		back(0);
		int cnt = 0;
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				if(map[i][j] == 2)
					cnt++;
		
		System.out.println(max);
	}

}
