package M0306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 파도반수열 {
	static long dp[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());

			if (N == 1) {
				System.out.println(1);
			} else if (N == 2) {
				System.out.println(1);
			} else if (N == 3) {
				System.out.println(1);
			} else {
				dp = new long[N + 1];
				dp[1] = 1;
				dp[2] = 1;
				dp[3] = 1;
				for (int i = 4; i <= N; i++) {
					dp[i] = dp[i - 2] + dp[i - 3];
				}
				System.out.println(dp[N]);
			}
		}
	}

}
