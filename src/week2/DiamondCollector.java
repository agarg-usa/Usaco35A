package week2;

import java.util.Arrays;
import java.util.Scanner;
/*
Diamond Collector
=================

Bessie the cow, always a fan of shiny objects, has taken up a hobby
of mining diamonds in her spare time! She has collected N diamonds
(N <= 50,000) of varying sizes, and she wants to arrange some of
them in a pair of display cases in the barn.

Since Bessie wants the diamonds in each of the two cases to be
relatively similar in size, she decides that she will not include
two diamonds in the same case if their sizes differ by more than K
(two diamonds can be displayed together in the same case if their
sizes differ by exactly K). Given K, please help Bessie determine
the maximum number of diamonds she can display in both cases
together.

PROBLEM NAME: diamond

INPUT FORMAT:

The first line of the input file contains N and K (0 <= K <=
1,000,000,000). The next N lines each contain an integer giving the
size of one of the diamonds. All sizes will be positive and will
not exceed 1,000,000,000.

OUTPUT FORMAT:

Output a single positive integer, telling the maximum number of
diamonds that Bessie can showcase in total in both the cases.

SAMPLE INPUT:

7 3
10
5
1
12
9
5
14

SAMPLE OUTPUT:

5
 */
public class DiamondCollector {
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int K = s.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
        {
            arr[i] = s.nextInt();
        }
        Arrays.sort(arr);
        int[] LtoR = slidingDoorLtoR(arr, K);
        int[] RtoL = slidingDoorRtoL(arr, K);

        int ans = 0;
        for (int i = 1; i < N; i++)
        {
            ans = Math.max(ans, LtoR[i-1] + RtoL[i]);
        }
        System.out.println(ans);

    }
    public static int[]  slidingDoorRtoL(int[] arr, int k)
    {
        int lastIndex = arr.length-1;
        int lead = lastIndex; int follow = lastIndex;
        int maxDiamonds = 1;
        int[] maxDiamondsInACase = new int[arr.length];
        while (follow > 0)
        {
            if (lead <= 0)
            {
                follow--;
                continue;
            }
            else if (Math.abs(arr[lead] - arr[follow]) <= k)
            {
                maxDiamonds = Math.max(maxDiamonds, Math.abs(lead - follow) + 1);
                maxDiamondsInACase[lead] = maxDiamonds;
                lead--;
            }
            else
            {
                follow--;
            }
        }
        maxDiamondsInACase[0] = maxDiamonds;

        return maxDiamondsInACase;
    }

    public static int[]  slidingDoorLtoR(int[] arr, int k)
    {
        int lastIndex = arr.length-1;
        int lead = 0; int follow = 0;
        int maxDiamonds = 1;
        int[] maxDiamondsInACase = new int[arr.length];
        while (follow < lastIndex)
        {
            if (lead >= lastIndex)
            {
                follow++;
                continue;
            }
            else if (arr[lead] - arr[follow] <= k)
            {
                maxDiamonds = Math.max(maxDiamonds, lead - follow + 1);
                maxDiamondsInACase[lead] = maxDiamonds;
                lead++;
            }
            else
            {
                follow++;
            }
        }
        maxDiamondsInACase[arr.length-1] = maxDiamonds;

        return maxDiamondsInACase;
    }

}
