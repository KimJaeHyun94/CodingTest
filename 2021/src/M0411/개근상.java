package M0411;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 개근상 {
	static int mod = 1000000;
	static int dp[][][];
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		dp = new int[N + 1][2][3];

		for (int i = 0; i <= N; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 3; k++) {
					dp[i][j][k] = -1;
				}
			}
		}

		System.out.println(DFS(0, 0, 0));

	}

	private static int DFS(int day, int c1, int c2) {
		if (c1 >= 2 || c2 >= 3) {
			return 0;
		}
		if (day == N) {
			return 1;
		}
		if (dp[day][c1][c2] != -1) {
			return dp[day][c1][c2] % mod;
		}

		return dp[day][c1][c2] = (DFS(day + 1, c1, 0) + DFS(day + 1, c1 + 1, 0) + DFS(day + 1, c1, c2 + 1)) % mod;
	}
}
