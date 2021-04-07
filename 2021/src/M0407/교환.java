package M0407;

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
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(N, 0));
		boolean visited[][] = new boolean[1000001][K + 1];
		visited[N][0] = true;
		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (cur.k > K)
				break;
			if (cur.k == K) { // K번 한 뒤로도 계속 할 수 있기 때문
				max = Math.max(max, cur.num);
				continue;
			}

			String str = String.valueOf(cur.num);
			for (int i = 0; i < str.length() - 1; i++) {
				for (int j = i + 1; j < str.length(); j++) {
					if (i == 0 && str.charAt(j) == '0') { // 0의 자리를 0으로 바꾸는 건 최대의 수가 나올 수 없음
						continue;
					}
					int newnum = swap(i, j, str);
					if(!visited[newnum][cur.k+1]) {
						visited[newnum][cur.k+1] = true;
						q.add(new Node(newnum, cur.k + 1));
					}
				}
			}

		}
	}

	private static int swap(int i, int j, String str) {
		StringBuilder sb = new StringBuilder(str);

		char ch = sb.charAt(i);
		sb.setCharAt(i, sb.charAt(j));
		sb.setCharAt(j, ch);
		int num = Integer.parseInt(sb.toString());

		return num;
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
