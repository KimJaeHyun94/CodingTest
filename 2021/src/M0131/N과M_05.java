package M0131;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;
/*
 * @Author AKKabiyo
 * @Explanation : 순열(같은 수 중복 불가)
 */
public class N과M_05 {
	static int N, M;
	static int arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		boolean visited[] = new boolean[N];

		makePermutation(0, visited, new int[M]);
		
	}

	private static void makePermutation(int i, boolean visited[], int temp[]) {
		if (i == M) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < temp.length; j++) {
				sb.append(temp[j]+" ");
			}
			System.out.println(sb);
			return;
		}
		for (int j = 0; j < N; j++) {
			if (!visited[j]) {
				visited[j] = true;
				temp[i] = arr[j];
				makePermutation(i + 1, visited, temp);
				visited[j] = false;
			}
		}
	}

}