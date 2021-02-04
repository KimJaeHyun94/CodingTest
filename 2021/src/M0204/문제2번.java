package M0204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문제2번 {
	static int[][] map;
	static int[][] route;
	static int K, N;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		map = new int[K][N];
		route = new int[K][N];
		dp = new int[K][N];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}

			if (i != K - 1) {
				for (int j = 1; j <= K; j++) {
					int v = (i + j) % K;
					route[i][v] = Integer.parseInt(st.nextToken());
				}
			}
		}

		for (int i = 1; i < K; i++) {
			for (int j = 0; j < N; j++) {
				
			}
		}
	}
}