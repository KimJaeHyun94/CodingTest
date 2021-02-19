package M0219;

import java.util.ArrayList;

public class 뉴스클러스터링 {
	public static void main(String[] args) {
		System.out.println(solution("FRANCE", "french"));
		System.out.println(solution("handshake", "shake hands"));
		System.out.println(solution("aa1+aa2", "AAAA12"));
		System.out.println(solution("E=M*C^2", "e=m*c^2"));
	}

	public static int solution(String str1, String str2) {
		int answer = 0;
		ArrayList<String> list1 = new ArrayList<>();
		ArrayList<String> list2 = new ArrayList<>();

		for (int i = 0; i < str1.length() - 1; i++) {
			StringBuilder sb = new StringBuilder();
			if (isalpha(str1.charAt(i)) && isalpha(str1.charAt(i + 1))) {
				sb.append(str1.charAt(i)).append(str1.charAt(i + 1));
				list1.add(sb.toString().toLowerCase());
			}
		}

		for (int i = 0; i < str2.length() - 1; i++) {
			StringBuilder sb = new StringBuilder();
			if (isalpha(str2.charAt(i)) && isalpha(str2.charAt(i + 1))) {
				sb.append(str2.charAt(i)).append(str2.charAt(i + 1));
				list2.add(sb.toString().toLowerCase());
			}
		}
		int size1 = list1.size();
		int size2 = list2.size();
		double gyo = 0; // 교집합의 크기
		for (String str : list1) {
			if (list2.contains(str)) {
				list2.remove(str);
				gyo++;
			}
		}

		double hap = size1 + size2 - gyo;

		if (hap == 0)
			return 65536;
		answer = (int) ((int) gyo / hap * 65536);

		return answer;
	}

	private static boolean isalpha(char ch) {
		return 'A' <= ch && ch <= 'Z' || ch >= 'a' && ch <= 'z';
	}
}
