package M0501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author 김재현
 * @see https://emoney96.tistory.com/99
 */
public class 출근경로TopDown {
	static int W, H;
	static int dp[][][][];
	static int MOD = 100000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		dp = new int[101][101][2][2];

		for (int i = 1; i <= H; i++) {
			for (int j = 1; j <= W; j++) {
				Arrays.fill(dp[i][j][0], -1);
				Arrays.fill(dp[i][j][1], -1);
			}
		}

		System.out.println((dfs(1, 1, 0, 1) + dfs(1, 1, 1, 1)) % MOD);
	}

	private static int dfs(int x, int y, int t, int chk) {
		if (x <= 0 || y <= 0 || x > H || y > W) // 범위 벗어나면 0
			return 0;

		if (x == H && y == W) {
			return dp[x][y][t][chk] = 1;
		}
		if (dp[x][y][t][chk] != -1) {
			return dp[x][y][t][chk];
		}

		dp[x][y][t][chk] = 0;

		if (t == 1) {
			if (chk == 0) {
				dp[x][y][t][chk] = (dp[x][y][t][chk] + dfs(x, y + 1, t, chk) + dfs(x + 1, y, 1 - t, 1 - chk)) % MOD;
			} else {
				dp[x][y][t][chk] = (dp[x][y][t][chk] + dfs(x, y + 1, t, 1 - chk)) % MOD;
			}
		} else {
			if (chk == 0) {
				dp[x][y][t][chk] = (dp[x][y][t][chk] + dfs(x + 1, y, t, chk) + dfs(x, y + 1, 1 - t, 1 - chk)) % MOD;
			} else {
				dp[x][y][t][chk] = (dp[x][y][t][chk] + dfs(x + 1, y, t, 1 - chk)) % MOD;
			}
		}
		return dp[x][y][t][chk];
	}

}
