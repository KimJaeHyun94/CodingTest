package M0210;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 * Map + Recursion
 * @author AKKabiyo
 * @see https://bcp0109.tistory.com/entry/Kakao-2019-Internship-Winter-%ED%98%B8%ED%85%94-%EB%B0%A9-%EB%B0%B0%EC%A0%95-Java
 *
 */
public class 호텔방배정 {
	static Map<Long, Long> map = new HashMap<>();

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(10, new long[] { 1, 3, 4, 1, 3, 1 })));
	}

	public static long[] solution(long k, long[] room_number) {
		long[] answer = new long[room_number.length];

		for (int i = 0; i < room_number.length; i++) {
			answer[i] = sol(room_number[i]);
		}
		return answer;
	}

	private static long sol(long room) { // 방을 찾는다
		if (!map.containsKey(room)) {  //비어있다면 그 방을 알려준다
			map.put(room, room+1);   //처음에 방 채울때는 그 다음방
			return room;
		}
		map.put(room, sol(map.get(room)));   //재귀를 통해 계속 빈 방을 찾아나간다. 
		return map.get(room);   //다음방으로 이동 
	}
}
