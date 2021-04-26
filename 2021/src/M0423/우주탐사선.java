package M0423;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 우주탐사선 {
	static int N, K;
	static int dist[][];
	static final int INF = 987654321;
	static boolean visited[];
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dist = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				dist[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		FloydWarshall();
		visited = new boolean[N];
		visited[K] = true;
		DFS(K, 0, 0);
		
		System.out.println(ans);
	}

	private static void DFS(int k, int sum, int depth) {
		if (depth == N - 1) {
			ans = Math.min(ans, sum);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				DFS(i, sum + dist[k][i], depth + 1);
				visited[i] = false;
			}
		}

	}

	private static void FloydWarshall() {
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}

	}
}
