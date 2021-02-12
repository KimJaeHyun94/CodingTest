package M0212;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 문제집 {
	static List<Integer>[] graph;
	static int N, M;
	static int[] degree;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new List[N + 1];
		degree = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			graph[start].add(end); // 방향 그래프
			degree[end]++;// 후행 정점에 대한 간선의 수 증가
		}

		BFS();
		System.out.println(sb);
	}

	private static void BFS() {
		PriorityQueue<Integer> q = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			if (degree[i] == 0) {
				q.add(i);
			}
		}
		while (!q.isEmpty()) {
			int node = q.poll();
			sb.append(node + " ");
			for (int child : graph[node]) {
				if (--degree[child] == 0) {
					q.add(child);
				}
			}
		}
	}
}
