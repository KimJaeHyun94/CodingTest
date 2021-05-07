package 두포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 합이0인네정수 {
	static int N, arr[][];
	static int AB[], CD[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][4];
		AB = new int[N * N];
		CD = new int[N * N];
		StringTokenizer st;
		int Abid = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken()); // A
			arr[i][1] = Integer.parseInt(st.nextToken()); // B
			arr[i][2] = Integer.parseInt(st.nextToken()); // C
			arr[i][3] = Integer.parseInt(st.nextToken()); // D
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				AB[Abid] = arr[i][0] + arr[j][1]; // AB곱셈
				CD[Abid] = arr[i][2] + arr[j][3]; // CD곱셈
				Abid++;
			}
		}
		Arrays.sort(AB);
		Arrays.sort(CD);

		int left = 0;
		int right = N * N - 1;
		long ans = 0;

		while (left < N * N && right >= 0) {
			int ABsum = AB[left];
			int CDsum = CD[right];

			int sum = ABsum + CDsum;
			if (sum == 0) {
				int acnt = 0;
				int bcnt = 0;

				while (left < N*N  && ABsum == AB[left]) {
					left++;
					acnt++;
				}
				while (right >= 0 && CDsum == CD[right]) {
					right--;
					bcnt++;
				}
				ans += (long) (acnt * bcnt);

			} else if (sum > 0) {
				right--;
			} else
				left++;

		}
		System.out.println(ans);
	}
}
