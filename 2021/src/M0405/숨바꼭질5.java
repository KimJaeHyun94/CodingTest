package M0405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 숨바꼭질5 {
	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		if (N == K) {
			System.out.println(0);
			System.exit(0);
		}
		BFS();
	}

	private static void BFS() {
		boolean visited[][] = new boolean[2][500001];
		Queue<Integer> q = new LinkedList<>();
		q.add(N);

		int time = 0;
		int bro = K;
		while (!q.isEmpty()) {

			int size = q.size();
			int la = time % 2;

			for (int i = 0; i < size; i++) {
				int cur = q.poll();
				if (cur > 500000 || cur < 0)
					continue;
				
				if (cur == bro) {
					System.out.println(time);
					System.exit(0);
				}

				if (cur * 2 <= 500000) {
					if (!visited[la][cur * 2]) {
						visited[la][cur * 2] = true;
						q.add(cur * 2);
					}
				}
				if (cur + 1 <= 500000) {
					if (!visited[la][cur + 1]) {
						visited[la][cur + 1] = true;
						q.add(cur + 1);
					}
				}
				if (cur - 1 >= 0) {
					if (!visited[la][cur - 1]) {
						visited[la][cur - 1] = true;
						q.add(cur - 1);
					}
				}
			}
			
			bro = getBro(++time);
			if (bro > 500000) {
				System.out.println(-1);
				System.exit(0);
			}

			if (visited[la][bro]) {
				System.out.println(time);
				System.exit(0);
			}
		}
		System.out.println(-1);
		System.exit(0);
	}

	private static int getBro(int time) {
		return K + (time * (time + 1) / 2);
	}

}