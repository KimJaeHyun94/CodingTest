package M0208;

import java.util.Arrays;
import java.util.HashMap;
/*
 * @Author : AKKabiyo
 * @See : https://blog.junghl.ee/ps/programmers/lyrics-search-60060/
 * 
 */
public class 가사검색trie {
	static String words[] = { "frodo", "front", "frost", "frozen", "frame", "kakao" };
	static String queries[] = { "fro??", "????o", "fr???", "fro???", "pro?" };

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(words, queries)));
	}

	public static int[] solution(String[] words, String[] queries) {
		int[] answer = {};

		Trie[] trie = new Trie[10001];
		Trie[] rev = new Trie[10001];

		answer = new int[queries.length];

		for (int i = 0; i < words.length; i++) {
			int len = words[i].length();

			if (trie[len] == null) {
				trie[len] = new Trie(); // 만들어준다
			}
			trie[len].insert(words[i]); // 단어를 입력한다 (단어 길이별로)
			if (rev[len] == null) {
				rev[len] = new Trie(); // 거꾸로 된 단어
			}
			rev[len].insert(reverse(words[i]));
		}
		
		for (int i = 0; i < queries.length; i++) {
			int len = queries[i].length();
			
			if(queries[i].charAt(0)=='?') {   //접두사가 ?라면
				if(rev[len]!=null) {    
					answer[i] = rev[len].count(reverse(queries[i]));   
				}
			}else {
				if(trie[len]!=null)
					answer[i] = trie[len].count(queries[i]);
			}
		}
		return answer;
	}

	private static String reverse(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = str.length()-1; i >= 0; i--) {
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}

	static class Trie {

		static class Node { // node
			private final HashMap<Character, Node> children;   //자식 노드
			private int cnt;   //개수 세기
 
			Node() {
				children = new HashMap<>();
			}
		}

		private final Node root;

		Trie() {
			root = new Node();
		}

		public void insert(final String word) {
			Node cur = root;
			final int len = word.length();

			for (int i = 0; i < len; i++) {
				char c = word.charAt(i);

				cur.cnt++;
				Node child = cur.children.get(c);
				if (child == null) {
					child = new Node();
					cur.children.put(c, child);
				}
				cur = child;
			}
		}

		public int count(final String query) {
			Node cur = root;
			final int len = query.length();

			for (int i = 0; i < len; i++) {
				char c = query.charAt(i);

				if (c == '?') {    //"?"일 경우에 무조건 맞기 때문에 넘어간다.
					return cur.cnt;
				}
				Node child = cur.children.get(c);
				if (child == null) {
					return 0;
				}
				cur = child;
			}
			return cur.cnt;
		}
	}
}
