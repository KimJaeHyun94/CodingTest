package M0414;

public class 광고삽입 {
	public static void main(String[] args) {
		String play_time = "02:03:55";
		String adv_time = "00:14:15";
		String logs[] = { "01:20:15-01:45:14", "00:25:50-00:48:29", "00:40:31-01:00:00", "01:37:44-02:02:30",
				"01:30:59-01:53:29" };
		System.out.println(solution(play_time, adv_time, logs));
	}

	public static String solution(String play_time, String adv_time, String[] logs) {
		String pla[] = play_time.split(":");
		int playtime = sol(pla); // 총 재생시간

		String adv[] = adv_time.split(":");
		int advtime = sol(adv);

		if (playtime == advtime) {
			return "00:00:00";
		}

		long[] total = new long[playtime + 1];

		for (String str : logs) {
			String[] split = str.split("-");
			String[] start = split[0].split(":");
			String[] end = split[1].split(":");

			int starttime = sol(start);
			int endtime = sol(end);

			for (int i = starttime; i < endtime; i++) {
				total[i]++;
			}
		}

		for (int i = 1; i <= playtime; i++) {
			total[i] += total[i - 1];
		}

		int max = 0;
		long at = total[advtime];

		for (int i = advtime; i <= playtime; i++) {
			if (at < total[i] - total[i - advtime]) {
				at = Math.max(at, total[i] - total[i - advtime]);
				max = i - advtime + 1;
			}
		}

		int hour = max / 3600;
		int min = (max % 3600) / 60;
		int sec = (max % 60);
		
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

	private static int sol(String[] arr) {
		int hour = Integer.parseInt(arr[0]) * 3600;
		int min = Integer.parseInt(arr[1]) * 60;
		int sec = Integer.parseInt(arr[2]);

		return hour + min + sec;
	}
}
