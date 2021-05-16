package 누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 도둑누적합 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int[] arr = new int[N];
			int[] psum = new int[N + M];
			int sum = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				if (i < M)
					sum += arr[i];
			}
			for (int i = 1; i < N + M; i++) {
				psum[i] = psum[i - 1] + arr[(i - 1) % N];
			}
			int cnt = 0;
			if (N == M) {
				if (sum < K) {
					sb.append(1).append("\n");
				} else
					sb.append(0).append("\n");
			} else {
				for (int i = M; i < N + M; i++) {
					if (psum[i] - psum[i - M] < K)
						cnt++;

				}
				sb.append(cnt).append("\n");
			}

		}
		System.out.println(sb);
	}
}
