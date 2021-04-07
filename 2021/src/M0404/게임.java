package M0404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 게임 {
	static long X, Y, Z;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		Z = Y * 100 / X;

		if (Z >= 99) {
			System.out.println(-1);
		} else {
			System.out.println(binarySearch(1, X));
		}

	}

	private static long binarySearch(long start, long end) {
		while (start <= end) {
			long mid = (start + end) >> 1;
			long ratio = (Y + mid) * 100 / (X + mid);

			if (ratio > Z) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return start;
	}
}
