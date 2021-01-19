package M1212;

public class 문자열압축2 {
	public static void main(String[] args) {
		System.out.println(solution("xxxxxxxxxxyyy"));
	}

	public static int solution(String s) {
		int answer = s.length();
		for (int i = 1; i <= s.length() / 2; i++) {
			String tmp = "";
			String str = "";
			int cnt = 1;
			int j;
			for (j = 0; j <= s.length() - i; j += i) {
				String str2 = s.substring(j, j + i);
				if (str2.equals(str)) {
					cnt++;
				} else {
					if (cnt != 1) {// 1개 이상 있다면
						tmp += Integer.toString(cnt) + str;
						cnt = 1;
					} else {
						tmp += str;
					}
					str = str2;
				}
			}
			if (cnt > 1)
				tmp = tmp + Integer.toString(cnt) + str;
			else {
				tmp += str;
			}

			if (j < s.length()) {
				tmp += s.substring(j);
			}
			answer = Math.min(answer, tmp.length());
		}
		return answer;
	}
}