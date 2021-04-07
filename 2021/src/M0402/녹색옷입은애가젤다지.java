package M0402;

import java.io.BufferedReader
;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 녹색옷입은애가젤다지 {
	static int N;
	static int route[][];
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int INF = 987654321;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int idx = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			route = new int[N][N];

			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					route[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append("Problem ").append(idx++).append(": ").append(BFS()).append("\n");
		}
		System.out.println(sb);
	}

	private static Object BFS() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int D[][] = new int[N][N];
		boolean visited[][] = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				D[i][j] = INF;
			}
		}

		D[0][0] = route[0][0];
		pq.add(new Edge(0, 0, D[0][0]));
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(cur.r==N-1 && cur.c==N-1) {
				return cur.w;
			}
			if (!visited[cur.r][cur.c]) {
				visited[cur.r][cur.c] = true;

				for (int d = 0; d < dirs.length; d++) {
					int dr = cur.r + dirs[d][0];
					int dc = cur.c + dirs[d][1];

					if (isOK(dr, dc) && !visited[dr][dc]) {
						if (D[dr][dc] > D[cur.r][cur.c] + route[dr][dc]) {
							D[dr][dc] = D[cur.r][cur.c] + route[dr][dc];
							pq.add(new Edge(dr, dc, D[dr][dc]));
						}
					}
				}
			}
		}
		return D[N - 1][N - 1];
	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < N && dc >= 0 && dc < N;
	}

	static class Edge implements Comparable<Edge> {
		int r;
		int c;
		int w;

		public Edge(int r, int c, int w) {
			this.r = r;
			this.c = c;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
}
