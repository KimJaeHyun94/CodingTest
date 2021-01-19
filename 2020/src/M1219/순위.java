package M1219;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 순위 {
	static int cnt;
	private static List<Integer>[] graph;
	private static List<Integer>[] graph2;
	static int N;
	static boolean visited[];

	public static void main(String[] args) {
		System.out.println(
				solution(8, new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 }, { 6, 7 }, { 7, 8 } }));
	}

	public static int solution(int n, int[][] results) {
		int answer = 0;
		graph = new List[n + 1];
		graph2 = new List[n + 1];
		visited = new boolean[N + 1];
		N = n;
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
			graph2[i] = new ArrayList<>();
		}

		for (int i = 0; i < results.length; i++) {
			int x = results[i][0];
			int y = results[i][1];

			graph[x].add(y);
			graph2[y].add(x);
		}

		for (int i = 1; i <= n; i++) {
			cnt = 0;
			BFS(i);
			if (cnt == n - 1) {
				answer++;
			}
		}
		return answer;
	}

	private static void BFS(int i) {
		Queue<Integer> q = new LinkedList<>();
		q.add(i);
		visited = new boolean[N + 1];
		visited[i] = true;
		while (!q.isEmpty()) {
			int s = q.poll();

			for (int son : graph[s]) {
				if (!visited[son]) {
					q.add(son);
					visited[son] = true;
					cnt++;
				}
			}
		}

		q = new LinkedList<>();
		q.add(i);
		visited = new boolean[N + 1];
		visited[i] = true;
		while (!q.isEmpty()) {
			int s = q.poll();
			for (int son : graph2[s]) {
				if (!visited[son]) {
					q.add(son);
					visited[son] = true;
					cnt++;
				}
			}
		}
	}
}
