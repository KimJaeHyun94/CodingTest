package 위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class ACMCraft {
	static List<Integer>[] graph;
	static int N, K;
	static int[] degree;
	static int[] cost; // 건물 1개당 걸리는 시간
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			graph = new List[N + 1];
			degree = new int[N + 1];
			cost = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
				cost[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());

				graph[X].add(Y);
				degree[Y]++;
			}

			int w = Integer.parseInt(br.readLine());
			sb.append(BFS(w)).append("\n");
		}
		System.out.println(sb);
	}

	private static int BFS(int w) {
		Queue<Integer> q = new LinkedList<>();
		int result[] = new int[N + 1]; // 시간 소요되는것 저장
		for (int i = 1; i <= N; i++) {
			result[i] = cost[i];

			if (degree[i] == 0)
				q.add(i);
		}

		while (!q.isEmpty()) {
			int temp = q.poll();

			for (int child : graph[temp]) {
				result[child] = Math.max(result[child], result[temp] + cost[child]);

				if (--degree[child] == 0) {
					q.add(child);
				}
			}
		}
		return result[w];
	}

}