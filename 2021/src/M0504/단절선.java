package M0504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 단절선 {
	static int V, E;
	static int visited[];
	static ArrayList<Node> ans;
	static int cnt = 1;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		List<Integer> graph[] = new List[V + 1];
		visited = new int[V + 1];
		ans = new ArrayList<>();

		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}

		for (int i = 1; i <= V; i++) {
			if (visited[i] == 0) {
				dfs(i, 0, graph);
			}
		}

		System.out.println(ans.size());
		Collections.sort(ans);
		StringBuilder sb = new StringBuilder();
		for (Node child : ans) {
			sb.append(child.x).append(" ").append(child.y).append(" ").append("\n");
		}
		System.out.println(sb);
	}

	private static int dfs(int v, int p, List<Integer>[] graph) {
		visited[v] = cnt++;
		int re = visited[v];

		for (Integer child : graph[v]) {
			if (child == p) { // 내가 왔던 길은 제외
				continue;
			}

			if (visited[child] == 0) { // 방문 되지 않은 경우에
				int low = dfs(child, v, graph);

				if (low > visited[v]) { // 단절선이다 (방문을 더 많이 한다면)
					if (child > v) {
						ans.add(new Node(v, child));
					} else
						ans.add(new Node(child, v));
				}
				re = Math.min(re, low);
			} else {
				re = Math.min(re, visited[child]);
			}
		}
		return re;
	}

	static class Node implements Comparable<Node> {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Node o) {
			if (o.x == this.x) {
				return this.y - o.y;
			}
			return this.x - o.x;
		}
	}
}
