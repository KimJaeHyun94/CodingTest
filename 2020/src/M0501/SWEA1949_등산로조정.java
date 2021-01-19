package M0501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1949_등산로조정 {
	static int map[][];
	static int N, K;
	static int max, ans;
	static int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			ans = 0; max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, map[i][j]);
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 1; k <= K; k++) {
						map[i][j] -= k; // 모든 원소들에 대해서 K를 빼본다
						for (int a = 0; a < N; a++) {
							for (int b = 0; b < N; b++) {
								if (map[a][b] == max) {	//안깎은 가장 긴 봉우리부터 역으로 찾는다
									dfs(a, b, 1);
								}
							}
						}
						map[i][j] += k; // 진행한 후에 원래대로 되돌린다.
					}
				}
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int i, int j, int cnt) {
		ans = Math.max(ans, cnt);
		for (int d = 0; d < dirs.length; d++) {
			int di = i + dirs[d][0];
			int dj = j + dirs[d][1];
			if (isOK(di, dj)) {
				if (map[di][dj] < map[i][j]) {	//역순으로 찾는다 (큰 거에서 작은거로)
					dfs(di, dj, cnt + 1);
				}
			}
		}
	}

	private static boolean isOK(int di, int dj) {
		return di >= 0 && di < N && dj >= 0 && dj < N;
	}
}
