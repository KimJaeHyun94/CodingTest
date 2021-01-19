package M1219;

import java.util.Collections;
import java.util.PriorityQueue;

public class 야근지수 {

	public static void main(String[] args) {
		System.out.println(solution(1, new int[] { 2, 1, 2 }));

	}

	public static long solution(int n, int[] works) {
		long answer = 0;
		PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());

		for (int work : works) {
			q.add(work);
		}

		for (int i = 0; i < n; i++) {
			int max = q.poll();
			if (max == 0)
				break;
			q.offer(max - 1);
		}
		for (Integer integer : q) {
			answer+=integer*integer;
		}
		return answer;
	}
}
