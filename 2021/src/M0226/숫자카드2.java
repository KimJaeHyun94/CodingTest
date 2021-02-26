package M0226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 숫자카드2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A[] = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);

		int M = Integer.parseInt(br.readLine());
		int ans[] = new int[M];
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			int s = Integer.parseInt(st.nextToken());
			sb.append(upperbound(A, s, N) - lowerbound(A, s, N) + " ");
		}
		System.out.println(sb);
	}

	private static int lowerbound(int[] arr, int s, int n) {
		int start = 0;
		int end = n;
		int mid = 0;

		while (start < end) {
			mid = (start + end) >> 1;
			if (arr[mid] >= s) {
				end = mid;
			} else
				start = mid + 1;
		}
		return end;
	}

	private static int upperbound(int[] arr, int s, int n) {
		int start = 0;
		int end = n;
		int mid = 0;

		while (start < end) {
			mid = (start + end) >> 1;
			if (arr[mid] <= s) {
				start = mid + 1;
			} else
				end = mid;

		}
		return end;
	}
}
