package DP_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 동전바꿔주기topdown {
	static int dp[][];
	static Coin coin[];
	static int k;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int bill = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		coin = new Coin[k + 1];

		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			coin[i] = new Coin(p, c);
		}

		dp = new int[k + 1][bill + 1];
		for (int i = 0; i < k; i++) {
			Arrays.fill(dp[i], -1);
		}

		System.out.println(dfs(bill, 0));

	}

	private static int dfs(int bill, int cnt) {
		if (bill == 0)
			return 1;
		if (cnt >= k) {
			return 0;
		}
		if (dp[cnt][bill] != -1) {
			return dp[cnt][bill];
		}

		dp[cnt][bill] = 0;
		
		Coin cur = coin[cnt];
		int p = cur.p;
		int c = cur.c;
		for (int i = 0; i <= c; i++) {
			if (bill >= p * i)
				dp[cnt][bill] += dfs(bill - p * i, cnt + 1);

		}
		return dp[cnt][bill];
	}

	static class Coin {
		int p;
		int c;

		public Coin(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}
}
