package M0412;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 다이어트 {
	static int G;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(br.readLine());

		int left = 1;
		int right = 1;
		boolean flag = false;
		while (left <= right) {
			long g = (long) right * right - (long) left * left;
			if (right == left + 1 && g > G)
				break;
			if (g <= G) {
				if (g == G) {
					System.out.println(right);
					flag = true;
				}
				right++;
			} else {
				left++;
			}
		}
		if (!flag) {
			System.out.println(-1);
		}

	}
}
