package sds_day7_8_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

//4
//5
//2 1 4
//2 3 3
//4 2 -1
//3 4 2
//1 3 -2

// ���� �׷����� INF�� 0 ��� �־������

// ��� ������ �� ���� �ִ� ����� ����
public class SDS_11404_floyd {
	public static int n, m;
	public static final int MAX = (int) (1e9 + 1);
	public static int[][] d;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());		
		
		d = new int[n+1][n+1];  // ��� i->j ��ο� ���� �ּҺ��
		for (int[] tmp : d) Arrays.fill(tmp, MAX);
		
		for (int i=1; i<=m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (c < d[a][b]) d[a][b] = c;  // ���� ������ ����ġ �ٸ� �� �ִ�
		}
		
		for (int i=1; i<=n; i++) d[i][i] = 0;
		for (int k=1; k<=n; k++) { // k�� ��������
			for (int i=1; i<=n; i++) {
				for (int j=1; j<=n; j++) {
					// i->j (i->k->j)
					// k�� ����ϴ� ��� or k�� ����ϱ� �ʴ� ���
					if (d[i][j] > d[i][k]+d[k][j]) {
//						System.out.println(i+" "+k+" "+j+" "+d[i][j]);
						d[i][j] = d[i][k]+d[k][j];  // ��������� �Ѵ�. (������ ���)
					}
				}
			}
//			for (int i=1; i<=n; i++) {
//				for (int j=1; j<=n; j++) bw.write(d[i][j]+" ");
//				bw.write("\n");
//			}
//			bw.write("\n");
		}
		
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				if (d[i][j] == MAX) bw.write(String.valueOf(0)+" ");
				else bw.write(d[i][j]+" ");
			}
			bw.write("\n");
		}
		
		// �������� ���� ���ø� ����?!??
		
		bw.flush();
		bw.close();
	}
}
