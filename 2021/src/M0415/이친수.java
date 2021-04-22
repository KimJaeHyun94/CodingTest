package M0415;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 이친수 {
	static long dp[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		dp = new long[2][N + 1];

		if (N <= 2) {
			System.out.println(1);
		} else {
			dp[0][2] = 1;
			dp[1][2] = 0;
			for (int i = 3; i <= N; i++) {
				dp[0][i] += dp[0][i - 1] + dp[1][i - 1];
				dp[1][i] += dp[0][i - 1];
			}
			System.out.println(dp[0][N] + dp[1][N]);
		}
	}
}
