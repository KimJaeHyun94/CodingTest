package M0320;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class 라인2 {
	public static void main(String[] args) {
		String inp_str = "CaCbCgCdC888834A";
		System.out.println(Arrays.toString(solution(inp_str)));

	}

	public static int[] solution(String inp_str) {
		boolean check[] = new boolean[6];
		int len = inp_str.length();

		if (len > 15 || len < 8) { // 1번 규칙
			check[1] = true;
		}

		boolean col[] = new boolean[4];
		HashMap<Character, Integer> map = new HashMap<>();

		for (int i = 0; i < len; i++) {
			boolean flag = false;
			char ch = inp_str.charAt(i);
			if (map.containsKey(ch)) {
				int n = map.get(ch);
				map.put(ch, n + 1);
			}else {
				map.put(ch, 1);
			}
			if ('A' <= inp_str.charAt(i) && inp_str.charAt(i) <= 'Z') {
				col[0] = true;
				flag = true;
			} else if ('a' <= inp_str.charAt(i) && inp_str.charAt(i) <= 'z') {
				col[1] = true;
				flag = true;
			} else if ('0' <= inp_str.charAt(i) && inp_str.charAt(i) <= '9') {
				col[2] = true;
				flag = true;
			} else if (ch == '~' || ch == '~' || ch == '@' || ch == '#' || ch == '$' || ch == '%' || ch == '^'
					|| ch == '&' || ch == '*') {
				col[3] = true;
				flag = true;
			}

			if (!flag) {
				check[2] = true;
			}
		}
		int ct = 0;
		for (int i = 0; i < col.length; i++) {
			if (col[i])
				ct++;
		}

		if (ct < 3) {
			check[3] = true;
		}
		for (char ch : map.keySet()) {
			if (map.get(ch) >= 4) {
				check[4] = true;
			}
			if (map.get(ch) >= 5) {
				check[5] = true;
			}
		}
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 1; i < check.length; i++) {
			if (check[i]) {
				list.add(i);
			}
		}
		int[] answer;
		if (list.size() == 0) {
			answer = new int[1];
			answer[0] = 0;
		} else {
			answer = new int[list.size()];

			for (int i = 0; i < list.size(); i++) {
				answer[i] = list.get(i);
			}
		}

		return answer;
	}
}
