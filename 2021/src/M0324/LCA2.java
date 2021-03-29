package M0324;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LCA2 {
	static int N, M, K;
	static List<Integer> graph[];
	static int[][] parents;
	static int[] depth;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new List[N + 1];

		int tmp = 1;
		K = 0;
		while (tmp <= N) { // 최대 depth 알아내기.
			tmp <<= 1;
			K++;
		}
		parents = new int[N + 1][K];
		depth = new int[N + 1];
		visited = new boolean[N + 1];

		for (int n = 1; n <= N; n++) {
			graph[n] = new ArrayList<>();
		}

		for (int j = 0; j < N - 1; j++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			visited[b] = true;

			graph[a].add(b);
			graph[b].add(a);
		}

		DFS(1, 1);
		for (int i = 1; i < K; i++) { // DP를 이용해 각 노드별 2^K 번 째 조상 노드를 저장한다.
			for (int j = 1; j <= N; j++) {
				parents[j][i] = parents[parents[j][i - 1]][i - 1];
			}
		}
		M = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			sb.append(findParent(a, b)).append("\n");
		}
		System.out.println(sb);
	}

	private static int findParent(int a, int b) {
		int adepth = depth[a];
		int bdepth = depth[b];

		if (adepth < bdepth) {
			int temp = a;
			a = b;
			b = temp;
		}

		for (int i = K - 1; i >= 0; i--) {
			if (Math.pow(2, i) <= depth[a] - depth[b]) {
				a = parents[a][i]; // a를 2^i 번 째 조상 노드로 대체한다.
			}
		}

		if (a == b)
			return a;
		for (int i = K - 1; i >= 0; i--) {
			if (parents[a][i] != parents[b][i]) {
				a = parents[a][i];
				b = parents[b][i];
			}
		}

		return parents[a][0];
	}

	private static void DFS(int cur, int d) {
		depth[cur] = d;

		for (int child : graph[cur]) {
			if (depth[child] == 0) {
				DFS(child, d + 1);
				parents[child][0] = cur;
			}
		}
	}
}
