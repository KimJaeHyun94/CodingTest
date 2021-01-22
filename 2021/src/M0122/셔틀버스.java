package M0122;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 셔틀버스 {
	public static void main(String[] args) {
		System.out.println(solution(1, 1, 5, new String[] { "08:00", "08:01", "08:02", "08:03" }));
		System.out.println(solution(2, 10, 2, new String[] { "09:10", "09:09", "08:00" }));
	}

	public static String solution(int n, int t, int m, String[] timetable) {
		String answer = "";
		PriorityQueue<Integer> crew = new PriorityQueue<>(new Comparator<Integer>() { // 시간 순서대로 정렬하기 위해 우선순위 큐 사용

			@Override
			public int compare(Integer arg0, Integer arg1) {
				return arg0 - arg1;
			}

		});
		for (String str : timetable) {
			int time = Integer.parseInt(str.substring(0, 2)) * 60 + Integer.parseInt(str.substring(3)); // 시간은 60곱해서 모두
																										// 분으로 구해준다
			crew.add(time);
		}
		int busTime = 9 * 60; // 버스 출발시간
		int sol = 0;
		while (n > 0) {
			int max = m; // 한 셔틀에 탈 수 있는 최대 크루 수
			int time = 0;
			n--; // 셔틀 돌 때 마다 감소시키기

			while (!crew.isEmpty()) {
				if (crew.peek() <= busTime && max > 0) // 만약에 크루 첫번째 넘이 버스타임 내에 도착하고 셔틀에 탈 수 있다면
				{
					max--; // 그 크루들을 태운다
					time = crew.poll();
				} else
					break; // 셔틀에 못탄다면 break;
			}

			if (n > 0) {
				if (crew.isEmpty()) { // 모든 크루가 다 탔다면
					sol = busTime + ((n + 1) * t); // 모든 크루가 다 탓을때 시간
					break;
				}
				busTime += t;
			} else { // 운행횟수가 끝낫을때
				if (max > 0) { // 더 탈 수 있다면
					sol = busTime;
				} else {
					sol = time - 1;	//마지막 크루보다 1분 빨리
				}
				break;
			}

		}
		answer = String.format("%02d", sol / 60) + ":" + String.format("%02d", sol % 60);
		return answer;
	}
}
