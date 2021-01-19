package M1208;

public class 줄서는방법 {
	static long N;
	static int[] answer;
	static int idx;

	public static void main(String[] args) {
		solution(3, 5);
	}

	public static int[] solution(int n, long k) {
		answer = new int[n];
		// factorial을 n으로 나눈 수, (n-1)! => 한 숫자가 가장앞에오는 경우의 수
		boolean[] chk = new boolean[n];
		N = n - 1;
		idx = 0;
		getNumber(n, k, chk, fac(n - 1));

		return answer;
	}

	private static void getNumber(int n, long k, boolean[] chk, long fact) {
		if (idx == N) {
			for (int i = 0; i < chk.length; i++) {
				if (!chk[i]) {
					answer[idx] = i + 1;
					break;
				}

			}
			return;
		}
		long last = k % fact == 0 ? fact : k % fact;
		long first = k % fact == 0 ? k / fact : k / fact + 1;
		int cnt = 0;
		for (int i = 0; i < chk.length; i++) {
			if (!chk[i]) {
				cnt++;
				if (cnt == first) {
					answer[idx++] = i + 1;
					chk[i] = true;
					getNumber(n - 1, last, chk, fact / (n - 1));
				}
			}
		}
	}

	public static long fac(int n) {
		long i = 1;
		for (int j = 2; j < n + 1; j++) {
			i *= j;
		}
		return i;
	}
}
