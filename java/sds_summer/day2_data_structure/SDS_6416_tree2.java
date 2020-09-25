package sds_summer.day2_data_structure;

import java.util.*;
import java.io.*;

// �ݷ�
//0 0
//
//-1 -1

public class SDS_6416_tree2 {
	public static class Vert {
		int u, v;
		Vert(int a, int b) {
			this.u = a;
			this.v = b;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
		StringTokenizer st;
		
		int testcase = 0;
		ArrayList<Vert> edge = new ArrayList<Vert>();  // ��� ����
		int[][] adj; // ���� ���
		int[] indeg; // ���� ���� ��
		int u, v; u = v = 1;  // u -> v
		int max, tmax; max = tmax = 0;  // �迭 ũ�� ���Ϸ���
		int root = -1;  // ��Ʈ
		
		// Ʈ�� Ž����
		boolean flag;  // ����Ŭ üũ
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited;
		
		while (u >= 0 || v >= 0) { 
			// ���پ� �Է� -> ��ū�� Ư�� ���� �����Ҷ����� ����, �ƹ� ���� �־��� ���� 
			st = new StringTokenizer(br.readLine());
			
			while (st.hasMoreTokens()) {  // ���Ϳ��� ���� �� �� ��ū ���� ������ ����
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				
				if (u < 0) break;
				
				else if (u > 0 || v > 0) {
					//temp.u = u; temp.v = v;  // ��ü�� �����ͷ� �� �ٲ�
					// �����ϱ�
					edge.add(new Vert(u, v));
					tmax = (u > v)? u:v;
					if (max < tmax) max = tmax;
				}
				
				else {
					testcase++;
					if (edge.size() == 0) {  // �� ��嵵 �׷�����
						bw.write("Case "+String.valueOf(testcase)+" is a tree.\n");
						continue;
					}
					
					// �ʱ�ȭ
					flag = false;
					adj = new int [max+1][max+1];
					indeg = new int [max+1];
					Arrays.fill(indeg, -1);
					visited = new boolean [max+1];
					
					for (Vert t : edge) {
						if (indeg[t.u] == -1) indeg[t.u] = 0; // ��Ʈ ã������
						if (indeg[t.v] == -1) indeg[t.v] = 1; // �ʱ�ȭ �߸��Ǵ� �� ����
						else indeg[t.v]++;
						adj[t.u][t.v]++;
					}
					
					// ��Ʈ�� ���� & ���� 2�� �̻� �ȵ�
					root = rootChk(indeg);
					if (root == -1) flag = true;
					else {
						// ��Ʈ���� �ٸ� ���� ���� ������ ��� ����, ����Ŭ?
						q.add(root);
						while (!q.isEmpty()) {
							u = q.poll();
							for (int i=0; i<max+1; i++) {
								if (adj[u][i] == 1) {
									if (visited[i]) {
										flag = true; break;
									}
									else {
										q.add(i);
										visited[i] = true;
									}
								}
							}
						}
					}
					
					if (flag) bw.write("Case "+String.valueOf(testcase)+" is not a tree.\n");
					else bw.write("Case "+String.valueOf(testcase)+" is a tree.\n");
					edge.clear();
				}
			}
		}
		
		bw.flush(); bw.close();
	}
	
	private static int rootChk(int[] ind) {  // ��Ʈ ��ȯ�ϴ� �Լ�
		int root = -1; int temp = -1;
		
		for (int i=0; i<ind.length; i++) {
			temp = ind[i];
			if (temp == 0) {
				if (root != -1) {  // ��Ʈ�� �ΰ�
					return -1;
				}
				else root = i;
			}
			else if (temp > 1) {
				return -1;
			}
		}
		return root;
	}
}