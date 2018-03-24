package sam180320;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swtest_bee2115ans {
	
	private static int N,M,C, temp, max1, max2;
	private static int[][] map;
	private static int curX, curY;
	
	private static void addHoney(int x, int y, int sum, int ssum, int ret) {
		if(sum<=C) {
			if(temp<ssum) {
				temp = ssum;
				curX = x;
				curY = y-1;
			}
		}
		
		if(ret == 0 || sum>C)
			return;
		
		if(y>=N)
			return;
		
		addHoney(x, y+1, sum+map[x][y], ssum+map[x][y]*map[x][y], ret-1);
		addHoney(x, y+1, sum, ssum, ret-1);
	}
	private static void find() {
		curX = 0; 
		curY = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++)
				addHoney(i, j, 0, 0, M);
		}
		
		int firstX = curX;
		int firstY = curY;
		max1 = temp;
		temp = 0;
		
		for(int i=0; i<N; i++) {
			if(i==firstX) {
				for(int j=firstY+1; j<N; j++)
					addHoney(i, j, 0,0,M);
			}else {
				for(int j=0; j<N; j++)
					addHoney(i,j,0,0,M);
			}
		}
		
		max2 = temp;
	}
	
	public static void main(String args[]) throws IOException{
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
			
			temp=0;
			max1=0;
			max2=0;
			
			find();
			System.out.println("#"+tc+" "+(max1+max2));
		}
	}
}
