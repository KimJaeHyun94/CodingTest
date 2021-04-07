package M0406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 장난감조립 {
	static int N, M;
	static List<Node>[] graph;
	static int[] degree;
	static int[][] count;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		graph = new List[N + 1];
		degree = new int[N + 1];
		count = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[e].add(new Node(s, c));
			degree[s]++;
		}
		BFS();

		for (int i = 1; i <= N; i++) {
			if (count[N][i] != 0) {
				System.out.println(i + " " + count[N][i]);
			}
		}

	}

	private static void BFS() {
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (degree[i] == 0) {
				q.add(i);
				count[i][i] = 1;
			}
		}

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (Node child : graph[cur]) {
				for (int i = 1; i <= N; i++) {
					count[child.e][i] += count[cur][i] * child.c;
				}
				if (--degree[child.e] == 0) {
					q.add(child.e);
				}
			}
		}
	}

	static class Node {
		int e;
		int c;

		public Node(int e, int c) {
			this.e = e;
			this.c = c;
		}
	}
}
