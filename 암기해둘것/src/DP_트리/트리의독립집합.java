package DP_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 트리의독립집합 {
	static List<Integer> graph[];
	static int arr[];
	static int N;
	static int dp[][];
	static ArrayList<Integer> list = new ArrayList<>();
	static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		graph = new List[N + 1];
		dp = new int[2][N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}

		DFS(1);

		System.out.println(Math.max(dp[0][1], dp[1][1]));

		if (dp[0][1] < dp[1][1]) {
			trace(1, 1, 0);
		} else {
			trace(1, 0, 0);
		}
		Collections.sort(list);
		for (int child : list) {
			System.out.print(child + " ");
		}
	}

	private static void trace(int idx, int include, int parent) {
		if (include == 1) {
			list.add(idx);
			for (int child : graph[idx]) {
				if (child != parent) {
					trace(child, 0, idx);
				}
			}
		} else {
			for (int child : graph[idx]) {
				if (child != parent) {
					if (dp[0][child] < dp[1][child]) {
						trace(child, 1, idx); // 자식이 포함
					} else {
						trace(child, 0, idx); // 자식 포함 X
					}
				}
			}
		}
	}

	private static void DFS(int idx) {
		if (visited[idx])
			return;
		visited[idx] = true;
		dp[0][idx] = 0;
		dp[1][idx] += arr[idx];
		for (int child : graph[idx]) {
			if (!visited[child]) {
				DFS(child);
				dp[0][idx] += Math.max(dp[0][child], dp[1][child]);
				dp[1][idx] += dp[0][child];
			}
		}

	}
}