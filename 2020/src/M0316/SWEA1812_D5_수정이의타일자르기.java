package M0316;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA1812_D5_수정이의타일자르기 {
	static int N, M;
	static PriorityQueue<Integer> queue;
	static ArrayList<Tile> tiles;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			result = 0;
			queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				queue.add((int) Math.pow(2, Integer.parseInt(st.nextToken())));
			}
			tiles = new ArrayList<>();
			getTitles();
			System.out.println("#"+tc+" "+(result+1));
		}
	}

	private static void getTitles() {
		tiles.add(new Tile(M, M));
		while (!queue.isEmpty()) {
			Integer nowTile = queue.poll();
			boolean flag = true;
			int size = tiles.size();
			for (int i = 0; i < size; i++) {
				Tile tmp = tiles.get(i);
				int w = tmp.w;
				int h = tmp.h;
				if (w - nowTile >= 0 && h - nowTile >= 0) {
					flag = false;
					if (h - nowTile > 0 && nowTile > 0) {
						tiles.add(new Tile(nowTile, h - nowTile));
					}
					if (h > 0 && w - nowTile > 0) {
						tiles.add(new Tile(w - nowTile, h));
					}
					tiles.remove(i);
					break;
				}
			}
			if(flag) {
				result++;
				tiles.add(new Tile(nowTile,M-nowTile));
				tiles.add(new Tile(M-nowTile,M));
			}
		}
	}

	static class Tile {
		int w;
		int h;

		public Tile(int w, int h) {
			this.w = w;
			this.h = h;
		}
	}
}