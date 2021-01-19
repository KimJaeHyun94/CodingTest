import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class JO1863_종교 {
	private static religion[] rel;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(bf.readLine()," ");
		
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		rel=new religion[n];
		
		for (int i = 0; i < n; i++) {
			rel[i]=new religion(i,i,1);
		}
		
		for (int i = 0; i < m; i++) {
			StringTokenizer sr=new StringTokenizer(bf.readLine()," ");
			int a=Integer.parseInt(sr.nextToken())-1;
			int b=Integer.parseInt(sr.nextToken())-1;
			union(a,b);
		}
		int count=0;
		for (int i = 0; i < n; i++) {
			if(rel[i].index==rel[i].check)
				count++;
		}
		System.out.println(count);
	}
	public static class religion {
		int index;
		int check;
		int depth;
		public religion(int index, int check, int depth) {
			super();
			this.index = index;
			this.check = check;
			this.depth = depth;
		}
	}
	public static int find(int a) {
		if(rel[a].index==rel[a].check)
			return rel[a].index;
		rel[a].check=find(rel[a].check);
		return rel[a].check;
	}
	public static void union(int a, int b) {
		int pa=find(a);
		int pb=find(b);
		if(rel[pa].depth<rel[pb].depth)
			rel[pa].check=rel[pb].index;
		else
			rel[pb].check=rel[pa].index;
			if(rel[pa].depth==rel[pb].depth)
				rel[pa].depth++;
	}
}
