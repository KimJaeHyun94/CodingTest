package M0207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 플로이드 {
	static int N, M;
	static final int INF = 987654321;
	static int distance[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		distance = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;
				distance[i][j] = INF;
			}
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			distance[start][end] = Math.min(distance[start][end], w);  //똑같은  start/end로 입력되는 경우가 있어서
		}
		floydWarshall();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (distance[i][j] == INF) {
					sb.append(0).append(" ");
				} else {
					sb.append(distance[i][j]).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}

	public static void floydWarshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					distance[i][j] = Math.min(distance[i][k] + distance[k][j], distance[i][j]);
				}
			}
		}
	}
}
