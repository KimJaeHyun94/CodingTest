package M0123;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class 추석트래픽 {
	static String sol [] = {"2016-09-15 01:00:04.001 2.0s","2016-09-15 01:00:07.000 2s"};
	static String sol3 [] = {
"2016-09-15 20:59:57.421 0.351s",
"2016-09-15 20:59:58.233 1.181s",
"2016-09-15 20:59:58.299 0.8s",
"2016-09-15 20:59:58.688 1.041s",
"2016-09-15 20:59:59.591 1.412s",
"2016-09-15 21:00:00.464 1.466s",
"2016-09-15 21:00:00.741 1.581s",
"2016-09-15 21:00:00.748 2.31s",
"2016-09-15 21:00:00.966 0.381s",
"2016-09-15 21:00:02.066 2.62s"
	};
	public static void main(String[] args) {
		System.out.println(solution(sol));
		System.out.println(solution(sol3));
	}

	public static int solution(String[] lines) {
		int answer = 0;
		ArrayList<Log> list = new ArrayList<>(); // 로그 기록
		for (String line : lines) {
			line = line.replace("s", ""); // 마지막 s를 없애주기
			String[] str = line.split(" ");
			String date = str[0]; // 날짜
			String[] time = str[1].split(":"); // 시간을 ":"기준으로 나눠준다 시 / 분/ 초

			Float end = (Float.parseFloat(time[0]) * 3600 + Float.parseFloat(time[1]) * 60 + Float.parseFloat(time[2])); // 시,분을 모두 초로 변환한다				
			Float start = end - Float.parseFloat(str[2])+ (float) 0.001; // 끝날 시간에서 처리시간을 빼줘서 시작 시간을 기록해준다. (마지막에 0.001초를 더해줘야 시작 시간이 나옴)
			list.add(new Log(date, start, end));
		}
		Collections.sort(list, new Comparator<Log>() { // 정렬

			@Override
			public int compare(Log log1, Log log2) {
				if (log1.end != log2.end) {
					return Float.compare(log1.end, log2.end); // 끝나는 기간 기준으로 정렬
				} else {
					return Float.compare(log2.start, log1.start); // 끝나는 기간 같다면 출발 시간이 빠른것부터
				}
			}
		});

		for (int i = 0; i < list.size(); i++) { // 리스트에서 하나씩 비교
			Log temp = list.get(i);
			float check = (float) (temp.end + 0.999); // 1초 단위로 비교하므로 0.01~ 다음 000까지이므로 1에서 0.01빼기
			int idx = 0;
			for (int j = i; j < list.size(); j++) {
				Log next = list.get(j); // 다음꺼
				if (check >= next.start) {
					idx++;
				}
			}
			answer = Math.max(idx, answer);
		}

		return answer;
	}

	static class Log {
		String date; // 날짜
		float start; // 시작시간
		float end; // 끝날때

		public Log(String date, float start, float end) {
			this.date = date;
			this.start = start;
			this.end = end;
		}
	}
}
