package M0315;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미네랄 {
	static int R, C;
	static char map[][];
	static int Route[];
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static ArrayList<Mineral> cluster;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for (int r = 0; r < R; r++) {
			String line = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c);
			}
		}
		int N = Integer.parseInt(br.readLine());
		Route = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			Route[i] = Integer.parseInt(st.nextToken());
		}
		Game();
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				sb.append(map[r][c]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void Game() {
		for (int i = 0; i < Route.length; i++) {
			int h = R - Route[i];
			if (i % 2 == 0) { // 왼쪽
				for (int c = 0; c < C; c++) {
					if (map[h][c] == 'x') {
						map[h][c] = '.';
						break;
					}
				}
			} else { // 오른쪽
				for (int c = C - 1; c >= 0; c--) {
					if (map[h][c] == 'x') { // 미네랄을 만나면 부신다
						map[h][c] = '.';
						break;
					}
				}
			}
			find();// 공중에 떠있는 클러스터 찾기
			if (cluster.size() != 0) {
				Down();
			}
		}

	}

	private static void find() { // 공중에 떠있는 미네랄 찾기
		Queue<Mineral> q = new LinkedList<>();
		boolean visited[][] = new boolean[R][C];
		cluster = new ArrayList<>();

		for (int i = 0; i < C; i++) {
			if (map[R - 1][i] == 'x') { // 맨 밑에 미네랄은 건드리지 말기
				visited[R - 1][i] = true;
				q.add(new Mineral(R - 1, i));
			}
		}
		while (!q.isEmpty()) {
			Mineral cur = q.poll();
			for (int d = 0; d < dirs.length; d++) {
				int dr = cur.r + dirs[d][0];
				int dc = cur.c + dirs[d][1];

				if (isOK(dr, dc) && map[dr][dc] == 'x' && !visited[dr][dc]) { // 만약에 붙어있는 얼음이라면

					visited[dr][dc] = true; // 방문체크 해둔다.
					q.add(new Mineral(dr, dc));
				}
			}
		}
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (!visited[r][c] && map[r][c] == 'x') {
					map[r][c] = '.';
					cluster.add(new Mineral(r, c)); // 방문체크 되지 않은 미네랄들을 넣는다.
				}
			}
		}
	}

	private static void Down() {

		int cnt = 0;

		outer: while(true) {
			for (Mineral node : cluster) {
				if (node.r + cnt >= R || map[node.r + cnt][node.c] == 'x') {
					cnt--;
					break outer;
				}
			}
			cnt++;
		}

		for (Mineral node : cluster) {
			map[node.r+cnt][node.c] = 'x';
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < R && dc >= 0 && dc < C;
	}

	static class Mineral {
		int r;
		int c;

		public Mineral(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
