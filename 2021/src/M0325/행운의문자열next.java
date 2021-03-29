package M0325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/**
 * 
 * @author 김재현
 * @see NextPermutation
 */
public class 행운의문자열next {
	static String line;
	static char[] charray;
	static int ct = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		line = br.readLine();

		charray = line.toCharArray();
		Arrays.sort(charray);
		do {
			if (isLucky(charray)) {
				ct++;
			}
		} while (nextPermutation());

		System.out.println(ct);
	}

	private static boolean nextPermutation() {
		int i = -1;
		for (i = charray.length - 2; i >= 0; i--) {
			if (charray[i] < charray[i + 1]) {
				break;
			}
		}
		if (i < 0) {
			return false;
		}
		int j;
		for (j = charray.length - 1; j >= 0; j--) {
			if (charray[i] < charray[j]) {
				break;
			}
		}

		swap(i,j);

		for (int a = i + 1, b = charray.length - 1; a < b; a++, b--) {
			swap(a,b);
		}

		return true;
	}

	private static void swap(int i, int j) {
		char temp = charray[i];
		charray[i] = charray[j];
		charray[j] = temp;
	}

	private static boolean isLucky(char[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] == arr[i + 1]) {
				return false;
			}
		}
		return true;
	}
}
