package 누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 개똥벌레 {
	static int N, H;
	static int suk[], jong[], check[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		suk = new int[N / 2];
		jong = new int[N / 2];
		check = new int[H + 1];
		int idx = 0, idx2 = 0;

		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) {
				suk[idx++] = Integer.parseInt(br.readLine());
			} else {
				jong[idx2++] = Integer.parseInt(br.readLine());
			}
		}

		Arrays.sort(suk);
		Arrays.sort(jong);
		int min = Integer.MAX_VALUE;

		for (int i = 1; i <= H; i++) {
			int s = binarySearch(0, N / 2 - 1, i, suk);
			int j = binarySearch(0, N / 2 - 1, H - i + 1, jong);
			check[i] = s + j;
			if (min > check[i])
				min = check[i];
		}
		int cnt = 0;
		for (int i = 1; i <= H; i++) {
			if (min == check[i]) {
				cnt++;
			}
		}
		System.out.println(min + " " + cnt);

	}

	private static int binarySearch(int left, int right, int h, int[] arr) {

		int min = Integer.MAX_VALUE;

		while (left <= right) {
			int mid = (left + right) >> 1;
			if (h <= arr[mid]) {
				min = Math.min(min, mid);
				right = mid - 1;
			} else
				left = mid + 1;
		}
		if (min == Integer.MAX_VALUE)
			return 0;
		else
			return N / 2 - min;
	}
}
