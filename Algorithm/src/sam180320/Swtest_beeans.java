package sam180320;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swtest_beeans {
	static int N, M, C, ans;
	static int temp, chkX, chkY, firstMax, secondMax;
	static int[][] honey;
	
	
	public static void oper(int x, int y, int sum, int ssum, int ret) {
		if(sum<=C) {
			if(temp<ssum) {
				temp=ssum;
				chkX = x;
				chkY = y-1;
			}
		}
		
		if(ret == 0 || sum>C)
			return;
		
		if(y>=N)
			return;
		
		oper(x, y+1, sum+honey[x][y], ssum+honey[x][y]*honey[x][y], ret-1);
		oper(x, y+1, sum, ssum, ret-1);
	}
	
	public static void run() {
		chkY = 0;
		chkX = 0;
		temp =0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++)
				oper(i, j, 0, 0, M);
		}
		
		int passX = chkX;
		int passY = chkY;
		firstMax = temp;
		temp = 0;
		for(int i=0; i<N; i++) {
			if(i == passX) {
				for(int j=passY+1; j<N; j++)
					oper(i, j, 0,0,M);
			}else {
				for(int j=0; j<N; j++)
					oper(i, j, 0, 0, M);
			}
		}
		secondMax = temp;
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for(int tt=1; tt<=T; tt++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	M = Integer.parseInt(st.nextToken());
        	C = Integer.parseInt(st.nextToken());
        	
        	honey = new int[N][N];
        	for(int i=0; i<N; i++) {
        		st = new StringTokenizer(br.readLine());
        		for(int j=0; j<N; j++)
        			honey[i][j] = Integer.parseInt(st.nextToken());
        
        	}
        	temp=0;
        	firstMax = 0;
        	secondMax = 0;
        	run();
        	System.out.println(String.format("#%d %d", tt, firstMax+secondMax));
        }
    }
}