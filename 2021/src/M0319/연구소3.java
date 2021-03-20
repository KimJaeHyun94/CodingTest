package M0319;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 연구소3 {
	static LinkedList<Virus> virus;
	static Virus active[];
	static int N, M;
	static int map[][];
	static int count = 0;
	static int MIN = Integer.MAX_VALUE;
	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		virus = new LinkedList<>();
		active = new Virus[M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virus.add(new Virus(i, j, false));
				} else if (map[i][j] == 0)
					count++;
			}
		}
		combination(0, 0);

		if (MIN == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else
			System.out.println(MIN);
	}

	private static void combination(int s, int cnt) {
		if (cnt == M) {
			MIN = Math.min(BFS(), MIN);
			return;
		}
		for (int i = s; i < virus.size(); i++) {
			active[cnt] = virus.get(i);
			combination(i + 1, cnt + 1);
		}
	}

	private static int BFS() {
		boolean visited[][] = new boolean[N][N];
		int check = count;
		Queue<Node> q = new LinkedList<>();
		for (Virus virus : active) {
			System.out.println(virus.r+" "+virus.c);
			 
			q.add(new Node(virus.r, virus.c, 0));
			visited[virus.r][virus.c] = true;
		}
		if (check == 0) {
			return 0;
		}
		while (!q.isEmpty()) {
			Node cur = q.poll();

			int r = cur.r;
			int c = cur.c;

			for (int d = 0; d < dirs.length; d++) {
				int dr = r + dirs[d][0];
				int dc = c + dirs[d][1];

				if (isOK(dr, dc) && !visited[dr][dc] && map[dr][dc] != 1) {
					if (map[dr][dc] == 0) {
						q.add(new Node(dr, dc, cur.d + 1));
						check--;
					}
					if (check == 0) {
						return cur.d + 1;
					}

					visited[dr][dc] = true;
					q.add(new Node(dr, dc, cur.d + 1));
				}
			}
		}

		return Integer.MAX_VALUE;
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < N;
	}

	static class Node {
		int r;
		int c;
		int d;

		public Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

	}

	static class Virus {
		int r;
		int c;
		boolean flag;

		public Virus(int r, int c, boolean flag) {
			this.r = r;
			this.c = c;
			this.flag = flag;
		}
	}
}
