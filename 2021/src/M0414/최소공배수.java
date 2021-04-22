package M0414;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 최소공배수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int gcd = 0;
			if (a > b) {
				gcd = getGCD(a, b);
			} else {
				gcd = getGCD(b, a);
			}

			int lcm = getLCM(a, b, gcd);
			
			System.out.println(lcm);
		}
		
	}
	
	private static int getLCM(int a, int b, int gcd) {
		return a * b / gcd;
	}

	private static int getGCD(int a, int b) {
		if (b == 0)
			return a;
		return getGCD(b, a % b);
	}
}
