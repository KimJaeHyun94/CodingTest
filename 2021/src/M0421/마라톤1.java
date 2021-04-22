package M0421;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 마라톤1 {
	static int N, K;
	static int sx, sy;
	static int dist, skip;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Node> list = new ArrayList<>();
		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());
		list.add(new Node(sx, sy));

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list.add(new Node(x, y));
			dist += Math.abs(sx - x) + Math.abs(sy - y);
			sx = x; sy = y;
		}

		for (int i = 1; i < N - 1; i++) {
			Node pre = list.get(i - 1);
			Node cur = list.get(i);
			Node next = list.get(i + 1);

			int nojump = Math.abs(pre.x - cur.x) + Math.abs(pre.y - cur.y) + Math.abs(cur.x - next.x)
					+ Math.abs(cur.y - next.y);
			int jump = Math.abs(pre.x - next.x) + Math.abs(pre.y - next.y);

			skip = Math.max(nojump - jump, skip);
		}
		System.out.println(dist - skip);

	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
