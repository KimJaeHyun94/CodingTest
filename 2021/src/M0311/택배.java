package M0311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
/**
 * 
 * @author 김재현
 * @See https://jaimemin.tistory.com/764
 */

public class 택배 {
	static int N, C, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		ArrayList<Delivery> list = new ArrayList<>();
		int max[] = new int[N + 1];

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			list.add(new Delivery(s, e, w));
		}
		Collections.sort(list);
		int ans = 0;
		for (Delivery delivery : list) {
			int cnt = 0;
			int s = delivery.s;
			int e = delivery.e;
			int w = delivery.w;

			for (int i = s; i < e; i++) {
				cnt = Math.max(cnt, max[i]);
			}
			int space = Math.min(w, C - cnt);
			ans += space;
			for (int i = s; i < e; i++) {
				max[i] += space;
			}
		}
		System.out.println(ans);
	}

	static class Delivery implements Comparable<Delivery> {
		int s;
		int e;
		int w;

		public Delivery(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Delivery d) {
			if (e == d.e) {
				return s - d.s;
			}
			return e - d.e;
		}

	}
}
