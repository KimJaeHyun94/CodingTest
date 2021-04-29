package M0429;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class one학년 {
	static int N;
	static int map[];
	static long dp[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}

		dp = new long[N][21];
		dp[0][map[0]] = 1;

		for (int i = 1; i < N - 1; i++) {
			for (int j = 0; j <= 20; j++) {
				if (dp[i - 1][j] != 0) {
					if (j + map[i] <= 20) {
						dp[i][j + map[i]] += dp[i - 1][j];
					}
					if (j - map[i] >= 0) {
						dp[i][j - map[i]] += dp[i - 1][j];
					}
				}
			}
		}
		
		System.out.println(dp[N-2][map[N-1]]);

	}

}
