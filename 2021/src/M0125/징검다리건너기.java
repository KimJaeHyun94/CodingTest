package M0125;
/*
 * @See https://howtolivelikehuman.tistory.com/41
 * @Author AKKabiyo
 */
public class 징검다리건너기 {
	public int solution(int[] stones, int k) {
		int answer = 0;

		int left = 0;
		int right = Integer.MAX_VALUE;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (check(stones, mid, k)) {
				answer = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}

		}
		return answer;
	}

	private boolean check(int[] stones, int mid, int k) {
		int cnt = 0;
		for (int i = 0; i < stones.length; i++) {
			if (stones[i] - mid < 0) { // 만약에 0이라면 건너 뛰기
				cnt++;				//하나씩 증가
			} else {			//1이상일 경우엔 뛸 수 있으므로 뛰고 건너뜀거리를 0으로 바꿔준다.
				cnt = 0;
			}

			if (cnt >= k) {  //건너뛰는게 k 이상이라면 못 뛰므로 false
				return false;
			}
		}
		return true;
	}
}
