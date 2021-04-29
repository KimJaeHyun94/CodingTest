package M0427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 극장좌석 {
	static int N, M;
	static boolean seat[];
	static int dp[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		seat = new boolean[N + 1];
		dp = new int[N + 1];
		dp[0] = dp[1] = 1;

		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		
		M = Integer.parseInt(br.readLine());
		
		int ans = 1;
		int before = 0;
		
		for (int i = 0; i < M; i++) {
			int n = Integer.parseInt(br.readLine());
			ans*=dp[n-before-1];
			before = n;
		}
		ans*=dp[N-before];
		System.out.println(ans);
	}
}
