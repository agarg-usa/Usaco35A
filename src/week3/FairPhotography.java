package week3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/*
same test case: 7
1 g
3 h
4 g
8 h
11 g
14 h
15 g

output:
13
 */
//TODO: there is a case where the whole photo is of the same breed cow
public class FairPhotography
{
    public static final int H = 1;
    public static final int G = -1;

    public static void main(String[] args)
    {
        //find prefix sums
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        Cow[] myCows = new Cow[n+1];
        for (int i = 1; i < myCows.length; i++)
        {
            myCows[i] = new Cow(s.nextInt(), ( s.next().equalsIgnoreCase("h") ));
        }

        Arrays.sort(myCows, 1, myCows.length);
        int[] pSums = new int[n+1];
        for (int i = 1; i < pSums.length; i++)
        {
            if (myCows[i].isH)
            {
                pSums[i] = pSums[i-1] + H;
            }
            else
            {
                pSums[i] = pSums[i-1] + G;
            }
        }

        //start looking for maximum range of identical prefix sums

        int ans = 0;
        HashMap<Integer, Integer> firstIndexOfPrefixSumNumber = new HashMap<>();
        for (int i = 0; i < pSums.length; i++)
        {
            int pSumNum = pSums[i];
            if (!firstIndexOfPrefixSumNumber.containsKey(pSumNum))
            {
                firstIndexOfPrefixSumNumber.put(pSumNum, i);
            }
            else
            {
                int firstIndex = firstIndexOfPrefixSumNumber.get(pSumNum);
                ans = Math.max(ans, myCows[i].getPos() - myCows[firstIndex+1].getPos());
            }
        }

        System.out.println(ans);



    }
}
class Cow implements Comparable
{
    Boolean isH;
    Integer pos;

    @Override
    public int compareTo(Object o) {
        if ( ((Cow)o).getPos() == null)
        {
            return 1;
        }
        else if (pos == null)
        {
            return -1;
        }
        return pos - ((Cow)o).pos;
    }

    public boolean isH() {
        return isH;
    }

    public Integer getPos() {
        return pos;
    }

    public Cow(int pos, boolean isH) {
        this.isH = isH;
        this.pos = pos;
    }

    public Cow(){}

}