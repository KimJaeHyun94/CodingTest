package M0403;

public class test2 {
	static int N, R, K;
	static int MAX = Integer.MIN_VALUE;

	public static void main(String[] args) {
		int[][] needs = { { 1, 0, 0 }, { 1, 1, 0 }, { 1, 1, 0 }, { 1, 0, 1 }, { 1, 1, 0 }, { 0, 1, 1 } };
		System.out.println(solution(needs, 2));
	}

	public static int solution(int[][] needs, int r) {
		int answer = 0;
		N = needs.length;
		K = needs[0].length;
		R = r;

		combination(new int[r], 0, 0, needs);

		return MAX;
	}

	private static void combination(int[] temp, int cnt, int start, int[][] needs) {
		if (cnt == R) {
			int tmp = 0;

			for (int j = 0; j < needs.length; j++) {
				int count = 0;
				int one = 0;
				for (int i = 0; i < needs[j].length; i++) {
					if (needs[j][i] == 1)
						one++;
				}
				for (int i = 0; i < temp.length; i++) {
					if (temp[i] >= needs[j].length)
						continue;
					if (needs[j][temp[i]] == 1) {
						count++;
					}

				}
				if (one == count) {
					tmp++;
				}
			}

			MAX = Integer.max(MAX, tmp);
			return;
		}
		for (int i = start; i < N; i++) {
			temp[cnt] = i;
			combination(temp, cnt + 1, i + 1, needs);
		}
	}
}
