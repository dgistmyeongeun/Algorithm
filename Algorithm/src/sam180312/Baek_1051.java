package sam180312;

import java.util.Scanner;

public class Baek_1051 {

	public static int n, m;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		sc.nextLine();
		int[][] arr = new int[n][m];

		for (int i = 0; i < n; i++) {
			String[] line = sc.nextLine().split("");
			for (int j = 0; j < m; j++)
				arr[i][j] = Integer.parseInt(line[j]);
		}
		int min = n > m ? m : n;
		int max = 1;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int k = 1; k < min; k++) {
					if (!check(i + k, j + k))
						continue;
					if (arr[i + k][j + k] == arr[i][j] && arr[i][j + k] == arr[i][j] && arr[i + k][j] == arr[i][j]) {
						int temp = (k + 1) * (k + 1);
						max = max > temp ? max : temp;
					}

				}
			}
		}

		System.out.println(max);
	}

	public static boolean check(int row, int col) {
		if (row < 0 || row >= n || col < 0 || col >= m)
			return false;
		return true;
	}

}
