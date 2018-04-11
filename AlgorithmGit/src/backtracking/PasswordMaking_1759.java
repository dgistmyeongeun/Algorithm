package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PasswordMaking_1759 {

	static int L, C;
	static char[] arr;

	static void combi(int idx, int l, int mo, int ja, String s) {
		if(l==L) {
			if(mo>=1 && ja>=2)
				System.out.println(s);
			return;
		}
		if(idx==C)
			return;
		if(arr[idx]=='a' || arr[idx]=='e' || arr[idx]=='i' || arr[idx]=='o' || arr[idx]=='u')
			combi(idx+1, l+1, mo+1, ja, s+arr[idx]);
		else
			combi(idx+1, l+1, mo, ja+1, s+arr[idx]);
		combi(idx+1, l, mo, ja, s);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[C];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++)
			arr[i] = st.nextToken().charAt(0);
		Arrays.sort(arr);

		combi(0,0,0,0,"");
	}

}
