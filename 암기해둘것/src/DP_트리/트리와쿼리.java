package DP_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 트리와쿼리 {
	static int N, R, Q;
	static List<Integer> graph[];
	static int dp[];
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		graph = new List[N + 1];
		dp = new int[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			graph[b].add(a);
		}
		Tree(R);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
		}
		System.out.println(sb);
	}

	private static int Tree(int r) {
		if (visited[r]) {
			return dp[r];
		}
		visited[r] = true;
		dp[r] = 1;
		for (int child : graph[r]) {
			if (!visited[child])
				dp[r] += Tree(child);
		}
		return dp[r];

	}
}
