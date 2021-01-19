package M0226;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1058친구 {
	private static int N;
	private static char map[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			max = Math.max(max, BFS(i));
		}
		System.out.println(max);
	}

	private static int BFS(int vertex) {
		boolean visited[] = new boolean[N];
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(vertex, 0));
		visited[vertex] = true;

		while (!queue.isEmpty()) {
			Node nowing = queue.poll();
			int now = nowing.now;
			int depth = nowing.depth;

			if (depth == 2)
				break;

			for (int i = 0; i < N; i++) {
				if (map[now][i] == 'Y' && !visited[i]) {
					visited[i] = true;
					queue.add(new Node(i, depth + 1));
				}
			}
		}
		int ret = 0;
		for (int i = 0; i < N; i++) {
			if (visited[i])
				ret++;
		}
		return ret - 1;
	}

	static class Node {
		int now;
		int depth;

		public Node(int y, int x) {
			now = y;
			depth = x;
		}
	}
}
