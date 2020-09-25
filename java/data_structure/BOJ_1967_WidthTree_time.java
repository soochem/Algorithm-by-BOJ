package data_structure;

// Ʈ���� ���� ȿ������ �����غ���

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_1967_WidthTree_time {

    public static int n, MAX;
    public static ArrayList<ArrayList<int[]>> children;

    public static int travel(int current) {
        int max1 = 0;  // ���� ū ����ġ ���� ���� subtree
        int max2 = 0;  // �ι�°�� ū ����ġ ���� ���� subtree

        ArrayList<int[]> tmp = children.get(current);
        for (int[] item : tmp) {
            int c = item[0];
            int w = item[1];

            int max_child = travel(c) + w;

            if (max_child > max1) {
                max2 = max1;
                max1 = max_child;
            } else if (max_child > max2) {
                max2 = max_child;
            }
        }

        int sub_sum = max1 + max2;
        MAX = (MAX < sub_sum) ? sub_sum : MAX;
//        if (MAX < sub_sum) {
//            MAX = sub_sum;
//        }

        return max1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // �ʱ�ȭ
        n = Integer.parseInt(br.readLine());
        children = new ArrayList<>();
        int i;
        for (i=0; i<n+1; i++) {
            children.add(new ArrayList<>());
        }

        for (i=0; i<n-1; i++) {
            String[] s = br.readLine().split(" ");
            int p = Integer.parseInt(s[0]);
            int c = Integer.parseInt(s[1]);
            int w = Integer.parseInt(s[2]);

            children.get(p).add(new int[]{c,w});  // �ܹ���
        }

        // Ž��
        MAX = 0;
        travel(1);
        System.out.println(MAX);
    }

}
