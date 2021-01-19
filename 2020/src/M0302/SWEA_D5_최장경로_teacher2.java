package M0302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D5_최장경로_teacher2 {
	static int N, p[];

	/*
	 * N:고객집 수 p : 고객집의 순서를 만들 순열용 배열
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] customers = new int[N][2];
			int[][] distance = new int[N + 2][2];
			p = new int[N];
			int min = Integer.MAX_VALUE;

			st = new StringTokenizer(br.readLine());
			distance[0][0] = Integer.parseInt(st.nextToken()); // 회사
			distance[0][1] = Integer.parseInt(st.nextToken());
			distance[N + 1][0] = Integer.parseInt(st.nextToken()); // 집
			distance[N + 1][1] = Integer.parseInt(st.nextToken());

			for (int i = 0; i < N; i++) {
				customers[i][0] = Integer.parseInt(st.nextToken());
				customers[i][1] = Integer.parseInt(st.nextToken());
				p[i] = i + 1;
			}
			// p배열 = {1,2,3,4,5}
			// np 호출 => 1,2,3,5,4
			// np 호출 => 1,2,4,3,5

			do {
				for (int i = 0; i < N; i++) {
					distance[p[i]] = customers[i];
				}
				int temp = 0;
				for (int i = 0; i <= N; i++) {
					temp += Math.abs(distance[i][0] - distance[i + 1][0]);
					temp += Math.abs(distance[i][1] - distance[i + 1][1]);
				}
				if(min>temp) {
					min=temp;
				}
			} while (nextPermutation2());
			System.out.println("#"+tc+" "+min);
		}
	}
	private static boolean nextPermutation2() {
		//step 1 : i찾기
		int i;
		for (i = p.length-2; i>=0; i--) {
			if(p[i]<p[i+1]) {
				break;
			}
		}
		//맨 마지막 경우는 위에 만족하는 i가 없다!
		if(i<0)
			return false;
		//step 2 : j찾기
		int j;
		for (j =p.length-1; j>=0; j--) {
			if(p[i]<p[j])
				break;
		}
		//step 3 : swap
		swap(i,j);
		
		//step 4 : reverse
		for (int a = i+1,b=p.length-1; a<b; a++, b--) {
			swap(a,b);
		}
		return true;
	}
	private static void swap(int a, int b) {
		int temp=p[a];
		p[a]=p[b];
		p[b]=temp;
		
	}

	/*private static boolean nextPermutation() {
		//1.뒤쪽부터 탐색하며 꼭대기(i) 찾기 : i-1=> 교환 위치
		int i=N-1;
		while(i>0 && p[i-1]>=p[i])
			--i;
		if(i==0)
			return false;  //이미 제일 큰 마지막 순열이므로 다음 순열 없음
		
		//2. 뒤쪽부터 탐색하며 교환할 큰 값(j) 찾기
		int j=N-1;
		while(p[i-1]>=p[j]) 
			--j;
		//3. i-1, j위치값 교환
		int temp=p[i-1];
		p[i-1]=p[j];
		p[j]=temp;
		
		//4. i 위치부터 N-1(맨뒤)까지 내림차순형태의 숫자를 오름차순으로 가장 작은 수로 만들기 위해 정렬
		int k=N-1;
		while(i<k) {
			temp=p[i];
			p[i]=p[k];
			p[k]=temp;
			++i; --k;
		}
		
		return true;
	}*/
}
