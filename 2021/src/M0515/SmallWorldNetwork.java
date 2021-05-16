package M0515;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SmallWorldNetwork {

	static int N, K;
	static List<Integer> graph[];
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		graph = new List[N + 1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			graph[b].add(a);
		}

		for (int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			BFS(i);

			for (int j = 1; j <= N; j++) {
				if (!visited[j]) {
					System.out.println("Big World!");
					System.exit(0);
				}
			}
		}

		System.out.println("Small World!");

	}

	private static void BFS(int idx) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { idx, 0 });

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (cur[1] > 6) {
				System.out.println("Big World!");
				System.exit(0);
			}

			for (int child : graph[cur[0]]) {
				if (!visited[child]) {
					visited[child] = true;
					q.add(new int[] { child, cur[1]+1 });
				}
			}
		}

	}

}
