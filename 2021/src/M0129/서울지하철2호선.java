package M0129;

import java.util.*;
import java.io.*;

public class 서울지하철2호선 {
	static List<Integer>[] graph;
	static int V;
	static boolean cycle[]; // cycle 여부를 확인
	static boolean iscycle;
	static int startv;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		graph = new List[V + 1];
		cycle = new boolean[V + 1];
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			graph[b].add(a);
		}

		for (int i = 1; i <= V; i++) { // cycle 완성
			if (iscycle) {
				break;
			}
			cycle = new boolean[V + 1];
			startv = i;

			DFS(i, 0);
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= V; i++) {
			if (!cycle[i]) {
				sb.append(BFS(i) + " ");
			}
			else {
				sb.append(0+" ");
			}
		}
		System.out.println(sb);
	}

	private static int BFS(int v) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { v, 0 });
		boolean[] visited = new boolean[V + 1];
		visited[v] = true;

		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int r = temp[0];
			int d = temp[1];
			if (cycle[r]) {
				return d;
			}
			for (Integer child : graph[r]) {
				if (!visited[child]) {
					visited[child] = true;
					q.add(new int[] { child, d + 1 });
				}
			}
		}
		return 0;
	}

	private static void DFS(int v, int cnt) {
		if (startv == v && cnt >= 2) { // 정점 하나는 찍어야 사이클이 형성되므로
			iscycle = true;
			return;
		}
		cycle[v] = true;
		for (Integer child : graph[v]) {
			if (!cycle[child]) {
				DFS(child, cnt + 1);
			} else if (startv == child && cnt >= 2) {
				DFS(child, cnt);
			}
			if (iscycle)
				return;
		}
		cycle[v] = false;
	}
}
