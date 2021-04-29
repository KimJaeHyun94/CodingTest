package DP_독특;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 크리보드 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		long dp[] = new long[101];
		if (N < 4) {
			System.out.println(N);
		} else {
			for (int i = 1; i <= 4; i++) {
				dp[i] = i;
			}
			for (int i = 5; i <= N; i++) {
				dp[i] = dp[i - 1] + 1;
				for (int j = 1; j <= i - 3; j++) {
					dp[i] = Math.max(dp[i], dp[i - (j + 2)] * (j + 1));
				}
			}
			System.out.println(dp[N]);
		}
	}
}
