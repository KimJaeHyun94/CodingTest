package M0510;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 버블소트1517 {
	static int N;
	static int arr[], B[];
	static int cnt;
	static long result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		B = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		mergesort(0, N - 1);

		System.out.println(result);
	}

	private static void mergesort(int left, int right) {
		if (left < right) {
			int mid = (left + right) >> 1;
			mergesort(left, mid);
			mergesort(mid + 1, right);
			merge(left, mid, right);
		}

	}

	private static void merge(int left, int mid, int right) {
		int i = left;
		int j = mid + 1;
		int idx = 0;
		while (i <= mid || j <= right) {
			if (i <= mid && (j > right || arr[i] <= arr[j])) {
				B[idx++] = arr[i++];
			} else {
				B[idx++] = arr[j++];
				result += (long) mid - i + 1;
			}
		}

		for (int k = left; k <= right; k++) {
			arr[k] = B[k - left];
		}
	}
}
