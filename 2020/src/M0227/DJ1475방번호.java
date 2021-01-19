package M0227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DJ1475방번호 {

	private static int arr[] = new int[10];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		for (int i = 0; i < str.length(); i++) {
			arr[str.charAt(i) - '0']++;
		}
		int max=0;
		for (int i = 0; i < arr.length; i++) {
			if(i!=6&&i!=9) {
				max=Math.max(max, arr[i]);
			}
		}
		if(arr[9]<=arr[6])
			max=Math.max(max, arr[6]/2);
		else
			max=Math.max(max, arr[9]/2);
		
		System.out.println(max);
	}

}
