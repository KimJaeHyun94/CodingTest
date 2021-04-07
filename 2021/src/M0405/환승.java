package M0405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 환승 {
	static int N, K, M;
	static List<Integer> graph[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new List[N + M + 1];	//1~n 정점을 역 n+1 ~ n+m 정점을 하이퍼튜브
		for (int i = 1; i <= N + M; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < K; j++) {
				int s = Integer.parseInt(st.nextToken());
				graph[s].add(N+i);
				graph[N+i].add(s);
			}
		}
		
		BFS();
		System.out.println(-1);
	}

	private static void BFS() {
		int dist[] = new int[N+M+1];
		dist[1] = 1;
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(cur==N) {
				System.out.println(dist[N]);
				System.exit(0);
			}
			
			for (Integer child : graph[cur]) {
				if(dist[child]==0) {
					if(child>N) {
						dist[child] = dist[cur];
					}else {
						dist[child] = dist[cur]+1;
					}
					q.add(child);
				}
			}
		}
	}
}
