package M0307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정수삼각형 {
	static int dp[][];
	static int map[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		dp = new int[N][N];
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < i + 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp[0][0] = map[0][0];

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (j == 0) {
					dp[i][j] = dp[i - 1][j] + map[i][j];
				} else if (j == N - 1) {
					dp[i][j] = dp[i - 1][j - 1] + map[i][j];
				} else {
					dp[i][j] = dp[i - 1][j - 1] > dp[i - 1][j] ? dp[i - 1][j - 1] + map[i][j]
							: dp[i - 1][j] + map[i][j];
				}
			}
		}
		int answer = Integer.MIN_VALUE;
		for (int i = 0; i < dp[N - 1].length; i++) {
			answer = Math.max(answer, dp[N - 1][i]);
		}

		System.out.println(answer);

	}
}
