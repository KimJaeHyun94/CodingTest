package DP_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 사회망서비스 {
	static List<Integer> graph[];
	static int N;
	static int dp[][];
	static boolean visited[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		graph = new List[N + 1];
		dp = new int[2][N + 1];
		visited = new boolean[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}

		DFS(1);

		System.out.println(Math.min(dp[1][1], dp[0][1]));
	}

	private static void DFS(int cnt) {
		dp[0][cnt] = 0;
		dp[1][cnt] = 1;
		visited[cnt] = true;
		for (int child : graph[cnt]) {
			if (!visited[child]) {
				DFS(child);
				dp[0][cnt] += dp[1][child];
				dp[1][cnt] += Math.min(dp[0][child], dp[1][child]);
			}
		}
	}
}