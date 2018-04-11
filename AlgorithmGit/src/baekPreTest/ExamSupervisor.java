package baekPreTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ExamSupervisor {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] room = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			room[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		long cnt=N;
		
		for(int i=0; i<N; i++) {
			room[i] -= B;
			if(room[i]>0) {
				if(room[i]%C==0) {
					cnt+=(room[i]/C);
				}else {
					cnt+=(room[i]/C)+1;
				}
			}
		}
		
		System.out.println(cnt);
		
		
	}

}
