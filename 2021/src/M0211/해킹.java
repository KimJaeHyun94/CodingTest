package M0211;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 해킹 {
	static int V, N, C;
	static List<Edge>[] graph;
	static int D[];
	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			graph = new List[V + 1];

			for (int i = 1; i <= V; i++) {
				graph[i] = new ArrayList<>();
			}

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				;
				int s = Integer.parseInt(st.nextToken());

				graph[b].add(new Edge(a, s));
			}
			dijkstra();
			int cnt = 0, time = 0;
			for (int i = 1; i <= V; i++) {
				if (D[i] == INF)
					continue;
				cnt++;
				time = Math.max(time, D[i]);
			}
			sb.append(cnt + " ").append(time).append("\n");
		}
		System.out.println(sb);
	}

	public static void dijkstra() {
		PriorityQueue<Edge> q = new PriorityQueue<>();
		D = new int[V + 1];
		Arrays.fill(D, INF);
		q.add(new Edge(C, 0)); // 처음
		D[C] = 0;
		boolean visited[] = new boolean[V + 1];
		while (!q.isEmpty()) {
			Edge temp = q.poll();

			for (Edge next : graph[temp.v]) {
				if (!visited[next.v] && D[next.v] > D[temp.v] + next.weight) {
					D[next.v] = D[temp.v] + next.weight;
					q.add(new Edge(next.v, D[next.v]));
				}
			}
		}
	}

	static class Edge implements Comparable<Edge> {
		int v, weight;

		public Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
	}
}
