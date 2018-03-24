package sam180320;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sw_cooker4012 {
   
   private  static int[][] arr;
   private static int min, N;
   private static int[] cnt;
   
   private static void cook(int ri, int rj, int idx) {
      if(idx>N/2)
         return;
      
      for(int i=1; i<=N; i++) {
         if(i==ri || i==rj)
            continue;
         for(int j=1; j<=N; j++) {
            if(i==j || i==ri || i==rj)
               continue;
            cnt[idx] = arr[i][j]+ arr[j][i];
         }
      }
      
   }
   public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int TestCase = Integer.parseInt(br.readLine());
      StringTokenizer st;
      
      for(int tc=1; tc<=TestCase; tc++) {
         N = Integer.parseInt(br.readLine());
         arr = new int[N+1][N+1];
         
         for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++)
               arr[i][j] = Integer.parseInt(st.nextToken());
         }
         
         min = Integer.MAX_VALUE;
         cnt = new int[N/2];
         int temp;
         
         for(int i=1; i<=N; i++) {
            for(int j=i+1; j<=N; j++ ) {
               cnt[0] = arr[i][j]+ arr[j][i];
               cook(i, j, 1);
               temp =cnt[0];
               for(int k=1; k<N/2; k++) {
                  temp -= cnt[k];
               }
               min = Math.min(Math.abs(temp), min);
            }
         }
         
         System.out.println("#"+tc+" "+min);
      }
   }

}