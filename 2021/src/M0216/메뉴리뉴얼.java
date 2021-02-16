package M0216;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class 메뉴리뉴얼 {

	public static void main(String[] args) {
		String orders[] = { "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" };
		String orders2[] = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
		System.out.println(Arrays.toString(solution(orders, new int[] { 2, 3, 4 })));
		System.out.println(Arrays.toString(solution(orders2, new int[] {2,3,5})));
	}

	public static String[] solution(String[] orders, int[] course) {
		String[] answer = {};
		HashMap<String, Integer> map = new HashMap<>();
		for (String str : orders) {
			char[] order = str.toCharArray();
			Arrays.sort(order); // 알파벳 순서대로 정렬
			combi(0, "", order, map);
		}

		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < course.length; i++) {
			int max = 0;
			Iterator<String> keys = map.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				if (key.length() == course[i]) {
					max = Math.max(max, map.get(key));
				}
			}
			Iterator<String> keyss = map.keySet().iterator();
			while (keyss.hasNext()) {
				String key = keyss.next();
				if (key.length() == course[i] && map.get(key) == max && max>1) {
					list.add(key);
				}
			}
		}
		answer = new String[list.size()];
		int idx = 0;
		for (String string : list) {
			answer[idx++] = string;
		}
		Arrays.sort(answer);
		return answer;
	}

	private static void combi(int start, String str, char[] order, HashMap<String, Integer> map) {
		if (str.length() > 1) { // 2개짜리 조합은 다 넣어준다.
			map.put(str, map.getOrDefault(str, 0) + 1);
		}

		for (int i = start; i < order.length; i++) {
			combi(i + 1, str + order[i], order, map);
		}
	}
}
