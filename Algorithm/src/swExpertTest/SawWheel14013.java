package swExpertTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SawWheel14013 {

   private static int[][] arr, temparr;
   private static boolean visited[];
   
   private static void Move(int[][] inarr, int heel, int dir) {
      boolean rot = false;
      if(heel>3 || heel<0)
         return;
      
      if(heel==1 || heel==2) {
         if(!visited[heel] && inarr[heel][2] != inarr[heel+1][6]) {
            //visited[heel] = true;
            Move(inarr, heel+1, dir*(-1));
            rot = true;
         }
         if(!visited[heel] && inarr[heel][6] != inarr[heel-1][2]){
            visited[heel] = true;
            Move(inarr, heel-1, dir*(-1));
            rot = true;
         }
      }else {
         if(heel==0) {
            if(!visited[heel] && inarr[heel][2] != inarr[heel+1][6]) {
               visited[heel] = true;
               Move(inarr, heel+1, dir*(-1));
               rot = true;
            }
         }else {
            if(!visited[heel] && inarr[heel-1][2] != inarr[heel][6]) {
               visited[heel] = true;
               Move(inarr, heel-1, dir*(-1));
               rot = true;
            }
         }
         
      }
      
         if(dir==1) {
            for(int i=0; i<8; i++) {
               temparr[heel][(i+1)%8] = inarr[heel][i];
            }
         }else {
            for(int i=0; i<8; i++) {
               temparr[heel][(i+7)%8] = inarr[heel][i];
            }
         }
   }
   
   public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      int TestCase = Integer.parseInt(br.readLine());
      
      for(int tc=1; tc<=TestCase; tc++) {
         int K = Integer.parseInt(br.readLine());
         
         arr = new int[4][8];
         temparr = new int[4][8];
         visited = new boolean[4];
         for(int i=0; i<4; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<8; j++)
               temparr[i][j] = arr[i][j] = Integer.parseInt(st.nextToken());
         }
         
         for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int heel = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            
            Move(arr, heel-1, dir);
            for(int r=0; r<4; r++) {
               for(int c=0; c<8; c++)
                  arr[r][c] = temparr[r][c];
            }
            visited = new boolean[4];
         }
         
         int ans = 0;
         for(int i=0; i<4; i++) {
            if(arr[i][0]==1)
               ans += Math.pow(2, i);
         }
         System.out.println("#" + tc + " "+ans);
      }
   }

}