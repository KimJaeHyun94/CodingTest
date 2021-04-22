package M0421;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 선분위의점 {
	static int N, M;
	static int arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			int ps = UpperBound(s);
			int pe = LowerBound(e);

			sb.append(pe - ps).append("\n");

		}
		System.out.println(sb);
	}

	static int LowerBound(int target) {
		int left = 0;
		int right = N - 1;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (arr[mid] <= target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return right + 1;
	}

	static int UpperBound(int target) {
		int left = 0;
		int right = N - 1;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (arr[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return left;
	}
}
