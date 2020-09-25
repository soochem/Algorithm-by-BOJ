package programmers.binary_search;

// �Ա� �ɻ�

// n : ��ٸ��� ��� ��
// int[] times : �� �ɻ���� �ɻ��ϴ� �� �ɸ��� �ð�
// ��ǥ : ��� ����� �ɻ縦 �޴µ� �ּҷ� �ɸ��� �ð�

//2
//1000000000

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Lv3_immigration {

    public static long binary_search(long start, long end, int n, int[] times) {
        long ans = 0;
        long mid = (start + end) /2;

//        System.out.println(start + " " + end + " " +mid);
        if (start >= end) {
            return mid;
        }

        for (int t : times) {
            ans += mid/t;
        }

        if (ans >= n) {
            mid = binary_search(start, mid, n, times);
        } else {  // n �̸�
            mid = binary_search(mid+1, end, n, times);
        }

        return mid;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n, num_jud;
        int[] times;

        n = Integer.parseInt(br.readLine());
        String[] times_input = br.readLine().split(" ");

        times = new int[times_input.length];

        int i = 0;
        long min = 1000000001;

        for (String s : times_input){
            int tmp = Integer.parseInt(s);
//            System.out.println(i + " " + tmp);
            times[i++] = tmp;
            if (tmp < min)
                min = tmp;
        }

//        num_jud = times.length;
//        System.out.println(num_jud);

        long ans = binary_search(min, min*n, n, times);
        System.out.println(ans);

    }
}