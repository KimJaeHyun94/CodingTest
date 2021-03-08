package M0307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author 김재현
 * 선택을 하지 않아도 되는 dp
 */
public class 포도주시식 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];
		int[] dp = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		if (N == 1) {
			System.out.println(arr[1]);
		} else if (N == 2) {
			System.out.println(arr[1] + arr[2]);
		} else {
			dp[1] = arr[1];
			dp[2] = arr[1] + arr[2];
			dp[3] = Math.max(dp[2], Math.max(arr[1] + arr[3], arr[2] + arr[3]));
			if (N == 3) {
				System.out.println(dp[3]);
			} else {
				for (int i = 4; i <= N; i++) {
					dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + arr[i], dp[i - 3] + arr[i - 1] + arr[i]));
				}
				System.out.println(dp[N]);
			}
		}
	}
}
