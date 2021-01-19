package M1217;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 가장먼노드 {
	private static List<Integer>[] graph;
	static int dist[];
	static int max = Integer.MIN_VALUE;
	static int cnt = 0;
	static boolean visited[];

	public static void main(String[] args) {
		int[][] edge = { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } };
		solution(6,edge);
	}

	public static int solution(int n, int[][] edge) {
		int answer = 0;
		graph = new List[n + 1];
		dist = new int[n + 1];
		visited = new boolean[n + 1];

		boolean visit[][] = new boolean[n + 1][n + 1];
		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < edge.length; i++) {
			int x = edge[i][0];
			int y = edge[i][1];

			graph[x].add(y);
			graph[y].add(x);
		}
		BFS();
		Sol();
	
		answer = cnt;
		return answer;
	}

	private static void BFS() {
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		visited[1] = true;
		while (!q.isEmpty()) {
			Integer temp = q.poll();
			for (Integer i : graph[temp]) {
				//System.out.println(i);
				if (!visited[i] && dist[i]==0) {
					
					q.add(i);
					dist[i] = dist[temp] + 1;
					max = Math.max(max, dist[i]);
					visited[i] = true;
				}
			}
		}

	}

	private static void Sol() {
		for (int i = 0; i < dist.length; i++) {
			if(max==dist[i]) {
				cnt++;
			}
		}
	}
}
