package M0323;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author 김재현
 * @see https://velog.io/@hyeon930/BOJ-16638-%EA%B4%84%ED%98%B8-%EC%B6%94%EA%B0%80%ED%95%98%EA%B8%B0-2-Java
 */
public class 괄호추가하기2 {
	static int N;
	static long ans = Integer.MIN_VALUE;
	static String[] input;
	static boolean[] braket;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		input = br.readLine().split("");
		braket = new boolean[N];
		DFS(1);
		System.out.println(ans);

	}

	private static void DFS(int idx) {
		if (idx >= N) {
			String[] copy = Copy(input);
			ans = Math.max(ans, sol(copy));
			return;
		}
		
		if(idx==1) {
			braket[idx] = true;
			DFS(idx+2);
			braket[idx]= false;
			DFS(idx+2);
		}else {
			if(!braket[idx - 2]) {
				braket[idx] = true;
				DFS(idx + 2);
				braket[idx] = false;
			}
			DFS(idx + 2);
		}
	}

	private static long sol(String[] copy) {
		long result = 0;
		copy = calcBraket(copy);
		copy = calcMulti(copy);
		result = calcPlusMinus(copy);

		return result;
	}

	private static long calcPlusMinus(String[] copy) {
		for (int i = 1; i < N; i += 2) {
			// 앞선 연산으로 연산자 자리에 숫자가 있는 경우가 있기 때문에 다음과 같이 조건을 걸어야한다.
			if (copy[i] != null && (copy[i].equals("+") || copy[i].equals("-"))) {
				int left = i - 1;
				int right = i + 1;

				while (copy[left] == null)
					left--;
				while (copy[right] == null)
					right++;

				copy[i] = calc(copy[i].charAt(0), stoi(copy[right]), stol(copy[left])) + "";
				copy[left] = null;
				copy[right] = null;
			}
		}

		// 마지막에 남아있는 숫자 하나가 연산의 결과다.
		for (int i = 0; i < N; ++i) {
			if (copy[i] != null) {
				return stol(copy[i]);
			}
		}

		return 0;
	}

	private static String[] calcMulti(String[] copy) {
		for (int i = 1; i < N; i += 2) {
			// 연산자를 찾고 가장 가까운 피연산자를 찾는다.
			if (copy[i] != null && copy[i].charAt(0) == '*') {
				int left = i - 1;
				int right = i + 1;

				while (copy[left] == null)
					left--;
				while (copy[right] == null)
					right++;

				copy[i] = calc('*', stoi(copy[right]), stol(copy[left])) + "";
				copy[left] = null;
				copy[right] = null;
			}
		}
		return copy;
	}

	private static String[] calcBraket(String[] copy) {
		// TODO Auto-generated method stub
		for (int i = 1; i < N; i += 2) {
			if (braket[i]) {
				copy[i] = calc(copy[i].charAt(0), stoi(copy[i + 1]), stoi(copy[i - 1])) + "";
				copy[i - 1] = null;
				copy[i + 1] = null;
			}
		}
		return copy;
	}

	private static String[] Copy(String[] input) {
		String[] copy = new String[input.length];
		for (int i = 0; i < input.length; i++) {
			copy[i] = input[i];
		}
		return copy;
	}

	private static long calc(char op, int num, long result) {
		switch (op) {
		case '+':
			result += num;
			break;
		case '-':
			result -= num;
			break;
		case '*':
			result *= num;
			break;
		}

		return result;
	}

	private static long stol(String s) {
		return Long.parseLong(s);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
