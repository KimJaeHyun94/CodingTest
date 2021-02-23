package M0223;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 동굴탐험 {
	static List<Integer>[] graph;
	static int before[];
	static int save[];

	public static void main(String[] args) {
		System.out.println(solution(9,
				new int[][] { { 0, 1 }, { 0, 3 }, { 0, 7 }, { 8, 1 }, { 3, 6 }, { 1, 2 }, { 4, 7 }, { 7, 5 } },
				new int[][] { { 8, 5 }, { 6, 7 }, { 4, 1 } }));
	}

	public static boolean solution(int n, int[][] path, int[][] order) {
		graph = new List[n];
		before = new int[n];
		save = new int[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < path.length; i++) {
			graph[path[i][0]].add(path[i][1]);
			graph[path[i][1]].add(path[i][0]);
		}

		for (int i = 0; i < order.length; i++) {
			before[order[i][1]] = order[i][0];
		}

		if (before[0] != 0) {
			return false;
		}

		boolean visited[] = new boolean[n];
		return BFS(visited, n);
	}

	private static boolean BFS(boolean[] visited, int n) {
		visited[0] = true;
		Queue<Integer> q = new LinkedList<>();
		for (int i : graph[0]) {
			q.add(i);
		}
		while (!q.isEmpty()) {
			int temp = q.poll();

			if (visited[temp]) {
				continue;
			}

			if (!visited[before[temp]]) {
				save[before[temp]] = temp;
				continue;
			}

			visited[temp] = true;
			for (int i : graph[temp]) {
				q.add(i);
			}

			q.add(save[temp]);
		}
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				return false;
			}
		}
		return true;
	}

}
