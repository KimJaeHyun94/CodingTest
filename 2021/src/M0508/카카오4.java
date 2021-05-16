package M0508;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 카카오4 {
	static List<Node> graph[];
	static int[] traps;
	static int N;
	static boolean[] trapper;

	public static void main(String[] args) {
		int[][] roads = { { 1, 2, 2 }, { 3, 2, 3 } };
		int[] traps = { 2 };
		// System.out.println(solution(3, 1, 3, roads, traps));
		int[][] roads2 = { { 1, 2, 1 }, { 3, 2, 1 }, { 2, 4, 1 } };
		int[] traps2 = { 2, 3 };
		System.out.println(solution(4, 1, 4, roads2, traps2));
	}

	public static int solution(int n, int start, int end, int[][] roads, int[] traping) {
		int answer = 0;
		graph = new List[n + 1];
		traps = traping;
		N = n;
		trapper = new boolean[n + 1];

		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < roads.length; i++) {
			int s = roads[i][0];
			int e = roads[i][1];
			int c = roads[i][2];

			graph[s].add(new Node(e, c, true));
			graph[e].add(new Node(s, c, false));
		}

		answer = Sol(start, end);
		return answer;
	}

	private static int Sol(int start, int end) {
		Queue<Choco> q = new LinkedList<>();
		q.add(new Choco(start, 0, true));
		boolean visited[][][] = new boolean[N + 1][N + 1][2];

		while (!q.isEmpty()) {
			Choco cur = q.poll();

			int n = 0;
			if (cur.flag) {
				n = 1;
			}
			if (cur.cur == end) {
				return cur.dep;
			}

			for (Node child : graph[cur.cur]) {
				boolean flag = false;
				int idx = -1;

				if (!visited[cur.cur][child.e][n] && child.trap == cur.flag) { // 방향이 같은 경우에만
					for (int i = 0; i < traps.length; i++) { // 만약에 함정이라면
						if (child.e == traps[i]) {
							flag = true;
							idx = i;
							break;
						}
					}
					if (flag) {
						if(trapper[idx]==cur.flag) {
							trapper[idx] = true;
							q.add(new Choco(child.e, cur.dep + child.c, true));
							visited[cur.cur][child.e][0] = true;
						}else {
							trapper[idx]=cur.flag;
							q.add(new Choco(child.e, cur.dep + child.c, !cur.flag));
							if(n==0) n=1;
							else n=0;
							visited[cur.cur][child.e][n] = true;
						}
						
					} else {
						q.add(new Choco(child.e, cur.dep + child.c, cur.flag));
						visited[cur.cur][child.e][n] = true;
					}
				}
			}
		}
		return 0;
	}

	static class Choco {
		int cur;
		int dep;
		boolean flag;

		public Choco(int cur, int dep, boolean flag) {
			this.cur = cur;
			this.dep = dep;
			this.flag = flag;
		}
	}

	static class Node {
		int e;
		int c;
		boolean trap;

		public Node(int e, int c, boolean trap) {
			this.e = e;
			this.c = c;
			this.trap = trap;
		}
	}
}
