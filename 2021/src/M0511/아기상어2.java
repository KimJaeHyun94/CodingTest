package M0511;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아기상어2 {

	static int N, M;
	static int map[][];
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };
	static ArrayList<Shark> list;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					list.add(new Shark(i, j, 0));
				}
			}
		}
		for (Shark baby : list) {
			BFS(baby);
		}

		System.out.println(max);
	}

	private static void BFS(Shark shark) {
		Queue<Shark> q = new LinkedList<>();
		q.add(shark);
		boolean visited[][] = new boolean[N][M];
		while (!q.isEmpty()) {
			Shark cur = q.poll();

			if (map[cur.r][cur.c] == 1) {
				max = Math.max(max, cur.dep);
				return;
			}
			for (int d = 0; d < dirs.length; d++) {
				int dr = cur.r + dirs[d][0];
				int dc = cur.c + dirs[d][1];

				if (isOK(dr, dc) && !visited[dr][dc]) {
					visited[dr][dc] = true;
					q.add(new Shark(dr, dc, cur.dep + 1));
				}
			}
		}
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < M;
	}

	static class Shark {
		int r;
		int c;
		int dep;

		public Shark(int r, int c, int dep) {
			this.r = r;
			this.c = c;
			this.dep = dep;
		}

	}
}
