package M0402;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class K번째최단경로찾기 {
	static int N, M, K;
	static List<Edge>[] graph;
	static int INF = 987654321;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		graph = new List[N + 1];

		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			graph[a].add(new Edge(b, v));
		}
		dijkstra(1);

	}

	private static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		PriorityQueue<Integer>[] Route = new PriorityQueue[N + 1];
		for (int i = 0; i < N + 1; i++) {
			Route[i] = new PriorityQueue<Integer>(K, new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return Integer.compare(o2, o1);
				}

			});
		}
		pq.add(new Edge(start, 0));
		Route[1].add(0);

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			for (Edge next : graph[cur.v]) {
				if (Route[next.v].size() < K) {
					Route[next.v].add((cur.w + next.w));
				} else if (Route[next.v].peek() > cur.w + next.w) {
					Route[next.v].poll();
					Route[next.v].add((cur.w + next.w));
				} else
					continue;

				pq.add(new Edge(next.v, cur.w + next.w));
			}
		}
		for (int i = 1; i <= N; i++) {
			if (Route[i].size() == K) {
				System.out.println(Route[i].peek());
			} else {
				System.out.println(-1);
			}
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
