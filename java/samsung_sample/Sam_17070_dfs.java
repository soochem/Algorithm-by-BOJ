package samsung_sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 17070 ������ �ű��1
// ��Ʈ��ŷ
// ���Ϳ��� �ð��ʰ� ��

public class Sam_17070_dfs {
	static int n, cnt;  // cnt: �� �ڸ��� �������� �� �� �ִ� ����� ��
	static int[][] map;  
	static int[][] dir = {{0,1}, {1,0}, {1,1}};  // ���������� ����, ����, ����, �밢��
	
	static boolean inside(int x, int y) {
		if (x >= 0 && y >= 0 && x < n && y < n) return true;
		return false;
	}
	
	static void dfs (int cx, int cy, int d) {
		int nx, ny;
			
		if (cx == n-1 && cy == n-1) {
			// ���� ������ �־��, ���ʿ��� ���x
//			return 1;  // (��� 2)
			cnt++;
			return;
		}
		
		// int c = 0;
		for (int i=0; i<3; i++) {
			if (i == 0 && d == 1 || i == 1 && d == 0) continue;
			
			nx = cx + dir[i][0];
			ny = cy + dir[i][1];
			
			if (!inside(nx, ny) || map[nx][ny] == 1) continue;
			if (i == 2 && (map[cx][cy+1] == 1 || map[cx+1][cy] == 1)) break; 
			
			dfs(nx, ny, i);
//			c += dfs(nx, ny, i); // ��� 2
		}
		return;
		// return c; // ��� 2 
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		cnt = 0;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		String[] s;
		
		for (int i=0; i<n; i++) {
			s = br.readLine().split(" ");
			for (int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		dfs(0, 1, 0);
		
		bw.write(String.valueOf(cnt));
		bw.write("\n");
		bw.flush();
		bw.close();
	}
}
