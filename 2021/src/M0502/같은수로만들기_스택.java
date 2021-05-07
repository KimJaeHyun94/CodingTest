package M0502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;

public class 같은수로만들기_스택 {
	static int N;
	static long A[], ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		A = new long[N];
		Stack<Long> s = new Stack<>();
		
		long ans = 0;
  		for (int i = 0; i < N; i++) {
			A[i] = Long.parseLong(br.readLine());
			if(s.isEmpty()) {
				s.push(A[i]);
			}else {
				while(s.peek()<A[i]) {
					long del = s.pop();
					
					if(s.isEmpty() || s.peek()>A[i]) {
						ans+=A[i]-del;
						break;
					}else {
						ans+=s.peek()-del;
					}
				}
				s.push(A[i]);
				
			}
			
		}
  		if(s.size()>1) {
  			ans+=s.firstElement()-s.peek();
  		}
		System.out.println(ans);
	}

	
}
