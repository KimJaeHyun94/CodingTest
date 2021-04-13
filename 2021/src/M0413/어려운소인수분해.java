package M0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 어려운소인수분해 {
	static int MAX = 5000000;
	static boolean prime[];
	static ArrayList<Integer> list = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
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
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			int s = Integer.parseInt(st.nextToken());

			solve(s);
		}
		System.out.println(sb);
	}

	private static void solve(int s) {
		outer: while (s > 1) {
			if (prime[s]) {
				for (int i = 0; i < list.size(); i++) {
					if (s % list.get(i) == 0) {
						sb.append(list.get(i)).append(" ");
						s /= list.get(i);
						break;
					}
				}
			} else {
				sb.append(s);
				break outer;
			}
		}
		sb.append("\n");
	}
}
