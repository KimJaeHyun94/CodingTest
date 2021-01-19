package M1212;

public class 문자열압축 {
	public static void main(String[] args) {
		System.out.println(solution("xxxxxxxxxxyyy"));
	}

	public static int solution(String s) {
		int answer = s.length();
		for (int i = 1; i <= s.length() / 2; i++) {
			StringBuilder sb = new StringBuilder();
			String str = "";
			int cnt = 1;
			int j;
			for (j = 0; j <= s.length() - i; j += i) {
				String str2 = s.substring(j, j + i);
				if (str2.equals(str)) {
					cnt++;
				} else {
					if (cnt != 1) // 1개 이상 있다면
						sb.append(Integer.toString(cnt));
					sb.append(str);
					str = str2;
					cnt = 1;
				}
			}
			if (cnt != 1)
				sb.append(cnt);
			sb.append(str);
			if (j < s.length())
				sb.append(s.substring(j));

			answer = Math.min(answer, sb.toString().length());
		}
		return answer;
	}
}
