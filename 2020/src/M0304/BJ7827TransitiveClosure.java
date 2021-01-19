package M0304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ7827TransitiveClosure {
	static List<Integer> graph[];
	static int N, M;
	static int cnt;
	static boolean visited[][];
	static int a;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			graph = new List[N + 1];
			visited = new boolean[N + 1][N+1];
		
			cnt = 0;
			for (int i = 1; i <= N; i++) {
				graph[i] = new LinkedList<>();
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph[a].add(b);
			}
			for (int i = 1; i <= N; i++) {
				a = i;
				DFS(i);
			}
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(visited[i][j])
						cnt++;
				}
			}
			System.out.println(cnt);
		}
	}

	private static void DFS(int b) {
		for (Integer child : graph[b]) {
			if (!visited[a][child] &&a!=child ) {
				visited[a][child] = true;
				DFS(child);
			}
		}
	}
}
