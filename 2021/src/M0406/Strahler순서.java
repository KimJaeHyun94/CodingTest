package M0406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Strahler순서 {
	static int K, M, P;
	static List<Integer>[] graph;
	static int[] degree;
	static int[] cost;
	static int[] count;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());

		for (int t = 0; t < TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			graph = new List[M + 1];
			degree = new int[M + 1];
			cost = new int[M + 1];
			count = new int[M + 1];
			for (int i = 1; i <= M; i++) {
				graph[i] = new ArrayList<>();
			}

			for (int i = 0; i < P; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				graph[s].add(e);
				degree[e]++;
			}
			BFS();
			sb.append(K).append(" ").append(cost[M]).append("\n");
		}
		System.out.println(sb);
	}

	private static void BFS() {
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= M; i++) {
			if (degree[i] == 0) {
				cost[i] = 1;
				q.add(i);
			}
		}
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (Integer child : graph[cur]) {
				if (cost[child] < cost[cur]) {
					cost[child] = cost[cur];
					count[child] = 0;
				} else if (cost[child] == cost[cur]) {
					count[child]++;
				}
				if (--degree[child] == 0) {
					if (count[child] > 0)
						cost[child]++;
					q.add(child);
				}
			}
		}
	}
}
