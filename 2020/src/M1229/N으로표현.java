package M1229;

import java.util.HashSet;
import java.util.Set;

public class N으로표현 {

	public static void main(String[] args) {	
		solution(5,12);
	}

	public static int solution(int N, int number) {
		int answer = -1;
		Set<Integer> dp[];
		dp = new HashSet[9];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = new HashSet<>();
		}

		int cnt = 1;
		int temp_N = 0;
		for (int i = 0; i < 8; i++) {
			temp_N = temp_N * 10 + N;
			dp[i].add(temp_N);
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < i; j++) {
				for (Integer op1 : dp[j]) {
					for (Integer op2 : dp[i-j-1]) {
						dp[i].add(op1+op2);
						dp[i].add(op1-op2);
						dp[i].add(op1*op2);
						if(op2!=0) dp[i].add(op1/op2);
					}
				}
			}
			if(dp[i].contains(number)) {
				answer=i+1;
				break;
			}
		}
		
		
		return answer;
	}
}
