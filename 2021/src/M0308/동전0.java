package M0308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 동전0 {
	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> coin = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			coin.add(Integer.parseInt(br.readLine()));
		}
		
		Collections.sort(coin, Collections.reverseOrder());
		int ans = 0;
		for (int i = 0; i < N; i++) {
			if(coin.get(i)>K)
				 continue;
			
			ans+=K/coin.get(i); //나눈 개수
			K%=coin.get(i);  //나누고 난 나머지
		}
		System.out.println(ans);
		
	}

}
