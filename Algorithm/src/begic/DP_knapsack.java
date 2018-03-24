package begic;

import java.util.*;

public class DP_knapsack {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();	//물건개수
        int k = sc.nextInt();	//배낭수용무게

        int v[] = new int[n + 1];
        int w[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }

        int d[][] = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (j < w[i]) {	//수용할 수 있는 무게를 초과했을 경우:물건i를 뺀값을 저장.
                    d[i][j] = d[i - 1][j];
                }
                else if (d[i - 1][j - w[i]] + v[i] > d[i - 1][j]) {	//물건i를 추가한 값 > 물건 i추가x 값
                    d[i][j] = d[i - 1][j - w[i]] + v[i];
                }
                else {	//물건i를 추가한 값 < 물건 i추가x 값
                    d[i][j] = d[i - 1][j];
                }
            }
        }

        int ans = 0;
        for(int i = 0; i <= k; i++){
            if(ans < d[n][i]){
                ans = d[n][i];
            }
        }

        System.out.println(ans);
    }
}
	