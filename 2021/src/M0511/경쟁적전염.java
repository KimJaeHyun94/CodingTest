package M0511;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 경쟁적전염 {
	static int N, K;
	static int map[][];
	static int S, X, Y;
	static Queue<Virus> q;
	static boolean visited[][];
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static List<Virus> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		q = new LinkedList<>();
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) {
					list.add(new Virus(i, j, map[i][j]));
					visited[i][j] = true;
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		BFS();
	}

	private static void BFS() {
		int time = 0;

		Collections.sort(list, new Comparator<Virus>() {

			@Override
			public int compare(Virus o1, Virus o2) {
				return o1.n - o2.n;
			}
		});
		
		for (Virus v : list) {
			q.add(new Virus(v.r,v.c,v.n));
		}
		while (!q.isEmpty() && time < S) {

			int size = q.size();
			for (int i = 0; i < size; i++) {
				Virus cur = q.poll();

				for (int d = 0; d < dirs.length; d++) {
					int dr = cur.r + dirs[d][0];
					int dc = cur.c + dirs[d][1];

					if (isOK(dr, dc) && !visited[dr][dc] && map[dr][dc] == 0) {
						q.add(new Virus(dr, dc, cur.n));
						visited[dr][dc] = true;
						map[dr][dc] = cur.n;
					}
				}
			}
			time++;
		}
		System.out.println(map[X - 1][Y - 1]);
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < N;
	}

	static class Virus {
		int r;
		int c;
		int n;

		public Virus(int r, int c, int n) {
			this.r = r;
			this.c = c;
			this.n = n;
		}
	}
}
