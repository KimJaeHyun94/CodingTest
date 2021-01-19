package M0116;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * @See https://onejunu.tistory.com/106
 * @Author AKKabiyo
 */
public class 전구와스위치 {
	static int arr[];
	static int sol[];
	static int copy[];
	static int N;
	static int ans1 = 0, ans2 = 0, ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		sol = new int[N];
		String line = br.readLine();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = line.charAt(i)-'0';
		}
		line = br.readLine();
		for (int i = 0; i < sol.length; i++) {
			sol[i] = line.charAt(i)-'0';
		}
		copy = arr.clone();

		switching(0, copy);
		ans2=1;
		for (int i = 1; i < N; i++) {
			if (arr[i - 1] != sol[i - 1]) {
				switching(i, arr);
				ans1++;
			}
			if (copy[i - 1] != sol[i - 1]) {
				switching(i, copy);
				ans2++;
			}
		}

		if (Arrays.equals(arr, sol)) {
			ans = ans1;
		}

		if (Arrays.equals(copy, sol)) {
			ans = Math.min(ans, ans2);
		}

		if (ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
	}

	private static void switching(int i, int[] arr) {
		for (int j = i - 1; j <= i+1; j++) {
			if (isOK(j)) {
				if (arr[j] == 1)
					arr[j] = 0;
				else
					arr[j] = 1;
			}
		}
	}

	private static boolean isOK(int i) {
		return i >= 0 && i < N;
	}
}
