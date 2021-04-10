package M0408;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 소형기관차 {
	static int N, max;
	static int arr[];
	static int dp[][];
	static int sum[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		dp = new int[4][N+1];
		sum = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			arr[i]= Integer.parseInt(st.nextToken());
			sum[i] = sum[i-1]+arr[i];
		}
		max = Integer.parseInt(br.readLine());
		
		for (int i = 1; i < 4; i++) {
			for (int j = i*max; j <= N; j++) {
				dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-max]+(sum[j]-sum[j-max]));
			}
		}
		
		System.out.println(dp[3][N]);
		
		
		
	}
}
