package M0303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 최소비용구하기2 {
	static int V, E;
	static List<Edge> graph[];
	static int Route[];
	static final int INF = 987654321;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());

		graph = new List[V + 1];
		Route = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph[a].add(new Edge(b, w));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		int cost = Dijkstra(start, end);

		Stack<Integer> stack = new Stack<>();
		int idx = end;
		while (true) {
			if (idx == start) {
				stack.push(start);
				break;
			} else {
				stack.push(idx);
				idx = Route[idx];
			}
		}

		System.out.println(cost);
		System.out.println(stack.size());
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");

		}
	}

	private static int Dijkstra(int start, int end) {
		int D[] = new int[V + 1];
		boolean[] visited = new boolean[V + 1];
		Arrays.fill(D, INF);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0));
		D[start] = 0;
		while (!pq.isEmpty()) {
			Edge temp = pq.poll();

			if (!visited[temp.v]) {
				visited[temp.v] = true;

				for (Edge next : graph[temp.v]) {
					if (!visited[next.v] && D[next.v] > D[temp.v] + next.w) {
						D[next.v] = D[temp.v] + next.w;
						Route[next.v] = temp.v;
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
