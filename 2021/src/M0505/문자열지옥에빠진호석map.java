package M0505;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 문자열지옥에빠진호석map {
	static int N, M, K;
	static char map[][];
	static String strarr[];
	static int dirs[][] = { { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 }, { -1, -1 }, { 1, -1 }, { -1, 1 }, { 1, 1 } };
	static HashMap<String, Integer> hmap = new HashMap<>();
	static int max;
	static String sts;

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
		max = Integer.MIN_VALUE;
		for (int i = 0; i < K; i++) {
			String line = br.readLine();
			max = Math.max(max, line.length());
			strarr[i] = line;
			hmap.put(line, 0);
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dfs(i, j, 0, Character.toString(map[i][j]));
			}
		}

		StringBuilder sb = new StringBuilder();
		for (String str : strarr) {
			sb.append(hmap.get(str)).append("\n");
			
		}
		System.out.println(sb);
	}

	private static void dfs(int r, int c, int de, String str) {
		if (hmap.containsKey(str)) {
			hmap.put(str, hmap.get(str) + 1);
		}
		if (de == max - 1) {
			return;
		}
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

			dfs(dr, dc, de + 1, str + map[dr][dc]);

		}

	}

}
