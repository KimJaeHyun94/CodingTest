package M0427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 줄어들지않아 {
	static long dp[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		dp = new long[65][10];
		for (int i = 0; i <=9; i++) {
			dp[1][i] = 1;
		}
		for (int i = 2; i < 65; i++) {
			dp[i][0] = 1;
			long k = 0;
			for (int j = 0; j < 10; j++) {
				dp[i][j] = dp[i - 1][j] + k;
				k += dp[i - 1][j];
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());

			long sum = 0;
			for (int i = 0; i < 10; i++) {
				sum += dp[N][i];
			}
			sb.append(sum).append("\n");
		}
		System.out.println(sb);

	}

}
