package M0311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ3055탈출 {
	static int R, C;
	static char map[][];
	static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static Queue<Point> Points;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map=new char[R][C];
		Points = new LinkedList<>();
		for (int r = 0; r < R; r++) {
			String str=br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c);
			}
		}
		Point player=null;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(map[r][c]=='*') {
					Points.offer(new Point(r,c,0,true));
				}else if(map[r][c]=='S') {
					player=new Point(r,c,0,false);
				}
			}
		}
		Points.offer(player);
		
		int A=Integer.MAX_VALUE;
		outer : while(!Points.isEmpty()) {
			Point front=Points.poll();
			
			for (int d = 0; d < dir.length; d++) {
				int nr=front.r+dir[d][0];
				int nc=front.c+dir[d][1];
				
				if(isOK(nr,nc)) {
					if(front.water) {
						if(map[nr][nc]=='.' || map[nr][nc]=='S') {
							map[nr][nc]='*';
							
							Points.offer(new Point(nr,nc,front.d+1,true));
						}
					}else {
						if(map[nr][nc]=='D') {
							A=front.d+1;
							break outer;
						}
						else if(map[nr][nc]=='.') {
							map[nr][nc]='S';
							Points.offer(new Point(nr,nc,front.d+1,false));
						}
					}
				}
			}
		}
		if(A==Integer.MAX_VALUE) {
			System.out.println("KAKTUS");
		}
		else {
			System.out.println(A);
		}
	
	}
	private static boolean isOK(int nr, int nc) {
		return nr>=0&&nr<R&&nc>=0&&nc<C;
	}
	static class Point{
		int r;
		int c;
		int d;
		boolean water;
		public Point(int r, int c, int d, boolean water) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
			this.water = water;
		}
	}
}
