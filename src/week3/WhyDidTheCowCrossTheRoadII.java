package week3;

import java.util.Scanner;
/*
Why Did the Cow Cross the Road II
=================================

The long road through Farmer John's farm has N crosswalks across it,
conveniently numbered 1...N (1 <= N <= 100,000). To allow cows to
cross at these crosswalks, FJ installs electric crossing signals,
which light up with a green cow icon when it is ok for the cow to
cross, and red otherwise. Unfortunately, a large electrical storm has
damaged some of his signals. Given a list of the damaged signals,
please compute the minimum number of signals that FJ needs to repair
in order for there to exist some contiguous block of at least K
working signals.

PROBLEM NAME: maxcross

INPUT FORMAT:

The first line of input contains N, K, and B (1 <= B, K <= N). The
next B lines each describe the ID number of a broken signal.

OUTPUT FORMAT:

Please compute the minimum number of signals that need to be repaired
in order for there to be a contiguous block of K working signals
somewhere along the road.

SAMPLE INPUT:

10 6 5
2
10
1
5
9

SAMPLE OUTPUT:

1
 */
public class WhyDidTheCowCrossTheRoadII
{
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int k = s.nextInt();
        int b = s.nextInt();
        boolean[] isBrokenLight = new boolean[n+1];
        for (int i = 0; i < b; i++)
        {
            isBrokenLight[s.nextInt()] = true;
        }
        int[] pSums = new int[n+1];
        for (int i = 1; i < pSums.length; i++)
        {
            if (isBrokenLight[i])
            {
                pSums[i] = pSums[i-1]+1;
            }
            else
            {
                pSums[i] = pSums[i-1];
            }
        }

        int ans = Integer.MAX_VALUE;
        for(int i = k; i < pSums.length; i++)
        {
            ans = Math.min(pSums[i] - pSums[i-k], ans);
        }

        System.out.println(ans);


    }
}
