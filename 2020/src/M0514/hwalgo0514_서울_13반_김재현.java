package M0514;

import java.util.Scanner;

public class hwalgo0514_서울_13반_김재현 {
	private static int N;
	private static int[][] graph;
	private static boolean[][] visited;
	private static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static int k;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		graph = new int[N][N];

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				graph[i][j] = sc.nextInt();
				max = Math.max(max, graph[i][j]);
				min = Math.min(min, graph[i][j]);
			}
		}
		int max2 = 0;
		for (k = 1; k <= max; k++) {
			int cnt = 0;
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (graph[i][j] >= k && !visited[i][j]) {
						DFS(i, j);
						cnt++;
					}
				}
			}
			max2 = Math.max(max2, cnt);
		}
		System.out.println(max2);
	}

	private static void DFS(int i, int j) {
		visited[i][j] = true;

		for (int j2 = 0; j2 < 4; j2++) {
			int di = i + dir[j2][0];
			int dj = j + dir[j2][1];

			if (isOK(di, dj)) {
				DFS(di, dj);
			}
		}
	}

	private static boolean isOK(int di, int dj) {
		if (di >= 0 && dj >= 0 && di < N && dj < N) {
			if (!visited[di][dj] && graph[di][dj] >= k)
				return true;
		}
		return false;
	}
}