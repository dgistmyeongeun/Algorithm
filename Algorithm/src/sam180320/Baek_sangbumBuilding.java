package sam180320;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baek_sangbumBuilding {

	private static int L,R,C;
	private static char[][][] map;
	private static int[][] dir = {{0,0,1},{0,1,0},{0,0,-1},{0,-1,0},{1,0,0},{-1,0,0}};
	private static boolean visited[][][];
	
	private static int bfs(Pos start, Pos end) {
		int time = 0;
		Queue<Pos> que = new LinkedList<>();
		que.add(start);
		
		while(!que.isEmpty()) {
			int size = que.size();
			
			for(int i=0; i<size; i++) {
				Pos cur = que.poll();
				
				if(cur.l == end.l && cur.c == end.c && cur.r == end.r)
					return time;
				
				for(int d=0; d<6; d++) {
					int nl = cur.l + dir[d][0];
					int nr = cur.r + dir[d][1];
					int nc = cur.c + dir[d][2];
					
					if(nl<0 || nr<0 || nc<0 || nl>=L || nr>=R || nc>=C)
						continue;
					
					if(visited[nl][nr][nc])
						continue;
					
					if(map[nl][nr][nc] == '#')
						continue;
					
					visited[nl][nr][nc] = true;
					que.add(new Pos(nl,nr,nc));
				}
			}
			time++;
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			if(L==0 && R==0 && C==0)
				break;
			
			map = new char[L][R][C];
			visited  = new boolean[L][R][C];
			Pos start= null;
			Pos end = null;
			
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					String ts = br.readLine();
					for (int k = 0; k < C; k++) {
						map[i][j][k] = ts.charAt(k);
						if(map[i][j][k] == 'S') {
							start = new Pos(i, j, k);
						}else if(map[i][j][k] == 'E') {
							end = new Pos(i, j, k);
						}
					}
				}
				st = new StringTokenizer(br.readLine());
			}
			
			int ans = bfs(start, end);
			
			if(ans == -1) {
				System.out.println("Trapped!");
			}else {
				System.out.println("Escaped in "+ ans + " minute(s).");
			}
		}

	}
}

class Pos {
	int l;
	int r;
	int c;
	
	public Pos(int l, int r, int c) {
		this.l = l;
		this.r = r;
		this.c = c;
	}
}
