package M0306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class KCMTravel_시간개선 {
	static int N, M, K;
	static List<Edge>[] graph;
	static final int INF = 987654321;
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			graph = new List[N + 1];
			for (int i = 1; i < graph.length; i++) {
				graph[i] = new ArrayList<>();
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				graph[u].add(new Edge(v, c, d));
			}
			ans = INF;
			dijkstra();
			if (ans == INF) {
				System.out.println("Poor KCM");
			} else {
				System.out.println(ans);
			}
		}

	}

	private static void dijkstra() {
		int D[][] = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++)
			Arrays.fill(D[i], INF);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(1, 0, 0));
		D[1][0] = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (cur.v == N) {
				ans = Math.min(ans, cur.d);
				break;
			}

			if (D[cur.v][cur.c] < cur.d) {
				continue;
			}
			D[cur.v][cur.c] = cur.d;

			for (Edge next : graph[cur.v]) {

				int sum = cur.c + next.c;

				if (sum > M)
					continue;

				int sumtime = cur.d + next.d;

				if (D[next.v][sum] > sumtime) {
					D[next.v][sum] = sumtime;
					pq.add(new Edge(next.v, sum, sumtime));
				}

			}
		}
	}

	static class Edge implements Comparable<Edge> {
		int v, c, d;

		public Edge(int v, int c, int d) {
			this.v = v;
			this.c = c;
			this.d = d;
		}

		@Override
		public int compareTo(Edge o) {
			if (this.d == o.d)
				return this.c - o.c;
			return this.d - o.d;
		}

	}
}