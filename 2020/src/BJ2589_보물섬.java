import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2589_보물섬 {
	private static int N;
	private static int M;
	private static char arr[][];
	private static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static boolean visited[][];
	private static int dist[][];
	private static int max = Integer.MIN_VALUE;
	private static int cnt = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new char[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 'L') {
					visited = new boolean[N][M];
					dist = new int[N][M];
					BFS(i, j);
				}
			}
		}
		System.out.println(max);
	}

	private static void BFS(int a, int b) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { a, b });
		visited[a][b] = true;
		while (!queue.isEmpty()) {
			int now[] = queue.poll();

			int y = now[0];
			int x = now[1];
			for (int i = 0; i < dir.length; i++) {
				int dy = y + dir[i][0];
				int dx = x + dir[i][1];

				if (isSafe(dy, dx, y, x)) {
					queue.offer(new int[] { dy, dx });
					dist[dy][dx] = dist[y][x] + 1;
					visited[dy][dx] = true;

					max = Math.max(max, dist[dy][dx]);
				}
			}
		}
	}

	private static boolean isSafe(int dy, int dx, int y, int x) {
		if (dy >= 0 && dy < N && dx >= 0 && dx < M && arr[dy][dx] == 'L' && !visited[dy][dx]) {
			return true;
		}
		return false;
	}

}
