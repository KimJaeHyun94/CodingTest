package M0228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 미확인도착지 {
	static int N, M, T, S, G, H;
	static List<Edge>[] graph;
	static final int INF = 987654321;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			T = Integer.parseInt(st.nextToken());
			graph = new List[N + 1];
			for (int i = 1; i < graph.length; i++) {
				graph[i] = new ArrayList<>();
			}
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			G = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());

				graph[a].add(new Edge(b, w));
				graph[b].add(new Edge(a, w));
			}
			int dest[] = new int[T];

			for (int i = 0; i < T; i++) {
				dest[i] = Integer.parseInt(br.readLine()); // 도착지들 저장 배열
			}
			Arrays.sort(dest);

			for (int de : dest) {
				int route1 = dijkstra(S, G) + dijkstra(G, H) + dijkstra(H, de);// s -> g- > h - >d
				int route2 = dijkstra(S, H) + dijkstra(H, G) + dijkstra(G, de);// s - >h-> g - > d
				int route3 = dijkstra(S, de);// s -> d

				int route = Math.min(route1, route2);
				if (route <= route3) {
					sb.append(de).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static int dijkstra(int start, int end) {
		boolean[] visited = new boolean[N + 1];
		int[] D = new int[N + 1];
		Arrays.fill(D, INF);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0));
		D[start] = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (!visited[cur.v]) {
				visited[cur.v] = true;

				for (Edge next : graph[cur.v]) { // 그 정점에 모든 다른 정점에서
					if (!visited[next.v] && D[next.v] > D[cur.v] + next.w) {
						D[next.v] = D[cur.v] + next.w;
						pq.add(new Edge(next.v, D[next.v]));
					}
				}
			}
		}
		return D[end];
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
