package M0406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 선수과목 {
	static int N, M;
	static List<Integer>[] graph;
	static int[] degree;
	static int[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new List[N + 1];
		degree = new int[N + 1];
		result = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			graph[s].add(e);
			degree[e]++;
		}

		BFS();

	}

	private static void BFS() {
		Queue<Integer> q = new LinkedList<>();
		Arrays.fill(result, 1);
		for (int i = 1; i <= N; i++) {
			if (degree[i] == 0) {
				q.add(i);
			}
		}

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (Integer child : graph[cur]) {
				if (--degree[child] == 0) {
					q.add(child);
					result[child] = result[cur]+1;
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			System.out.print(result[i]+" ");
		}
		System.out.println();

	}
}
