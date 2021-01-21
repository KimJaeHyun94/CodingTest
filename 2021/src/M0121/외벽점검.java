package M0121;

import java.util.LinkedList;
/*
 * @See https://bellog.tistory.com/124
 * @Author AKKabiyo
 */
public class 외벽점검 {

	private static LinkedList<Integer> circleWeak;
	private static LinkedList<Integer> friends;
	private static int[] d;
	private static boolean[] visited;
	private static int ans = Integer.MAX_VALUE;
	private static int weakSize;

	public static void main(String[] args) {
		System.out.println(solution(12, new int[] { 1, 5, 6, 10 }, new int[] { 1, 2, 3, 4 }));
	}

	public static int solution(int n, int[] weak, int[] dist) {
		int answer = 0;
		int len = weak.length;
		d = dist;

		friends = new LinkedList<>();
		visited = new boolean[dist.length];
		weakSize = weak.length;
		circleWeak = new LinkedList<>();
		for (int i = 0; i < len; i++) {
			circleWeak.add(weak[i]); // 약점 위치 저장
		}

		for (int i = 0; i < len; i++) {
			circleWeak.add(weak[i] + n); // 약점 위치 저장
		}

		permutation(0);
		if (ans == Integer.MAX_VALUE) {
			answer = -1;
		} else {
			answer = ans;
		}
		return answer;
	}

	private static void permutation(int cnt) {
		if (cnt == d.length) {
			check();
			return;
		}
		for (int i = 0; i < d.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				friends.add(d[i]);
				permutation(cnt + 1);
				friends.removeLast(); // 맨 마지막 거 제거
				visited[i] = false;
			}
		}
	}

	private static void check() {
		for (int i = 0; i < weakSize; i++) {
			int idx = 0;
			boolean flag = false;
			int start = circleWeak.get(i);

			for (int j = i; j < i + weakSize; j++) {
				if (friends.get(idx) < (circleWeak.get(j) - start)) {
					start = circleWeak.get(j);
					idx++;
					if (idx == friends.size()) {
						flag = true;
						break;
					}
				}
			}
			if (!flag) {
				ans = Math.min(ans, idx + 1);
			}
		}
	}
}
