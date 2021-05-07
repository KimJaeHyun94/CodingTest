package M0503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 탑 {
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		Stack<Wall> stack = new Stack<>();
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int h = Integer.parseInt(st.nextToken());
			
			while(!stack.isEmpty()) {
				if(stack.peek().height<h) {
					stack.pop();
				}else
					break;
			}
			if(stack.isEmpty()) {
				sb.append("0 ");
			}else
				sb.append(stack.peek().idx+" ");
			stack.push(new Wall(h, i+1));
		}
		System.out.println(sb);
		
	}
	
	static class Wall{
        int height;
        int idx;
         
        public Wall(int height, int idx) {
            super();
            this.height = height;
            this.idx = idx;
        }
    }
}
