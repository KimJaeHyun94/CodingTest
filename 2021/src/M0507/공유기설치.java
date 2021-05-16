package M0507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 공유기설치 {
	static int N, C;
	static int map[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[N];

		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(map);

		int left = 1;
		int right = map[N - 1] - map[0]; // 최대거리
		int max = Integer.MIN_VALUE;
		while (left <= right) {
			int cnt = 1;
			int mid = (left + right) >> 1;
			int s = map[0];
			for (int i = 0; i < N; i++) {
				int d = map[i] - s;
				if (d >= mid) {
					cnt++;
					s = map[i];
				}
			}
			if (cnt >= C) {
				max = mid;
				left = mid + 1;
			} else {
				right = mid-1;
			}
		}
		System.out.println(max);
	}
}
