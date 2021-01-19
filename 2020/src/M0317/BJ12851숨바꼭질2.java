package M0317;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ12851숨바꼭질2 {
	static int min = Integer.MAX_VALUE;
	static int cnt, N, K;
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[100001];
		BFS();
		System.out.println(min);
		System.out.println(cnt);
	}

	private static void BFS() {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(N, 0));
		visited[N] = true;
		while (!queue.isEmpty()) {
			Node temp = queue.poll();
			int n = temp.n;
			int d = temp.d;

			visited[n] = true;
			if(n==K && min==d) {
				cnt++;
			}
			if(n==K && min==Integer.MAX_VALUE) {
				min=d;
				cnt++;
			}
			if (n - 1 >= 0 && !visited[n - 1]) {
				queue.add(new Node(n - 1, d + 1));
			}
			if (n + 1 <= 100000 && !visited[n + 1]) {
				queue.add(new Node(n + 1, d + 1));
			}
			if (n * 2 <= 100000 && !visited[n * 2]) {
				queue.add(new Node(n * 2, d + 1));
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
