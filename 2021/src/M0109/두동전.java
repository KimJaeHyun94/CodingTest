package M0109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * @see https://velog.io/@hyeon930/BOJ-16197-%EB%91%90-%EB%8F%99%EC%A0%84-Java
 */
public class 두동전 {
	static char[][] map;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int N, M;
	static int fr, fc, sr, sc;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		fr = -1;
		fc = -1; // 첫번째 단추 초기화

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'o') {
					if (fr == -1 && fc == -1) {
						fr = i;
						fc = j;
					} else {
						sr = i;
						sc = j;
					}
				}
			}
		}

		BFS();
		if (ans == Integer.MAX_VALUE || ans==-1) {
			System.out.println(-1);
		} else
			System.out.println(ans);
	}

	private static void BFS() {
		Queue<coin> q = new LinkedList<>();
		q.add(new coin(fr, fc, sr, sc, 0));

		while (!q.isEmpty()) {
			coin temp = q.poll();
			int r1 = temp.r1;
			int c1 = temp.c1;
			int r2 = temp.r2;
			int c2 = temp.c2;
			int dep = temp.d;

			if (dep >= 10) { // 버튼을 10번보다 많이 눌러야 한다면
				ans = -1;
				return;
			}
			for (int d = 0; d < dirs.length; d++) {
				int dr1 = r1 + dirs[d][0];
				int dc1 = c1 + dirs[d][1];
				int dr2 = r2 + dirs[d][0];
				int dc2 = c2 + dirs[d][1];
				
				if (!isOK(dr1, dc1) && !isOK(dr2, dc2)) // 두 동전이 둘 다 밖으로 떨어진다면
					continue;

				if (!isOK(dr1, dc1) || !isOK(dr2, dc2)) { // 둘 중 하나만 떨어진다면
					ans = dep + 1;
					return;
				} else {
					if (map[dr1][dc1] == '#') { // 벽이라면 이동 안함
						dr1 = r1;
						dc1 = c1;
					}
					if (map[dr2][dc2] == '#') {
						dr2 = r2;
						dc2 = c2;
					}
					if ((dr1 == dr2) && (dc1 == dc2))
						continue;

					q.add(new coin(dr1, dc1, dr2, dc2, dep + 1));
				}
			}
		}

	}

	private static boolean isOK(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	static class coin {
		int r1;
		int c1;
		int r2;
		int c2;
		int d;

		public coin(int r1, int c1, int r2, int c2, int d) {
			this.r1 = r1;
			this.c1 = c1;
			this.r2 = r2;
			this.c2 = c2;
			this.d = d;
		}

	}
}
