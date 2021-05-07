package M0429;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 카드게임easy {
	static int left[], right[], dp[][], N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		left = new int[N];
		right = new int[N];
		dp = new int[N][N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			left[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
			right[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(dfs(0, 0));

	}

	private static int dfs(int i, int j) {
		if (i == N || j == N) { // 어느 한쪽이건 다 썻을경우
			return 0;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		dp[i][j] = 0;
		if (left[i] > right[j]) {
			dp[i][j] = Math.max(dp[i][j], dfs(i, j + 1) + right[j]); // 오른쪽 카드만 통에 버릴수 잇는 경우
		}
		dp[i][j] = Math.max(dp[i][j], Math.max(dfs(i + 1, j + 1), dfs(i + 1, j)));

		return dp[i][j];
	}
}
