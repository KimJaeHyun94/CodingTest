package M0505;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 통신망분할 {
	static int N, M, Q;
	static int parents[], cost[], disconnect[];
	static ArrayList<Node> list;
	static boolean dis[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		parents = new int[N + 1];
		cost = new int[N + 1];
		list = new ArrayList<>();
		disconnect = new int[Q + 1];
		dis = new boolean[M + 1];

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.add(new Node(a, b));
		}

		for (int i = 1; i <= Q; i++) {
			int a = Integer.parseInt(br.readLine());
			disconnect[i] = a;
			dis[a] = true;
		}

		for (int i = 1; i <= N; i++) {
			parents[i] = i;
			cost[i] = 1;
		}

		for (int i = 1; i <= M; i++) {
			if (dis[i])
				continue;
			else {
				Node cur = list.get(i - 1);
				int x = cur.x;
				int y = cur.y;

				union(x, y);
			}
		}

		long ans = 0;
		for (int i = Q; i > 0; i--) {
			int temp = disconnect[i];

			int x = findSet(list.get(temp - 1).x);
			int y = findSet(list.get(temp - 1).y);

			if (x != y) {
				ans += (long) cost[findSet(x)] * (long) cost[findSet(y)];
			}
			union(x, y);
		}

		System.out.println(ans);
	}

	private static void union(int a, int b) {
		int pa = findSet(a);
		int pb = findSet(b);

		if (pa != pb) {
			if (pa < pb) {
				cost[pa] = cost[pb] + cost[pa];
				cost[pb] = 0;
				parents[pb] = findSet(pa);
			} else {
				cost[pb] = cost[pb] + cost[pa];
				cost[pa] = 0;
				parents[pa] = findSet(pb);
			}
		}
	}

	private static int findSet(int p) {
		if (p == parents[p])
			return p;
		return findSet(parents[p]);
	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}
