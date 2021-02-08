package M0208;

import java.util.Arrays;

public class 가사검색 {
	static String words[] = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
	static String queries[] = {"fro??", "????o", "fr???", "fro???", "pro?"};
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(words, queries)));
	}

	public static int[] solution(String[] words, String[] queries) {
		int[] answer = {};
		int N = queries.length;
		answer = new int[N];
		int idx = 0;
		for (int i = 0; i < queries.length; i++) {
			for (int j = 0; j < words.length; j++) {
				answer[i] +=sol(queries[i], words[j]);
			}
		}
		return answer;
	}

	private static int sol(String query, String word) {
		if(query.length()!=word.length())
			return 0;
		
		for (int i = 0; i < query.length(); i++) {
			if(query.charAt(i)==word.charAt(i) || query.charAt(i)=='?')
				continue;
			else
				return 0;
		}
		return 1;
	}
}
