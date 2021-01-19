package M1230;

public class 징검다리건너기 {

	public static void main(String[] args) {
		
	}

	public int solution(int[] stones, int k) {
		int answer = 0;

		int left = 0;
		int right = Integer.MAX_VALUE;

		while (left <= right) {
			int mid = (left + right) / 2;
			if (sol(mid, stones, k)) {
				answer = mid;
				left = mid+1;
			}
			else {
				right = mid-1;
			}
		}
		return answer;
	}

	private boolean sol(int mid, int[] stones, int k) {
		int cnt = 0;
		for (int i = 0; i < stones.length; i++) {
			if (stones[i] - (mid - 1) <= 0)
				cnt++;
			else
				cnt = 0;

			if (cnt >= k)
				return false;
		}

		return true;
	}
}