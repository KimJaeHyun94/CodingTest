package M0304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 트리의지름1967 {
	static int N;
	static List<Node> graph[];
	static boolean visited[];
	static int maxidx = 0, max = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new List[N + 1];

		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			graph[s].add(new Node(e, d));
			graph[e].add(new Node(s, d));
		}

		visited = new boolean[N + 1];
		DFS(1, 0);
		visited = new boolean[N + 1];
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
				DFS(child.e, child.c + cost);
			}
		}

	}

	static class Node {
		int e; // 자식
		int c; // 가중치

		public Node(int e, int c) {
			this.e = e;
			this.c = c;
		}

	}
}
