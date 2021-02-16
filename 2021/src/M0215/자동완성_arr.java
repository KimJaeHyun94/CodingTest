package M0215;

import java.util.Arrays;

/**
 * @author AKKabiyo
 * @See : 카카오 풀이
 */
public class 자동완성_arr {
	public int solution(String[] words) {
		int answer = 0;
		Arrays.sort(words);

		for (int i = 0; i < words.length; i++) {
			if (i == 0) { // 처음일때
				for (int j = 1; j < words[i].length(); j++) {
					if (words[i + 1].indexOf(words[i].substring(0, j)) == 0) { // 처음에 똑같은 데가 있다면 계속 쳐줘야한다.
						answer++;
					} else
						break;
				}
			} else if (i == words.length - 1) { // 맨 마지막이라면
				for (int j = 1; j < words[i].length(); j++) {
					if (words[i - 1].indexOf(words[i].substring(0, j)) == 0) {
						answer++;
					} else
						break;
				}
			} else { // 가운데라면
				for (int j = 1; j < words[i].length(); j++) {
					if (words[i - 1].indexOf(words[i].substring(0, j)) == 0
							|| words[i + 1].indexOf(words[i].substring(0, j)) == 0) {
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
