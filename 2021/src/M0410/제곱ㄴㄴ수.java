package M0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 제곱ㄴㄴ수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long min = Long.parseLong(st.nextToken());
		long max = Long.parseLong(st.nextToken());

		boolean[] cache = new boolean[(int) (max - min + 1)];
		for (long i = 2; i * i <= max; i++) {
			long power = i * i;
			long start = ((min - 1) / power + 1) * power;
			for (long j = start; j <= max; j += power) {
				cache[(int) ((j) - min)] = true;
			}
		}
		int count = 0;
   
        for(int i = 0; i <= max - min; i++){
            if(!cache[i]){
                count++;
            }
        }
        
        System.out.println(count);
	}
}
