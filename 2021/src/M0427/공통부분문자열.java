package M0427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 공통부분문자열 {
	static String s1;
	static String s2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s1 = br.readLine();
		s2 = br.readLine();
		
		int dp[][] = new int[s1.length()+1][s2.length()+1];
		
		int max = 0;
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if(s1.charAt(i-1)==s2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1]+1;
					max = Math.max(dp[i][j], max);
				}
			}
		}
		System.out.println(max);
		
	}
	

}
