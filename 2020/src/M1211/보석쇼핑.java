package M1211;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class 보석쇼핑 {

	public static void main(String[] args) {
		String[] gems = { "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" };
		System.out.println(Arrays.toString(solution(gems)));
	}

	public static int[] solution(String[] gems) {
		Set<String> set = new HashSet<>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Queue<String> q = new LinkedList<>();
		int start = 0, length = Integer.MAX_VALUE;
		int ans = 0;
		for (String str : gems) {
			set.add(str);
		}
		for (int i = 0; i < gems.length; i++) {
			if (!map.containsKey(gems[i])) {
				map.put(gems[i], 1);
			} else
				map.put(gems[i], map.get(gems[i]) + 1);

			q.add(gems[i]);

			while (true) {
				String temp = q.peek();
				if (map.get(temp) > 1) {
					map.put(temp, map.get(temp) - 1);
					q.poll();
					start++;
				} else
					break;
			}
			if (map.size() == set.size() && length > q.size()) {
				length = q.size();
				ans = start;
			}

		}

		int[] answer = { ans + 1, ans + length };

		return answer;
	}

}
