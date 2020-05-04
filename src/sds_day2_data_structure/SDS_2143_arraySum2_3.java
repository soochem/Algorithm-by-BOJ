// ���ڸ� 0 ����
// �ؽø�???

package sds_day2_data_structure;

import java.util.*;
import java.io.*;


public class SDS_2143_arraySum2_3 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		long t;
		int n, m;
		int[] a, b;
		
		t = Long.parseLong(br.readLine());  // target
		
		n = Integer.parseInt(br.readLine());
		a = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		m = Integer.parseInt(br.readLine());
		b = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<m; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}
		
		//////// �˰��� /////////
		// t���� ���� �ι迭��
		long sum = 0;
		long[] sa = new long[n*n];
		int idx = 0;
		for (int i=0; i<n; i++) {
			sum = 0;
			for (int j=i; j<n; j++) {
				sum+=a[j];
				sa[idx++] = sum;
			}
		}
		
		idx = 0;
		sum = 0;
		long[] sb = new long[m*m];
		for (int i=0; i<m; i++) {
			sum = 0;
			for (int j=i; j<m; j++) {
				sum+=b[j];
				sb[idx++] = sum;
			}
		}
		
		Arrays.sort(sa);
		Arrays.sort(sb);

		for (long z: sa) System.out.print(z+" ");
		
		// ����Ž��?
		long diff;
		long cnt, temp; cnt = temp = 0;  // ��
		int left, right, mid; right = m*m-1;
		long oper;
		
		for (int i=0; i<n*n; i++) {
			diff = t-sa[i];
			left = mid = 0;
			oper = 1; // ������� ���Ұ�
			temp = 0;
			
			while (i < n*n-1 && sa[i] == sa[i+1]) {
				i++;
				oper++;
			}
			
			while (left < right) {
				mid = (left + right)/2;
				if (sb[mid] >= diff) {
					// �̻��� ���� ���� �������� (lower bound)
					// �ʰ��ϴ� ���� ���� ��������  (upper bound)
					right = mid;
				}
				else {
					left = mid+1;
				}
			}
			
			// ����� right (�̻��� �� �߿� ���� ���� idx, ���� ���� �߿� ���� ū ��)
			
			while (right < m*m && sb[right] == diff) {
				right++;
				temp++;
			}
			
			cnt += temp*oper;
		}
		
		
		bw.write(String.valueOf(cnt));
		bw.write("\n");
		
		bw.flush(); bw.close();
	}
}