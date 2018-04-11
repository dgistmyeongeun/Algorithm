package baekPreTest;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Zzero {

	private static char map[][];
	private static boolean visited[][];
	private static Point red, blue;
	private static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
	private static int time,N,M;
	
	private static void move(int r, int c, int br, int bc, int cnt) {
		if(cnt>10) {
			time = -1;
			return;
		}
		
		for(int i=0; i<4; i++) {
			int nextR = r+dir[i][0];
			int nextC = c+dir[i][1];
			int bnextR = br+dir[i][0];
			int bnextC = bc+dir[i][1];
					
			if(nextR<0 || nextC<0 || nextR>=N || nextC>=M || bnextR<0 || bnextC<0 || bnextR>=N || bnextC>=M) 
				continue;
			
			if(map[nextR][nextC] == 'O' && map[bnextR][bnextC] != 'O') {
				time = cnt>time?time:cnt;
				return;
			}
			printArr();
			if(map[nextR][nextC]=='B') {
				if(map[bnextR][bnextC]=='#') {
					bnextR =br;
					bnextC = bc;
				}
				map[r][c] = '&';
				map[nextR][nextC] = '&';
				map[br][bc] = '.';
				boolean flag = false;
				while(true) {
					bnextR += dir[i][0];
					bnextC += dir[i][1];
					
					if(bnextR<0 || bnextC<0 || bnextR>=N || bnextC>=M) 
						break;
					
					if(map[bnextR][bnextC] == 'O') {
						time = -1;
						return;
					}
					if(map[bnextR][bnextC]=='R')
						break;
					
					if(map[bnextR][bnextC]=='#' )
						break;
				}
				bnextR-=dir[i][0];
				bnextC -=dir[i][1];
				map[bnextR][bnextC] = 'B';
				while(true) {
					nextR +=dir[i][0];
					nextC += dir[i][1];
					
					if(nextR<0 || nextC<0 || nextR>=N || nextC>=M) 
						break;
					
					if(map[nextR][nextC] == 'O') {
						time = cnt>time?time:cnt;
						flag =true;
						break;
					}
					if(map[nextR][nextC]=='B')
						break;
					
					if(map[nextR][nextC]!='.')
						break;
					map[nextR][nextC] = '&';
				}
				if(!flag) {
					nextR-=dir[i][0];
					nextC -=dir[i][1];
					map[nextR][nextC] = 'R';
				}
			}
			
			if(map[nextR][nextC]=='.') {
				if(map[bnextR][bnextC]=='#') {
					bnextR =br;
					bnextC = bc;
				}
				map[r][c] = '&';
				map[nextR][nextC] = '&';
				map[br][bc] = '.';
				boolean flag = false;
				while(true) {
					nextR +=dir[i][0];
					nextC += dir[i][1];
					
					if(nextR<0 || nextC<0 || nextR>=N || nextC>=M) 
						break;
					
					if(map[nextR][nextC] == 'O') {
						time = cnt>time?time:cnt;
						flag =true;
						break;
					}
					if(map[nextR][nextC]!='.')
						break;
					map[nextR][nextC] = '&';
				}
				if(!flag) {
					nextR-=dir[i][0];
					nextC -=dir[i][1];
					map[nextR][nextC] = 'R';
				}
				while(true) {
					bnextR += dir[i][0];
					bnextC += dir[i][1];
					
					if(bnextR<0 || bnextC<0 || bnextR>=N || bnextC>=M) 
						break;
					
					if(map[bnextR][bnextC] == 'O') {
						time = -1;
						return;
					}
					if(map[bnextR][bnextC]=='R')
						break;
					
					if(map[bnextR][bnextC]=='#' )
						break;
				}
				
				if(flag) {
					return;
				}
				
				bnextR -= dir[i][0];
				bnextC -= dir[i][1];
				
				map[bnextR][bnextC] = 'B';
				printArr();
				move(nextR, nextC, bnextR, bnextC, cnt+1);
			}
		}
		
	}
	
	private static void printArr() {
		System.out.println("---------------------");
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++)
				System.out.print(map[i][j]);
			System.out.println();
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		time = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = tmp.charAt(j);
				if (red == null || blue == null) {
					if (map[i][j] == 'R')
						red = new Point(i, j);
					else if (map[i][j] == 'B')
						blue = new Point(i, j);
				}
			}
		}
		
		move(red.x,red.y, blue.x, blue.y,  1);
		System.out.println(time);
	}

}
