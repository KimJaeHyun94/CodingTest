package M0516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 소수인팰린드롬 {
	static int A, B;
	static ArrayList<Integer> prime;
	static int dp[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		if (B > 10000000)
			B = 10000000;

		boolean[] cache = new boolean[B + 1];
		cache[0] = cache[1] = false;

		for (int i = 2; i <= B; i++) {
			cache[i] = true;
		}
		for (int i = 2; i * i <= B; i++) {
			if (cache[i]) {
				for (int j = i * i; j <= B; j += i) {
					if (cache[j]) {
						cache[j] = false;
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = A; i <= B; i++) {
			if (palin(i) && cache[i]) {
				sb.append(i).append("\n");
			}
		}
		sb.append(-1).append("\n");
		System.out.println(sb);

	}

	private static boolean palin(int s) {
		String origin = String.valueOf(s);
		StringBuilder sb = new StringBuilder(origin);
		String str = sb.reverse().toString();

		if (origin.equals(str))
			return true;
		return false;
	}
}
