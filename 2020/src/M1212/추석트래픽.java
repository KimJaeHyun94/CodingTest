package M1212;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class 추석트래픽 {

	public static void main(String[] args) {

	}

	public int solution(String[] lines) {
		int answer = 0;
		ArrayList<Log> list = new ArrayList<>();
		for (String line : lines) {
			line = line.replace("s", "");
			String[] str = line.split(" ");
			String date = str[0];
			String[] time = str[1].split(":");

			float end = (Float.parseFloat(time[0]) * 3600) + (Float.parseFloat(time[1]) * 60)
					+ Float.parseFloat(time[2]);

			float start = (end - Float.parseFloat(str[2])) + (float) 0.001;
			list.add(new Log(date, start, end));
		}
		Collections.sort(list, new Comparator<Log>() {

			@Override
			public int compare(Log log1, Log log2) {
				if (log1.end != log2.end) {
					return Float.compare(log1.end, log2.end);
				} else {
					return Float.compare(log2.start, log1.start);
				}
			}
		});
		for (int i = 0; i < list.size(); i++) {

			Log cur = list.get(i);
			float range = cur.end + (float) 0.999;
			int cnt = 0;
			for (int j = i, inner = list.size(); j < inner; j++) {

				Log next = list.get(j);
				if (range >= next.start)
					cnt++;
			}
			answer = Math.max(cnt, answer);
		}
		return answer;
	}

	class Log {
		String date;
		float start;
		float end;

		public Log(String date, float start, float end) {
			this.date = date;
			this.start = start;
			this.end = end;
		}

	}
}
