package swExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class WirelessCamera4111 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int TestCase = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TestCase; tc++) {
			int N = Integer.parseInt(br.readLine());
			int K = Integer.parseInt(br.readLine());
			
			int[] arr = new int[N];
			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr);
			
			int tmp = 0;
			if(N-1!=0) {
				int[] diffarr = new int[N-1];
				int[] diffarr2 = new int[N-1];
				
				for(int i=0; i<N-1; i++) {
					diffarr[i] = arr[i+1]-arr[i];
				}
				Arrays.sort(diffarr);	
				
				
				for(int i=0; i<diffarr.length-(K-1); i++) {
					tmp += diffarr[i];
				}
			}else {
				tmp = arr[1]-arr[0];
			}
			System.out.println("#"+tc+" "+tmp);
		}
	}

}