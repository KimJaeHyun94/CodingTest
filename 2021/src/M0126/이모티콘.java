package M0126;

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
		q.add(new imo(1, 0, 0)); // 화면에 이미 이모티콘 하나를 입력했음
		visited[1][0] = true;

		while (!q.isEmpty()) {
			imo temp = q.poll();
			int display = temp.display;
			int clipboard = temp.clipboard;
			int time = temp.time;

			if (display == S) { // S개의 이모티콘을 만들었다면 (화면에)
				ans = time;
				return;
			}
			if (isOK(display)) {
				if (!visited[display][display]) {   
					visited[display][display] = true;
					q.add(new imo(display, display, time + 1)); // 화면에 있는 이모티콘 복사해서 클립보드에 저장
				}

				if (clipboard > 0 && display + clipboard < 1001 && !visited[display+clipboard][clipboard]) { // 클립보드가 범위 안에 있다면
					visited[display + clipboard][clipboard] = true;
					q.add(new imo(display + clipboard, clipboard, time + 1));
				}

				if (!visited[display - 1][clipboard]) {   //화면에 있는 이모티콘 하나 삭제
					visited[display - 1][clipboard] = true;
					q.add(new imo(display - 1, clipboard, time + 1));
				}

			}
		}

	}

	private static boolean isOK(int display) {

		return display >= 1 && display < 1001; // 이미 1개가 있으므로
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
