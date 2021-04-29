package M0428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class 추월 {
	static HashMap<String, Integer> order;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		order = new HashMap();

		for (int i = 0; i < N; i++) {
			order.put(br.readLine(), i);
		}
		
		int[] move = new int[N];
		for (int i = 0; i < N; i++) {
			move[i] = order.get(br.readLine());
		}
		int cnt =0;
		
		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				if(move[i]>move[j]) {
					cnt++;
					break;
				}
			}
		}
		
		System.out.println(cnt);
	}

}
