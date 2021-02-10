package M0210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/**
 * Dijkstra + Binary Search
 * @author AKKabiyo
 * @see https://bellog.tistory.com/132
 *
 */
public class 인터넷설치 {
	static int N, K, P;
	static private int INF = 10000000;
	static List<Edge>[] graph;
	static int D[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		graph = new List[N + 1];
		D = new int[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		int left = 0, right = 0;
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph[start].add(new Edge(end, w));
			graph[end].add(new Edge(start, w));

			right = Math.max(w, right);
		}
		
		int ans = -1;
		while (left <= right) {
			int mid = (left + right) /2;

			if (dijkstra(mid)) {
				ans = mid;
				right = mid - 1;
			} else
				left = mid + 1;
		}
		System.out.println(ans);
	}

	private static boolean dijkstra(int end) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		//Arrays.fill(D, INF);
		for (int i = 1; i <= N; i++) {
			D[i] = INF; 
		}
		D[1] = 0; // 처음 시작을 0으로 시작
		pq.add(new Edge(1, 0)); // 시작점, 무게는 0

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			if (D[edge.v] < edge.weight) {
				continue;
			}
			for (Edge next : graph[edge.v]) { // 그 정점에 모든 다른 정점에서
				int weight = edge.weight;
				if (next.weight > end) {
					weight++;
				}
				if (D[next.v] > weight) {
					D[next.v] = weight;
					pq.add(new Edge(next.v, weight));
				}
			}
		}
		return D[N] <= K;
	}

	static class Edge implements Comparable<Edge> {
		int v, weight;

		public Edge(int v, int weight) {
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
