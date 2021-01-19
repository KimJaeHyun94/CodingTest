import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ1722순열의순서 {
	private static int N;
	private static int M;
	private static long arr[];
	private static long T;
	private static int check[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		arr = new long[N+1];
		arr[0]=1;
		for (int i = 1; i < N; i++) {
			arr[i] = arr[i-1]*i;
		}
		if (M == 1) {
			T=Long.parseLong(st.nextToken());
			int arr2[]=nth_premutation(T, N);
			for (int i = 0; i < N; i++) {
				System.out.print(arr2[i]+" ");
			}
			System.out.println();
		} else if (M == 2) {
			check = new int[N];
			for (int i = 0; i < N; i++) {
				check[i] = Integer.parseInt(st.nextToken());
			}
			System.out.println(order_permutation());
		}
	}

	private static long order_permutation() {
		Set<Integer> set = new HashSet<>();
		long count = 0;
		int k;
		for (int i = 0; i < check.length; i++) {
			for (k = 1; k < check[i]; k++) {
				if (!set.contains(k))
					count += arr[check.length - i - 1];
			}
			set.add(k);
		}
		return count+1;

	}

	public static int[] nth_premutation(long M, int size) {
		Set<Integer> set = new HashSet<>();
		int[] arr2 = new int[size]; // N번째 순열 , size
		int i;
		for (i = 0; i < size; i++) {
			for (int j = 1; j <= size; j++) {
				if (set.contains(j))
					continue;
				if (arr[size - i - 1] < M) {
					M -= arr[size - i - 1];
				} else {
					arr2[i] = j;
					set.add(j);
					break;
				}
			}
		}
		return arr2;
	}
}
