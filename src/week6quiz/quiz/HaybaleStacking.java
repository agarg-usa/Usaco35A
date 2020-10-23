package week6quiz.quiz;

import java.util.Arrays;
import java.util.Scanner;

public class HaybaleStacking
{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int k = s.nextInt();
        int[] haybales = new int[n];
        for (int i = 0; i < k; i++)
        {
            int a = s.nextInt()-1;
            int b = s.nextInt()-1;
            for (int j = a; j <= b; j++)
            {
                haybales[j]++;
            }
        }

        Arrays.sort(haybales);
        System.out.println(haybales[ (haybales.length - 1) / 2 ]);

    }
}
