package M0201;

import java.util.*;

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
			Arrays.fill(arr, INF); // 초기화 시켜준다
		}
		dp[0][gps_log[0]] = 0; // 시작점에 0을 넣어준다
		for (int i = 1; i < k; i++) { // 거점 정보들의 총 개수
			for (int j = 1; j < n + 1; j++) { // 거점 개수

				dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]); // 예전 거점 정보와의 비교를 통해 값을 갱신 시킨다. (최소한의 오류 수정)

				for (int node : graph[j]) {
					dp[i][j] = Math.min(dp[i - 1][node], dp[i][j]); // 그래프 내 연결되어있는 노드들 중에 최소 값을 저장해둔다.
				}
				if (gps_log[i] != j) { // 경로 위치가 다른경우 증가시킨다.(위치가 다른 경우는 수정한 것이므로)
					dp[i][j] += 1;
				}
			}
		}
		if (dp[k - 1][gps_log[k - 1]] >= INF) { // INF라면 정답이 없다
			answer = -1;
		} else {
			answer = dp[k - 1][gps_log[k - 1]];   
		}
		return answer;
	}
}
