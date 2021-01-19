package M0303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ14496그대그머가되어 {
	static int N;
	static List<Integer> graph[];
	static int a, b;
	static int min = Integer.MAX_VALUE;
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		graph = new List[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			graph[c].add(d);
			graph[d].add(c);
		}
		BFS(a);
		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);
	}

	private static void BFS(int start) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(start, 0));
		while (!queue.isEmpty()) {
			Node temp = queue.poll();
			int n = temp.n;
			int d = temp.d;
			if (n == b) {
				min = Math.min(min, d);
			}

			List<Integer> childs = graph[n];
			for (Integer child : childs) {
				if (!visited[child]) {
					queue.add(new Node(child, d + 1));
					visited[child]=true;
				}
			}
		}
	}

	static class Node {
		int n;
		int d;

		public Node(int n, int d) {
			super();
			this.n = n;
			this.d = d;
		}
	}
}
