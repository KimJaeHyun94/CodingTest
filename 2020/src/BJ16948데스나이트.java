import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16948데스나이트 {
	private static int arr[][];
	private static int r1, c1, r2, c2;
	private static boolean visited[][];
	private static int dir[][] = { { -2, -1 }, { -2, 1 }, { 0, -2 }, { 0, 2 }, { 2, -1 }, { 2, 1 } };
	private static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		r1 = Integer.parseInt(st.nextToken());
		c1 = Integer.parseInt(st.nextToken());
		r2 = Integer.parseInt(st.nextToken());
		c2 = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		visited = new boolean[N][N];

		BFS(r1, c1);
		if (!visited[r2][c2]) {
			System.out.println(-1);
		} else
			System.out.println(arr[r2][c2]);
	}

	private static void BFS(int r1, int c1) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(r1, c1));
		visited[r1][c1] = true;

		while (!queue.isEmpty()) {
			Node now = queue.poll();

			int y = now.y;
			int x = now.x;
			visited[y][x] = true;

			for (int i = 0; i < dir.length; i++) {
				int dy = dir[i][0] + y;
				int dx = dir[i][1] + x;

				if (isOK(dy, dx)) {
					visited[dy][dx] = true;
					queue.offer(new Node(dy, dx));
					arr[dy][dx] = arr[y][x] + 1;
				}
			}

		}
	}

	private static boolean isOK(int dy, int dx) {
		if (dy >= 0 && dy < N && dx >= 0 && dx < N) {
			if (!visited[dy][dx])
				return true;
		}
		return false;
	}

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
