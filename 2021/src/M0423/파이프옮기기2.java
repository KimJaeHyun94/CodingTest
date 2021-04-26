package M0423;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파이프옮기기2 {
	static int map[][];
	static long dp[][][];
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new long[N][N][3];

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		dp[0][1][0] = 1;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (isOK(i, j - 1) && isOK(i, j)) {
					dp[i][j][0] += dp[i][j - 1][0] + dp[i][j - 1][2];
				}

				if (isOK(i - 1, j) && isOK(i, j)) {
					dp[i][j][1] += dp[i - 1][j][1] + dp[i - 1][j][2];
				}

				if (isOK(i - 1, j - 1) && isOK(i, j) && isOK(i, j - 1) && isOK(i - 1, j)) {
					dp[i][j][2] += dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
				}

			}
		}

		System.out.println(dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2]);
	}

	private static boolean isOK(int ni, int nj) {
		return ni >= 0 && ni < N && nj >= 0 && nj < N && map[ni][nj] == 0;
	}
}
