package M0223;

import java.util.HashMap;
import java.util.Map;

public class 호텔방배정 {
	static Map<Long, Long> map = new HashMap<>();

	public static long[] solution(long k, long[] room_number) {
		long[] answer = new long[room_number.length];
		int idx = 0;
		for (long l : room_number) {
			answer[idx++] = sol(l);
		}
		return answer;
	}

	private static long sol(long room) {
		if(!map.containsKey(room)) {
			map.put(room, room+1);
			return room;
		}
		map.put(room, sol(map.get(room)));
		return map.get(room);
	}
}
