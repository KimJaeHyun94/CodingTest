package M0125;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수이어쓰기2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int left = 0;
		int right = N;
		int ans = 0;
		
		if (calc(N) < k) {			//첨에 못구하는 거면
			System.out.println(-1);  //-1 출력
			return;
		}

		while (left <= right) {
			int mid = (left + right) / 2; // 이분 탐색
			long check = calc(mid);

			if (check < k) {
				left = mid + 1;
			} else {
				ans = mid;
				right = mid - 1;
			}
		}
		
		long l = calc(ans);   //ans의 맨 마지막 길이
		int sub = (int)l-k;  //실제 구하는 k와의 차이 
		String str= String.valueOf(ans);

		System.out.println(str.charAt(str.length()-sub-1));   //그 차이를 
		
		
	}

	private static long calc(int mid) {
		long sum = 0;
		int len = 1; // 길이
		for (int i = 1; i <= mid; i *= 10) { // 자릿수 1~9 -> 10 ~ 99 -> 100~999
			int end = i * 10 - 1; // 9 99 999
			if (end >= mid) {
				sum += (long) ((mid - i + 1) * len);
			} else {
				sum += (long) ((end - i + 1) * len); // 현재 자리수까지의 길이 (1의자리는 (9-1+1)*1 로 9개 더해주고 2의자리는 (99-10+1)*2 180
														// (2자리수 씩 90개 잇으므로)
			}
			len++;
		}
		return sum;
	}
}
