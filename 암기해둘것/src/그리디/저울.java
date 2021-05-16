package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 저울 {
	static int N;
	static int arr[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		if(arr[0]!=1) {
			System.out.println(1);
		}else {
			
			int sum = arr[0];
			
			for (int i = 1; i < N; i++) {
				if(arr[i]>sum+1) {
					break;
				}
				sum+=arr[i];
			}
			System.out.println(sum+1);
		}
	}
}
