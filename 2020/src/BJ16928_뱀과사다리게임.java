import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16928_뱀과사다리게임 {
	private static int N;
	private static int M;
	private static int[] snake = new int[101];
	private static int[] Ladder = new int[101];
	private static int[] visited = new int[101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			Ladder[a] = b;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			snake[a] = b;
		}
		 for (int i = 0; i < visited.length; i++) {
	         visited[i] = Integer.MAX_VALUE;
	      }

		BFS();
		System.out.println(visited[100]);
	}

	private static void BFS() {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(1, 0));
		visited[1] = 0;

		while (!queue.isEmpty()) {
			Node now = queue.poll();
			int nowPos = now.pos;
			int nowCnt = now.cnt;

			if (Ladder[nowPos] != 0) {
				int nextPos = Ladder[nowPos];
				queue.add(new Node(nextPos, nowCnt));
				continue;
			} else if (snake[nowPos] != 0) {
				int nextPos = snake[nowPos];
				queue.add(new Node(nextPos, nowCnt));
				continue;
			}

			for (int i = 1; i <= 6; i++) {
				int nextPos = nowPos + i;
				int nextCnt = nowCnt + 1;

				if (nextPos > 100)
					continue;

				if (nextCnt < visited[nextPos]) {
					visited[nextPos] = nextCnt;
					queue.add(new Node(nextPos, nextCnt));
				}
			}
		}
	}

	static class Node {
		int pos;
		int cnt;

		public Node(int pos, int cnt) {
			this.pos = pos;
			this.cnt = cnt;
		}
	}
}
