package M0222;

import java.util.Arrays;

public class 자동완성_arr {
	public static void main(String[] args) {
		System.out.println(solution(new String[] { "word", "war", "warrior", "world" }));
	}

	public static int solution(String[] words) {
		int answer = 0;
		Arrays.sort(words); // 정렬

		for (int i = 0; i < words.length; i++) {
			if (i == 0) {
				for (int j = 1; j < words[i].length(); j++) {
					if (words[i + 1].substring(0, j).equals((words[i].substring(0, j)))) {
						answer++;
					} else
						break;
				}
			} else if (i == words.length - 1) {
				for (int j = 1; j < words[i].length(); j++) {
					if (words[i - 1].substring(0, j).equals((words[i].substring(0, j)))) {
						answer++;
					} else
						break;
				}
			} else {
				for (int j = 1; j < words[i].length(); j++) {

					if (words[i - 1].length() >= j && words[i - 1].substring(0, j).equals((words[i].substring(0, j)))
							|| words[i + 1].length() >= j
									&& words[i + 1].substring(0, j).equals((words[i].substring(0, j)))) {
						answer++;
					} else
						break;
				}
			}
			answer++;
		}
		return answer;
	}
}
