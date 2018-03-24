package swExpertTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Road4014 {
    static int N, X, ans;
    static int[][] a ,b;
    
    static void check(int row, int a[][]) {
        boolean c[] = new boolean[N];
        int temp = a[row][0];
        for (int i = 0; i < N; i++) {
            if (a[row][i] != temp) {
                if (Math.abs(a[row][i] - temp) > 1) 
                	return; 
                
                if (a[row][i] < temp) { //다음것이 나보다 작을 경우(오른쪽 경사로)
                    int num = -1;
                    for (int j = i; j <= i + X - 1; j++) {
                        if (j >= N || c[j]) 
                        	return;
                        c[j] = true;
                        
                        if (num == -1) {
                            num = a[row][j];
                            continue;
                        }
                        if (num != -1 && a[row][j] != num) return;
                    }
                    i = i + X - 1;
                    /*if (i >= N) 
                    	break;*/
                }
                else { //다음것이 나보다 큰 경우(왼쪽 경사로)
                    int num = -1;
                    for (int j = i - 1; j >= i - X; j--) {
                        if (j < 0 || c[j]) return;
                        c[j] = true;
                        if (num == -1) {
                            num = a[row][j];
                            continue;
                        }
                        if (num != -1 && a[row][j] != num) return;
                    }
                }
                temp = a[row][i];
            }
        }
        ++ans;
    }
    
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int TestCase = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=TestCase; tc++) {
        	st = new StringTokenizer(br.readLine());
	        N = Integer.parseInt(st.nextToken());
	        X = Integer.parseInt(st.nextToken());
	        ans = 0;
	        a = new int[N][N];
	        b = new int[N][N];
	        
	        for(int i=0; i<N; i++) {
	        	st = new StringTokenizer(br.readLine());
	            for(int j=0; j<N; j++){
	                a[i][j] = Integer.parseInt(st.nextToken());
	                b[j][i] = a[i][j];
	            }
	        }
	         
	        for(int i=0; i<N; i++){
	            check(i,a);
	            check(i,b);
	        }
	        System.out.println("#"+tc+" " +ans);
        }
    }
}


