package M0514;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 국영수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		ArrayList<Score> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int k = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			list.add(new Score(name, k, e, m));
		}

		Collections.sort(list);

		StringBuilder sb = new StringBuilder();
		for (Score score : list) {
			sb.append(score.name).append("\n");
		}
		System.out.println(sb);
	}

	static class Score implements Comparable<Score> {
		String name;
		int k;
		int e;
		int m;

		public Score(String name, int k, int e, int m) {
			this.name = name;
			this.k = k;
			this.e = e;
			this.m = m;
		}

		@Override
		public int compareTo(Score o) {
			if (this.k == o.k) {
				if (this.e == o.e) {
					if (this.m == o.m) {
						return this.name.compareTo(o.name);
					}
					return o.m - this.m;
				}
				return this.e - o.e;
			}
			return o.k - this.k;
		}

	}
}
