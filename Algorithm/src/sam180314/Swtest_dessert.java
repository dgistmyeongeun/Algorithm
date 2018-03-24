package sam180314;

import java.util.Scanner;

public class Swtest_dessert {

	public static int[][] arr;
	public static int N, max, dessertmax;
	public static int dx[] = {1,1,-1,-1};
	public static int dy[] = {1,-1,-1,1};

	public static boolean Eatting(int x, int y, int maxl, int maxr) {
		int visited[] = new int[dessertmax+1];
		
		for(int i=0; i<maxr; i++) {
			x+=dx[0];
			y+=dy[0];
			
			if(visited[arr[x][y]]==1)
				return false;
			else	
				visited[arr[x][y]] = 1;
		}
		for(int i=0; i<maxl; i++) {
			x+=dx[1];
			y+=dy[1];
			
			if(visited[arr[x][y]]==1)
				return false;
			else	
				visited[arr[x][y]] = 1;
		}
		for(int i=0; i<maxr; i++) {
			x+=dx[2];
			y+=dy[2];
			
			if(visited[arr[x][y]]==1)
				return false;
			else	
				visited[arr[x][y]] = 1;
		}
		for(int i=0; i<maxl; i++) {
			x+=dx[3];
			y+=dy[3];
			
			if(visited[arr[x][y]]==1)
				return false;
			else	
				visited[arr[x][y]] = 1;
		}
				
 		return true;
	}
	
	public static void Find(int x, int y) {
		int maxLeft = y;
		int maxRight = (N - 1) - y;
		
		for(int i=maxLeft; i>0; i--)
			for(int j=maxRight; j>0; j--) {
				if(i+j+x>=N)
					continue;
				
				int esize = (i+j)*2;
				if(max<esize) {
					if(Eatting(x,y,i,j))
						max = esize;
				}
			}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TestCase = sc.nextInt();

		for (int tc = 1; tc <= TestCase; tc++) {
			N = sc.nextInt();
			arr = new int[N][N];
			sc.nextLine();

			String[] line;
			dessertmax=0;
			for (int i = 0; i < N; i++) {
				line = sc.nextLine().split(" ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(line[j]);
					if(arr[i][j]>dessertmax)
						dessertmax = arr[i][j];
				}
			}

			max = -1;
			int cnt;
			for (int i = 0; i < N - 2; i++)
				for (int j = 1; j < N - 1; j++) {
					Find(i, j);
				}

			System.out.println("#" + tc + " " + max);
		}
	}

}