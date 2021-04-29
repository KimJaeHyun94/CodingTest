package M0428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCS3 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str1 = br.readLine();
		String str2 = br.readLine();
		String str3 = br.readLine();

		int N = str1.length();
		int M = str2.length();
		int K = str3.length();

		int dp[][][] = new int[N + 1][M + 1][K + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				for (int k = 1; k <= K; k++) {
					if (str1.charAt(i - 1) == str2.charAt(j - 1) && str2.charAt(j - 1) == str3.charAt(k - 1)) {
						dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
					} else {
						dp[i][j][k] = Math.max(dp[i - 1][j][k], Math.max(dp[i][j - 1][k], dp[i][j][k - 1]));
					}
				}
			}
		}

		System.out.println(dp[N][M][K]);

	}

}
