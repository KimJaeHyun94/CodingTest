package M1212;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 셔틀버스 {

	public static void main(String[] args) {
		String[] timetable = { "09:00", "09:00", "09:00", "09:00" };
		System.out.println(solution(2, 1, 2, timetable));
	}

	public static String solution(int n, int t, int m, String[] timetable) {
		String answer = "";
		PriorityQueue<Integer> crew = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer arg0, Integer arg1) {
				return arg0 - arg1;
			}

		});
		for (String str : timetable) {
			int time = Integer.parseInt(str.substring(0, 2)) * 60 + Integer.parseInt(str.substring(3));
			crew.add(time);
		}

		int busTime = 9 * 60;
		int last = 0;
		while (n > 0) {
			int accept = m;
			int time = 0;
			n--;

			while (!crew.isEmpty()) {
				if (crew.peek() <= busTime && accept > 0) {
					accept--;
					time = crew.poll();
				} else
					break;
			}
			if (n > 0) {
				if (crew.isEmpty()) {
					last = busTime + ((n + 1) * t);
					break;
				}
				busTime += t;
			} else {
				if (accept > 0)
					last = busTime;
				else
					last = time - 1;

				break;
			}
		}

		answer = String.format("%02d", last / 60) + ":" + String.format("%02d", last % 60);
		return answer;
	}
}
