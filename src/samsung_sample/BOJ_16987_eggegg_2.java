// ������� ��� ġ�� DP�� ����? DFS
// N < 8, 2�ʶ�� ���� ������ DFS ����
package samsung_sample;

import java.util.*;
import java.io.*;

public class BOJ_16987_eggegg_2 {
	public static int n, max;
	public static int [][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		map = new int[n+1][2];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken()); //������
			map[i][1] = Integer.parseInt(st.nextToken()); //����
		}
		
		int[] pass = new int[n+1];
		for (int i=0; i<n; i++) pass[i] = map[i][0];
		
		max = -1;
		find(pass, 0, 0);
		System.out.println(max);
	}
	private static void find (int[] pass, int cur, int cnt) {
		//System.out.println();
		//System.out.println("cur: "+cur);
		//System.out.println("cnt : "+cnt);
		
		if (cur >= n) {  // n-1�� ���� ����� ġ�� ������.
			if (max < cnt) max = cnt;
			System.out.println("cnt "+cnt+" max "+max);
			return;
		}
		
		if (pass[cur] <= 0) {
			find(pass, cur+1, cnt);
		}
		
		else {
			boolean end = true; // ������ �� �ϳ��� ���� ��
			for (int i=0; i<n; i++) {
				if (i != cur) {
					if (pass[i] > 0) {
						end = false;
						pass[i] -= map[cur][1];
						pass[cur] -= map[i][1];
						//printp(pass);
						
						int tmp = cnt;
						if (pass[cur] <= 0) tmp++;
						if (pass[i] <= 0) tmp++;
						find(pass, cur+1, tmp);
						
						// array �� ����!!
						pass[i] += map[cur][1];
						pass[cur] += map[i][1];
					}
				}
			}
			if (end) {
				if (max < cnt) max = cnt;
				return;
			}
		}
	}
//	private static void printp(int[] map) {
//		System.out.print("map : ");
//		for (int i=0; i<n; i++) {
//			System.out.print(map[i]+" ");
//		}
//		System.out.println();
//	}
}
