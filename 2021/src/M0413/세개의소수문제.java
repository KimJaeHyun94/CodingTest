package M0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 세개의소수문제 {

	static int MAX = 1000;
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
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());

			if (!prime[N - 4]) {
				sb.append(2).append(" ").append(2).append(" ").append(N - 4).append("\n");
			} else {
				boolean flag = false;
				outloop: for (int i = 2; i <= MAX; i++) {
					for (int j = 2; j <= MAX; j++) {
						for (int k = 0; k <= MAX; k++) {
							if (!prime[i] && !prime[j] && !prime[k] && (i+j+k)==N) {
								sb.append(i).append(" ").append(j).append(" ").append(k).append("\n");
								flag = true;
								break outloop;
							}
						}
					}
				}
				if(!flag) {
					sb.append(0).append("\n");
				}
			}
		}
		System.out.println(sb);
	}

}
