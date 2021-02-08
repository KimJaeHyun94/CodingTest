package M0208;

import java.util.HashMap;

import M0208.가사검색trie.Trie.Node;

public class 자동완성map {
	public int solution(String[] words) {
		int answer = 0;

		Trie trie = new Trie();
		for (String word : words) {
			trie.insert(word);
		}

		for (String word : words) {
			answer += trie.count(word);
		}
		return answer;
	}

	static class Node { // node
		private final HashMap<Character, Node> children; // 자식 노드
		private boolean isEnd; // 마지막인지 체크
		private int cnt; // 개수 세기

		Node() {
			children = new HashMap<>();
		}
	}

	static class Trie {

		private final Node root;

		Trie() {
			root = new Node();
		}

		public void insert(final String word) {
			Node cur = root;
			final int len = word.length();

			for (int i = 0; i < len; i++) {
				char c = word.charAt(i);
				Node child = cur.children.get(c);
				
				if (child == null) {
					child = new Node();
					cur.children.put(c, child);
				}
				child.cnt++;
				cur = child;
			}
			cur.isEnd = true;
		}

		public int count(final String query) {
			Node cur = root;
			int cnt = 1;
			final int len = query.length();

			for (int i = 0; i < len; i++) {
				char c = query.charAt(i);
				if (i == len - 1)
					return cnt;
				Node child = cur.children.get(c);
				
				if (child != null) {
					if (child.cnt <= 1) {
						return cnt;
					}
					cur = child;
				}
				cnt++;
			}
			return cnt;
		}
	}
}
