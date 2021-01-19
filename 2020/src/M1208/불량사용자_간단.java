package M1208;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 불량사용자_간단 {
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
		if (r == banned_id.length) {
			String[] str = string.split(" ");

			//Arrays.sort(str);
			StringBuilder sb = new StringBuilder();
			for (String s : str) {
				sb.append(s);
			}
			set.add(sb.toString());
			return;
		}
		for (int i = 0; i < user_id.length; i++) {
			String s = banned_id[r].replace("*", ".");
			if (user_id[i].matches(s) && !visited[i]) {
				visited[i] = true;
				dfs(r + 1, string + " " + user_id[i], user_id, banned_id);
				visited[i] = false;
			}
		}
	}

}
