package M0426;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 이분그래프BFS {
	static int V, E;
	static List<Integer>[] graph;
	static int visited[];
	static boolean flag;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			visited = new int[V + 1];
			graph = new List[V + 1];
			flag = true;
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

			BFS();

		}
		System.out.println(sb);
	}

	private static void BFS() {
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= V; i++) {
			if (visited[i] == 0) {
				q.add(i);
				visited[i] = 1;
			}

			while (!q.isEmpty()) {
				int cur = q.poll();

				for (Integer child : graph[cur]) {
					if (visited[child] == 0) {
						q.add(child);
					}
					if (visited[child] == visited[cur]) {
						sb.append("NO").append("\n");
						return;
					}

					if (visited[cur] == 1 && visited[child] == 0) {
						visited[child] = 2;
					} else if (visited[cur] == 2 && visited[child] == 0) {
						visited[child] = 1;
					}
				}

			}
		}
		sb.append("YES").append("\n");
	}
}
