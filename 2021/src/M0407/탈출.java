package M0407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 탈출 {
	static int N, T, G;
	static int MAX = 100000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());

		BFS();

	}

	private static void BFS() {
		boolean visited[] = new boolean[MAX];
		visited[N] = true;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(N, 0));

		while (!q.isEmpty()) {
			Node cur = q.poll();
			int num = cur.num;
			if (cur.d > T)
				break;

			if (num == G) {
				System.out.println(cur.d);
				return;
			}

			if (num + 1 < MAX&& !visited[num + 1]) { // 버튼 A를 누르면 N이 1 증가한다.
				visited[num + 1] = true;
				q.add(new Node(num + 1, cur.d + 1));
			}

			
			int newnum = num * 2;
			if (newnum >= MAX)
				continue; // 버튼 B를 눌러 N에 2를 곱한 순간 수가 99,999를 넘어간다면, 높은 자릿수의 수를 1 낮췄을때 99,999를 넘지 않는다고 해도
							// 탈출에 실패하게 된다.

			int check = newnum;
			int max = 1; // 최대 자릿수자 구하기
			while (check != 0) {
				check /= 10;
				max *= 10;
			}
			max /= 10;
			newnum -= max; // 최대 자릿숫자 빼기

			if (newnum < MAX && !visited[newnum]) {
				visited[newnum] = true;
				q.add(new Node(newnum, cur.d + 1));
			}
		}
		System.out.println("ANG");
	}

	static class Node {
		int num;
		int d;

		public Node(int num, int d) {
			this.num = num;
			this.d = d;
		}
	}
}
