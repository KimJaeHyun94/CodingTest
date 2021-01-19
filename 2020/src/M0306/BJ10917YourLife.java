package M0306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ10917YourLife {
	static List<Integer> graph[];
	static boolean visited[];
	static int N, M;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new List[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
		}
		BFS();
		System.out.println(-1);

	}

	private static void BFS() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(1, 0));
		visited[1] = true;
		while (!q.isEmpty()) {
			Node temp = q.poll();
			if (temp.a == N) {
				System.out.println(temp.d);
				System.exit(0);
			}
			for (Integer child : graph[temp.a]) {
				if (!visited[child]) {
					q.add(new Node(child, temp.d + 1));
					visited[child]=true;
				}
			}
		}
	}

	static class Node {
		int a;
		int d;

		public Node(int a, int d) {
			this.a = a;
			this.d = d;
		}
	}
}
