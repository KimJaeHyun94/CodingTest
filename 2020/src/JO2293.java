import java.util.Scanner;

public class JO2293 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int coins[] =new int[N+1];
		int[] dp=new int[K+1];
		
		for (int i = 1; i <= N; i++) {
			coins[i] = sc.nextInt();
		}
		dp[0]=1;
		for(int i=1; i<=N; i++) {
		    for(int j=coins[i]; j<=K; j++) {
		        dp[j] += dp[j-coins[i]];
		    } 
		}
		System.out.println(dp[K]);
	}
}