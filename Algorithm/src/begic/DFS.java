package begic;
import java.util.*;

public class DFS {
    static boolean edge[][];
    static boolean visited[];
    static int n;
    static int m;

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        edge = new boolean[n + 1][n + 1];
        visited = new boolean[n + 1];
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            edge[u][v] = true;
        }
        dfs(1);
    }

    public static void dfs(int cur) {
        visited[cur] = true;
        System.out.print(String.valueOf(cur) + ' ');
        for (int i = 1; i <= n; i++) {
            if (visited[i] || !edge[cur][i]) continue; // already visited or not connected.
            dfs(i);
        }
    }
}
