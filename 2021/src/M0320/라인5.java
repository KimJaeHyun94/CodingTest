package M0320;

import java.util.Arrays;
import java.util.HashMap;

public class 라인5 {

	static HashMap<String, String> map;

	public static void main(String[] args) {
		String program = "line";
		String[] flag_rules = { "-s STRING", "-n NUMBER", "-e NULL" };
		String[] commands = { "line -n 100 -s hi -e", "lien -s Bye" };
		String[] commands2 = { "line -s 123 -n HI", "line fun" };
		System.out.println(Arrays.toString(solution(program, flag_rules, commands)));
		System.out.println(Arrays.toString(solution(program, flag_rules, commands2)));
	}

	public static boolean[] solution(String program, String[] flag_rules, String[] commands) {
		boolean[] answer = {};

		answer = new boolean[commands.length];
		map = new HashMap<>();
		for (int i = 0; i < flag_rules.length; i++) {
			String str[] = flag_rules[i].split(" ");

			map.put(str[0], str[1]);
		}
		int idx = -1;
		for (String str : commands) {
			idx++;
			String line[] = str.split(" ");

			String name = line[0];
			if (!name.equals(program)) {
				answer[idx] = false;
				continue;
			}
			boolean flag = true;
			for (int i = 1; i < line.length; i += 2) {

				if (map.containsKey(line[i])) {
					if (map.get(line[i]).equals("NULL")) {
						if (i == line.length - 1) {
							break;
						} else if (map.containsKey(line[i + 1])) {
							i -= 1;
							continue;
						}
					} else if (!commanding(line[i], line[i + 1])) {
						flag = false;
						break;
					} else {
						continue;
					}
				} else {
					flag = false;
					break;
				}
			}
			if (flag)
				answer[idx] = true;
			else
				answer[idx] = false;
		}

		return answer;
	}

	private static boolean commanding(String key, String sol) {
		String command = map.get(key);

		switch (command) {

		case "STRING":
			for (int i = 0; i < sol.length(); i++) {
				char ch = sol.charAt(i);
				if ('A' <= ch && ch <= 'Z' || 'a' <= ch && ch <= 'z')
					continue;
				else
					return false;
			}
			return true;

		case "NUMBER":
			for (int i = 0; i < sol.length(); i++) {
				char ch = sol.charAt(i);

				if ('0' <= ch && ch <= '9')
					continue;
				else
					return false;
			}
			return true;

		}
		return false;
	}
}
