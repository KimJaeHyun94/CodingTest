package M0506;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 소가길을건너간이유8 {
	static int N;
	static int left[], right[];
	static int dp[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		left = new int[N + 1];
		right = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			left[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 1; i <= N; i++) {
			right[i] = Integer.parseInt(br.readLine());
		}

		dp = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				if (Math.abs(left[i] - right[j]) <= 4) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
			}
		}
		System.out.println(dp[N][N]);
	}

}
