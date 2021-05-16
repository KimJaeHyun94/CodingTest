package M0513;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 수들의합4 {
	static int N, K;
	static int arr[], sum[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		sum = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum[i] = sum[i - 1] + arr[i];			//구간 합 구성
		}

		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);
		long ans = 0;

		for (int i = 1; i <= N; i++) {
			ans += map.getOrDefault(sum[i] - K, 0);	  //잇으면 갯수를 더해준다.

			map.put(sum[i], map.getOrDefault(sum[i], 0) + 1);  
		}
		System.out.println(ans);
	}
}
