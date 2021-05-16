package M0511;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 암벽등반 {
	static int n, T;
	static List<Integer> list[];
	static Set<Integer> set[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		list = new List[n + 1];
		set = new HashSet[n + 1];
		for (int i = 0; i <= n; i++) {
			list[i] = new ArrayList<>();
			set[i] = new HashSet<>();
		}
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if(y > T) continue;
			list[y].add(x);
		}
		BFS();
	}

	private static void BFS() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0)); // 0,0에서 시작
		set[0].add(0);	//2차원 visited 배열 사용 시 메모리 초과
		int cnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point cur = q.poll();
				int r = cur.r;
				int c = cur.c;

				if (r == T) {
					System.out.println(cnt);
					return;
				}
				for (int j = Math.max(r - 2, 0); j <= r + 2; j++) { // 앞 뒤로 거리가 2개 되는 것중에서
					
					for (Integer child : list[j]) {
						if (Math.abs(child - c) <= 2 && set[j].add(child)) {
							q.add(new Point(i, child));
						}
					}
				}
			}
			cnt++;
		}

		System.out.println(-1);
	}

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}
}
