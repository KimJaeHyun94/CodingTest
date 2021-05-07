package M0503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class AC {
	static Deque<Integer> deque;
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			String command = br.readLine();
			int n = Integer.parseInt(br.readLine());
			int arr[] = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine(), "[],");
			deque = new ArrayDeque<Integer>();

			for (int i = 0; i < n; i++) {
				deque.add(Integer.parseInt(st.nextToken()));
			}

			AC(command);
		}
		System.out.println(sb);
	}

	private static void AC(String command) {
		boolean reverse = true;

		for (char com : command.toCharArray()) {
			if (com == 'R') {
				reverse = !reverse; // 돌리기
				continue;
			}

			if (reverse) { // 정방향이라면
				if (deque.pollFirst() == null) {
					sb.append("error").append("\n");
					return;
				}
			} else {
				if (deque.pollLast() == null) {
					sb.append("error").append("\n");
					return;
				}
			}
		}

		// error가 없다면 이제 덱에서 빼서 넣어주자
		sb.append('['); // 처음 배열 시작
		if (deque.size() > 0) { // 출력할 게 있다면
			if (reverse) {
				while (!deque.isEmpty()) {
					sb.append(deque.pollFirst()).append(",");
				}
				sb.deleteCharAt(sb.length() - 1);
			} else {
				while (!deque.isEmpty()) {
					sb.append(deque.pollLast()).append(",");
				}
				sb.deleteCharAt(sb.length() - 1);
			}
		}
		sb.append(']').append("\n");
	}

}
