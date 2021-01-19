package M1201;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 키순서Graph {
	static int N, M;
	static List<Integer>[] Graph;
	static List<Integer>[] Graph2;
	static int ans;
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Graph = new List[N + 1];
		Graph2 = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			Graph[i] = new ArrayList<>();
			Graph2[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			Graph[x].add(y);
			Graph2[y].add(x);
		}
		for (int i = 1; i <= N; i++) {
			if (Sol(i) == N-1)
				ans++;
		}
		System.out.println(ans);

	}

	private static int Sol(int r) {
		int cnt = 0;
		visited = new boolean[N + 1];
		Queue<Integer> q = new LinkedList<>();
		q.add(r);
		visited[r] = true;
		while (!q.isEmpty()) {
			int s = q.poll();
			for (Integer i : Graph[s]) {
				if (!visited[i]) {
					q.offer(i);
					cnt++;
					visited[i] = true;
				}

			}
		}

		q = new LinkedList<>();
		q.add(r);
		visited = new boolean[N + 1];
		visited[r] = true;

		while (!q.isEmpty()) {
			int s = q.poll();

			for (Integer i : Graph2[s]) {
				if (!visited[i]) {
					q.offer(i);
					visited[i] = true;
					cnt++;
				}

			}
		}
		return cnt;
	}
}
