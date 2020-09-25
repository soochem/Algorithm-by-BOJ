// �޸���
// 1. merge sort
// 2. seg tree
// ���� �ź��� 1�� ǥ���ؼ� �� ��ġ���� ���� �� ���ϱ� (--> sum)

package sds_summer.day2_data_structure;

import java.util.*;
import java.io.*;


public class SDS_2517_running {
	static int n, len;
	static Pt[] arr;
	static int[] tree;
	
	static class Pt {
		int idx, val;
		Pt (int i, int v){
			this.idx = i;  // ���
			this.val = v;  // �Ƿ�
		}
	}
	
	static double log2 (int a) {
		return Math.log10(a)/Math.log10(2);
	}
	
	// ���׸�Ʈ Ʈ�� �Լ�
	static void newTree () {
		// Ʈ�� �����
		// �ִ� ���̴� 1 << r+1, �������� 0���� ä��
		// r = (int) ceil (log2(n))
		len = (int) Math.ceil(log2(n));
		len = 1 << (len+1);
		tree = new int[len];
		return;
	}
	
//	private static int sum(int l, int r, int i, int L, int R) {
//		if (r < L || R < l)
//			return 0;
//		if (L <= l && r <= R)
//			return tree[i];
//
//		int mid = (l + r) / 2;
//		return sum(l, mid, i * 2, L, R) + sum(mid + 1, r, i * 2 + 1, L, R);
//	}

	static int query (int left, int right) {
		// idx ���� �տ� �ִ� ���� �� ����
		// ���� ���� ����
		// ���� ���� left~right ������ tree �� (right ���� ���� index�� ������� �� �� �ִ�)
		int sum = 0;
		left += (len/2-1);
		right += (len/2-1);
		while (left <= right) {
			if (left%2 != 0) {
				sum += tree[left++];
			}
			if (right %2 == 0) {
				sum += tree[right--];
			}
			left /= 2;
			right /= 2;
		}
		
		return sum;
	}
	
	static void update (int idx, int value) {
		// Ư�� ���� ��带 ������Ʈ
		// ���� ��忡 �θ�� ����� ���� �յ� �ٲ�� ��
		idx += (len/2-1);
		int diff = value - tree[idx];  // 1-0
		tree[idx] = value;  // ����
		while (idx >= 1) {
			idx /= 2;  // �̷��� �ؾ� �� ������Ʈ
			tree[idx] += diff;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// ���� �� <= 5*(1e5)
		n = Integer.parseInt(br.readLine()); 
		len = 0;
		arr = new Pt[n];
		int[] ans = new int[n];

		for (int i=0; i<n; i++) {
			// �ӵ� ����� ���� ��ǥ ���� (�迭�� 1~500000���� �� ���� �ƴ϶�� �� ���� ����)
			arr[i] = new Pt(i+1, Integer.parseInt(br.readLine()));
		}
				
		//////// ���׸�Ʈ Ʈ�� /////////
		// �Ƿ�(Pt.val)�� ū ������ ����
		Arrays.sort(arr, new Comparator<Pt>() {
			public int compare(Pt p1, Pt p2) {
				return (p1.val > p2.val)? 1: -1;  // �Ƿ��� ��� �ٸ��� (0 �ʿ� ����)
			}
		});
		
		newTree();
		
//		for (Pt p: arr) {
//			System.out.print((p.idx+(len/2-1))+" "+p.val);
//			System.out.println();
//		}

		for (int i=0; i<n; i++) {
			int idx = arr[i].idx;  // 1~n
			ans[idx-1] = idx - query(1, idx-1);  // ������ �տ� �ִ� �� ����
			update(idx, 1);
		}
		
		for (int a: ans) bw.write(String.valueOf(a)+"\n");
		
		bw.flush(); bw.close();
	}
}