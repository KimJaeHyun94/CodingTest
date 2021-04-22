package M0416;

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
		if (sum % 3 != 0) { // 3개가 3으로 나눠떨어지지 않으면 같은 개수로 못만듬
			System.out.println(0);
			System.exit(0);
		}
		BFS(a, b, c);

		System.out.println(0);
	}

	private static void BFS(int sa, int sb, int sc) {
		Queue<Stone> q = new LinkedList<>();
		q.add(new Stone(sa, sb, sc));
		boolean visited[][] = new boolean[1501][1501]; // 최대 1000까지
		visited[sa][sb] = true;
		
		while (!q.isEmpty()) {
			Stone cur = q.poll();

			int a = cur.a;
			int b = cur.b;
			int c = cur.c;

			if (a == b && b == c) {
				System.out.println(1);
				System.exit(0);
			}
			
			int X = 0;
			int Y = 0;
			
			if (a > b) {
				X = b + b; // 작은쪽
				Y = a - b; // 큰쪽
				if (!visited[X][Y]) {
					visited[X][Y] = true;
					q.add(new Stone(Y, X, sum - (X + Y)));
				}
			}

			if (b > a) {
				X = a + a;
				Y = b - a;
				if (!visited[X][Y]) {
					visited[X][Y] = true;
					q.add(new Stone(X, Y, sum - (X + Y)));
				}
			}
			if (c > a) {
				X = a + a;
				Y = c - a;
				if (!visited[X][Y]) {
					visited[X][Y] = true;
					q.add(new Stone(X, sum - (X + Y), Y));
				}
			}
			if (a > c) {
				X = c + c;
				Y = a - c;
				if (!visited[X][Y]) {
					visited[X][Y] = true;
					q.add(new Stone(Y, sum - (X + Y), X));
				}
			}
			if (b > c) {
				X = c + c;
				Y = b - c;
				if (!visited[X][Y]) {
					visited[X][Y] = true;
					q.add(new Stone(sum-(X+Y),Y, X));
				}
			}
			if (c > b) {
				X = b + b;
				Y = c - b;
				if (!visited[X][Y]) {
					visited[X][Y] = true;
					q.add(new Stone(sum-(X+Y),X, Y));
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
