package M0315;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 돌그룹 {
	static int sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		sum = a + b + c;
		if (sum % 3 != 0) {
			System.out.println(0);
			System.exit(0);
		}
		BFS(a, b, c);

		System.out.println(0);
	}

	private static void BFS(int a, int b, int c) {
		Queue<Stone> q = new LinkedList<>();
		boolean visited[][] = new boolean[1501][1501];
		q.add(new Stone(a, b, c));
		visited[a][b] = true;

		while (!q.isEmpty()) {
			Stone cur = q.poll();
			if (cur.a == cur.b && cur.b == cur.c) {
				System.out.println(1);
				System.exit(0);
			}

			if (cur.a > cur.b) {
				int Y = cur.a - cur.b;
				int X = cur.b + cur.b;

				if (!visited[Y][X]) {
					visited[Y][X] = true;
					q.add(new Stone(Y, X, sum - (X + Y)));
				}
			}
			if (cur.b > cur.a) {
				int Y = cur.b - cur.a;
				int X = cur.a + cur.a;

				if (!visited[X][Y]) {
					visited[X][Y] = true;
					q.add(new Stone(X, Y, sum - (X + Y)));
				}
			}
			if (cur.b < cur.c) {
				int Y = cur.c - cur.b;
				int X = cur.b + cur.b;

				if (!visited[X][Y]) {
					visited[X][Y] = true;
					q.add(new Stone(sum - (X + Y), X, Y));
				}
			}
			if (cur.b > cur.c) {
				int Y = cur.b - cur.c;
				int X = cur.c + cur.c;

				if (!visited[Y][X]) {
					visited[Y][X] = true;
					q.add(new Stone(sum - (X + Y), Y, X));
				}
			}
			if (cur.a > cur.c) {
				int Y = cur.a - cur.c;
				int X = cur.c + cur.c;

				if (!visited[Y][X]) {
					visited[Y][X] = true;
					q.add(new Stone(Y, sum - (X + Y), X));
				}
			}
			if (cur.a < cur.c) {
				int Y = cur.c - cur.a;
				int X = cur.a + cur.a;

				if (!visited[X][Y]) {
					visited[X][Y] = true;
					q.add(new Stone(X, sum - (X + Y), Y));
				}
			}
		}
	}

	static class Stone {
		int a;
		int b;
		int c;

		public Stone(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}
}
