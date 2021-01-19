package M0416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ11726_2Xn타일링 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int memo[] = new int[1001];
		memo[1] = 1;
		memo[2] = 2;
		for (int i = 3; i <=N; i++) {
			memo[i] = (memo[i-2]+memo[i-1])%10007;
		}
		System.out.println(memo[N]);
	}
}
