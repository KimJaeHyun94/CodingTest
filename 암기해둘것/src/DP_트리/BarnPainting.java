package DP_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BarnPainting {
	static List<Integer> graph[];
	static int N, K;
	static long dp[][];
	static boolean isend[];
	static int already[];
	static int mod = 1000000007;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		graph = new List[N];
		dp = new long[N][3];
		already = new int[N];
		Arrays.fill(already, -1);
		isend = new boolean[N];

		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			graph[a].add(b);
			graph[b].add(a);
		}

		for (int i = 1; i < N; i++) {
			if (graph[i].size() == 1) {
				isend[i] = true;
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int v =  Integer.parseInt(st.nextToken()) - 1;
			int c =  Integer.parseInt(st.nextToken()) - 1;
			already[v] = c;
		}

		DFS(0, 0);

		System.out.println((dp[0][0] + dp[0][1] + dp[0][2]) % mod);
	}

	private static void DFS(int cnt, int parent) {
		if (isend[cnt]) { // 맨 마지막이에 색칠
			for (int i = 0; i < 3; i++) {
				if (already[cnt] == -1 || already[cnt] == i) {
					dp[cnt][i] = 1;
				}
			}
			return;
		}

		for (int child : graph[cnt]) {
			if (child != parent) {
				DFS(child, cnt);
			}
		}

		for (int i = 0; i < 3; i++) {
			if (already[cnt] == -1 || already[cnt] == i) {
				long temp = 1;

				for (int child : graph[cnt]) {
					if (child != parent) {
						long cl = 0;
						for (int j = 0; j < 3; j++) {
							if (j == i)
								continue;
							cl += dp[child][j];
						}
						temp *= cl;
						temp %= mod;
					}
				}
				dp[cnt][i] = temp;
			}
		}
	}
}