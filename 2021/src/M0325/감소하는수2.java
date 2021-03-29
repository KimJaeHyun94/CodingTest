package M0325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author 김재현
 * @see https://mygumi.tistory.com/150
 */
public class 감소하는수2 {
	static int idx;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		idx = Integer.parseInt(br.readLine());
		if (idx > 1022) {
			System.out.println(-1);
			System.exit(0);
		}
		int count = 0;
		long num = 0;

		while (true) {
			boolean isDsc = true;
			long temp = num;
			long jump = 1;
			long before = -1;

			while (temp != 0) {
				
				if (temp % 10 <= before) {
					isDsc = false;
				}

				if (!isDsc && temp % 10 > before) {
					break;
				}
				
				jump *= 10;
				before = temp % 10;
				temp /= 10;
			}
			
			if (isDsc) {
				if (idx == count++) {
					break;
				}
				num++;
			} else {
				jump /= 10;
				num += jump;
				num = num / jump * jump;
				continue;
			}
			
		}
		System.out.println(num);
	}
}