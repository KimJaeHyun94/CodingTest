package M0427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 돌다리건너기bottomup {
	static int dp[][][];
	static String ring, devil, angel;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ring = br.readLine();
		devil = br.readLine();
		angel = br.readLine();

		dp = new int[2][devil.length() + 1][ring.length() + 1];

		for (int k = 0; k < devil.length(); k++) {
			if (devil.charAt(k) == ring.charAt(0)) {
				dp[0][k][0] = 1;
			}
		}

		for (int k = 0; k < angel.length(); k++) {
			if (angel.charAt(k) == ring.charAt(0))
				dp[1][k][0] = 1;
		}

		for (int i = 1; i < ring.length(); i++) {
			for (int j = 0; j <= 1; j++) {
				for (int k = 0; k < angel.length(); k++) {
					if (j == 0) {
						if (devil.charAt(k) == ring.charAt(i)) {
							int cnt = 0;
							for (int l = 0; l < k; l++) {
								cnt += dp[1 - j][l][i - 1];
							}
							dp[j][k][i] = cnt;
						}
					} else {
						if (angel.charAt(k) == ring.charAt(i)) {
							int cnt = 0;
							for (int l = 0; l < k; l++) {
								cnt += dp[1 - j][l][i - 1];
							}
							dp[j][k][i] = cnt;
						}
					}
				}
			}
		}
		int ans = 0;
		for (int i = 0; i <= 1; i++) {
			for (int j = 0; j < devil.length(); j++) {
				ans += dp[i][j][ring.length()-1];
			}
		}
		System.out.println(ans);

	}

}
