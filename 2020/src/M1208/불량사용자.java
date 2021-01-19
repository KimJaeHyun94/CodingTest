package M1208;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 불량사용자 {
	static boolean visited[];
	static Set<String> set = new HashSet<>();

	public static void main(String[] args) {
		String[] user_id = { "frodo", "fradi", "crodo", "abc123", "frodoc" };
		String[] banned_id = { "fr*d*", "abc1**" };
		System.out.println(solution(user_id, banned_id));

	}

	public static int solution(String[] user_id, String[] banned_id) {
		visited = new boolean[user_id.length];
		dfs(0, "", user_id, banned_id);
		return set.size();
	}

	private static void dfs(int r, String string, String[] user_id, String[] banned_id) {
		if (r == banned_id.length) // 모든 불량 사용자 다 찾았을 때
		{
			String[] str = string.split(" ");
			Arrays.sort(str);
			StringBuilder sb = new StringBuilder();
			for (String stdr : str) {
				sb.append(stdr);
			}
			set.add(sb.toString()); // 똑같은 경우가 없어야 되서
			return;
		}
		for (int i = 0; i < user_id.length; i++) {
			outer: if (user_id[i].length() == banned_id[r].length() && !visited[i]) {
				for (int j = 0; j < banned_id[r].length(); j++) {
					if (user_id[i].charAt(j) == banned_id[r].charAt(j) || banned_id[r].charAt(j) == '*') {
						continue;
					} else
						break outer;
				}

				visited[i] = true;
				dfs(r + 1, string + " " + user_id[i], user_id, banned_id);
				visited[i] = false;

			}
		}
	}
}
