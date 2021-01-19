package M1211;

import java.util.ArrayList;
import java.util.Arrays;

public class n진수게임 {
	private static ArrayList<String> ans;
	static String map[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
	
	public static void main(String[] args) {
		System.out.println(solution(2, 4, 2, 1));
	}

	public static String solution(int n, int t, int m, int p) {
		String answer = "";
		ans = new ArrayList<>();
		int num = t * m;
		
		for (int i = 0; i < num; i++) {
			sol(i,n);
		}
		int cnt = 0;
		for (int c = p - 1; c <ans.size() ; c +=m) {
			answer += ans.get(c);
			cnt++;
			if (cnt == t)
				break;
		}

		return answer;
	}

	private static void sol(int i, int n) {
		 ArrayList<String> str = new ArrayList<>();
		
		while (i / n != 0) {
			int temp = i % n;
			str.add(map[temp]);
			i /= n;
		}
		str.add(map[i%n]);
		for (int j = str.size()-1; j >=0; j--) {
			ans.add((str.get(j)));
		}
	}
}
