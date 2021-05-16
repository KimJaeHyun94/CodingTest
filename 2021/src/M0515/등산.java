package M0515;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 등산 {
	static int dist[][];
	static int INF = 987654321;
	static int N, M, T, D;
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int map[][];
	static int MAX;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dist = new int[666][666];
		MAX = N*M;
		for (int i = 0; i < MAX; i++) {
			for (int j = 0; j < MAX; j++) {
				if (i == j)
					continue;
				dist[i][j] = INF;
			}
		}

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				char ch = line.charAt(j);
				if (ch <= 'Z' && ch >= 'A') {
					map[i][j] = ch - 'A';
				} else {
					map[i][j] = ch - 'a' + 26;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int d = 0; d < dirs.length; d++) {
					int di = i + dirs[d][0];
					int dj = j + dirs[d][1];

					if (!isOK(di, dj))
						continue;
					
					int here = i * M + j;
					int next = di * M + dj;
					
					int diff = map[i][j] - map[di][dj];

					if (Math.abs(diff) > T)
						continue;
					
					else if (diff < 0) { // 오르막
						dist[here][next] = diff * diff;
					} else {
						dist[here][next] = 1;
					}

				}
			}
		}
		FloydWarshall();

		int ans = map[0][0];
		for (int i = 0; i < MAX; i++) {
			if (dist[0][i] + dist[i][0] <= D) {
				ans = Math.max(ans, map[i / M][i % M]);
			}
		}
		System.out.println(ans);
	}

	private static boolean isOK(int di, int dj) {
		return di >= 0 && di < N && dj >= 0 && dj < M;
	}

	private static void FloydWarshall() {
		for (int k = 0; k < MAX; k++) {
			for (int i = 0; i < MAX; i++) {
				for (int j = 0; j < MAX; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
	}
}