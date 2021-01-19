package M0302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ15900나무탈출 {
	static List<Integer> graph[];
	static int N;
	static boolean visited[];
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		graph = new List[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new LinkedList<>();
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			graph[b].add(a);
		}
		BFS();
		if (result % 2 == 1)
			System.out.println("Yes");
		else
			System.out.println("No");
	}

	private static void BFS() {
		Queue<tree> q = new LinkedList<>();
		q.add(new tree(1, 0));
		visited[1] = true;
		while (!q.isEmpty()) {
			tree temp = q.poll();
			boolean flag = false;
			int a = temp.a;
			int d = temp.d;
			
			for (Integer child : graph[a]) {
				if (!visited[child]) {
					visited[child] = true;
					flag = true;
					q.add(new tree(child, d + 1));
				}
			}
			if (!flag) {
				result += d;
			}
		}
	}

	static class tree {
		int a;
		int d;

		public tree(int a, int d) {
			this.a = a;
			this.d = d;
		}
	}
}
