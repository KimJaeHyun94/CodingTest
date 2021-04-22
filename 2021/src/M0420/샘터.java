package M0420;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 샘터 {
	static int N, K;
	static int sam[];
	static HashSet<Integer> visited;
	static Queue<Node> q;
	static int dirs[] = { 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		sam = new int[K];
		q = new LinkedList<>();
		visited = new HashSet<>();

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			visited.add(n);
			q.add(new Node(n, 0));
		}

		long sum = 0;
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int num = cur.num;
			long dist = cur.dist;

			for (int d = 0; d < dirs.length; d++) {
				int nr = num + dirs[d];

				if (nr > 1000000000 || nr < -1000000000 || visited.contains(nr))
					continue;
				--K;
				 // 거리도 한칸 벌어짐
				sum += dist+1;
				visited.add(nr);
				q.add(new Node(nr, dist+1));

				if (K == 0) {
					System.out.println(sum);
					System.exit(0);
				}
			}
		}

	}

	static class Node {
		int num;
		long dist;

		public Node(int num, long dist) {
			this.num = num;
			this.dist = dist;
		}

	}
}
