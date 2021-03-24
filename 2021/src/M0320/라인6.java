package M0320;

import java.util.Arrays;

import java.util.HashMap;

public class 라인6 {

	static HashMap<String, String> map;

	public static void main(String[] args) {
		String program = "trip";
		String[] flag_rules = {"-days NUMBERS", "-dest STRING" };
		String[] commands = { "line -n 100 102 -s hi -e", "line -n id pwd -n 100" };
		String[] commands2 = { "trip -days 15 10 -dest Seoul Paris", "trip -days 10 -dest Seoul" };
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
					} else if (map.get(line[i]).equals("STRINGS") || map.get(line[i]).equals("NUMBERS")) {
						String go ="";
						String temp = line[i];
						for (int j = i + 1; j < line.length; j++) {
							if (map.containsKey(line[j])) {
								break;
							}
							go += line[j];
						}
						i += go.length() - 2;
						if (!commanding(temp, go)) {
							flag = false;
							break;
						} else
							continue;

					} else {
						if (!commanding(line[i], line[i + 1])) {
							flag = false;
							break;
						} else {
							continue;
						}
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

		case "STRINGS":
			for (int i = 0; i < sol.length(); i++) {
				char ch = sol.charAt(i);
				if ('A' <= ch && ch <= 'Z' || 'a' <= ch && ch <= 'z')
					continue;
				else
					return false;
			}
			return true;

		case "NUMBERS":
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
