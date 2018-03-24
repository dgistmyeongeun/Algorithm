
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sw_cube3812 {

	private static int X, Y, Z, a, b, c, N;
	private static int[] arr, temparr;
	private static long[] ncnt;

	private static void find(int y, int z) {
		int temparr3[] = new int[X + 1];
		
		for (int k = y + 1; k <= Y; k++) {
			if(k>=y+N) {
				for (int j = 0; j <N; j++) {
					ncnt[j]*=(Y+1)/N;
				}
			}
				for (int i = z + 1; i <= Z ; i++) {
					if(i>=z+N) {
						for (int j = 0; j <N; j++) {
							ncnt[j]*=(Z+1)/N;
						}
						return;
					}else {
						for (int j = 0; j <= X; j++) {
							temparr[j] = (arr[j] + 1) % N;
							temparr3[j] = (temparr[j] + 1) % N;
							if(k%2==0 && i%2==0) {
								ncnt[temparr[j]]+=4;
								ncnt[temparr3[j]]+=4;
							}else if(k%2==0 || i%2==0) {
								ncnt[temparr[j]]+=2;
								ncnt[temparr3[j]]+=2;
							}else {
								ncnt[temparr[j]]+=2;
								ncnt[temparr3[j]]+=1;
							}
						} 
					}
				}
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int TestCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TestCase; tc++) {
			st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken()) - 1;
			Y = Integer.parseInt(st.nextToken()) - 1;
			Z = Integer.parseInt(st.nextToken()) - 1;
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			ncnt = new long[N];
			arr = new int[X + 1];
			temparr = new int[X + 1];
			for (int i = 0; i <= X; i++) {
				arr[i] = Math.abs(i - a) % N;
				ncnt[arr[i]]++;
			}

			find(b, c);
			System.out.print("#" + tc + " ");
			for (int i = 0; i < N; i++)
				System.out.print(ncnt[i] + " ");
			System.out.println();
		}

	}
}