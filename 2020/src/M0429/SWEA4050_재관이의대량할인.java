package M0429;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class SWEA4050_재관이의대량할인 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			Integer map[] = new Integer[N];
			int sum = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				map[i] = Integer.parseInt(st.nextToken());
				sum += map[i];
			}
			Arrays.sort(map, Collections.reverseOrder());

			for (int i = 0; i < N-2; i += 3) {
				int min = Math.min(map[i], map[i + 1]);
				min = Math.min(min, map[i + 2]);
				sum-=min;
			}
			sb.append("#" + tc + " " + sum + "\n");
		}
		System.out.println(sb);
	}
}
