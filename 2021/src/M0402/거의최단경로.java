package M0402;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 거의최단경로 {
	static int N, M, start, end;
	static List<Edge>[] graph;
	static List<Integer>[] Route;
	static int INF = 987654321;
	static int D[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if(N==0 && M==0) break;
			graph = new List[N + 1];
			Route = new List[N + 1];
			for (int i = 0; i <= N; i++) {
				graph[i] = new ArrayList<>();
				Route[i] = new ArrayList<>();
			}
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());

				graph[a].add(new Edge(b, v));
			}

			dijkstra(start);
			Delete(end);
			dijkstra(start);
			if (D[end] == INF)
				sb.append(-1).append("\n");
			else
				sb.append(D[end]).append("\n");
		}
		System.out.println(sb);
	}

	private static void Delete(int end) {
		if (Route[end].size() == 0)
			return;
		boolean visited[][] = new boolean[N+1][N+1];
		Queue<Integer> q = new LinkedList<>();
		q.add(end);

		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int i = 0; i < Route[cur].size(); i++) {
				int next = Route[cur].get(i);
				if(visited[cur][next]) continue;
				visited[cur][next] = true;
				for (int j = 0; j < graph[next].size(); j++) {

					if (graph[next].get(j).v == cur) {
						graph[next].remove(j);
					}
					q.add(next);
				}
			}
		}

	}

	private static void dijkstra(int start) {
		D = new int[N + 1];
		Arrays.fill(D, INF);
		boolean visited[] = new boolean[N + 1];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0));
		D[start] = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			if(D[cur.v] < cur.w) continue;
			if (!visited[cur.v]) {
				visited[cur.v] = true;
				for (Edge next : graph[cur.v]) {
					if (D[next.v] > D[cur.v] + next.w) {
						Route[next.v].clear();
						Route[next.v].add(cur.v);

						D[next.v] = D[cur.v] + next.w;
						pq.add(new Edge(next.v, D[next.v]));
						
					} else if (D[next.v] == D[cur.v] + next.w) {
						Route[next.v].add(cur.v);
					}
				}
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
