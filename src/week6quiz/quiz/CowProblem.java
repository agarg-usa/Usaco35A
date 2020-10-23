package week6quiz.quiz;

import java.util.ArrayList;
import java.util.Scanner;

public class CowProblem
{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.nextLine();
        String str = s.nextLine().toUpperCase();
        int[] sumOfOBackward = new int[str.length()+1];
        sumOfOBackward[str.length()] = 0;
        ArrayList<Integer> allPositionsOfC = new ArrayList<>();
        long ans = 0;

        if (str.charAt(str.length()-1) == 'C')
        {
            allPositionsOfC.add(str.length()-1);
        }

        for (int i = str.length()-1; i >= 0; i-- )
        {
            if(str.charAt(i) == 'O')
            {
                sumOfOBackward[i] = sumOfOBackward[i+1] + 1;
            }
            else
            {
                sumOfOBackward[i] = sumOfOBackward[i+1];
            }

            if (str.charAt(i) == 'C')
            {
                allPositionsOfC.add(i);
            }
        }
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == 'W')
            {
                for (int j = 0; j < allPositionsOfC.size(); j++)
                {
                    if (allPositionsOfC.get(j) < i)
                    {
                        ans += sumOfOBackward[allPositionsOfC.get(j)] - sumOfOBackward[i + 1];
                    }
                }
            }
        }
        System.out.println(ans);
        /*
        int[][] cumilSum = new int[2][str.length()]; // first index is 0 for C and 1 for O, second index is for pos in string
        long ans = 0;

        char myChar = str.charAt(0);
        switch (myChar)
        {
            case 'C':
            {
                cumilSum[0][0] = 1;
                cumilSum[1][0] = 0;
                break;
            }
            case 'O':
            {
                cumilSum[0][0] = 0;
                cumilSum[1][0] = 1;
                break;
            }
            default:
            {
                cumilSum[0][0] = 0;
                cumilSum[1][0] = 0;
                break;
            }
        }
        for (int i = 1; i < str.length(); i++)
        {
            myChar = str.charAt(i);
            cumilSum[0][i] = cumilSum[0][i-1];
            cumilSum[1][i] = cumilSum[1][i-1];
            switch (myChar)
            {
                case 'C':
                {
                    cumilSum[0][i]++;
                    break;
                }
                case 'O':
                {
                    cumilSum[1][i]++;
                    break;
                }
                case 'W':
                {
                    ans += cumilSum[0][i] * cumilSum[1][i];
                }
            }
        }

         */

    }
}
