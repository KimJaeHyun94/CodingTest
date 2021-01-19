import java.util.Scanner;

public class SWEA_1244_D3_최대상금_김재현 {
	private static int arr[];
	private static int N;
	private static int max = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			max = 0;
			String str = sc.next();
			N = sc.nextInt();

			arr = new int[str.length()];

			for (int i = 0; i < arr.length; i++) {
				arr[i] = str.charAt(i) - '0';
			}

			DFS(0, 0);
			System.out.print("#" + tc + " " + max);
			System.out.println();
		}
	}

	private static void DFS(int r, int start) {
		if (r == N) {
			String str = "";
			for (int i = 0; i < arr.length; i++) {
				str += Integer.toString(arr[i]);
			}
			max = Math.max(max, Integer.parseInt(str));
			return;
		} else {
			for (int i = start; i < arr.length - 1; i++) {
				for (int j = i + 1; j < arr.length; j++) {
					if (arr[i] <= arr[j]) {
						swap(i, j);
						DFS(r + 1, i);
						swap(i, j);
					}
				}
			}
		}
	}

	private static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}

