package M0330;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14501퇴사_DP {
	static int N;
	static int T[];
	static int P[];
	static int dp[];
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		T = new int[N + 10];
		P = new int[N + 10];
		dp = new int[N + 10];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= N+1; i++) {
			dp[i] = Math.max(dp[i], max);
			dp[T[i] + i] = Math.max(dp[T[i]+i], P[i] + dp[i]);
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}

}
