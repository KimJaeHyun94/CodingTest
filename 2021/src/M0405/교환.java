package M0405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 교환 {
	static int N, K;
	static int max = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		BFS();
		System.out.println(max);
	}

	private static void BFS() {
		String str = String.valueOf(N);
		int length = str.length();
		boolean visited[][] = new boolean[1000001][K + 1];
		visited[N][0] = true;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(N, 0));

		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (cur.k > K)
				break;
			if (cur.k == K) {
				max = Math.max(max, cur.num);
				continue;
			}

			for (int i = 0; i < length - 1; i++) {
				for (int j = i + 1; j < length; j++) {
					String numstr = String.valueOf(cur.num);
					if (i == 0 && numstr.charAt(j) == '0') {
						continue;
					}
					
					int newnum = swap(i, j, numstr);
					if (!visited[newnum][cur.k + 1]) {
						visited[newnum][cur.k + 1] = true;
						q.add(new Node(newnum, cur.k + 1));
					}
				}
			}
		}

	}

	private static int swap(int i, int j, String numstr) {
		StringBuilder sb = new StringBuilder(numstr);

		char ch = sb.charAt(i);
		sb.setCharAt(i, sb.charAt(j));
		sb.setCharAt(j, ch);

		return Integer.parseInt(sb.toString());
	}

	static class Node {
		int num;
		int k;

		public Node(int num, int k) {
			this.num = num;
			this.k = k;
		}
	}
}
