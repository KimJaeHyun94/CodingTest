package M0422;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class 단어수학 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Integer alphabet[] = new Integer[26];
		Arrays.fill(alphabet, 0);
		
		for (int i = 0; i < N; i++) {
			char arr[] = br.readLine().toCharArray();
			for (int j = 0; j < arr.length; j++) {
				Integer n = arr[j] - 'A';
				alphabet[n]+=(int)Math.pow(10,  arr.length-j-1);
			}
		}

		Arrays.sort(alphabet, Collections.reverseOrder());
		int idx = 9;
		int sum = 0;
		for (Integer child : alphabet) {
			if (child > 0) {
				sum += child * idx--;
			} else
				break;
		}
		System.out.println(sum);
	}
}
