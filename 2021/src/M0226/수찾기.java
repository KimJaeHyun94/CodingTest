package M0226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 수찾기 {
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
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int s = Integer.parseInt(st.nextToken());
			int start = 0;
			int end = N - 1;
			int mid = 0;
			boolean flag = false;
			while (start <= end) {
				mid = (start + end) / 2;
				if (s == A[mid]) {
					System.out.println(1);
					flag = true;
					break;
				} else {
					if (s < A[mid]) {
						end = mid - 1;
					} else {
						start = mid + 1;
					}
				}
			}
			if (!flag)
				System.out.println(0);
		}
	}
}
