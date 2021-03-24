package M0320;

import java.util.Arrays;

import java.util.HashMap;

public class 라인7 {

	static HashMap<String, String> map;
	static HashMap<String, String> aliasmap;

	public static void main(String[] args) {
		String program = "naver";
		String[] flag_rules = { "-send NUMBERS", "-s ALIAS -send" };
		String[] commands = { "naver -s 10 20 30", "naver -s 10 send" };

		System.out.println(Arrays.toString(solution(program, flag_rules, commands)));
	}

	public static boolean[] solution(String program, String[] flag_rules, String[] commands) {
		boolean[] answer = {};

		answer = new boolean[commands.length];
		map = new HashMap<>();
		aliasmap = new HashMap<>();
		for (int i = 0; i < flag_rules.length; i++) {
			String str[] = flag_rules[i].split(" ");
			if (str.length == 2) {
				map.put(str[0], str[1]);
			} else {
				aliasmap.put(str[0], str[2]);
			}
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
					if (aliasmap.containsKey(line[i])) {
						aliasmap.remove(line[i]);
					}
					if (map.get(line[i]).equals("NULL")) {
						if (i == line.length - 1) {
							break;
						} else if (map.containsKey(line[i + 1])) {
							i -= 1;
							continue;
						}
					} else if (map.get(line[i]).equals("STRINGS") || map.get(line[i]).equals("NUMBERS")) {
						String go = "";
						String temp = line[i];
						for (int j = i + 1; j < line.length; j++) {
							if (map.containsKey(line[j])) {
								break;
							}
							go += line[j];
						}
						i += go.length() - 2;
						if (!commanding(temp, go, map)) {
							flag = false;
							break;
						} else
							continue;

					} else {
						if (!commanding(line[i], line[i + 1], map)) {
							flag = false;
							break;
						} else {
							continue;
						}
					}
				} else {
					if (aliasmap.containsKey(line[i])) {
						if (map.containsKey(aliasmap.get(line[i]))) { // 지워지기 전
							String check = aliasmap.get(line[i]);
							String command = map.get(aliasmap.get(line[i]));
							aliasmap.remove(line[i]);
							map.remove(check);
							aliasmap.put(line[i], command);
						}
						if (aliasmap.get(line[i]).equals("NULL")) {
							if (i == line.length - 1) {
								break;
							} else if (aliasmap.containsKey(line[i + 1])) {
								i -= 1;
								continue;
							}
						} else if (aliasmap.get(line[i]).equals("STRINGS") || aliasmap.get(line[i]).equals("NUMBERS")) {
							String go = "";
							String temp = line[i];
							for (int j = i + 1; j < line.length; j++) {
								if (aliasmap.containsKey(line[j])) {
									break;
								}
								go += line[j];
							}
							i += go.length() - 2;
							if (!commanding(temp, go, aliasmap)) {
								flag = false;
								break;
							} else
								continue;

						} else {
							if (!commanding(line[i], line[i + 1], aliasmap)) {
								flag = false;
								break;
							} else {
								continue;
							}
						}
					}
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

	private static boolean commanding(String key, String sol, HashMap<String, String> map) {
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
