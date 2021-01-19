package M1214;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class 튜플 {

	public static void main(String[] args) {
		String s = "{{4,2,3},{3},{2,3,4,1},{2,3}}";
		System.out.println(Arrays.toString(solution(s)));

	}

	public static int[] solution(String s) {

		s = s.substring(2, s.length() - 2);
		s = s.replace("},{", " ");
		String[] str = s.split(" ");

		ArrayList<String> list = new ArrayList<>();

		Arrays.sort(str, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}

		});
		for (String string : str) {
			String[] val = string.split(",");
			for (int i = 0; i < val.length; i++) {
				if (!list.contains(val[i])) {
					list.add(val[i]);
				}
			}
		}
		list.remove(",");
		int[] answer = new int[list.size()];
		int i = 0;
		for (String str2 : list) {
			answer[i++] = Integer.parseInt(str2);
		}
		return answer;
	}
}
