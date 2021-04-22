package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 합이0인네정수 {
	static int N, arr[][];
	static int AB[], CD[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][4];
		AB = new int[N * N];
		CD = new int[N * N];
		StringTokenizer st;
		int index = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken()); // A
			arr[i][1] = Integer.parseInt(st.nextToken()); // B
			arr[i][2] = Integer.parseInt(st.nextToken()); // C
			arr[i][3] = Integer.parseInt(st.nextToken()); // D
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				AB[index] = arr[i][0] + arr[j][1]; // AB곱셈
				CD[index] = arr[i][2] + arr[j][3]; // CD곱셈
				index++;
			}
		}
		Arrays.sort(CD);

		long ans = 0;
		for (int key : AB) {
			int upper = upperbound(CD, -key);
			int	lower = lowerbound(CD, -key);
			ans+=(upper-lower);
		}
		
		System.out.println(ans);
	}

	private static int upperbound(int[] CD, int target) {
		int left = 0;
		int right = CD.length;
		
		while(left<right) {
			int mid = (left+right)>>1;
			
			if(CD[mid]<=target) {
				left = mid+1;
			}else
				right = mid;
		}
		return right;
	}

	private static int lowerbound(int[] CD, int target) {
		int left = 0;
		int right = CD.length-1;
		
		while(left<=right) {
			int mid = (left+right)>>1;
			
			if(CD[mid]<target) {
				left = mid+1;
			}else
				right = mid-1;
		}
		return right+1;
	}
}

