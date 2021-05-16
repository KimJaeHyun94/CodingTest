package 누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 직사각형으로나누기 {

	static int N, M;
	static int arr[][];
	static long dp[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N + 1][M + 1];
		dp = new long[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 1; j <= M; j++) {
				arr[i][j] = line.charAt(j-1)-'0';
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + arr[i][j];
			}
		}

		long ans = Long.MIN_VALUE;
		long f, s, t;

		for (int i = 1; i <= M - 2; i++) {
			f = dp[N][i];
			for (int k = i + 1; k <= M - 1; k++) {
				s = dp[N][k] - f;
				t = dp[N][M] - s - f;
				ans = Math.max(ans, f * s * t);
			}
		}

		// case:2
		for (int i = 1; i <= N - 2; i++) {
			f = dp[i][M];
			for (int k = i + 1; k <= N - 1; k++) {
				s = dp[k][M] - f;
				t = dp[N][M] - s - f;
				ans = Math.max(ans, f * s * t);
			}
		}

		// case:3
		for (int i = 1; i <= M - 1; i++) {
			f = dp[N][i];
			for (int k = 1; k <= N - 1; k++) {
				s = dp[k][M] - dp[k][i];
				t = dp[N][M] - f - s;
				ans = Math.max(ans, f * s * t);
			}
		}

		// case:4
		for (int i = 1; i <= M - 1; i++) {
			for (int k = 1; k <= N - 1; k++) {
				f = dp[k][i];
				s = dp[N][i] - f;
				t = dp[N][M] - f - s;
				ans = Math.max(ans, f * s * t);
			}
		}

		// case:5
		for (int i = 1; i <= N - 1; i++) {
			f = dp[i][M];
			for (int k = 1; k <= M - 1; k++) {
				s = dp[N][k] - dp[i][k];
				t = dp[N][M] - f - s;
				ans = Math.max(ans, f * s * t);
			}
		}

		// case:6
		for (int i = 1; i <= N - 1; i++) {
			for (int k = 1; k <= M - 1; k++) {
				f = dp[i][k];
				s = dp[i][M] - f;
				t = dp[N][M] - f - s;
				ans = Math.max(ans, f * s * t);
			}
		}

		System.out.println(ans);

	}
}
