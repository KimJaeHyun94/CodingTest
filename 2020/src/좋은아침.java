import java.util.Arrays;

public class 좋은아침 {
	private static char[] chars= {'A','B','C','D'};
	private static boolean[] status=new boolean[chars.length];
	public static void main(String[] args) {
		subset(4,0);
		permutation(3,0,new char[3],new boolean[chars.length]);
		combination(3,0,0,new char[3]);
		
	}
	private static void subset(int n, int r) {
		if(r==n) {
			for (int i = 0; i < status.length; i++) {
				if(status[i])
					System.out.print(chars[i]);
			}
			System.out.println();
		}
		else {
				status[r]=true;
				subset(n,r+1);
				status[r]=false;
				subset(n,r+1);
				
		}
	}

	private static void combination(int n,int ti, int start, char[] temp) {
		if(ti==n) {
			System.out.println(Arrays.toString(temp));
		}
		else {
			for (int i = start; i < chars.length; i++) {
				temp[ti]=chars[i];
				combination(n,ti+1,i+1,temp);
			}
		}
	}

	private static void permutation(int n, int r, char[] temp, boolean[] visited) {
		if(r==n) {
			System.out.println(Arrays.toString(temp));
		}
		else {
			for (int i = 0; i < chars.length; i++) {
				if(!visited[i]) {
					visited[i]=true;
					temp[r]=chars[i];
					permutation(n,r+1,temp,visited);
					visited[i]=false;
				}
			}
		}
	}
}
