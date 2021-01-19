package M0416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2579_계단오르기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[301];
		int dp[] = new int[301];
		
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		dp[1] = arr[1];
		dp[2] = dp[1]+arr[2];
		
		for (int i = 3; i <= N; i++) {
			int a = dp[i-3]+arr[i-1]+arr[i];
			int b = dp[i-2]+arr[i];
			
			dp[i] = Math.max(a, b);
		}
		System.out.println(dp[N]);
		
	}
}
