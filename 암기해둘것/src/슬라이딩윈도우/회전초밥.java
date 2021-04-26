package 슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 회전초밥 {
	private static int N, d, k, c;
	private static int[] arr;
	private static int[] eat;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		eat = new int[d + 1];
		arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		System.out.println(sol());
	}

	private static int sol() {
		int cnt = 0, max;

		for (int i = 0; i < k; i++) {
			if (eat[arr[i]] == 0)
				cnt++;
				eat[arr[i]]++;
		}
		max = cnt;

		for (int i = 1; i < N; i++) {
			if (max <= cnt) {
				if (eat[c] == 0)
					max = cnt + 1; // 쿠폰 사용 안햇으면
				else
					max = cnt;
			}

			eat[arr[i - 1]]--;
			if (eat[arr[i - 1]] == 0)
				cnt--;

			if (eat[arr[(i + k - 1) % N]] == 0)
				cnt++;
			eat[arr[(i + k - 1) % N]]++;
		}
		return max;
	}
}
