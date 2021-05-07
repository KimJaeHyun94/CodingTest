package M0429;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 기타리스트 {
	static int N, S, M;
	static int V[];
	static boolean dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		V = new int[N+1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			V[i] = Integer.parseInt(st.nextToken());
		}

		dp = new boolean[N+1][M + 1];

		dp[0][S] = true;

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= M; j++) {
				if (!dp[i - 1][j]) {
					continue;
				}
				if (j - V[i] >= 0) {
					dp[i][j - V[i]] = true;
				}
				if (j + V[i] <= M) {
					dp[i][j + V[i]] = true;
				}
			}
		}

		for (int i = M; i >= 0; i--) {
			if (dp[N][i]) {
				System.out.println(i);
				System.exit(0);
			}
		}

		System.out.println(-1);

	}
}
