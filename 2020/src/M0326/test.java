package M0326;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class test {
	static int M;
	static int[] numbers = { 3, 5 };
	static int min = -1;
	static int cnt;
	static int[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			M = Integer.parseInt(br.readLine());
			min = -1;
			if (M % 5 == 0) {
				System.out.println(M / 5);

			} else {
				visited = new int[M + 1];
				BFS();
				System.out.println(min);
			}
		}
	}

	private static void BFS() {
		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		visited[0] = 0;

		while (!q.isEmpty()) {
			int temp = q.poll();
			if (temp == M) {
				min = visited[temp];
				break;
			}
			for (int i = 0; i < 2; i++) {
				int sum = temp + numbers[i];
				if (isOK(sum) && visited[sum] == 0) {
					visited[sum] = visited[temp] + 1;
					q.add(sum);
				}
			}
		}
		return;
	}

	private static boolean isOK(int sum) {
		return sum<=M;
	}
}