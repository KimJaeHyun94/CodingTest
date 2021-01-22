package M0122;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
/*
 * @See Beaverbae Code
 * @Author AKKabiyo
 * 
 */
public class 보석쇼핑투포인터 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new String[] { "ZZZ", "YYY", "NNNN", "YYY", "BBB" })));

	}

	public static int[] solution(String[] gems) {

		HashSet<String> set = new HashSet<>();
		for (int i = 0; i < gems.length; i++) {
			set.add(gems[i]);
		}
		HashMap<String, Integer> map = new HashMap<>();
		int start = 0, end = 0;
		int left = 0, right = 0;
		int len = gems.length+1;

		while (true) {
			if (set.size() == map.size()) { // 보석을 다 찾앗다면
				map.put(gems[left], map.get(gems[left]) - 1);

				if (map.get(gems[left]) == 0) { // 만약에 맨 왼쪽것이 한개라면
					map.remove(gems[left]); // 삭제
				}
				left++; // 왼쪽 이동
			} else {
				if (right == gems.length) {
					break; // 모두 탐색하였다면
				}

				map.put(gems[right], map.getOrDefault(gems[right], 0) + 1); // 있으면 원래 값에서 +1 없으면 1
				right++;
			}
			if (map.size() == set.size()) {

				if (right - left < len) { // 길이 최소인것을 계속 저장해둠
					len = right - left;
					start = left + 1;
					end = right;
				}
			}
		}

		int[] answer = new int[2];
		answer[0] = start;
		answer[1] = end;
		return answer;
	}
}
