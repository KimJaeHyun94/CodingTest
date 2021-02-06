package M0206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 여행가자플로이드와샬 {
	static int N, M;
	static final int INF = 987654321;
	static int distance[][];
	static int Route[];

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
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int check = Integer.parseInt(st.nextToken());
				if (check == 1) {
					distance[i][j] = check;
					distance[j][i] = check;
				}
			}
		}
		floydWarshall();
		Route = new int[M];
		st = new StringTokenizer(br.readLine());
		int first = Integer.parseInt(st.nextToken());
		boolean flag = true;
		for (int i = 0; i < M - 1; i++) {
			int idx = Integer.parseInt(st.nextToken());
			if (distance[first][idx] == INF) {
				flag = false;
				break;
			}
		}

		if (!flag)
			System.out.println("NO");
		else
			System.out.println("YES");

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
