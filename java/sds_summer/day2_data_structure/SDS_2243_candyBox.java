package sds_summer.day2_data_structure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// �ö󰡰ų� �������ų� �ϳ��� ���ߴ� �� ���ƺ���

public class SDS_2243_candyBox {
	static int n, a, b, c;
	static int len;
	static final int MAX = (int) 1e6;
	static int[] tree;
	
	static double log2(int num) {
		return Math.log10(num)/Math.log10(2);
	}
	
	static void make() {
		len = 1 << ((int) Math.ceil(log2(MAX))+1);
		tree = new int[len];
		return;
	}
	
 	static int query(int target, int idx, int left, int right) {
 		// ��ǲ: ���� (target)
		// �ش� ������ idx(ĵ�� ��) ��ȯ
		
 		// ���� ��� 
 		if (left == right) {
			return left;
		}
		
		int mid = (left+right)/2;

		if (tree[idx*2] >= target) {
			return query(target, idx*2, left, mid);
		}
	
		else {  // �������� �Ⱥ�����?
			return query(target-tree[idx*2], idx*2+1, mid+1, right);
		}
 	}
	
 	
 	// 1+(len/2)-1 ���� �����ؼ� len/2 ���� �ε��� �ű� ���¿��� ���������� �ö���� ����� ����
 	// left+right/2 �ؼ� �������� ����̶� �ε����� ��ġ�� �ȵȴ�.
//	static void update(int idx, int val) {
//		// idx: ��, val: �޶����� ��
//		idx += len/2-1;
//		tree[idx] += val;
//		System.out.println("updating "+idx);
//		while (idx >= 1) {  // 1�����ؾ���
//			idx /= 2;
//			tree[idx] += val;
//			System.out.println("updating "+idx);
//		}
//		return;
//	}

	static void update(int idx, int val, int left, int right, int target) {
		// idx: ��, val: �޶����� ��

		if (left > target || right < target) return;
		
		tree[idx] += val;
		
		if (left != right) {  // 1�����ؾ���
			int mid = (left+right)/2;
	        update(idx*2, val, left, mid, target);
	        update(idx*2+1, val, mid+1, right, target);
		}
		return;
	}
 	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
		
		n = Integer.parseInt(br.readLine());
		make();
		
		for (int i=0; i<n; i++) {
			String[] s = br.readLine().split(" ");
			a = Integer.parseInt(s[0]);
			if (a == 1) {
				// ���� b�� �� ���� ���
				b = Integer.parseInt(s[1]);
				int idx = query(b, 1, 1, MAX);
				bw.write(String.valueOf(idx));
				bw.write("\n");
				update(1, -1, 1, MAX, idx);
			}
			else {
				// c�� ����� �ְ� ������ ����
				b = Integer.parseInt(s[1]);
				c = Integer.parseInt(s[2]);
				update(1, c, 1, MAX, b);
			}
		}
		
		bw.write("\n");
		bw.flush();
		bw.close();
	}
}
