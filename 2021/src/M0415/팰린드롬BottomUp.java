package M0415;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 팰린드롬BottomUp {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];
		boolean[][] dp = new boolean[N + 1][N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= N; i++) {
			dp[i][i] = true;
		}

		for (int i = 1; i <= N; i++) {
			if (arr[i] == arr[i + 1]) {
				dp[i][i + 1] = true;
			}
		}

		for (int i = 2; i < N; i++) {
			for (int j = 1; j <= N - i; j++) {
				if (arr[j] == arr[j+i] && dp[j + 1][j + i - 1]) {
					dp[j][j + i] = true;
				}
			}
		}

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			if (dp[s][e]) {
				sb.append(1).append("\n");
			} else {
				sb.append(0).append("\n");
			}
		}
		System.out.println(sb);
	}
}
