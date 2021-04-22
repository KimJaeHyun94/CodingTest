package M0420;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class WateringtheFields {
	static int N, C;
	static int parents[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		parents = new int[N + 1];
		Net arr[] = new Net[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			arr[i] = new Net(x, y);
			parents[i] = i;
		}
		List<Node> list = new ArrayList<>();
		for (int i = 1; i < N; i++) {
			for (int j = i + 1; j <= N; j++) {
				double len = Math.pow(arr[i].x - arr[j].x, 2) + Math.pow(arr[i].y - arr[j].y, 2);
				if (len >= C)
					list.add(new Node(i, j, len));
			}
		}
		Collections.sort(list, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return Double.compare(o1.c, o2.c);
			}
		});
		int ans = 0;
		int cnt = 0;
		for (Node node : list) {
			int s = findSet(node.s);
			int e = findSet(node.e);

			if (s == e)
				continue;
			ans += node.c;
			cnt++;
			union(s, e);

		}

		if (cnt == N - 1)
			System.out.println(ans);

		else
			System.out.println(-1);
	}

	private static void union(int i, int j) {
		int si = findSet(i);
		int sj = findSet(j);

		if (si > sj) {
			parents[si] = sj;
		} else
			parents[sj] = si;
	}

	private static int findSet(int x) {
		if (x == parents[x])
			return x;
		else
			return parents[x] = findSet(parents[x]);
	}

	static class Net {
		int x;
		int y;

		public Net(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static class Node {
		int s; // 출발 지점
		int e; // 도착 지점
		double c; // 가중치

		public Node(int s, int e, double c) {
			this.s = s;
			this.e = e;
			this.c = c;
		}
	}
}
