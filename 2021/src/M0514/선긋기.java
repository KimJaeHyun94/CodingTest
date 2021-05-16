package M0514;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 선긋기 {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		ArrayList<Pos> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());
			list.add(new Pos(x, y));
		}

		Collections.sort(list);

		long ans = 0;
		long x = list.get(0).x;
		long y = list.get(0).y;

		for (int i = 1; i < N; i++) {
			if (y > list.get(i).x) { // 겹친다면
				y = Math.max(y, list.get(i).y);		//합치기
			} else {			//안겹치면
				ans += y - x;
				x = list.get(i).x;
				y = list.get(i).y;
			}
		}
		ans += y - x;
		System.out.println(ans);
	}

	static class Pos implements Comparable<Pos> {
		long x;
		long y;

		public Pos(long x, long y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pos o) {
			return Long.compare(this.x, o.x);
		}

	}
}
