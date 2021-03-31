package M0331;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DSLR {
	static boolean visited[];
	static char dirs[] = { 'D', 'S', 'L', 'R' };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int t = 0; t < TC; t++) {
			visited = new boolean[10000];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			BFS(start, end);
		}
	}

	private static void BFS(int start, int end) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(start, ""));
		visited[start] = true;
		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (cur.num == end) {
				System.out.println(cur.command);
				return;
			}

			for (int d = 0; d < dirs.length; d++) {
				int en = calc(d, cur.num);

				if (!visited[en]) {
					q.add(new Node(en, cur.command + dirs[d]));
					visited[en] = true;
				}
			}
		}
	}

	private static int calc(int d, int num) {
		int result = 0;
		if (d == 0) { // 'D'
			result = 2 * num % 10000;
			return result;
		} else if (d == 1) { // 'S'
			if (num == 0)
				result = 9999;
			else
				result = num - 1;
			return result;
		} else if (d == 2) { // 'L'
			int a = num / 1000; // 맨 윗자리
			int b = num % 1000; // 1의자리 빼고 왼쪽으로 이동
			result = (b * 10) + a;
			return result;
		} else { // 'R'
			int a = num % 10; // 맨 윗자리
			int b = num / 10; // 1의자리 빼고 왼쪽으로 이동
			result = (a * 1000) + b;
			return result;
		}
	}

	static class Node {
		int num;
		String command;

		public Node(int num, String command) {
			this.num = num;
			this.command = command;
		}

	}
}
