package M0501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 달려달려 {
	static int N, M;
	static int dp[][][];
	static int d[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		dp = new int[10001][502][2];
		d = new int[10001];

		for (int i = 1; i <= N; i++) {
			d[i] = Integer.parseInt(br.readLine());
		}

		dp[1][1][1] = d[1]; // 처음에 움직일때
		dp[1][0][0] = 0;
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j <= M; j++) {
				if (j == 0) {
					dp[i][j][0] = Math.max(Math.max(dp[i - 1][j + 1][0], dp[i - 1][j + 1][1]), dp[i - 1][j][0]);
				} else if (j == 1) {
					dp[i][j][1] = Math.max(dp[i - 1][j - 1][1], dp[i - 1][j - 1][0]) + d[i];
					dp[i][j][0] = Math.max(dp[i - 1][j + 1][1], dp[i - 1][j + 1][0]);
				} else {
					dp[i][j][1] = dp[i - 1][j - 1][1] + d[i]; // 움직일때
					dp[i][j][0] = Math.max(dp[i - 1][j + 1][1], dp[i - 1][j + 1][0]); // 멈춰있을때
				}
			}
		}

		System.out.println(dp[N][0][0]);
	}

}
