package M0427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 돌다리건너기 {
	static int dp[][][];
	static String ring, devil, angel;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ring = br.readLine();
		devil = br.readLine();
		angel = br.readLine();

		dp = new int[2][devil.length()+1][ring.length()+1];

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < devil.length(); j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		int res = dfs(0, 0, 0) + dfs(1, 0, 0);
		System.out.println(res);

	}

	private static int dfs(int o, int r, int idx) {

		if (idx == ring.length()) {
			return 1;
		}

		if (dp[o][r][idx] != -1) {
			return dp[o][r][idx];
		}

		dp[o][r][idx] = 0;
		for (int i = r; i < devil.length(); i++) {
			if (o == 0) {
				if (devil.charAt(i) == ring.charAt(idx)) {
					dp[o][r][idx] += dfs(1 - o, i + 1, idx + 1);
				}
			} else {
				if (angel.charAt(i) == ring.charAt(idx)) {
					dp[o][r][idx] += dfs(1 - o, i + 1, idx + 1);
				}
			}
		}
		return dp[o][r][idx];
	}

}
