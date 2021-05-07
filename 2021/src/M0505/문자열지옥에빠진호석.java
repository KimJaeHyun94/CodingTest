package M0505;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 문자열지옥에빠진호석 {
	static int N, M, K;
	static char map[][];
	static String strarr[];
	static int dirs[][] = { { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 }, { -1, -1 }, { 1, -1 }, { -1, 1 }, { 1, 1 } };
	static HashMap<String, Integer> hmap = new HashMap<>();
	static int len;
	static String sts;
	static int dp[][][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new char[N][M];

		strarr = new String[K];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < K; i++) {
			dp = new int[N][M][K];
			for (int j = 0; j < N; j++) {
				for (int j2 = 0; j2 < M; j2++) {
					Arrays.fill(dp[j][j2], -1);
				}
			}
			sts = br.readLine();
			len = sts.length();
			int cnt = 0;
			for (int k = 0; k < N; k++) {
				for (int j = 0; j < M; j++) {
					if (map[k][j] == sts.charAt(0)) {
						cnt += dfs(k, j, 0);
					}
				}
			}
			sb.append(cnt).append("\n");
		}

		System.out.println(sb);
	}

	private static int dfs(int r, int c, int idx) {
		if (idx == len - 1) {
			return 1;
		}

		if (dp[r][c][idx] != -1)
			return dp[r][c][idx];
		dp[r][c][idx] = 0;
		for (int d = 0; d < dirs.length; d++) {
			int dr = r + dirs[d][0];
			int dc = c + dirs[d][1];

			if (dr < 0) {
				dr = N - 1;
			} else if (dr >= N) {
				dr = 0;
			}

			if (dc < 0) {
				dc = M - 1;
			} else if (dc >= M) {
				dc = 0;
			}

			if (map[dr][dc] == sts.charAt(idx + 1)) {
				dp[r][c][idx] += dfs(dr, dc, idx + 1);
			}
		}
		return dp[r][c][idx];
	}

}
