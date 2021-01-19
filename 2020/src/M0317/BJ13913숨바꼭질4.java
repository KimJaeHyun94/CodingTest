package M0317;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ13913숨바꼭질4 {
	static int min = Integer.MAX_VALUE;
	static int cnt, N, K;
	static boolean visited[];
	static int parent[];
	static ArrayList<Integer> path;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[100001];
		parent = new int[100001];
		path = new ArrayList<>();
		BFS();
		System.out.println(min);
		for (int i = path.size()-1; i >=0; i--) {
			System.out.print(path.get(i) + " ");
		}
	}

	private static void BFS() {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(N, 0));
		visited[N] = true;
		while (!queue.isEmpty()) {
			Node temp = queue.poll();
			int n = temp.n;
			int d = temp.d;

			if (n == K) {
				int check = K;
				while (check != N) {
					path.add(check);
					check = parent[check];
				}
				path.add(N);
				min = d;
				return;
			}
			if (n - 1 >= 0 && !visited[n - 1]) {
				queue.add(new Node(n - 1, d + 1));
				visited[n - 1] = true;
				parent[n - 1] = n;
			}
			if (n + 1 <= 100000 && !visited[n + 1]) {
				queue.add(new Node(n + 1, d + 1));
				visited[n + 1] = true;
				parent[n + 1] = n;
			}
			if (n * 2 <= 100000 && !visited[n * 2]) {
				queue.add(new Node(n * 2, d + 1));
				visited[n * 2] = true;
				parent[n * 2] = n;
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