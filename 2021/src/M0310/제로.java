package M0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 제로 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Stack<Integer> stack = new Stack<>();
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num==0) {
				stack.pop();
			}else {
				stack.add(num);
			}
		}
		int sum=0;
		if(stack.isEmpty()) {
			System.out.println(0);
			
		}else {
			for (Integer integer : stack) {
				sum+=integer;
			}
			System.out.println(sum);
		}
		
	}
}
