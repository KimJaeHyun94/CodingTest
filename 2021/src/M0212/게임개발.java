package M0212;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 게임개발 {
	static List<Integer>[] graph;
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	static int[] degree;
	static int[] time;
	static int[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		graph = new List[N + 1];
		degree = new int[N + 1];
		time = new int[N + 1];
		result = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int end = 1; end <= N; end++) {
			st = new StringTokenizer(br.readLine());
			time[end] = Integer.parseInt(st.nextToken());
			while (true) {
				int start = Integer.parseInt(st.nextToken());
				if (start == -1) {
					break;
				}
				graph[start].add(end);
				degree[end]++;
			}
		}
		result = time.clone();
		BFS();
		for (int i = 1; i <= N; i++) {
			sb.append(result[i]).append("\n");
		}
		System.out.println(sb);

	}

	private static void BFS() {
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if (degree[i] == 0) {
				q.add(i);
			}
		}

		while (!q.isEmpty()) {
			int temp = q.poll();

			for (int child : graph[temp]) {
				result[child] = Math.max(result[child], result[temp] + time[child]);

				if (--degree[child] == 0) {
					q.add(child);
				}
			}
		}
	}
}
