package M0515;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 비밀모임_다익스트라 {
	static int N, M;
	static int INF = 987654321;
	static List<Edge> graph[];
	static int ans[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			graph = new List[N + 1];
			ans = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				graph[a].add(new Edge(b, c));
				graph[b].add(new Edge(a, c));
			}

			int K = Integer.parseInt(br.readLine());

			ArrayList<Integer> list = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}

			for (int i = 0; i < K; i++) {
				dijkstra(list.get(i));
			}

			int max = INF, idx = 1;
			for (int i = 1; i <= N; i++) {
				if (max > ans[i]) {
					max = ans[i];
					idx = i;
				}
			}
			sb.append(idx).append("\n");
		}
		System.out.println(sb);
	}

	private static void dijkstra(int start) {
		int D[] = new int[N + 1];
		Arrays.fill(D, INF);
		D[start] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue();
		pq.add(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			for (Edge next : graph[cur.v]) {
				if (D[next.v] > D[cur.v] + next.w) {
					D[next.v] = D[cur.v] + next.w;
					pq.add(new Edge(next.v, D[next.v]));
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			ans[i] += D[i];
		}
	}

	static class Edge implements Comparable<Edge> {
		int v, w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
}
