package M0106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 이모티콘 {
	static boolean visited[][];
	static int ans;
	static int S;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());
		visited = new boolean[1001][1001];
		BFS();
		System.out.println(ans);
	}

	private static void BFS() {
		Queue<imo> q = new LinkedList<>();
		q.add(new imo(1, 0, 0));
		visited[1][0] = true;

		while (!q.isEmpty()) {
			imo temp = q.poll();

			int display = temp.display;
			int clipboard = temp.clipboard;
			int time = temp.time;

			if (display == S) {
				ans = time;
				return;
			}
			if (isOK(display)) {
				if (!visited[display][display]) {
					visited[display][display] = true;
					q.add(new imo(display, display, time + 1));
				}

				if (clipboard > 0 && display + clipboard < 1001) {
					if (!visited[display + clipboard][clipboard]) {
						visited[display + clipboard][clipboard] = true;
						q.add(new imo(display + clipboard, clipboard, time + 1));
					}
				}

				if (!visited[display - 1][clipboard]) {
					visited[display - 1][clipboard] = true;
					q.add(new imo(display - 1, clipboard, time + 1));
				}

			}
		}

	}

	private static boolean isOK(int display) {

		return display > 0 && display < 1001;
	}

	static class imo {
		int display;
		int clipboard;
		int time;

		public imo(int display, int clipboard, int time) {
			this.display = display;
			this.clipboard = clipboard;
			this.time = time;
		}

	}
}
