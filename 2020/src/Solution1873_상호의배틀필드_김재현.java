import java.util.Scanner;

public class Solution1873_상호의배틀필드_김재현 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int H = sc.nextInt();
			int W = sc.nextInt();
			char arr[][] = new char[H][W];
			for (int i = 0; i < H; i++) {
				String str = sc.next();
				for (int j = 0; j < str.length(); j++) {
					arr[i][j] = str.charAt(j);
				}
			}
			int N = sc.nextInt();
			String str2 = sc.next();
			int y = 0;
			int x = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (arr[i][j] == '^' || arr[i][j] == '<' || arr[i][j] == 'v' || arr[i][j] == '>') {
						y = i;
						x = j;
						break;
					}
				}
			}
			for (int j2 = 0; j2 < str2.length(); j2++) {
				if (str2.charAt(j2) == 'D') {
					arr[y][x] = 'v';	
					if (y + 1 >= H)
						continue;
					else if (arr[y + 1][x] == '.') {
						arr[y + 1][x] = 'v';
						arr[y][x] = '.';
						y += 1;
					}
				} else if (str2.charAt(j2) == 'U') {
					arr[y][x] = '^';
					if (y - 1 < 0)
						continue;
					else if (arr[y - 1][x] == '.') {
						arr[y - 1][x] = '^';
						arr[y][x] = '.';
						y -= 1;
					}
				} else if (str2.charAt(j2) == 'R') {
					arr[y][x] = '>';
					if (x + 1 >= W)
						continue;
					else if (arr[y][x + 1] == '.') {
						arr[y][x + 1] = '>';
						arr[y][x] = '.';
						x += 1;
					}
				} else if (str2.charAt(j2) == 'L') {
					arr[y][x] = '<';
					if (x - 1 < 0)
						continue;
					else if (arr[y][x - 1] == '.') {
						arr[y][x - 1] = '<';
						arr[y][x] = '.';
						x -= 1;
					}
				} else if (str2.charAt(j2) == 'S') {
					switch (arr[y][x]) {
					case '^':
						for (int k = y - 1; k >= 0; k--) {
							if (arr[k][x] == '*') {
								arr[k][x] = '.';
								
								break;
							} else if (arr[k][x] == '#')
								break;
						}
						break;
					case 'v':
						for (int k = y + 1; k < H; k++) {
							if (arr[k][x] == '*') {
								arr[k][x] = '.';
								break;
							} else if (arr[k][x] == '#')
								break;
						}
						break;
					case '>':
						for (int k = x + 1; k < W; k++) {
							if (arr[y][k] == '*') {
								arr[y][k] = '.';
								break;
							} else if (arr[y][k] == '#')
								break;
						}
						break;
					case '<':
						for (int k = x - 1; k >= 0; k--) {
							if (arr[y][k] == '*') {
								arr[y][k] = '.';
								break;
							} else if (arr[y][k] == '#')
								break;
						}
						break;
					}
				}
			}

			System.out.print("#" + tc+" ");
			for (int k = 0; k < H; k++) {
				for (int k2 = 0; k2 < W; k2++) {
					System.out.print(arr[k][k2]);
				}
				System.out.println();
			}
		}
	}
}
