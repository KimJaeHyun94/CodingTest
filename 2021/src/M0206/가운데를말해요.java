package M0206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
/*
 * @Author : AKKabiyo
 * @See : https://dragon-h.tistory.com/6 
 */
public class 가운데를말해요 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> max = new PriorityQueue<>();
		PriorityQueue<Integer> min = new PriorityQueue<>(Collections.reverseOrder());
		int N = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			int num = Integer.parseInt(br.readLine());

			if (max.size() == min.size()) {	    //만약에 사이즈가 똑같다면	
				min.add(num);    //작은거 쌓는데에다가 증가
			} else
				max.add(num);  //아니면 크기를 맞춘다. 

			if (!max.isEmpty() && !min.isEmpty()) {  
				if (max.peek() < min.peek()) {
					int temp = max.poll();  //swap
					max.add(min.poll());
					min.add(temp);
				}
			}
			sb.append(min.peek()).append("\n");
		}
		System.out.println(sb);
	}
}
