package M0206;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class 합승택시요금 {
	static private int INF =10000000;   //INF 987654321로 설정할 시 정수의 범위를 넘어가서 -처리 되므로 범위 주의
	static List<Edge>[] list;
	static int ans;
	static int N;

	public static void main(String[] args) {
		System.out.println(solution(6, 4, 6, 2, new int[][] { { 4, 1, 10 }, { 3, 5, 24 }, { 5, 6, 2 }, { 3, 1, 41 },
				{ 5, 1, 24 }, { 4, 6, 50 }, { 2, 4, 66 }, { 2, 3, 22 }, { 1, 6, 25 } }));

		System.out.println(
				solution(7, 3, 4, 1, new int[][] { { 5, 7, 9 }, { 4, 6, 4 }, { 3, 6, 1 }, { 3, 2, 3 }, { 2, 1, 6 } }));

	}

	public static int solution(int n, int s, int a, int b, int[][] fares) {
		list = new ArrayList[n + 1]; // n은 정점의 개수
		N = n;
		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < fares.length; i++) {
			int start = fares[i][0];
			int end = fares[i][1];
			int w = fares[i][2];

			list[start].add(new Edge(end, w));
			list[end].add(new Edge(start, w));
		}
		int min = Integer.MAX_VALUE;

		int r1[] = dijkstra(s, n);    //s에서 n까지 갈 수 있는 모든 경우의 수를 구해놓는다. 
		int r2[] = dijkstra(a, n);	  //a에서 n까지 갈 수 있는 모든 경우의 수를 구해놓는다. 
		int r3[] = dijkstra(b, n);    //b에서 n까지 갈 수 있는 모든 경우의 수를 구해놓는다. 

		for (int i = 1; i <= N; i++) {
			int route = r1[i] + r2[i] + r3[i];
			min = Math.min(min, route);
		}
		return min;
	}

	private static int[] dijkstra(int start, int end) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];

		int D[] = new int[N + 1];
		Arrays.fill(D, INF);
		D[start] = 0; // 처음 시작을 0으로 시작
		pq.add(new Edge(start, 0)); // 시작점, 무게는 0

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			if (!visited[edge.v]) {
				visited[edge.v] = true;

				for (Edge next : list[edge.v]) { // 그 정점에 모든 다른 정점에서
					if (!visited[next.v] && D[next.v] > D[edge.v] + next.weight) {
						D[next.v] = D[edge.v] + next.weight;
						pq.add(new Edge(next.v, D[next.v]));
					}
				}
			}
		}
		return D;
	}

	static class Edge implements Comparable<Edge> {
		int v, weight;

		public Edge(int v, int weight) {
			super();
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
	}
}
