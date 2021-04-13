package M0412;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 용액합성하기 {
	static int N;
	static int arr[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0;
		int right = N - 1;
		int check = Integer.MAX_VALUE;

		while (left < right) {

			int sum = arr[left] + arr[right];

			if (Math.abs(check) > Math.abs(sum)) {
				check = sum;
			}

			if (sum == 0) {
				System.out.println(0);
				System.exit(0);
			}

			if (sum < 0) {
				left++;
			} else {
				right--;
			}
		}
		System.out.println(check);
	}
}
