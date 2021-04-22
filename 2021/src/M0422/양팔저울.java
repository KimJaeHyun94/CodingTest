package M0422;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 양팔저울 {
	static int N, M;
	static int w[], c[];
	static boolean dp[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		w = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			w[i] = Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine());
		c = new int[M + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			c[i] = Integer.parseInt(st.nextToken());
		}

		dp = new boolean[N + 1][30 * 500 + 1];

		DFS(0, 0);
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= M; i++) {
			if (c[i] > 30 * 500) {
				sb.append("N ");
			} else if (dp[N][c[i]]) {
				sb.append("Y ");
			} else
				sb.append("N ");
		}
		System.out.println(sb);
	}

	private static void DFS(int cnt, int weight) {
		if (dp[cnt][weight])
			return;

		dp[cnt][weight] = true;
		if (cnt == N)
			return;

		DFS(cnt + 1, weight + w[cnt]);
		DFS(cnt + 1, weight);
		DFS(cnt + 1, Math.abs(weight - w[cnt]));
	}
}
