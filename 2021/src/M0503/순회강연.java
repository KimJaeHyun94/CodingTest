package M0503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 순회강연 {
	static boolean visited[] = new boolean[10001];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Node> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			pq.add(new Node(p, d));
		}

		int ans = 0;
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int p = cur.p;
			int d = cur.d;
			for (int i = d; i >= 1; i--) {
				if (!visited[i]) {
					visited[i] = true;
					ans += p;
					break;
				}
			}
		}
		System.out.println(ans);
	}

	static class Node implements Comparable<Node> {
		int p;
		int d;

		public Node(int p, int d) {
			this.p = p;
			this.d = d;
		}

		@Override
		public int compareTo(Node o) {
			if (this.p == o.p) {
				return o.d - this.d;
			}
			return o.p - this.p;
		}
	}
}
