package M0324;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 가장가까운공통조상 {
	static int N;
	static List<Integer> graph[];
	static int[] parent;
	static int[] depth;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine());
			graph = new List[N + 1];
			parent = new int[N + 1];
			depth = new int[N + 1];
			visited = new boolean[N + 1];
			
			for (int n = 1; n <= N; n++) {
				graph[n] = new ArrayList<>();
				parent[n] = -1;
			}

			for (int j = 0; j < N - 1; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				visited[b] = true; 
				
				graph[a].add(b);
				graph[b].add(a);
			}
			int root = -1;
			for (int j = 1; j <= N; j++) {
				if(!visited[j]) {
					root = j;
					break;
				}
			}
			DFS(root, 1, 0);
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			sb.append(findParent(a, b));
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static int findParent(int a, int b) {
		int adepth = depth[a];
		int bdepth = depth[b];

		while (adepth > bdepth) {
			a = parent[a];
			adepth--;
		}

		while (bdepth > adepth) {
			b = parent[b];
			bdepth--;
		}
		while (a != b) {
			a = parent[a];
			b = parent[b];
		}
		return a;
	}

	private static void DFS(int cur, int d, int p) {
		depth[cur] = d;
		parent[cur] = p;

		for (int child : graph[cur]) {
			if (child != p) {
				DFS(child, d + 1, cur);
			}
		}
	}
}
