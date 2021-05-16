package M0511;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 암벽등반_2 {
	static int n, T;
	static List<Point> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list.add(new Point(x, y));
		}
		Collections.sort(list);
		BFS();
	}

	private static void BFS() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0)); // 0,0에서 시작
		boolean visited[] = new boolean[list.size()];
		int cnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point cur = q.poll();
				int r = cur.r;
				int c = cur.c;

				if (c == T) {
					System.out.println(cnt);
					return;
				}
				for (int j = r - 2; j <= r + 2; j++) { // 앞 뒤로 거리가 2개 되는 것중에서
					for (int k = c - 2; k <= c + 2; k++) {
						if (j < 0 || k < 0)
							continue;

						int idx = binary(j, k);
						if (idx == -1 || visited[idx])
							continue;

						visited[idx] = true;
						q.add(new Point(j, k));
					}
				}
			}
			cnt++;
		}

		System.out.println(-1);
	}

	private static int binary(int nr, int nc) {
		int result = -1;
		int left = 0;
		int right = n - 1;

		while (left <= right) {
			int mid = (left + right) >> 1;

			Point cur = list.get(mid);

			if (cur.r > nr) {
				right = mid - 1;
			} else if (cur.r < nr) {
				left = mid + 1;
			} else {
				if (cur.c < nc) {
					left = mid + 1;
				} else if (cur.c > nc) {
					right = mid - 1;
				} else {
					result = mid;
					break;
				}
			}
		}
		return result;
	}

	static class Point implements Comparable<Point> {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Point o) {
			if (this.r == o.r)
				return this.c - o.c;

			return this.r - o.r;
		}

	}
}
