package M0226;

public class 광고삽입 {
	static String[] log = { "01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29",
			"01:37:44-02:02:30" };

	public static void main(String[] args) {
		System.out.println(solution("02:03:55", "00:14:15", log));
	}

	public static String solution(String play_time, String adv_time, String[] logs) {
		String pla[] = play_time.split(":");
		int hour = Integer.parseInt(pla[0]) * 3600; // 초로 환산
		int min = Integer.parseInt(pla[1]) * 60;
		int sec = Integer.parseInt(pla[2]);

		int playtime = hour + min + sec; // 총 재생시간

		long[] total = new long[playtime + 1];

		String adv[] = adv_time.split(":");
		hour = Integer.parseInt(adv[0]) * 3600; // 초로 환산
		min = Integer.parseInt(adv[1]) * 60;
		sec = Integer.parseInt(adv[2]);

		int advtime = hour + min + sec; // 공익 광고 재생시간
		if (advtime == playtime) { // 가지치기 (총 재생시간이 같으면 걍 첨부터 틀면 되니까)
			return "00:00:00";
		}
		for (String str : logs) {
			String[] split = str.split("-");
			String[] start = split[0].split(":");
			String[] end = split[1].split(":");

			hour = Integer.parseInt(start[0]) * 3600; // 초로 환산
			min = Integer.parseInt(start[1]) * 60;
			sec = Integer.parseInt(start[2]);

			int startime = hour + min + sec;

			hour = Integer.parseInt(end[0]) * 3600; // 초로 환산
			min = Integer.parseInt(end[1]) * 60;
			sec = Integer.parseInt(end[2]);

			int endtime = hour + min + sec;

			for (int i = startime; i < endtime; i++) {
				total[i]++; // 표시해둔다.
			}
		}

		for (int i = 1; i <= playtime; i++) {
			total[i] += total[i - 1]; // 그 시간에 누적해둔다.
		}
		int max = 0;
		long at = total[advtime]; // 공익광고 재생시간

		for (int i = advtime; i <= playtime; i++) {
			if (at < total[i] - total[i - advtime]) {
				at = Math.max(at, total[i] - total[i - advtime]);
				max = i - advtime + 1;
			}
		}

		hour = max / 3600;
		min = (max % 3600) / 60;
		sec = (max % 60);

		StringBuilder sb = new StringBuilder();
		if (hour < 10) {
			sb.append("0").append(hour);
		} else {
			sb.append(hour);
		}

		if (min < 10) {
			sb.append(":").append("0").append(min);
		} else {
			sb.append(":").append(min);
		}

		if (sec < 10) {
			sb.append(":").append("0").append(sec);
		} else {
			sb.append(":").append(sec);
		}

		return sb.toString();
	}
}
