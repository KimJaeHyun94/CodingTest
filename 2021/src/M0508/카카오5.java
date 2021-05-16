package M0508;

import java.util.HashMap;
import java.util.HashSet;

public class 카카오5 {
	static HashSet<Integer> set = new HashSet<>();
	static HashMap<Integer, Node> map = new HashMap<>();
	public int solution(int k, int[] num, int[][] links) {
        int answer = 0;

        for (int i = 0; i < num.length; i++) {
			Node temp = new Node(num[i]);
			if(!set.contains(num[i])) {
				set.add(num[i]);
				map.put(i, new Node(num[i]));
			}
			
        	int left = links[i][0];
			int right = links[i][1];
			
			if(left==-1) {
				temp.l=null;
			}else {
				if(!set.contains(num[i])) {
					set.add(left);
				}
			}
			if(right==-1) {
				temp.r=null;
			}else {
				if(set.contains(num[i])) {
					temp.l = new Node(right);
				}
			}
        	
		}
        
        return answer;
    }
	
	static class Node {
		int v;
		Node l, r;

		public Node(int v) {
			super();
			this.v = v;
		}
	}
}
