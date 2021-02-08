package M0208;

import java.util.Arrays;
import java.util.Comparator;

public class 가사검색이분탐색 {
	static String words[] = { "frodo", "front", "frost", "frozen", "frame", "kakao" };
	static String queries[] = { "fro??", "????o", "fr???", "fro???", "pro?" };
	static int N, Q;
	static String[] head, tail;

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(words, queries)));
	}

	public static int[] solution(String[] words, String[] queries) {
		N = words.length;
		Q = queries.length;
		head = words;
		tail = new String[N];
		StringBuilder t;
		for (int i = 0; i < N; i++) {
			t = new StringBuilder(words[i]);
			tail[i] = t.reverse().toString();
		}
		Arrays.sort(head, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				if (o1.length() == o2.length()) {
					return o1.compareTo(o2);
				} else {
					return o1.length() - o2.length();
				}
			}

		});
		Arrays.sort(tail, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				if (o1.length() == o2.length()) {
					return o1.compareTo(o2);
				} else {
					return o1.length() - o2.length();
				}
			}

		});
		int[] answer = new int[Q];
		for (int i = 0; i < Q; i++) {
			int len = queries[i].length();
			String[] wordlist = head;
			if (queries[i].charAt(0) == '?') {
				t = new StringBuilder(queries[i]);
				queries[i] = t.reverse().toString();
				wordlist = tail;
			}
			for (int j = 0; j < len; j++) {
				if (queries[i].charAt(j) == '?') {
					int st = findLeft(queries[i].substring(0, j), wordlist, len);
					if (st == -1) {
						answer[i] = 0;
						break;
					}
					int en = findRight(queries[i].substring(0, j), wordlist, len, st);
					if (st == -1 || en == -1)
						answer[i] = 0;
					else
						answer[i] = en - st;
					break;
				}
			}
		}
		return answer;
	}

	static int findLeft(String str, String[] words, int len) {
		int l = 0, r = N - 1, answ = -1;
		while (l <= r) {
			int m = (l + r) /2;
			if (words[m].length() < len) {
				l = m + 1;
			} else if (words[m].length() > len) {
				r = m - 1;
			} else if (words[m].compareTo(str) >= 0) {
				answ = m;
				r = m - 1;
			} else
				l = m + 1;
		}
		return answ;
	}

	static int findRight(String str, String[] words, int len, int start) {
		int l = start, r = N - 1, answ = -2;
		while (l <= r) {
			int m = (l + r) /2;
			if (words[m].length() > len) {
				r = m - 1;
			} else if (words[m].startsWith(str)) {
				l = m + 1;
				answ = m;
			} else {
				r = m - 1;
			}
		}
		return answ + 1;
	}
}
