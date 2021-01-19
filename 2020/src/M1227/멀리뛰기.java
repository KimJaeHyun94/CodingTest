package M1227;

public class 멀리뛰기 {

	public static void main(String[] args) {
		System.out.println(solution(4));
	}

	public static long solution(int n) {
		long answer = 0;
		long mod = 1234567;
		
		long dp[] = new long[n+1];
       if(n<=2){
           return n;
       }
		dp[1] = 1;
        dp[2] = 2;
      
		for (int i = 3; i < n+1; i++) {
			dp[i] = (dp[i-1]+dp[i-2])%mod;
		}
        
		answer = dp[n];
		return dp[n];
	}
}
