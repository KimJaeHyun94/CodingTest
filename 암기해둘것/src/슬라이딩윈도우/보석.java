package 슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 보석 {
	static int N, M, T, K;
	static ArrayList<Node> list;
	static int max, r, c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();
		max = 0;

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			list.add(new Node(x, y));
		}

		for (int i = 0; i < T; i++) {
			for (int j = 0; j < T; j++) {
				int x;
				int y;

				if (list.get(i).x + K > N) {
					x = N - K;
				} else {
					x = list.get(i).x;
				}

				if (list.get(j).y + K > M) {
					y = M - K;
				} else {
					y = list.get(j).y;
				}

				int cnt = Count(x, y);

				if (cnt > max) {
					max = cnt;
					r = x;
					c = y + K;
				}
			}
		}
		System.out.println(r + " " + c);
		System.out.println(max);
	}

	private static int Count(int x, int y) {
		int dx = x + K, dy = y + K;
		int cnt = 0;
		for (Node node : list) {
			if (x <= node.x && node.x <= dx && y <= node.y && node.y <= dy) {
				cnt++;
			}
		}
		return cnt;
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
