package M0426;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 결혼식 {
	static int N;
	static List<Integer>[] graph;
	static int ans = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		int m = Integer.parseInt(br.readLine());

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			graph[b].add(a);
		}

		BFS();
		System.out.println(ans);
	}

	private static void BFS() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(1, 0));
		boolean visited[] = new boolean[N + 1];
		visited[1] = true;
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (cur.d == 1 || cur.d == 2) {
				ans++;
			} else if (cur.d > 2)
				return;

			for (Integer child : graph[cur.s]) {
				if (!visited[child]) {
					q.add(new Node(child, cur.d + 1));
					visited[child]= true;
				}
			}

		}
		return;
	}

	static class Node {
		int s;
		int d;

		public Node(int s, int d) {
			this.s = s;
			this.d = d;
		}

	}
}
