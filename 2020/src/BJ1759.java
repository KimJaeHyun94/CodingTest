import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1759 {
	private static int N;
	private static int M;
	private static char map[];
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new char[M];
		st=new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			map[i]=st.nextToken().charAt(0);
		}
		Arrays.sort(map);
		nextCombination(0,0,new char[N]);
	}
	private static void nextCombination(int ti, int start, char[] temp) {
		int mo=0;
		int jo=0;
		if(ti==N) {
			for (int i = 0; i < temp.length; i++) {
				if(isMo(temp[i])) {
					mo++;
				}
				else
					jo++;
			}
			if(mo>=1&&jo>=2) {
				for (int i = 0; i < temp.length; i++) {
					System.out.print(temp[i]);
				}
				System.out.println();
			}
		}else {
			for (int i = start; i <map.length; i++) {
				temp[ti]=map[i];
				nextCombination(ti+1,i+1,temp);
			}
		}	
	}
	private static boolean isMo(char c) {
		if(c=='a'||c=='e'||c=='o'||c=='i'||c=='u') {
			return true;
		}
		return false;
	}
}
