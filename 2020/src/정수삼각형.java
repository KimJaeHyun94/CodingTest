
public class 정수삼각형 {

	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{7},{3,8},{8,1,0},{2,7,4,4},{4,5,2,6,5}}));

	}

	public static int solution(int[][] triangle) {
		int answer = 0;
		int N = triangle.length;
		int dp[][] = new int[N][N];
		dp[0][0] = triangle[0][0];
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < triangle[i].length; j++) {
				// 왼쪽
				if (j == 0) {
					dp[i][j] = dp[i - 1][j] + triangle[i][j];
				}
				// 오른쪽
				else if (j == triangle[i].length-1) {
					dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
				} else {
					dp[i][j] = dp[i - 1][j - 1] > dp[i - 1][j] ? dp[i - 1][j - 1] + triangle[i][j]
							: dp[i - 1][j] + triangle[i][j];
				}
			}
		}

		for (int i = 0; i < dp[N - 1].length; i++) {
			System.out.println(dp[N-1][i]);
			answer = Math.max(answer, dp[N - 1][i]);
		}

		return answer;
	}
}
