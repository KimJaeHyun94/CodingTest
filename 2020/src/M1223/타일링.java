package M1223;

public class 타일링 {
	private int MOD = 1000000007;

	public int solution(int n) {
        int answer = 0;
        int dp[] = new int[n+2];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
			dp[i] = (dp[i-2]+dp[i-1])%MOD;
		}
        answer = dp[n];
 
        return answer;
    }
}
