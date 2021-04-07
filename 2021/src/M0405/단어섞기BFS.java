package M0405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 단어섞기BFS {
	static boolean flag = false;
	static boolean isable = false;
	static int N;
	static int alen, blen;
	static char A[], B[], C[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = st.nextToken().toCharArray();
			B = st.nextToken().toCharArray();
			C = st.nextToken().toCharArray();

			flag = false;
			isable = true;
			N = C.length;
			alen = A.length;
			blen = B.length;
			if (alen + blen != N) {
				sb.append("Data set ").append(t).append(": no").append("\n");
				continue;
			}
			HashSet<Character> set = new HashSet<>();
			for (int i = 0; i < A.length; i++) {
				set.add(A[i]);
			}
			for (int i = 0; i < B.length; i++) {
				set.add(B[i]);
			}
			for (int i = 0; i < C.length; i++) {
				if (!set.contains(C[i])) {
					isable = false;
					break;
				}
			}

			if (!isable) {
				sb.append("Data set ").append(t).append(": no").append("\n");
			} else {
				BFS();
				if (flag) {
					sb.append("Data set ").append(t).append(": yes").append("\n");
				} else {
					sb.append("Data set ").append(t).append(": no").append("\n");
				}
			}
		}
		System.out.println(sb);
	}

	private static void BFS() {
		boolean visited[][] = new boolean[alen + 1][blen + 1];
		visited[0][0] = true;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 0));

		while (!q.isEmpty()) {
			Node cur = q.poll();

			int adx = cur.adx;
			int bdx = cur.bdx;
			int cdx = cur.cdx;

			if (cur.cdx == N) {
				flag = true;
				return;
			}

			if (adx < alen && !visited[adx + 1][bdx] && A[adx] == C[cdx]) {
				visited[adx + 1][bdx] = true;
				q.add(new Node(adx + 1, bdx, cdx + 1));
			}

			if (bdx < blen && !visited[adx][bdx + 1] && B[bdx] == C[cdx]) {
				visited[adx][bdx + 1] = true;
				q.add(new Node(adx, bdx + 1, cdx + 1));
			}

		}
		return;
	}

	static class Node {
		int adx;
		int bdx;
		int cdx;

		public Node(int adx, int bdx, int cdx) {
			this.adx = adx;
			this.bdx = bdx;
			this.cdx = cdx;
		}

	}

}
