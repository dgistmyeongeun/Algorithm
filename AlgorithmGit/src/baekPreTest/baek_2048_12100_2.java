package baekPreTest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baek_2048_12100_2 {

   private static int n, ans, maxin = Integer.MIN_VALUE;
   private static int[][] temp;
   private static boolean[][] visited;

   private static int chkMax(int[][] arr6) {
      int chkmax = Integer.MIN_VALUE;
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
            if (arr6[i][j] > chkmax)
               chkmax = arr6[i][j];
         }
      }
      return chkmax;
   }

   private static void dfs(int[][] arr2, int cnt) {
      if (cnt == 5) {
         int count = chkMax(arr2);
         maxin = Math.max(count, maxin);
         return;
      }
      int[][] arr3 = new int[n][n];
      int[][] arr = new int[n][n];

      for (int k = 0; k < 4; k++) {
         for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
               arr[i][j] = arr2[i][j];
            }
         }
         move(k, arr);
         reset();
         for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
               arr3[i][j] = arr[i][j];
            }
         }
         dfs(arr3, cnt + 1);

      }

   }

   private static void move(int dir, int arr[][]) {
      if (dir == 0) { // 0
         for (int i = 0; i < n; i++) {
            int cnt = 0;
            while (cnt < n) {
               for (int j = n - 2; j >= 0; j--) {
                  if (arr[i][j] != 0) {
                     if (arr[i][j + 1] == 0) {
                        arr[i][j + 1] = arr[i][j];
                        arr[i][j] = 0;
                     } else if (arr[i][j + 1] == arr[i][j] && !visited[i][j + 1] && !visited[i][j]) {
                        arr[i][j + 1] += arr[i][j];
                        arr[i][j] = 0;
                        visited[i][j + 1] = true;
                     }
                  }
               }
               cnt++;
            }
         }
         /*
          * for (int i = 0; i < n; i++) { for (int j = 0; j < n; j++)
          * System.out.print(arr[i][j] + " "); System.out.println(); }
          */

      }

      else if (dir == 2) { // 0
         for (int i = 0; i < n; i++) {
            int cnt = 0;
            while (cnt <= n) {

               for (int j = 1; j < n; j++) {

                  if (arr[i][j] != 0) {
                     if (arr[i][j - 1] == 0) {
                        arr[i][j - 1] = arr[i][j];
                        arr[i][j] = 0;
                     } else {
                        if (arr[i][j - 1] == arr[i][j] && !visited[i][j - 1] && !visited[i][j]) {
                           arr[i][j - 1] += arr[i][j];
                           arr[i][j] = 0;
                           visited[i][j - 1] = true;
                        }
                     }
                  }
               }
               cnt++;
            }
         }
         /*
          * for (int i = 0; i < n; i++) { for (int j = 0; j < n; j++)
          * System.out.print(arr[i][j] + " "); System.out.println(); }
          */
      }

      else if (dir == 3) { // 0
         for (int j = 0; j < n; j++) {
            int cnt = 0;
            while (cnt < n) {
               for (int i = 1; i < n; i++) {
                  if (arr[i][j] != 0) {
                     if (arr[i - 1][j] == 0) {
                        arr[i - 1][j] = arr[i][j];
                        arr[i][j] = 0;
                     } else if (arr[i - 1][j] == arr[i][j] && !visited[i - 1][j] && !visited[i][j]) {
                        arr[i - 1][j] += arr[i][j];
                        arr[i][j] = 0;
                        visited[i - 1][j] = true;
                     }
                  }
               }
               cnt++;
            }
         }
         /*
          * for (int i = 0; i < n; i++) { for (int j = 0; j < n; j++)
          * System.out.print(arr[i][j] + " "); System.out.println(); }
          */
      }

      else if (dir == 1) { // 0
         for (int j = 0; j < n; j++) {
            int cnt = 0;
            while (cnt < n) {
               for (int i = n - 2; i >= 0; i--) {
                  if (arr[i][j] != 0) {
                     if (arr[i + 1][j] == 0) {
                        arr[i + 1][j] = arr[i][j];
                        arr[i][j] = 0;
                     } else if (arr[i + 1][j] == arr[i][j] && !visited[i + 1][j] && !visited[i][j]) {
                        arr[i + 1][j] += arr[i][j];
                        arr[i][j] = 0;
                        visited[i + 1][j] = true;
                     }
                  }
               }
               cnt++;
            }
         }
         /*
          * for (int i = 0; i < n; i++) { for (int j = 0; j < n; j++)
          * System.out.print(arr[i][j] + " "); System.out.println(); }
          */
      }
   }

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;

      n = Integer.parseInt(br.readLine());
      int[][] arrorig = new int[n][n];
      visited = new boolean[n][n];
      for (int i = 0; i < n; i++) {
         st = new StringTokenizer(br.readLine());
         for (int j = 0; j < n; j++) {
            arrorig[i][j] = Integer.parseInt(st.nextToken());
         }
      }

      int max = Integer.MIN_VALUE;

      maxin = 0;

      dfs(arrorig, 0);
      max = Math.max(maxin, max);

      System.out.println(max);
   }

   private static void reset() {
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
            visited[i][j] = false;
         }
      }
   }
}