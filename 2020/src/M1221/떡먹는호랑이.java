package M1221;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 떡먹는호랑이 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int memo[][] = new int[D + 1][2];
		memo[1][0] = 1;
		memo[1][1] = 0;
		memo[2][0] = 0;
		memo[2][1] = 1;

		for (int i = 3; i <= D; i++) {
			memo[i][0] = memo[i - 2][0] + memo[i - 1][0];
			memo[i][1] = memo[i - 2][1] + memo[i - 1][1];
		}

		for (int i = 1; i <= K; i++) {
			for (int j = i; j <= K; j++) {
				if (memo[D][0] * i + memo[D][1] * j == K) {
					System.out.println(i);
					System.out.println(j);
					break;
				}
				if(memo[D][0]*i + memo[D][1]*j>K) {
					break;
				}
			}
		}
		return;

	}

}
