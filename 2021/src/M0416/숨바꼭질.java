package M0416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 숨바꼭질 {
	static List<Integer> graph[];
	static int N, M;
	static int dist = Integer.MIN_VALUE;
	static int cnt = 0;
	static int ans = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new List[N + 1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			graph[s].add(e);
			graph[e].add(s);
		}

		BFS(1);
		System.out.println(ans + " " + dist + " " + cnt);
	}

	private static void BFS(int s) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(s, 0));
		boolean visited[] = new boolean[N + 1];
		visited[s] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (cur.d > dist) {
				dist = cur.d;
				ans = cur.a;
				cnt = 1;
			} else if (cur.d == dist) {
				cnt++;
				if (ans > cur.a) {
					ans = cur.a;
				}
			}
			for (Integer child : graph[cur.a]) {
				if (!visited[child]) {
					visited[child] = true;
					q.add(new Node(child, cur.d + 1));
				}
			}
		}

	}

	static class Node {
		int a;
		int d;

		public Node(int a, int d) {
			super();
			this.a = a;
			this.d = d;
		}

	}
}
