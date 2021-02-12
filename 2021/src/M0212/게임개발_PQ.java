package M0212;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 게임개발_PQ {
	static List<Integer>[] graph;
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	static int[] degree;
	static game arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		graph = new List[N + 1];
		degree = new int[N + 1];
		arr = new game[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int end = 1; end <= N; end++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			arr[end] = new game(time, end);
			while (true) {
				int start = Integer.parseInt(st.nextToken());
				if (start == -1) {
					break;
				}
				graph[start].add(end);
				degree[end]++;
			}
		}
		BFS();
		for (int i = 1; i <= N; i++) {
			sb.append(arr[i].time).append("\n");
		}
		System.out.println(sb);

	}

	private static void BFS() {
		PriorityQueue<game> q = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			if (degree[i] == 0) {
				q.add(arr[i]);
			}
		}

		while (!q.isEmpty()) {
			game temp = q.poll();

			for (int child : graph[temp.num]) {
				if (--degree[child] == 0) {
					arr[child].time += arr[temp.num].time;
					q.add(new game(arr[child].time, child));
				}
			}
		}
	}

	static class game implements Comparable<game> {
		int time;
		int num;

		public game(int time, int num) {
			this.time = time;
			this.num = num;
		}

		@Override
		public int compareTo(game o) {
			return this.time - o.time;
		}

	}
}
