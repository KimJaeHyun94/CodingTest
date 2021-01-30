package M0130;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class 불량사용자 {
	static boolean visited[];
	static Set<String> set = new HashSet<>();
	private String[] user_id;
	private String[] banned_id;

	public int solution(String[] user_id, String[] banned_id) {
		visited = new boolean[user_id.length];
		this.user_id = user_id;
		this.banned_id = banned_id;
		dfs(0, new ArrayList<String>());
		return set.size();
	}

	private void dfs(int r, ArrayList<String> list) {
		if (r == banned_id.length) {
			Collections.sort(list);
			StringBuilder sb = new StringBuilder();
			for (String string : list) {
				sb.append(string);
			}
			set.add(sb.toString());
			return;
		}

		for (int i = 0; i < user_id.length; i++) { // 모든 유저에 대해서
			if (!visited[i] && isOK(i, r)) {
				visited[i] = true;
				ArrayList<String> list2 = (ArrayList<String>) list.clone();
				list2.add(user_id[i]);
				dfs(r + 1, list2);
				visited[i] = false;
			}
		}
	}

	private boolean isOK(int i, int r) {
		if (user_id[i].length() == banned_id[r].length()) {
			for (int j = 0; j < user_id[i].length(); j++) {
				if (banned_id[r].charAt(j) == '*' || user_id[i].charAt(j) == banned_id[r].charAt(j)) {
					continue;
				} else
					return false;
			}
		} else
			return false;

		return true;
	}
}