package M0426;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 도둑 {
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
			int sum = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				if (i < M)
					sum += arr[i];
			}
			if (N == M) {
				if (sum < K) {
					sb.append(1).append("\n");
				} else
					sb.append(0).append("\n");
			} else {
				int left = 0;
				int right = M;

				int cnt = 0;

				while (left < N) {
					if (sum < K)
						cnt++;
					sum -= arr[left];
					sum += arr[right % N];
					left++;
					right++;

				}
				sb.append(cnt).append("\n");
			}

		}
		System.out.println(sb);
	}
}
