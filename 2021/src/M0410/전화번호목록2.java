package M0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class 전화번호목록2 {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			Trie trie = new Trie();
			List<String> keys = new ArrayList<String>();

			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				keys.add(line);
			}

			boolean flag = true;
			Collections.sort(keys);

			for (String str : keys) {
				if (!trie.insert(str)) {
					flag = false;
					break;
				}
			}

			if (flag) {
				sb.append("YES").append("\n");
			} else {
				sb.append("NO").append("\n");
			}

		}
		System.out.println(sb);
	}

	static class Trie {
		static class Node { // node
			private final TreeMap<Character, Node> children; // 자식 노드
			private boolean isEnd;

			Node() {
				children = new TreeMap<>();
			}
		}

		private final Node root;

		Trie() {
			root = new Node();
		}

		public boolean insert(String ants) {
			Node cur = root;
			int len = ants.length();

			for (int i = 0; i < len; i++) {
				char ch = ants.charAt(i);
				Node child = cur.children.get(ch);
				if (child == null) {
					child = new Node();
					cur.children.put(ch, child);
				}
				cur = child;
				if (cur.isEnd && i < len - 1)
					return false;
			}
			cur.isEnd = true;
			return true;
		}

	}
}
