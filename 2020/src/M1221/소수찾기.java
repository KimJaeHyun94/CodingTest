package M1221;

import java.util.HashSet;
import java.util.Set;

public class 소수찾기 {
	static Set<Integer> set = new HashSet<>();
	static boolean[] visited;
	public static void main(String[] args) {
		solution("17");
	}
	public static int solution(String numbers) {
		int answer = 0;
		String[] strArr = numbers.split("");

		visited = new boolean[strArr.length];

		for (int i = 1; i <= strArr.length; i++) {
			perm(strArr, 0, i, "");
		}
		answer = set.size();
		return answer;
	}

	private static void perm(String[] strArr, int r, int n, String str) {
		if (r == n) {
			prime(str, r);
		}
		for (int i = 0; i < strArr.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				perm(strArr, r+1, n, str + strArr[i]);
				visited[i] = false;
			}
		}
	}

	private static void prime(String str, int r) {
		int num = Integer.parseInt(String.valueOf(str));
		if(num<=1)
			return;
		else if (num <= 3) {
			set.add(num);
			return;
		} else {
			boolean flag = true;
			for (int i = 2; i < num; i++) {
				if (num % i == 0) {
					flag = false;
					break;
				}
			}
			if (flag) {
				set.add(num);
			}else
				return;
		}
		return;
	}
}
