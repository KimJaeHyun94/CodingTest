package M0428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA5653_줄기세포배양 {
	static int N, M, K;
	static boolean visited[][];
	static int dirs[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static PriorityQueue<cell> q;
	static int time, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			q = new PriorityQueue<>(new Comparator<cell>() {
				@Override
				public int compare(cell o1, cell o2) {
					if (o1.s != o2.s) {
						return Integer.compare(o1.s, o2.s);
					} else
						return Integer.compare(o2.l, o1.l);
				}

			});
			visited = new boolean[500][500];
			ans = 0; time = 0;
			for (int i = 200; i < 200 + N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 200; j < 200 + M; j++) {
					int temp = Integer.parseInt(st.nextToken());
					if (temp != 0) {
						visited[i][j] = true;
						q.add(new cell(temp, temp + 1, i, j));
						if (temp * 2 > K)
							ans++;
					}
				}
			}
			solution();
			System.out.println("#" + tc + " " + ans);
		}
	}

	private static void solution() {
		while (time <= K) {
			cell temp = q.poll();
			time = temp.s;
			if (time > K)
				return;
			for (int i = 0; i < dirs.length; i++) {
				int dx = temp.x + dirs[i][0];
				int dy = temp.y + dirs[i][1];
				if (!visited[dx][dy]) {
					visited[dx][dy] = true;
					q.add(new cell(temp.l, time + temp.l + 1, dx, dy));
					if (time + temp.l * 2 > K)
						ans++;
				}
			}
		}
	}

	static class cell {
		int l;
		int s;
		int x;
		int y;

		public cell(int l, int s, int x, int y) {
			this.l = l;
			this.s = s;
			this.x = x;
			this.y = y;
		}
	}
}
