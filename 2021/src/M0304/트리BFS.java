package M0304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 트리BFS {
	static int N, M;
	static List<Integer> graph[];
	static boolean visited[];
	static boolean cycle;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int t = 1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			if (N == 0 && M == 0) {
				break;
			}

			graph = new List[N + 1];

			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				graph[a].add(b);
				graph[b].add(a);

			}
			cycle = false;
			int cnt = 0; // 트리의 개수
			visited = new boolean[N + 1];

			for (int i = 1; i <= N; i++) {
				if (!visited[i]) {
					if (BFS(i))
						cnt++;
				}
			}
			sb.append("Case ").append(t++).append(": ");
			if (cnt == 0) {
				sb.append("No trees.").append("\n");
			} else if (cnt == 1) {
				sb.append("There is one tree.").append("\n");
			} else {
				sb.append("A forest of ").append(cnt).append(" trees.").append("\n");
			}
		}
		System.out.println(sb);
	}

	static boolean BFS(int start) {
		visited[start] = true;
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		int Node = 0, Edge = 0;
		while (!q.isEmpty()) {
			Integer temp = q.poll();
			Node++;
			for (Integer child : graph[temp]) {
				if (child != temp) {
					Edge++;
					if (!visited[child]) {
						q.add(child);
						visited[child] = true;
					}
				}
			}
		}
		if (Edge / 2 + 1 == Node) {
			return true;
		} else
			return false;
	}
}
