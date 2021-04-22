package M0420;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class ArcticNetwork {
	static int parents[];
	static int N, M;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			parents = new int[M + 1];
			Net arr[] = new Net[M + 1];

			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				arr[i] = new Net(x, y);
				parents[i] = i;
			}
			List<Node> list = new ArrayList<>();
			for (int i = 1; i < M; i++) {
				for (int j = i + 1; j <= M; j++) {
					double len = Math.sqrt(Math.pow(arr[i].x - arr[j].x, 2) + Math.pow(arr[i].y - arr[j].y, 2));
					list.add(new Node(i, j, len));
				}
			}

			Collections.sort(list, new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					return Double.compare(o1.c, o2.c);
				}

			});
			double ans = 0.0;
			int cnt = 0;
			int need = M-N;
			int idx = 0;
			while(cnt<need) {
				Node node = list.get(idx++);
				int s = node.s;
				int e = node.e;
				ans = node.c;
				if (findSet(s) == findSet(e))
					continue;

				union(s, e);
				cnt++;

			}
			String str = String.format("%.2f", ans);
			System.out.println(str);
		}

	}

	private static void union(int i, int j) {
		int si = findSet(i);
		int sj = findSet(j);

		if(si!=sj)
			parents[si] = sj;
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
