package M0229;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ1015_수열정렬 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N=Integer.parseInt(st.nextToken());
		int []arr=new int[N];
		int []tmp=new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if(arr[i]<arr[j]) {
					tmp[j]++;
				}
				else
					tmp[i]++;
			}
		}
		for (int i = 0; i < tmp.length; i++) {
			System.out.print(tmp[i]+" ");
		}
	}
}