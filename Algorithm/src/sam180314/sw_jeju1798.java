package sam180314;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class sw_jeju1798 {

	public static int N, M, pcnt;
	public static int[] istate;
	public static int[][] arr;
	public static boolean[] visited;
	public static PInfo[] pinfo;



	public static void bfs(int idx, int day) {
		int tempt, t = 0;
		if (M > day) { // 호텔로
			for(int i=1; i<=N; i++) {
				if(istate[i]==2) {
					tempt = arr[idx][i]+pinfo[i].time;
					if(tempt+t>540) {
						continue;
					}else {
						bfs(i, day);
					}
				}else if(istate[i]==3) {
					
				}
			}
		} else { // 공항으로

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int TestCase = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TestCase; tc++) {
			String line[] = br.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			M = Integer.parseInt(line[1]);
			arr = new int[N + 1][N + 1];
			istate = new int[N + 1];
			visited = new boolean[N + 1];

			for (int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = i + 1; j <= N; j++) {
					arr[j][i] = arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			pinfo = new PInfo[N+1];
			pcnt = 0;
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				String state = st.nextToken();
				if (state.equals("A"))
					istate[i] = 1;
				else if (state.equals("H"))
					istate[i] = 3;
				else if (state.equals("P")) {
					istate[i] = 2;
					pcnt++;
					int t = Integer.parseInt(st.nextToken());
					int v = Integer.parseInt(st.nextToken());
					pinfo[i] = new PInfo(t, v);
				}
			}

			bfs(1, 1);
		}
	}

	public static void print() {
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}

class PInfo {
	int time;
	int val;

	public PInfo(int time, int val) {
		this.time = time;
		this.val = val;
	}

	/*
	 * @Override public int compareTo(PInfo o) { if (val < o.val) return 1; else if
	 * (val > o.val) return -1; else return 0; }
	 */

}