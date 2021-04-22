package DP_배낭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 벼락치기 {
	static int N, T;
	static int time[], score[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		time = new int[N + 1];
		score = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			score[i] = Integer.parseInt(st.nextToken());
		}

		int dp[][] = new int[N+1][T + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= T; j++) {
				dp[i][j] = dp[i-1][j];
				if(j>=time[i]) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-time[i]]+score[i]);
				}
			}
		}
		System.out.println(dp[N][T]);

	}

}
