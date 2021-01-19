package M0305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ신기한소수 {
	static int num[] = new int[9];
	static int N;

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 9; i++) {
			num[i] = i + 1;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		nextpermutation(0, "");
	}

	private static void nextpermutation(int cnt, String temp) {
		if (cnt == N) {
			System.out.println(temp);
		} else {
			for (int j = 0; j < num.length; j++) {
				if (Prime(Integer.parseInt(temp.concat(Integer.toString(num[j]))))) {
					nextpermutation(cnt + 1, temp.concat(Integer.toString(num[j])));
				}
			}
		}
	}

	private static boolean Prime(int check) {
		if(check==1) {
			return false;
		}
		for (int i = 2; i*i <=check; i++) {
			if(check%i==0)
				return false;
		}
		return true;
	}
}
