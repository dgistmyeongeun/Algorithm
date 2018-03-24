package sam180320;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class cube3812 {

	private static int N;
	private static long[] arr, ncnt, temp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int TestCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TestCase; tc++) {
			int[] pos = new int[3];
			int[] center = new int[3];
			st = new StringTokenizer(br.readLine());
			pos[0] = Integer.parseInt(st.nextToken());
			pos[1] = Integer.parseInt(st.nextToken());
			pos[2] = Integer.parseInt(st.nextToken());
			center[0] = Integer.parseInt(st.nextToken());
			center[1] = Integer.parseInt(st.nextToken());
			center[2] = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			arr = new long[N];
			ncnt = new long[N];
			temp = new long[N];
			
			arr[0] = 1;
			ncnt[0] = 1;
			temp[0] = 1;
			
			for(int i=0; i<3; i++) {
				long num = 0;
				
				num += ((pos[i]-center[i]-1)/N) + (center[i]/N);
				
				for(int j=1; j<=(pos[i]-center[i]-1)%N; j++) {
					for(int k=0; k<N; k++) {
						ncnt[(j+k)%N]+=arr[k];
					}
				}
				for(int j=1; j<=center[i]%N; j++) {
					for(int k=0; k<N; k++) {
						ncnt[(j+k)%N]+=arr[k];
					}
				}
				
				if(num!=0) {
					for(int j=1; j<N; j++) {
						for(int k=0; k<N; k++) {
							temp[(j+k)%N] += arr[k]; 
						}
					}
					for(int j=0; j<N; j++) {
						ncnt[j] += temp[j]*num;
					}
				}
				
				for(int j=0; j<N; j++) {
					arr[j] = temp[j] = ncnt[j];
				}
			}
			
			System.out.print("#"+tc+" ");
			for(int i=0; i<N; i++)
				System.out.print(ncnt[i]+" ");
			System.out.println();
		}
	}

}
