package sds_day9_10_geometry;

// ������
// �ð��ʰ� (�ٺ��� �ð��ʰ�)
// �ð����� �ݽð���� Ǯ�̰� �� �ٸ�?
// �� �ݽð������ >= �� �ؾ��ϰ�, �߰� ���� �񱳰� �ʿ��ѵ� 
// �ݴ� ������ �ȱ׷��ɱ�???

import java.util.*;
import java.io.*;

public class SDS_10254 {
	static int n, test, x1, x2, y1, y2;
	static long max;
	static int[] p0, stack;
	static int[][] point;
	
//	static long ccw (int[] i, int[] j, int[] k) {
//		return (((long)i[0]*j[1]+(long)j[0]*k[1]+(long)k[0]*i[1])
//				- ((long)j[0]*i[1]+(long)k[0]*j[1]+(long)i[0]*k[1]));
//	}
	
	static int ccw2 (int i1, int i2, int j1, int j2, int k1, int k2) {
		long res = ((long)i1*j2+(long)j1*k2+(long)k1*i2)
				- ((long)j1*i2+(long)k1*j2+(long)i1*k2);
		return (res > 0)? 1: (res == 0)? 0 : -1;
	}
	
	static long dist(int[] i, int[] j) {
		int dx = i[0]-j[0]; int dy = i[1]-j[1];
		return (long)dx*dx + (long)dy*dy;
	}
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		test = Integer.parseInt(br.readLine());

		p0 = new int[2];
		stack = new int[200001];		
        
        while (-- test >= 0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			
			point = new int[n][2];
			Arrays.fill(p0, (int) 1e9+1);
			Arrays.fill(stack, 0);
			
			for (int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				point[i][0] = Integer.parseInt(st.nextToken());
				point[i][1] = Integer.parseInt(st.nextToken());
				if (p0[1] > point[i][1] || 
						p0[1] == point[i][1] && p0[0] > point[i][0]) {
					p0[0] = point[i][0];  p0[1] = point[i][1];
				}
			}

			// �׶� ��ĵ
			x1=x2=y1=y2=0;  // ���� ���ϸ� ��Ÿ�� ���� ***
			max = 0;
			int idx = convexHull();
			rotCal(idx);
			
			bw.write(String.valueOf(x1+" "));
			bw.write(String.valueOf(y1+" "));
			bw.write(String.valueOf(x2+" "));
			bw.write(String.valueOf(y2+" "));
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int convexHull () {
		int idx = -1;  // idx, idx-1�� ��
		
		// ���� ����
		Arrays.sort(point, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				long d1 = dist(p0, a);
				long d2 = dist(p0, b);
				int c = ccw2(p0[0], p0[1], a[0], a[1], b[0], b[1]);
				return c < 0? 1: c == 0? 
						(d1 > d2)? 1 : ((d1 < d2)? -1 : 0) : -1; 
			}
		});

		// ���� �� Ž��
		for (int i=0; i<n; i++) {  // ��� �� Ž��
			while (idx > 0 && 
				ccw2(point[stack[idx-1]][0], point[stack[idx-1]][1], 
						point[stack[idx]][0], point[stack[idx]][1],
						point[i][0], point[i][1]) <= 0) {  // �ݽð���� ����x
				// �Ѱ��� ���� ���ٰ� �� ���� or �����ϴ� �� ������ ���� ����
				stack[idx--] = 0;
			}
			// �ٸ� �� ������ (i+1)
			stack[++idx] = i;
		}
		stack[++idx] = stack[0];
		
		return idx;
	}
	
	static void rotCal (int idx) {
		// ���� �� �Ÿ�
		int j = 0; // ������ Ž���� ���, ���� �ʿ� ����
		long dis = 0;  // �Ÿ� -> �ƽ��� ���� ��
		int c = 0;
		
		for (int i=0; i<=idx; i++) {
			while (j+1 <= idx) {
				// 0�� �������� �����̵�
				c = ccw2 ( 0, 0, 
						point[stack[i+1]][0]-point[stack[i]][0],
						point[stack[i+1]][1]-point[stack[i]][1],
						point[stack[j+1]][0]-point[stack[j]][0], 
						point[stack[j+1]][1]-point[stack[j]][1] );
//				System.out.println("st "+stack[i]+" "+stack[j]+" "+stack[j+1]);
				dis = dist(point[stack[i]], point[stack[j]]);
				if (dis > max) {
					max = dis;
					x1=point[stack[i]][0];
					y1=point[stack[i]][1];
					x2=point[stack[j]][0];
					y2=point[stack[j]][1];
				}
				if (c <= 0) break; // �ð� ���� �پ�� *** �ð��ʰ� ***
				j++;
			}
					
			// ���� �հŸ��� �� ���� ��������
			dis = dist(point[stack[i]], point[stack[j]]);
			if (dis > max) {
				max = dis;
				x1=point[stack[i]][0];
				y1=point[stack[i]][1];
				x2=point[stack[j]][0];
				y2=point[stack[j]][1];
			}	
		}
	}
}
