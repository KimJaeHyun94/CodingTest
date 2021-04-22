package M0421;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 마라톤2_topdown {
	static int N, K;
	static int memo[][];
	static int dist[][];
	static int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		memo = new int[N + 1][K + 1];
		dist = new int[N + 1][N + 1];

		ArrayList<Node> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				dist[i][j] = Math.abs(list.get(i).x - list.get(j).x) + Math.abs(list.get(i).y - list.get(j).y);
			}
		}

		System.out.println(DFS(N, K));
	}

	private static int DFS(int n, int k) {
		if (memo[n][k] != 0) {
			return memo[n][k];
		}

		if (n == 1)
			return 0;
		else {
			memo[n][k] = INF;
			for (int i = 0; i <= k; i++) {
				if (n > i + 1) { // 건너갈 수 있을때
					memo[n][k] = Math.min(DFS(n - i - 1, k - i) + dist[n - i - 2][n - 1], memo[n][k]);
				}
			}
		}
		return memo[n][k];

	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
