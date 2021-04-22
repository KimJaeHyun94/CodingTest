package DP_배낭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 동전 {
	static int coins[];
	static int dp[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int t = 0; t < TC; t++) {

			int N = Integer.parseInt(br.readLine());
			coins = new int[N+1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			int M = Integer.parseInt(br.readLine());

			dp = new int[M + 1];
			dp[0] = 1;
			for (int i = 1; i <= N; i++) {
				for (int j = coins[i]; j <= M; j++) {
					dp[j] += dp[j - coins[i]];
				}
			}
			System.out.println(dp[M]);
		}
	}

}
