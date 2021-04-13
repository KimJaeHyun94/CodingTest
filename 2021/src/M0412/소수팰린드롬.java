package M0412;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 소수팰린드롬 {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		int s = N;
		while (true) {
			if (isPrime(s)) {
				if (isPalin(s)) {
					System.out.println(s);
					break;
				}
			}
			s++;
		}
	}

	private static boolean isPalin(int s) {
		String origin = String.valueOf(s);
		StringBuilder sb = new StringBuilder(origin);
		String str = sb.reverse().toString();

		if (origin.equals(str))
			return true;
		return false;
	}

	private static boolean isPrime(int s) {
		if(s==1)
			return false;
		for (int i = 2; i * i <= s; i++) {
			if (s % i == 0)
				return false;
		}
		return true;
	}
}
