package week3;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

/*

Hoof, Paper, Scissors
=====================

You have probably heard of the game "Rock, Paper, Scissors". The cows
like to play a similar game they call "Hoof, Paper, Scissors".

The rules of "Hoof, Paper, Scissors" are simple. Two cows play
against each-other. They both count to three and then each
simultaneously makes a gesture that represents either a hoof, a piece
of paper, or a pair of scissors. Hoof beats scissors (since a hoof
can smash a pair of scissors), scissors beats paper (since scissors
can cut paper), and paper beats hoof (since the hoof can get a
papercut). For example, if the first cow makes a "hoof" gesture and
the second a "paper" gesture, then the second cow wins. Of course, it
is also possible to tie, if both cows make the same gesture.

Farmer John wants to play against his prize cow, Bessie, at N games
of "Hoof, Paper, Scissors" (1 <= N <= 100,000).  Bessie, being an
expert at the game, can predict each of FJ's gestures before he makes
it. Unfortunately, Bessie, being a cow, is also very lazy. As a result,
she tends to play the same gesture multiple times in a row. In fact,
she is only willing to switch gestures at most once over the entire
set of games. For example, she might play "hoof" for the first x
games, then switch to "paper" for the remaining N-x games.

Given the sequence of gestures FJ will be playing, please determine
the maximum number of games that Bessie can win.

PROBLEM NAME: hps

INPUT FORMAT:

The first line of the input file contains N.

The remaining N lines contains FJ's gestures, each either H, P, or S.

OUTPUT FORMAT:

Print the maximum number of games Bessie can win, given that she can
only change gestures at most once.

SAMPLE INPUT:

5
P
P
H
P
S

SAMPLE OUTPUT:

4

    Test case:
    10
    p
    p
    s
    p
    h
    h
    h
    p
    s
    s

*/

public class HoofPaperScissors
{
    public static final int P = 0, H = 1, S = 2;
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] input = new int[n];
        for (int i = 0;i < n; i++)
        {
            String str = s.next();
            if (str.equalsIgnoreCase("p"))
            {
                input[i] = P;
            }
            else if (str.equalsIgnoreCase("h"))
            {
                input[i] = H;
            }
            else
            {
                input[i] = S;
            }
        }

        int[][] LtoRPrefix = new int[3][n]; //hand, pos
        int[][] RtoLPrefix = new int[3][n];

        for (int hand = 0; hand < 3; hand++)
        {
            for (int i = 0; i < input.length; i++)
            {
                if (input[i] == hand)
                {
                    if (i == 0)
                    {
                      LtoRPrefix[hand][i] = 1;
                    }
                    else
                    {
                        LtoRPrefix[hand][i] = LtoRPrefix[hand][i-1] + 1;
                    }
                }
                else if (i != 0)
                {
                    LtoRPrefix[hand][i] =  LtoRPrefix[hand][i-1];
                }

            }

            for (int i = input.length-1; i >= 0; i--)
            {
                if (input[i] == hand)
                {
                    if (i == input.length-1)
                    {
                        RtoLPrefix[hand][i] = 1;
                    }
                    else
                    {
                        RtoLPrefix[hand][i] = RtoLPrefix[hand][i+1] + 1;
                    }
                }
                else if (i != input.length-1)
                {
                    RtoLPrefix[hand][i] =  RtoLPrefix[hand][i+1];
                }
            }
        }

        int max = 1;
        for (int hand = 0; hand < 3; hand++)
        {
            for (int i = 0; i < n-1; i++)
            {
                max  = Math.max(max, LtoRPrefix[hand][i] + RtoLPrefix[P][i+1]);
                max  = Math.max(max, LtoRPrefix[hand][i] + RtoLPrefix[H][i+1]);
                max  = Math.max(max, LtoRPrefix[hand][i] + RtoLPrefix[S][i+1]);
            }
        }

        System.out.println(max);
    }
}
