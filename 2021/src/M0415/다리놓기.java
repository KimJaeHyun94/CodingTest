package M0415;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 다리놓기 {
	static int[][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());

			if (left == right)
				System.out.println(1);
			else {
				dp = new int[right + 1][left + 1];
				System.out.println(sol(right, left));
			}
		}
	}

	private static int sol(int n, int k) {
		if (dp[n][k] > 0) {
			return dp[n][k];
		}

		if (k == 0 || n == k) {
			return dp[n][k] = 1;
		}

		return dp[n][k] = sol(n - 1, k - 1) + sol(n - 1, k);
	}
}
