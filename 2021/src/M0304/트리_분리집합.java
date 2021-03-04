package M0304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 트리_분리집합 {
	static int N, M;
	static List<Integer> graph[];
	static boolean visited[];
	static boolean cycle;
	static int parents[];
	static boolean tree[];

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int t = 1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			if (N == 0 && M == 0) {
				break;
			}

			graph = new List[N + 1];

			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}
			parents = new int[N + 1];
			tree = new boolean[N + 1];
			Arrays.fill(tree, true);
			for (int i = 1; i <= N; i++) {
				parents[i] = i;
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (a != b)
					union(a, b);

			}
			int cnt = 0; // 트리의 개수

			for (int i = 1; i <= N; i++) {
				int r = findSet(i);
				if (tree[r]) {
					tree[r] = false;
					cnt++;
				}
			}
			sb.append("Case ").append(t++).append(": ");
			if (cnt == 0) {
				sb.append("No trees.").append("\n");
			} else if (cnt == 1) {
				sb.append("There is one tree.").append("\n");
			} else {
				sb.append("A forest of ").append(cnt).append(" trees.").append("\n");
			}
		}
		System.out.println(sb);
	}

	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);

		if (px == py || !tree[px] || !tree[py]) { // cycle 이 이뤄짐 (아비가 같음) || 이미 트리가 아닌넘과 연결이 되면
			tree[px] = false;
			tree[py] = false;
		}

		if (px != py) {
			if (px < py) {
				parents[py] = px;
			} else
				parents[px] = py;
		}
	}

	private static int findSet(int x) {
		if (x == parents[x])
			return x;
		else {
			parents[x] = findSet(parents[x]);
			return parents[x];
		}
	}
}
