package M0429;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 카드게임 {
	static int N;
	static int cards[];
	static int dp[][][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			cards = new int[N];
			for (int i = 0; i < N; i++) {
				cards[i] = Integer.parseInt(st.nextToken());
			}

			dp = new int[2][N][N];
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < N; j++) {
					Arrays.fill(dp[i][j], -1);
				}
			}
			sb.append(dfs(0, 0, N - 1)).append("\n");
		}
		System.out.println(sb);
	}

	private static int dfs(int turn, int s, int e) {
		if (dp[turn][s][e] != -1) {
			return dp[turn][s][e];
		}

		if (s == e) {
			if (turn == 0)
				return cards[s];
			else
				return 0;
		}
		dp[turn][s][e] = 0;

		if (turn == 0) { // 내 차례에서 왼쪽과 오른쪽 뽑앗을때 큰거 고름
			dp[turn][s][e] = Math.max(dfs(1, s + 1, e) + cards[s], dfs(1, s, e - 1) + cards[e]);
		} else { // 상대는 최소가 되게
			dp[turn][s][e] = Math.min(dfs(0, s + 1, e), dfs(0, s, e - 1));
		}

		return dp[turn][s][e];
	}
}
