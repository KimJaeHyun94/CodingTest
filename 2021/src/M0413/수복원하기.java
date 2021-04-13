package M0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 수복원하기 {
	static int MAX = 100000;
	static boolean prime[];
	static ArrayList<Integer> list = new ArrayList<>();

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

		for (int i = 2; i <= MAX; i++) {
			if (!prime[i])
				list.add(i);
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());

			for (int i = 0; i < list.size(); i++) {
				int p = list.get(i);

				int cnt = 0;
				if (p > N || p<=1)
					break;
				while (N % p == 0) {
					cnt++;
					N /= p;
				}
				if (cnt >= 1) {
					System.out.println(p + " " + cnt);
				}
			}
		}
	}
}
