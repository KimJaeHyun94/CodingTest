package M0302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ18243SmallWorldNetwork {
	static int N, K;
	static List<Integer> graph[];
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		graph = new List[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new LinkedList<>();
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
			BFS(1);
			for (int j = 1; j <= N; j++) {
				if (!visited[j]) {
					System.out.println("Big World!");
					System.exit(-1);
				}
			}
		}
		System.out.println("Small World!");
	}

	private static void BFS(int start) {
		int k = 0;
		Queue<world> q = new LinkedList<>();
		q.add(new world(start, 0));
		visited[start] = true;
		while (!q.isEmpty()) {
			world on = q.poll();
			int a = on.a;
			int d = on.d;
			if (d > 6) {
				System.out.println("Big World!");
				System.exit(-1);
			}
			for (Integer child : graph[a]) {
				if (!visited[child]) {
					visited[child] = true;
					q.add(new world(child, d + 1));
					k = d + 1;
				}
			}
		}
	}

	static class world {
		int a;
		int d;

		public world(int a, int d) {
			this.a = a;
			this.d = d;
		}
	}
}
