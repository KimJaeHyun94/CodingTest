package M0107;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GPS {
	public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
		int answer = 0;
		int INF = 987654321;
		int dp[][] = new int[k][n + 1];
		List<Integer>[] graph = new List[n + 1];

		for (int i = 1; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < edge_list.length; i++) {
			int a = edge_list[i][0];
			int b = edge_list[i][1];

			graph[a].add(b);
			graph[b].add(a);
		}
		for (int arr[] : dp) {
			Arrays.fill(arr, INF);
		}

		dp[0][gps_log[0]] = 0;
		for (int i = 1; i < k; i++) {
			for (int j = 1; j < n + 1; j++) {

				dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);

				for (int node : graph[j]) {
					dp[i][j] = Math.min(dp[i - 1][node], dp[i][j]);
				}
				if (gps_log[i] != j) {
					dp[i][j] += 1;
				}
			}
		}

		if (dp[k - 1][gps_log[k - 1]] >= INF) {
			answer = -1;
		} else {
			answer = dp[k - 1][gps_log[k - 1]];
		}

		return answer;
	}
}
