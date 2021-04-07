package M0406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 음악프로그램 {
	static int N, M;
	static List<Integer>[] graph;
	static int[] degree;

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
			int n = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());

			for (int j = 1; j < n; j++) {
				int s = Integer.parseInt(st.nextToken());
				graph[a].add(s);
				degree[s]++;

				a = s;
			}
		}

		BFS();

	}

	private static void BFS() {
		Queue<Integer> q = new LinkedList<>();
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if (degree[i] == 0) {
				q.add(i);
			}
		}

		while (!q.isEmpty()) {
			int cur = q.poll();
			list.add(cur);

			for (Integer child : graph[cur]) {
				if (--degree[child] == 0) {
					q.add(child);
				}
			}
		}

		if (list.size() !=N) {
			System.out.println(0);
		} else {
			for (Integer child : list) {
				System.out.println(child);
			}
		}

	}
}
