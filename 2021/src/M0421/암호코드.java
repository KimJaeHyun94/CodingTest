package M0421;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 암호코드 {
	static int mod = 1000000;
	static int dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();

		dp = new int[line.length() + 1];
		if (line.charAt(0)=='0') { // 0은 존재하지 않으므로
			System.out.println(0);
			System.exit(0);
		}
		
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= line.length(); i++) {
			if(line.charAt(i-1)=='0' && line.charAt(i-2)=='0') {
				System.out.println(0);
				System.exit(0);
			}
			if (line.charAt(i - 1) > '0') {
				dp[i] += dp[i - 1];
			}
			int su = (line.charAt(i - 2) - '0') * 10 + (line.charAt(i - 1) - '0');
			if (su >= 10 && su <= 26) { // 겹친다면
				dp[i] = (dp[i] + dp[i - 2]) % mod;
			}
		}

		System.out.println(dp[line.length()]%mod);
	}

}
