package M0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 골드바흐의추측2 {
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
		StringBuilder sb = new StringBuilder();

		while (true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			boolean flag = false;
			for (int i = 2; i <= N/2; i++) {
				if (!prime[i] && !prime[N - i]) {
					sb.append(N).append(" = ").append(i).append(" + ").append(N-i).append("\n");
					flag = true;
					break;
				}
			}
			if(!flag) {
				sb.append("Goldbach's conjecture is wrong.").append("\n");
			}
		}
		System.out.println(sb);
	}

}
