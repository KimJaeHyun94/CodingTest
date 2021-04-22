package M0416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Gaaaaaaaaaarden {
	static int N, M, G, R;
	static int map[][];
	static int dirs[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static ArrayList<Node> list = new ArrayList<>();
	static ArrayList<Node> Green = new ArrayList<>();
	static ArrayList<Node> Red = new ArrayList<>();

	static boolean visited[];
	static int ans = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken()); // 초록 배양액
		R = Integer.parseInt(st.nextToken()); // 빨강 배양액

		map = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 2) {
					list.add(new Node(r, c));
				}
			}
		}
		visited = new boolean[list.size()];
		DFS(0, 0);
		System.out.println(ans);
	}

	private static void DFS(int cnt, int idx) {
		if (cnt == G) {
			Green.clear();
			for (int i = 0; i < list.size(); i++) {
				if (visited[i]) {
					Green.add(list.get(i));
				}
			}
			DFS2(0, 0);
			return;
		}

		for (int i = idx; i < list.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				DFS(cnt + 1, i + 1);
				visited[i] = false;
			}
		}
	}

	private static void DFS2(int cnt, int idx) {
		if (cnt == R) {
			Red.clear();
			for (int i = 0; i < list.size(); i++) {
				if (visited[i] && !Green.contains(list.get(i))) {
					Red.add(list.get(i));
				}
			}
			BFS();
			return;
		}
		for (int i = idx; i < list.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				DFS2(cnt + 1, i + 1);
				visited[i] = false;
			}
		}

	}

	private static void BFS() {
		boolean green_visited[][] = new boolean[N][M];
		int green_time[][] = new int[N][M];
		boolean red_visited[][] = new boolean[N][M];
		int red_time[][] = new int[N][M];
		int copymap[][] = Copy();

		Queue<Node> Gq = new LinkedList<>();
		Queue<Node> Rq = new LinkedList<>();
		
		for (Node child : Red) {
			Rq.add(child);
			red_visited[child.r][child.c] = true;
		}

		for (Node child : Green) {
			Gq.add(child);
			green_visited[child.r][child.c] = true;
		}

	
		int flower = 0;

		while (!Rq.isEmpty() && !Gq.isEmpty()) {
			if (!Gq.isEmpty()) {
				int gsize = Gq.size();

				while (gsize-- != 0) {
					Node cur = Gq.poll();
					green_visited[cur.r][cur.c] = true;
					if (copymap[cur.r][cur.c] == 3)
						continue;

					for (int d = 0; d < dirs.length; d++) {
						int dr = cur.r + dirs[d][0];
						int dc = cur.c + dirs[d][1];

						if (isOK(dr, dc) && (copymap[dr][dc] == 1 || copymap[dr][dc] == 2)
								&& !green_visited[dr][dc]) {
							
							green_visited[dr][dc] = true;
							green_time[dr][dc] = green_time[cur.r][cur.c] + 1;
							Gq.add(new Node(dr, dc));
						}
					}
				}
			}
			if (!Rq.isEmpty()) {
				int rsize = Rq.size();

				while (rsize-- != 0) {
					Node cur = Rq.poll();
					red_visited[cur.r][cur.c] = true;
					if (copymap[cur.r][cur.c] == 3)
						continue;

					for (int d = 0; d < dirs.length; d++) {
						int dr = cur.r + dirs[d][0];
						int dc = cur.c + dirs[d][1];

						if (isOK(dr, dc) && (copymap[dr][dc] == 1 || copymap[dr][dc] == 2)
								&& !red_visited[dr][dc]) {
							
							red_visited[dr][dc] = true;
							red_time[dr][dc] = red_time[cur.r][cur.c] + 1;

							if (red_time[dr][dc] == green_time[dr][dc]) {
								flower++;
								copymap[dr][dc] = 3;
							} else
								Rq.add(new Node(dr, dc));
						}
					}
				}
			}
		}
		ans = Math.max(ans, flower);
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < M;
	}

	private static int[][] Copy() {
		int copy[][] = new int[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				copy[r][c] = map[r][c];
			}
		}
		return copy;
	}

	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
