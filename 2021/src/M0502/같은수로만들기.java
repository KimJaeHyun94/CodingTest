package M0502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 같은수로만들기 {
	static int N, A[], ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		A = new int[N];

		int max = 0;
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, A[i]);
		}

		Conquer(max, 0, N - 1);
		System.out.println(ans);
	}

	private static int Conquer(int max, int i, int j) {
		int amax = A[i];
		int amin = A[j];
		for (int k = i; k <= j; k++) {
			amax = Math.max(amax, A[k]);
			amin = Math.min(amin, A[i]);
		}
		int loc = 0;
		for (int k = i; k <= j; k++) {
			if (A[k] == amax) {
				loc = k;
			}
		}
		ans += max - amax;
		if (i >= j) {
			return max - amax;
		} else if (i == loc) {
			return Conquer(amax, i + 1, j);
		} else if (j == loc) {
			return Conquer(amax, i, j - 1);
		} else {
			return Conquer(amax, i, loc - 1) + Conquer(amax, loc + 1, j);
		}

	}
}
