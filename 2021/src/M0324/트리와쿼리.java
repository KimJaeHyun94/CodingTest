package M0324;

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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		graph = new List[N + 1];
		dp = new int[N + 1];
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

		Tree(R, -1);
		for (int i = 0; i < Q; i++) {
			int s = Integer.parseInt(br.readLine());
			System.out.println(dp[s]);
		}

	}

	private static int Tree(int r, int parent) {
		if (dp[r] != 0) { // 자식이 있다면
			return dp[r];
		}
		dp[r] = 1; // 자식이 없다면

		for (Integer child : graph[r]) {
			if (parent != child) {
				dp[r] += Tree(child, r); // 계속 자식의 수를 더해줌
			}
		}
		return dp[r];
	}
}
