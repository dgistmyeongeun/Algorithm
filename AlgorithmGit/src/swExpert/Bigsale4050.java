package swExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Bigsale4050 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TestCase = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TestCase; tc++) {
			int n = Integer.parseInt(br.readLine());
			String[] arr = br.readLine().split(" ");
			int num[] = new int[n];
			int num2[] = new int[n];
			
			for(int i=0; i<n; i++)
				num[i] = Integer.parseInt(arr[i]);
			
			Arrays.sort(num);
			for(int i=0; i<n; i++)
				num2[n-1-i] = num[i];
			int ans = 0;
			for(int i=0; i<n; i++) {
				if((i+1)%3==0)
					continue;
				ans += num2[i];
			}
			System.out.println("#"+tc+" "+ans);
		}
	}

}
