package M0404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 합이0인네정수 {
	static int N, arr[][];
	static long AB[], CD[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][4];
		AB = new long[N * N];
		CD = new long[N * N];
		StringTokenizer st;
		int index = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken()); // A
			arr[i][1] = Integer.parseInt(st.nextToken()); // B
			arr[i][2] = Integer.parseInt(st.nextToken()); // C
			arr[i][3] = Integer.parseInt(st.nextToken()); // D
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				AB[index] = arr[i][0] + arr[j][1]; // AB곱셈
				CD[index] = arr[i][2] + arr[j][3]; // CD곱셈
				index++;
			}
		}
		Arrays.sort(AB);
		Arrays.sort(CD);

		int left = 0;
		int right = N * N - 1; // 마지막
		long ans = 0;

		while (left < N * N && right >= 0) {
			long left_val = AB[left];
			long right_val = CD[right];

			if (left_val + right_val == 0) {
				long left_cnt = 0;
				while (left < AB.length && AB[left] == left_val) {
					left_cnt++;
					left++;
				}
				long right_cnt = 0;
				while (right >= 0 && CD[right] == right_val) {
					right_cnt++;
					right--;
				}
				ans += right_cnt * left_cnt;
			}
			if (left_val + right_val < 0) {
				left++;
			} else if (left_val + right_val > 0) {
				right--;
			}

		}
		System.out.println(ans);
	}
}
