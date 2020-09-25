// 2019. 01. 19
// ���� 4963 ���� ����

package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_M4963 {
	
	public static int w;
	public static int h;
	
	public static void main (String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			String[] s = br.readLine().split(" ");
			w = Integer.parseInt(s[0]);  // ��, x
			h = Integer.parseInt(s[1]);  // ��, y
		 	
			if (w==0 && h==0) break;
			
			int[][] island = new int[h][w];
			Queue<int[]> land = new LinkedList<int[]>();
			
			for (int i=0; i<h; i++) {
				String[] s2 = br.readLine().split(" ");
				for (int j=0; j<w; j++) {
					int n = Integer.parseInt(s2[j]);
					island[i][j] = n; // ��, ��
					if (n == 1) {
						// ������ �����ϱ�
						int[] a = {i,j};
						land.add(a);
					}
				}
			}
			System.out.println(search(land, island));
		}
		
	}
	
	private static int search (Queue<int[]> land, int[][] island) {
		int[][] dir = {{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}};
		int[][] pass = new int[h][w];
		
		Queue<int[]> q = new LinkedList<int[]>();
		int numSet = 0;
		int x, y, next_x, next_y;
		
		while (!land.isEmpty()) {
			int[] curLand = land.poll();
			//System.out.println("Ž�� ���� ���� "+curLand[0] +" "+ curLand[1]);
			
			if (pass[curLand[0]][curLand[1]] == 0) {
				x = curLand[0];
				y = curLand[1];
				numSet++;
				q.offer(curLand);
				
				while (!q.isEmpty()) {
					int[] b = q.poll();
					x = b[0];
					y = b[1];
					for (int i=0; i<8; i++) {
						next_x = x + dir[i][0];
						next_y = y + dir[i][1];
						if (inside(next_x, next_y)) {
							if (island[next_x][next_y] == 1 && pass[next_x][next_y] == 0){
								// �� ����, ����, �������� ���� �� Ȯ��
								//System.out.println(next_x+" "+next_y);
								int[] a = {next_x, next_y};
								q.offer(a);
								pass[next_x][next_y] = 1;
							}
						}
					}
					//System.out.println("���ŵ� ���� "+b[0]+ " " +b[1]);
				}
			}
			//System.out.println();
		}
		return numSet;
	}
	private static boolean inside(int x, int y) {
		if (x>=0 && y>=0 && x<h && y<w) return true;
		else return false;
	}
}
