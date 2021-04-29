package M0428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간나누기2 {
	static int N, M;
	static int arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		int right = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right, arr[i]);
		}
		int left = 0;

		while (left <= right) {
			int mid = (left + right) >> 1;

			if (check(mid)) {
				right = mid - 1;
			} else
				left = mid + 1;
		}

		System.out.println(left);
	}

	private static boolean check(int mid) {
		int min = arr[0];
		int max = arr[0];
		int go = 1;
		for (int i = 1; i < N; i++) {
			max = Math.max(arr[i], max);
			min = Math.min(arr[i], min);

			if (mid < max - min) {
				max = arr[i];
				min = arr[i];
				go++;
				if (go > M) { // 구간을 M개 이하로 나눠야한다.
					return false;
				}
			}
		}
		return true;
	}
}
