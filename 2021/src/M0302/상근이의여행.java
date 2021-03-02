package M0302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 상근이의여행 {
	static int V, E;
	static int parents[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			parents = new int[V + 1];
			for (int i = 1; i <= V; i++) {
				parents[i] = i;
			}

			ArrayList<Node> list = new ArrayList<>();
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				list.add(new Node(a, b));
			}

			int cnt = 0;
			for (int i = 0; i < list.size(); i++) {
				int s = findSet(list.get(i).s);
				int e = findSet(list.get(i).e);
				if (s == e)
					continue;
				union(s, e);
				cnt++;
				if (cnt == V - 1)
					break;
			}

			sb.append(cnt).append("\n");
		}
		System.out.println(sb);

	}

	private static void union(int i, int j) {
		int pi = findSet(i);
		int pj = findSet(j);

		if (pi != pj) {
			if (pi < pj) {
				parents[pj] = pi;
			} else {
				parents[pi] = pj;
			}
		}

	}

	private static int findSet(int s) {
		
		if (s == parents[s]) {
			return s;
		} else {
			parents[s] = findSet(parents[s]);
			return parents[s];
		}
	}

	static class Node {
		int s;
		int e;

		public Node(int s, int e) {
			this.s = s;
			this.e = e;
		}

	}

}
