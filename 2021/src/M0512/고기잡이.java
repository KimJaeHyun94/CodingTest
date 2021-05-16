package M0512;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 고기잡이 {
	static int N, I, M;
	static ArrayList<Node> list;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		I = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		max = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			list.add(new Node(x, y));
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 1; k < I / 2; k++) {
					int cnt = Count(list.get(i).x, list.get(j).y, k, I / 2 - k);
					max = Math.max(max, cnt);
				}

			}
		}
		
		System.out.println(max);
	}

	private static int Count(int x, int y, int k, int j) {
		int dx = x + k;
		int dy = y + j;
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
