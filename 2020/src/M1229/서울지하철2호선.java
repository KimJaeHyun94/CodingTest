package M1229;

import java.util.*;
import java.io.*;

public class 서울지하철2호선 {
	static List<Integer>[] graph;
	static int V;
	static boolean cycle[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		graph = new List[V + 1];
		cycle = new boolean[V+1];
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			graph[b].add(a);
		}
		for (int i = 1; i <= V; i++) {
			if (isCycle(i, i, i))
				break;

			else
				cycle = new boolean[V + 1];
		}
		for (int i = 1; i <= V; i++) {
			int cnt = 0;
			if (!cycle[i]) {
				cnt = BFS(i);
			}
			System.out.print(cnt + " ");
		}

	}

	private static int BFS(int i) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(i, 0));
		boolean[] visited = new boolean[V + 1];
		while (!q.isEmpty()) {
			Node temp = q.poll();
			int v = temp.v;
			int cnt = temp.cnt;
			  if(cycle[v])
	                return cnt;
			for (int e : graph[v]) {
				if (!visited[e]) {
					visited[e] = true;
					q.add(new Node(e, cnt + 1));
				}
			}
		}

		return 0;
	}

	private static boolean isCycle(int before, int v, int start) {
		cycle[v] = true;

		for (int i = 0; i < graph[v].size(); i++) {
			int e = graph[v].get(i);

			if (!cycle[e]) {
				if (isCycle(v, e, start))
					return true;
			}

			else if (e != before && e == start)
				return true;
		}
		cycle[v] = false;

		return false;
	}

	static class Node {
		int v;
		int cnt;

		public Node(int v, int cnt) {
			this.v = v;
			this.cnt = cnt;
		}

	}
}
