package M0105;

public class 스타수열 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] { 5, 2, 3, 3, 5, 3 }));

	}

	public static int solution(int[] a) {
		int answer = -1;
		int check[] = new int[a.length + 1];
		for (int i = 0; i < a.length; i++) {
			check[a[i]]++;
		}

		for (int i = 0; i < check.length; i++) {
			if (check[i] <= answer)
				continue;
			int res = 0;

			for (int j = 0; j < a.length - 1; j++) {
				if (a[j] != i && a[j + 1] != i)
					continue;
				if (a[j] == a[j + 1])
					continue;

				res++;
				j++;
			}
			answer = Math.max(answer, res);
		}
		return answer * 2;
	}
}
