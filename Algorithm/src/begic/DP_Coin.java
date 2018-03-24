package begic;

import java.util.*;

public class DP_Coin {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();	//동전개수
        int k = scanner.nextInt();	//금액

        int coin[] = new int[n];
        for (int i = 0; i < n; i++) {
            coin[i] = scanner.nextInt();
        }

        int d[] = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            d[i] = -1;
            for (int j = 0; j < n; j++) {
                if (coin[j] <= i) {
                    if (d[i - coin[j]] < 0) continue;
                    if (d[i] < 0) d[i] = d[i - coin[j]] + 1;
                    else if (d[i - coin[j]] + 1 < d[i]) d[i] = d[i - coin[j]] + 1;
                }
            }
        }

        System.out.println(d[k]);
    }
}
