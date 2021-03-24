package M0320;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class 라인1 {
	static HashMap<String, Integer> map;

	public static void main(String[] args) {
		String[] table = { "SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA" };
		String[] languages = {"JAVA", "JAVASCRIPT" };
		int[] preference = { 7, 5};
		System.out.println(solution(table, languages, preference));
	}

	public static String solution(String[] table, String[] languages, int[] preference) {
		String answer = "";
		map = new HashMap<>();

		for (int i = 0; i < languages.length; i++) {
			map.put(languages[i], preference[i]);
		}

		int MAX = Integer.MIN_VALUE;
		for (String str : table) {
			String[] sp = str.split(" ");

			String name = sp[0];

			String[] sp2 = new String[6];
			int j = 5;

			for (int i = 1; i <= 5; i++) {
				sp2[i] = sp[j--];
			}

			int score = 0;

			for (int i = 1; i <= 5; i++) {
				int pre = 0;
				if (map.containsKey(sp2[i])) {
					pre = map.get(sp2[i]);
				}
				pre *= i;
				score += pre;
			}
			if (MAX <= score) {
				MAX = score;
				if(answer.compareTo(name)>0) {
					answer=name;
				}else
				answer = name;
			}
		}
		return answer;
	}
}
