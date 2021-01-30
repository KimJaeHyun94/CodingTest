package M0131;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;
/*
 * @Author AKKabiyo
 * @Explanation : 같은 수를 여러 번 골라도 되는 순열 && 순열 간 중복 불가
 */
public class N과M_11 {
	static int N, M;
	static int arr[];

	static HashSet<String> set;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		set = new HashSet<>();
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
	
		makePermutation(0,"");
		System.out.println(sb);
	}

	private static void makePermutation(int i,String s) {
		if (i == M) {
	
			if (!set.contains(s)) {
				set.add(s);
				sb.append(s.substring(0, s.length()-1)).append("\n");
			}
			return;
		}
		for (int j = 0; j < N; j++) {
			makePermutation(i + 1, s+arr[j]+" ");

		}
	}

}