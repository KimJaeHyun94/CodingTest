package M0417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS스페셜저지 {
	static int N;
	static List<Integer> graph[];
	static int Route[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}

		Route = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			Route[i] = Integer.parseInt(st.nextToken());
		}
		if (Route[0] != 1) {
			System.out.println(0);
			System.exit(0);
		}
		if (BFS()) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}

	private static boolean BFS() {
		Queue<Integer> q = new LinkedList<>();
		boolean visited[] = new boolean[N+1];
		q.add(1);
		visited[1] = true;
		int idx = 1;

		while (!q.isEmpty()) {
			int cur = q.poll();
			int size = 0;
			for (Integer child : graph[cur]) {
				if (!visited[child]) {
					visited[child] = true;
					size++;
				}
			}

			for (int i = idx; i < idx + size; i++) {
				if (visited[Route[i]])
					q.add(Route[i]);
				else
					return false;
			}
			idx += size;
		}
		return true;
	}
}
