package begic;

import java.util.*;

public class DP_knapsack {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();	//���ǰ���
        int k = sc.nextInt();	//�賶���빫��

        int v[] = new int[n + 1];
        int w[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }

        int d[][] = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (j < w[i]) {	//������ �� �ִ� ���Ը� �ʰ����� ���:����i�� ������ ����.
                    d[i][j] = d[i - 1][j];
                }
                else if (d[i - 1][j - w[i]] + v[i] > d[i - 1][j]) {	//����i�� �߰��� �� > ���� i�߰�x ��
                    d[i][j] = d[i - 1][j - w[i]] + v[i];
                }
                else {	//����i�� �߰��� �� < ���� i�߰�x ��
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
	