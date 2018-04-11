package baekPreTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
	int d, rx, ry, bx, by;

	public Node(int d, int rx, int ry, int bx, int by) {
		this.d = d;
		this.rx = rx;
		this.ry = ry;
		this.bx = bx;
		this.by = by;
	}
}

public class Zzero2 {
	static int N, M, fx, fy, ans;
	static char[][] mat;
	static int[] dx = {-1,1,0,0};	//우,아래,좌,위
	static int[] dy = {0,0,-1,1};
	static Queue<Node> q;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		mat = new char[N][M];
		String temp;
		int srx=0, sry=0, sbx=0, sby=0;
		for(int i=0; i<N; i++) {
			temp = br.readLine();
			for(int j=0; j<M; j++) {
				mat[i][j] = temp.charAt(j);
				
				if(mat[i][j]=='O') {
					fx = i;
					fy = j;
				}else if(mat[i][j]=='R') {
					srx = i;
					sry = j;
				}else if(mat[i][j]=='B') {
					sbx = i;
					sby = j;
				}
			}
		}
		q = new LinkedList<>();
		q.offer(new Node(0, srx, sry, sbx, sby));
		
		ans=-1;
		
		find();
		System.out.println(ans);
		
	}
	
	static void find() {
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int cnt = node.d;
			int rx = node.rx;
			int ry = node.ry;
			int bx = node.bx;
			int by = node.by;
			
			if(cnt == 10)	break;
			
			for(int i=0; i<4; i++) {
				int rex = 0;	//red이동할 때 출구가 나오는 때
				int blue = 0;	//red이동할 때 blue가 나오는 때
				int nrx, nry;
				int rmove=0;
				nrx = rx+dx[i];
				nry = ry+dy[i];
				
				while(mat[nrx][nry] != '#') {
					rmove++;
					if(nrx == bx && nry == by) 
						blue=rmove;
					if(mat[nrx][nry]=='O')
						rex=rmove;
					nrx+=dx[i];
					nry+=dy[i];
				}
				
				boolean bex = false;
				boolean red = false;
				int nbx = bx+dx[i];
				int nby = by+ dy[i];
				int bmove=0;
				
				while(mat[nbx][nby] != '#') {
					bmove++;
					if(nbx==rx && nby==ry)
						red = true;
					if(mat[nbx][nby] == 'O')
						bex = true;
					nbx+=dx[i];
					nby+=dy[i];
				}
				
				if(blue==0 && !red) {
					if(rex!=0) {
						ans=cnt+1;
						return;
					}else if(bex) 
						continue;
					else
						q.offer(new Node(cnt+1, rx+dx[i]*rmove, ry+dy[i]*rmove, bx+dx[i]*bmove, by+dy[i]*bmove));
				
				}else if(blue != 0) {
					if(rex != 0) {
						if(rex<blue) {
							ans = cnt+1;
							return;
						}
						continue;
					}else {
						rmove--;
						q.offer(new Node(cnt+1, rx+dx[i]*rmove, ry+dy[i]*rmove, bx+dx[i]*bmove, by+dy[i]*bmove));
					}
				}else {
					if(rex != 0)	continue;
					else {
						bmove--;
						q.offer(new Node(cnt+1, rx+dx[i]*rmove, ry+dy[i]*rmove, bx+dx[i]*bmove, by+dy[i]*bmove));
					}
				}
			}
		}
	}
	
}
