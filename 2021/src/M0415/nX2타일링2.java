package M0415;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class nX2타일링2 {
	static int mod = 10007;
	static int dp[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		if (N == 1) {
			System.out.println(1);
		} else if (N == 2) {
			System.out.println(3);
		} else {
			dp[1] = 1;
			dp[2] = 3;
			for (int i = 3; i <= N; i++) {
				dp[i] = (dp[i - 1] + dp[i - 2]*2)%mod;
			}
			System.out.println(dp[N]);
		}
	}

}
