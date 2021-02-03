package M0203;

import java.util.Arrays;

public class 순위검색_1 {
	public static void main(String[] args) {
		String[] info = { "java backend junior pizza 150", "python frontend senior chicken 210",
				"python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
				"python backend senior chicken 50" };
		String[] query = { "java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
				"- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150" };
		System.out.println(Arrays.toString(solution(info, query)));
	}

	public static int[] solution(String[] info, String[] query) {
		int N = info.length;
		int[] answer = new int[query.length];

		String[][] information = new String[info.length][5];
		String[][] ans = new String[query.length][5];

		for (int i = 0; i < N; i++) {
			information[i] = info[i].split(" ");
		}
		for (int i = 0; i < query.length; i++) {
			query[i] = query[i].replace(" and", "");
		}
		for (int i = 0; i < query.length; i++) {
			ans[i] = query[i].split(" ");
		}
		
		
		for (int i = 0; i < query.length; i++) {
			int cnt = 0;
			boolean flag = true;
			for (int j = 0; j < info.length; j++) {
				for (int k = 0; k < 5; k++) {
					if (k == 4) {
						if (Integer.parseInt(information[j][k]) < Integer.parseInt(ans[i][k])) {
							flag = false;
						} else {
							flag = true;
						}
					} else {
						if (ans[i][k].equals(information[j][k]) || ans[i][k].equals("-")) {
							continue;
						} else {
							flag = false;
							break;
						}
					}
				}
				if (flag)
					cnt++;
			}
			answer[i] = cnt;
		}

		return answer;
	}
}
