package sam180307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class back_ {
	static int R,C;
	static int arr[][];
	static boolean visited[][];
	static int dy[] = {-1,0,1};
	static int dx[] = {1,1,1};
	static int res = 0;
	static int max = 0;
	static boolean flag = false;
	public static void main(String[]args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new int[R][C];
		visited = new boolean[R][C];
		for(int i=0;i<R;i++){
			String line = br.readLine();
			for(int j=0;j<C;j++){
				if(line.charAt(j)=='.'){ // ºóÄ­ 
					arr[i][j] = 0;
				}
				else{ //°Ç¹° 
					arr[i][j] = 1;
				}
			}
		}

		//showArr();

		for(int i=0;i<R;i++){
			dfs(i,0);
		}
		System.out.println(res);
	}
	public static void dfs(int y,int x){
		arr[y][x] = 1;
		if(x == C-1){
			res++;
			return;
		}
		
		for(int i=0;i<dx.length;i++){
			int ny = y+dy[i];
			int nx = x+dx[i];
			if(ny>=R || nx>=C || ny<0 || nx <0){
				continue;
			}
			if(arr[ny][nx]==1){
				continue;
			}
			dfs(ny,nx);
			return;
		}
	}
	public static void showArr(){
		for(int i=0;i<R;i++){
			for(int j=0;j<C;j++){
				System.out.print(arr[i][j]+" ");
			}
			System.out.println("");
		}
	}
	public static void showVisited(){
		for(int i=0;i<R;i++){
			for(int j=0;j<C;j++){
				if(visited[i][j]==true){
					System.out.print("t"+" ");
				}
				else{
					System.out.print("f"+" ");
				}

			}
			System.out.println("");
		}
	}
}