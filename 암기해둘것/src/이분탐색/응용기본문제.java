package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 응용기본문제 {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A[] = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			int s = Integer.parseInt(st.nextToken());
			sb.append(upperbound(A, s) - lowerbound(A, s) + " ");
		}
		System.out.println(sb);
	}

	private static int lowerbound(int[] arr, int s) {
		int start = 0;
		int end = N - 1;
		int mid = 0;

		while (start <= end) {
			mid = (start + end) >> 1;
			if (arr[mid] < s) {
				start = mid + 1;
			} else
				end = mid - 1;
		}
		return end + 1;
	}

	private static int upperbound(int[] arr, int s) {
		int start = 0;
		int end = N-1;
		int mid = 0;
		int idx = -1;
		while (start <= end) {
			mid = (start + end) >> 1;
			if (arr[mid] <= s) {
				start = mid + 1;
				idx = mid;
			} else
				end = mid - 1;
		}
		return idx;
	}
}