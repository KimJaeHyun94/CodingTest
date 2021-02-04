package M0204;

public class 광고삽입_초기 {
	static String[] log = { "01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29",
			"01:37:44-02:02:30" };
	static long total[]; // 재생 구간의 개수 (long으로 안할 시 18번 케이스 탈락)
	static int advtime;

	public static void main(String[] args) {
		System.out.println(solution("02:03:55", "00:14:15", log));
	}

	public static String solution(String play_time, String adv_time, String[] logs) {
		String sta[] = play_time.split(":");
		int hour = Integer.parseInt(sta[0]) * 3600; // 초로 환산
		int min = Integer.parseInt(sta[1]) * 60;
		int sec = Integer.parseInt(sta[2]);

		int playtime = hour + min + sec; // 동영상 재생시간
		total = new long[playtime + 1];
		String sta2[] = adv_time.split(":");
		hour = Integer.parseInt(sta2[0]) * 3600; // 초로 환산
		min = Integer.parseInt(sta2[1]) * 60;
		sec = Integer.parseInt(sta2[2]);

		advtime = hour + min + sec; // 공익광고의 재생시간

		for (String str : logs) {
			String[] split = str.split("-");
			String sta3[] = split[0].split(":");
			String sta4[] = split[1].split(":");

			hour = Integer.parseInt(sta3[0]) * 3600; // 초로 환산
			min = Integer.parseInt(sta3[1]) * 60;
			sec = Integer.parseInt(sta3[2]);

			int starting = hour + min + sec;

			hour = Integer.parseInt(sta4[0]) * 3600; // 초로 환산
			min = Integer.parseInt(sta4[1]) * 60;
			sec = Integer.parseInt(sta4[2]);
			int ending = hour + min + sec;

			for (int i = starting; i < ending; i++) { 
				total[i]++;
			}
		}

		int max = 0;
		long at = 0;

		for (int i = advtime; i <= playtime; i++) { 
			if (at < Sol(i)) {
				at = Math.max(at, Sol(i));
				max = i - advtime;
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

	private static long Sol(int i) {
		long sum = 0;
		for (int j = i-advtime; j < i; j++) {
			sum += total[j];
		}
		return sum;
	}
}
