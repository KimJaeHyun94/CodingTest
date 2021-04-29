package M0428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 구간나누기bottomup {
	static int N, M;
	static int dp[][];
	static int sum[];
	static int INF = -987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sum = new int[N + 1];
		dp = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			int n = Integer.parseInt(br.readLine());
			if (i == 1)
				sum[i] = n;
			else
				sum[i] = sum[i - 1] + n;
		}

		for (int i = 1; i <= M; i++) {
			dp[0][i] = INF; // 초기화
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				dp[i][j] = dp[i - 1][j];
				for (int k = i; k > 0; k--) {
					if (k >= 2) {
						dp[i][j] = Math.max(dp[k - 2][j - 1] + sum[i] - sum[k - 1], dp[i][j]);
					} else if (k == 1 && j == 1)
						dp[i][j] = Math.max(dp[i][j], sum[i]);
				}
			}
		}
		System.out.println(dp[N][M]);

	}
}
