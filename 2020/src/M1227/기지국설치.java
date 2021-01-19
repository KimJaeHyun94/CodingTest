package M1227;

public class 기지국설치 {

	public static void main(String[] args) {
		System.out.println(solution(11, new int[] {4,11}, 1 ));
	}

	public static int solution(int n, int[] stations, int w) {
		int answer = 0;

		int idx = 0;
		int start = 1;
		// 맨 왼쪽부터 진행
		
		while (start <= n) {
			System.out.println(start+" "+answer);
			if (idx<stations.length && start >= stations[idx] - w) { // 범위 안에 있으면
				start=stations[idx++]+w+1;
			} else { // 기지국 설치하고 넘기기
				answer++;
				start += (2 * w) + 1;
			}
		}
		return answer;
	}
}
