package M0426;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 이분그래프 {
	static int V, E;
	static List<Integer>[] graph;
	static int visited[];
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
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


			for (int i = 1; i <= V; i++) {
				if (visited[i] == 0) { // 얘와 연결되어 있는 그래프들에 집합 묶어주기
					DFS(i, 1);
				}
			}
			flag = true;
			for (int i = 1; i <= V; i++) {
				for (Integer child : graph[i]) {
					if (visited[i] == visited[child]) {
						flag = false;
						break;
					}
				}
			}

			if (flag) {
				sb.append("YES").append("\n");
			} else
				sb.append("NO").append("\n");
		}
		System.out.println(sb);
	}

	private static void DFS(int idx, int n) {
		visited[idx] = n;
		for (Integer child : graph[idx]) {
			if (visited[child] == 0)
				DFS(child, 3 - n);
		}

	}
}
