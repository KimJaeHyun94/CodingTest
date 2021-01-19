package M1227;

public class 숫자의표현 {
	static int MAX, ans;
	public static void main(String[] args) {
		System.out.println(solution(15));

	}
	 public static int solution(int n) {
		 	ans = 1;
	        MAX = n;
	        for (int i = 1; i < n; i++) {
				DFS(i, 0);
			}
	        return ans;
	    }
	private static void DFS(int r, int sum) {
		if(sum==MAX) {
			ans++;
			return;
		}
		if(sum>MAX)
			return;
		
		DFS(r+1, sum+r);
		
	}

}
