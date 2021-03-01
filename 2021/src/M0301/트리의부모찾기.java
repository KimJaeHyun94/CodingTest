package M0301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 트리의부모찾기 {
	static int N;
	static List<Integer> graph[];
	static int parents[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		parents = new int[N + 1];

		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			graph[b].add(a);
		}

		BFS();
		
		for (int i = 2; i <= N; i++) {
			System.out.println(parents[i]);
		}
	}

	private static void BFS() {
		Queue<Integer> q = new LinkedList<>();
		boolean visited[] = new boolean[N + 1];
		q.add(1);
		visited[1] = true;

		while (!q.isEmpty()) {
			int temp = q.poll();

			for (int child : graph[temp]) {
				if (!visited[child]) {
					visited[child] = true;
					parents[child] = temp;
					q.add(child);
				}
			}
		}
	}
}
