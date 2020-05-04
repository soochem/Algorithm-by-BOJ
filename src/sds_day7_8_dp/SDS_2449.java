// ���� 

package sds_day7_8_dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SDS_2449 {
	static int n, k;
	static int[] arr;
	static int[][] d;
	static final int MAX = (int) 1e4;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		StringTokenizer st;
		String[] s;
		
        s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        k = Integer.parseInt(s[1]);

        arr = new int[n];
        d = new int[n][n];
		
        s = br.readLine().split(" ");
        for (int i=0; i<n; i++) arr[i] = Integer.parseInt(s[i]);
        
        for (int[] dd: d) Arrays.fill(dd, MAX);
        for (int i=0; i<n; i++) d[i][i] = 0;  // ���� ��ġ���� 0���� �ʱ�ȭ, �������
        
        int j = 0;
        // d[i][j]: (i, j)�� ������ ���� ������ ����� �� �ʿ��� �ּ� ���� Ƚ��
        // (i, j) -> (i, k), (k+1, j)�� �κй��������� �����Ѵ�
        for (int diff=0; diff<n; diff++) {
			for (int i=0; i+diff<n; i++) {
				j = i+diff;
				for (int k=i; k<j; k++) {
					int tmp = (arr[i] == arr[k+1])? 0:1;
					d[i][j] = Math.min(d[i][j], d[i][k]+d[k+1][j]+tmp);
				}
			}
		}
		bw.write(String.valueOf(d[0][n-1]));
		bw.write("\n");
		bw.flush();
		bw.close();
	}
}
