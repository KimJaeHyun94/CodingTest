package 소수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 약수의합 {
	static int MAX = 1000000;
	static long cnt[];
	static long dp[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		cnt = new long[MAX + 1];
		dp = new long[MAX + 1];
		Arrays.fill(dp, 1);
		for (int i = 2; i <= MAX; i++) {
			for (int j = 1; i * j <= MAX; j++) {
				cnt[i * j] += i;
			}
		}

		for (int i = 2; i <= MAX; i++) {
			dp[i] += dp[i - 1] + cnt[i];
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			sb.append(dp[N]).append("\n");
		}
		System.out.println(sb);
	}
}
