package M0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 네개의소수 {
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
		int N = Integer.parseInt(br.readLine());
		if (N < 8) {
			System.out.println(-1);
		} else {
			if (N % 2 == 0) {
				N-=4;
				for (int i = N / 2; i < N; i++) {
					if (!prime[i] && !prime[N - i]) {
						System.out.println(2 + " " + 2 + " " + (N-i) + " " + i);
						System.exit(0);
					}
				}
			} else {
				N-=5;
				for (int i = N / 2; i < N; i++) {
					if (!prime[i] && !prime[N - i]) {
						System.out.println(2 + " " + 3 + " " + (N-i) + " " + i);
						System.exit(0);
					}
				}
			}
		}
	}
}
