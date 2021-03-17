package M0316;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 맥주마시면서걸어가기 {
	static int N;
	static ArrayList<Node> list;
	static List<Integer> graph[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());

			list = new ArrayList<>();
			graph = new List[N + 2];
			for (int i = 0; i < N + 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				list.add(new Node(r, c));
				graph[i] = new ArrayList<>();
			}

			for (int i = 0; i < N + 1; i++) {
				for (int j = i + 1; j < N + 2; j++) {
					if (Math.abs(list.get(i).r - list.get(j).r) + Math.abs(list.get(i).c - list.get(j).c) <= 1000) {
						graph[i].add(j);
						graph[j].add(i);
					}
				}
			}

			boolean flag = BFS();
			if (flag) {
				sb.append("happy");
			} else {
				sb.append("sad");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static boolean BFS() {
		Queue<Integer> q = new LinkedList<>();
		boolean visited[] = new boolean[N + 2];
		q.add(0);
		visited[0] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (Integer child : graph[cur]) {
				if (!visited[child]) {
					visited[child] = true;
					q.add(child);
				}
			}
		}
		if (!visited[N + 1]) {
			return false;
		} else {
			return true;
		}
	}

	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}
}
