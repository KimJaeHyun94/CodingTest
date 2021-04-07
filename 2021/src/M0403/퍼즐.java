package M0403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 퍼즐 {
	static String ans = "123456789";
	static int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int start = 0;
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				int temp = Integer.parseInt(st.nextToken());
				// 0일 때 9로 바꿔준다.
				if (temp == 0)
					temp = 9;
				start = (start * 10) + temp;
			}
		}
		String str = String.valueOf(start);
		int idx = str.indexOf('9'); // 9의 위치(0)
		BFS(start, idx);

		System.out.println(-1);
	}

	private static void BFS(int start, int idxs) {
		Queue<Node> q = new LinkedList<>();
		HashSet<Integer> visited = new HashSet<>();
		q.add(new Node(start, 0, idxs));
		visited.add(start);

		while (!q.isEmpty()) {
			Node cur = q.poll();
			int num = cur.num;
			int cnt = cur.cnt;
			int idx = cur.idx;

			String str = String.valueOf(num);
			if (str.equals(ans)) {
				System.out.println(cnt);
				System.exit(0);
			}

			int r = idx / 3;
			int c = idx % 3; // 2차원 배열로 생각해본다.

			for (int d = 0; d < dirs.length; d++) {
				int dr = r + dirs[d][0];
				int dc = c + dirs[d][1];

				if (!isOK(dr, dc))
					continue;

				StringBuilder sb = new StringBuilder(str);
	
				// swap
				char ch = sb.charAt(dr * 3 + dc);
				sb.setCharAt(dr * 3 + dc, '9');
				sb.setCharAt(idx, ch);

				int next = Integer.parseInt(sb.toString());
				if (!visited.contains(next)) {
					visited.add(next);
					q.add(new Node(next, cnt + 1, dr * 3 + dc));
				}
			}

		}

	}

	private static boolean isOK(int dr, int dc) {
		return dr >= 0 && dr < 3 && dc >= 0 && dc < 3;
	}

	static class Node {
		int num;
		int cnt;
		int idx;

		public Node(int num, int cnt, int idx) {
			this.num = num;
			this.cnt = cnt;
			this.idx = idx;
		}

	}

}
