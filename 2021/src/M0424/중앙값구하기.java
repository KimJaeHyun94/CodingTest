package M0424;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 중앙값구하기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 0; tc < T; tc++) {
			PriorityQueue<Integer> min = new PriorityQueue<>();
			PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());

			int N = Integer.parseInt(br.readLine());
			sb.append((N + 1) / 2).append("\n");

			int cnt = 0;
			StringTokenizer st = null;
			for (int i = 0; i < N; i++) {
				if (i % 10 == 0) {
					st = new StringTokenizer(br.readLine());
				}
				int num = Integer.parseInt(st.nextToken());
				if (max.size() == min.size()) {
					if (min.isEmpty()) {
						max.add(num);
					} else {
						if (min.peek() <= num) {
							int t = min.peek();
							min.poll();
							min.add(num);
							num = t;
						}
						max.add(num);
					}
				} else {
					if (max.peek() > num) {
						int t = max.peek();
						max.poll();
						max.add(num);
						num = t;
					}
					min.add(num);
				}
				if (i % 2 == 0) {
					if (cnt == 9 || i == N - 1) {
						sb.append(max.peek()).append("\n");
						cnt = 0;
					} else
						sb.append(max.peek()).append(" ");
					cnt++;
				}
			}
		}
		System.out.println(sb);
	}
}
