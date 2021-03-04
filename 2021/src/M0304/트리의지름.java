package M0304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/**
 * 
 * @author 김재현
 * @See https://moonsbeen.tistory.com/101?category=1184369
 */
public class 트리의지름 {
	static int V;
	static List<Node> graph[];
	static int start = 0;
	static int maxidx = -1, max;
	static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		graph = new List[V + 1];

		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		int root = 0;
		for (int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			if (i == 0)
				root = s; // 루트 찾기
			while (true) {
				int e = Integer.parseInt(st.nextToken());
				if (e == -1)
					break;
				int d = Integer.parseInt(st.nextToken());

				graph[s].add(new Node(e, d));
			}
		}
		visited = new boolean[V + 1];
		DFS(root, 0);
		visited = new boolean[V + 1];
		DFS(maxidx, 0);
		System.out.println(max);
	}

	private static void DFS(int s, int cost) {
		visited[s] = true;

		if (cost > max) {
			max = cost;
			maxidx = s;
		}
		for (Node child : graph[s]) {
			if (!visited[child.e]) {
				DFS(child.e, child.d + cost);
			}
		}
	}

	static class Node {
		int e;
		int d;

		public Node(int e, int d) {
			this.e = e;
			this.d = d;
		}
	}
}
