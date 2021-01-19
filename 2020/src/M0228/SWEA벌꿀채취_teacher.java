package M0228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA벌꿀채취_teacher {
	static int N;
	static int M;
	static int C;
	static int [][]map,maxMap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			C=Integer.parseInt(st.nextToken());
			map=new int[N][N];
			maxMap=new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			//부분집합 최대이익 계산
			makeMaxMap();
			//일꾼의 조합
			int result=getMaxBenefit();
			System.out.println("#"+tc+" "+result);
		}
	}
	private static void makeMaxMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N-M; j++) {
				makeMaxSubset(i,j,0,0,0);
			}
		}
	}
	/*
	 * cnt : 고려한 원소수
	 * sum : 부분집합에 속한 원소의 합
	 * powSum : 부분집합에 속한 원소의 이익
	 */
	private static void makeMaxSubset(int i, int j, int cnt,int sum, int powSum) {
		if(sum>C)
			return;
		if(cnt==M) {
			if(maxMap[i][j-M]<powSum) {   //초기위치를 위해서
				maxMap[i][j-M]=powSum;
			}
			return;
		}
		//i,j위치 원소 선택
		makeMaxSubset(i,j+1,cnt+1, sum+map[i][j], powSum+(map[i][j]*map[i][j]));
		//i,j위치 원소 비선택
		makeMaxSubset(i,j+1,cnt+1,sum,powSum);
	}
	private static int getMaxBenefit() {
		int max=0,temp=0; 
		//일꾼 A 기준 선택
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <=N-M; j++) { //A일꾼
				//일꾼 B 선택
				//같은 행기준 선택
				for (int j2 = j+M; j2 <= N-M; j2++) {
					temp=maxMap[i][j]+maxMap[i][j2];
					max=Math.max(max, temp);
				}
				//다음행부터 마지막행까지 선택
				for (int i2 = i+1; i2 < N; i2++) {
					for (int j2 = 0; j2 <= N-M; j2++) {
						temp=maxMap[i][j]+maxMap[i2][j2];
						max=Math.max(max, temp);
					}
				}
			}
		}
		return max;
	}
}
