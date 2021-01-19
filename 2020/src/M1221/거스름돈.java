package M1221;

public class 거스름돈 {
	public static void main(String[] args) {
		System.out.println(solution(5, new int[] { 1, 2, 5 }));
	}

	public static int solution(int n, int[] money) {
		int answer = 0;

		int[] dp = new int[n+1];
		dp[0] = 1;
		int c  = money.length;
		
		for (int i = 0; i < c; i++) {
			for (int j = money[i]; j <= n; j++) {
				dp[j] += dp[j - money[i]];
			}
		}
		answer = dp[n];
		return answer;
	}
}
