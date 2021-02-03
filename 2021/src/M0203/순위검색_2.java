package M0203;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;



public class 순위검색_2 {
	public static void main(String[] args) {
		String[] info = { "java backend junior pizza 150", "python frontend senior chicken 210",
				"python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
				"python backend senior chicken 50" };
		String[] query = { "java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
				"- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150" };
		System.out.println(Arrays.toString(solution(info, query)));
	}
	
	static HashMap<String, ArrayList<Integer>> map;

	public static int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];
		map = new HashMap<>();

		makeGroup(info);

		int idx = 0;
		for (String q : query) {
			String[] temp = q.split(" ");

			int score = Integer.parseInt(temp[temp.length - 1]); // 기준점수
			q = q.replace("and", "").replace(" ", "");
			q = q.replace(temp[temp.length - 1], ""); // 조건

			if (map.get(q) == null) {   //아예 없는 경우엔 0으로 처리 (가지치기)
				answer[idx++] = 0;
				continue;
			}
			answer[idx++] = find(map.get(q), score);
		}
		return answer;
	}

	public static void makeGroup(String[] info) {
		for (String i : info) {
			String[] person = i.split(" ");
			makeCase(person, 0, Integer.parseInt(person[4]), "");
		}
		// 각 리스트 오름차순 정렬
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			Collections.sort(map.get(it.next()));
		}
	}

	public static void makeCase(String[] info, int idx, int score, String cur) {
		if (idx == 4) {
			if (!map.containsKey(cur))
				map.put(cur, new ArrayList<>());
			map.get(cur).add(score);
			return;
		}
		makeCase(info, idx + 1, score, cur + "-");
		makeCase(info, idx + 1, score, cur + info[idx]);
	}

	public static int find(ArrayList<Integer> list, int score) {
		int ans = list.size();
		int idx = list.size();
		int s = 0;
		int e = list.size() - 1;

		while (s <= e) {
			int mid = (s + e) / 2;

			if (list.get(mid) < score)
				s = mid + 1;
			else {
				e = mid - 1;
				idx = mid;
			}
		}
		return ans - idx;
	}
}
