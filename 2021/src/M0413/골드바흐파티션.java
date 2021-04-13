package M0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 골드바흐파티션 {
	static int MAX = 1000000;
	static boolean prime[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		prime = new boolean[MAX + 1];
		prime[0] = true;
		prime[1] = true;

		for (int i = 2; i * i <= MAX; i++) {
			if (!prime[i]) {
				for (int j = i * i; j <= MAX; j += i) {
					prime[j] = true;
				}
			}
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int cnt = 0;
			for (int i = N / 2; i < N; i++) {
				if (!prime[i] && !prime[N - i]) {
					cnt++;
				}
			}
			System.out.println(cnt);
		}
	}
}
