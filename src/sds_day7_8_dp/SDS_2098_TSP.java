package sds_day7_8_dp;

import java.util.*;
import java.io.*;

// int �� �ִ�� ���� ����
// ��Ʈ��ŷ�� �տ��ſ� �ڿ��� �ִ°�
// ��� �������� ���� �� 0

public class SDS_2098_TSP {
	public static int n;
//	public static int MAX = Integer.MAX_VALUE;
	public static final int MAX = (int) (1e8 + 1);
	public static int min;
	public static int[][] w;
	public static int[][] d;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		
		w = new int[n+1][n+1];
		d = new int[n+1][1<<n+1];
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j=0; j<n; j++)
				if (st.hasMoreTokens())
					w[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for (int[] tmp: d) Arrays.fill(tmp, MAX);

//		d[0][1] = 0;
		tsp(0, 1);  // 1�� �湮

//		for (int[] tmp: d) {
//			for (int a:tmp) System.out.print(a+" ");
//			System.out.println();
//		}
		
		// ���� d[0][1....1]		
//		bw.write(String.valueOf(d[0][(1<<n)-1]));  // Ʋ��
//		bw.write(String.valueOf(tsp(0, 1)));  // ��°��?
		if (d[0][1] == MAX) 
			bw.write(String.valueOf(0));
		else 
			bw.write(String.valueOf(d[0][1]));
		bw.write("\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int tsp (int i, int state) {
		// d[i][state] : i �� ���ÿ��� ���� �湮 ���� s�� �� ���� ���� ��ȸ�ϰ�  i�����÷� �ٽ� ���� �ּ� ���
		
		// �Ϸ� ���� -> �Ϸ�� �� ä��鼭 ���ƿ�
		// ��� �湮 & ������ ����(1)�� ���� �� ����
		if (state == (1<<n)-1) {  // 111...11 ����
			if (w[i][0] != 0) { // i -> 0���� ���� ��ΰ� �ִ�
				return w[i][0];
			}
			else return MAX;
			//��ΰ� �������� ���� (��� ������) int_max�� �ϸ� ���ϸ鼭 ���� �޶���
 		}

		//���� �湮 �Ϸ��� ���� ���ִ� (��Ʈ��ŷ�� ���, ���������� ä����)
		if (d[i][state] != MAX) {
			return d[i][state];
		}
		
		// �湮�� �� (j) ã��
		for (int j=0; j<n; j++) { 
			if ((w[i][j] != 0) && (1<<j & state) == 0) { // ���� �湮 ����
//				System.out.println("dfs" + " "+i+" -> "+j);
				int news = (1<<j) | state;
				
				// ���������� ������ ä�� !!
				//d[is][state] = tsp(j, news);
				d[i][state] = Math.min(d[i][state], tsp(j, news)+w[i][j]);
//				System.out.println(j+" "+news+ " "+d[j][news]);
			}
		}
		return d[i][state];
	}
}

