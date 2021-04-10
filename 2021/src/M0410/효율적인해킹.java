package M0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 효율적인해킹 {
	static List<Integer> graph[];
	static int N, M;
	static boolean visited[];
	static int arr[];
	static int Node;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new List[N + 1];
		arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			graph[e].add(s);
		}

		for (int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			Node = i;
			visited[i] = true;
			DFS(i);
		}
		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, arr[i]);
		}

		for (int i = 1; i <= N; i++) {
			if (arr[i] == max) {
				System.out.print(i + " ");
			}
		}
	}

	private static void DFS(int idx) {
		for (int child : graph[idx]) {
			if (!visited[child]) {
				arr[Node]++;
				visited[child] = true;
				DFS(child);
			}
		}
	}
}
