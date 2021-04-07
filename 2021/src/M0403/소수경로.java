package M0403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 소수경로 {
	static boolean[] primeNumber = new boolean[10000];
	static int ans = -1;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		makePrimeNumber();

		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			ans = -1;
			BFS(start, end);
			if (ans == -1)
				sb.append(-1).append("\n");
			else
				sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

	private static void BFS(int start, int end) {
		boolean visited[] = new boolean[10000];
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(start, 0));
		visited[start] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();
			int num = cur.num;

			if (cur.num == end) {
				ans = cur.cnt;
				return;
			}
			int[] digit = { num / 1000, (num % 1000) / 100, (num % 100) / 10, num % 10 }; // 천의자리, 백의자리, 십의자리, 1의자리

			for (int i = 0; i < 4; i++) {
				for (int j = (i == 0 ? 1 : 0); j < 10; j++) {

					int origin = digit[i];
					digit[i] = j;
					int newnum = (digit[0] * 1000) + (digit[1] * 100) + (digit[2] * 10) + digit[3];
					digit[i] = origin;
					if (visited[newnum])
						continue;
					if (newnum < 1000)
						continue;
					if (primeNumber[newnum])
						continue;

					visited[newnum] = true;
					q.add(new Node(newnum, cur.cnt + 1));

				}
			}
		}
	}

	public static void makePrimeNumber() {
		for (int i = 2; i < 10000; i++) {
			for (int j = 2; j < i / 2; j++) {
				if (i % j == 0)
					primeNumber[i] = true;
			}
		}
	}

	static class Node {
		int num;
		int cnt;

		public Node(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}

	}
}
