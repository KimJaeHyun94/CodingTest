package M1215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 공유기설치 {
	static int N, C;
	static int map[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[N];

		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(map);

		int left = 1; // 최소 거리
		int right = map[N - 1] - map[0]; // 최대 거리

		while (left <= right) {
			int start = map[0];
			int mid = (left + right) / 2;
			int cnt = 1; // 공유기 갯수
			for (int i = 1; i < N; i++) { // 2번째부터 처음까지의 거리 계산
				int d = map[i] - start;
				if (d >= mid) {	//거리가 중간을 넘어가면
					cnt++;	//공유기 갯수 증가
					start = map[i];	//출발 지점 변경
				}
			}
			
			if (cnt >= C) {	//공유기가 같거나 더 많다면 
				left = mid + 1;	//왼쪽을 증가
			} else {	//아니라면
				right = mid - 1;	//오른쪽을 감소
			}
		}
		System.out.println(left-1);	
	}
}
