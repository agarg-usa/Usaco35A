package week1;

import javafx.util.Pair;

import java.util.Scanner;
/*
Imagine Bessie's surprise as she spied a leprechaun prancing through
the north pasture. Being no one's fool, she charged at the leprechaun
at captured him with her prehensile hooves.

"One wish, bovine one. That's all I have for cows," he said.

"Riches," Bessie said dreamily. "The opportunity for riches."

Leprechauns never grant precisely the easiest form of their captor's
wishes. As the smoke cleared from the location of a loud explosion,
a shimmering donut spun slowly over the verdant green fields.

"I have made you a torus," the leprechaun cooed. "And on that torus
is an N x N matrix (1 <= N <= 200) of integers in the range
-1,000,000..1,000,000 that will determine the magnitude of your
riches. You must find the sequence of contiguous integers all in
one row, one column, or on one diagonal that yields the largest sum
from among all the possible sequences on the torus."

Bessie pondered for a moment and realized that the torus was a
device for "wrapping" the columns, rows, and diagonals of a matrix
so that one could choose contiguous elements that "wrapped around"
the side or top edge.

Bessie will share the matrix with you.  Determine the value of the
largest possible sum (which requires choosing at least one matrix
element).

By way of example, consider the 4 x 4 matrix on the left below that
has all the elements from one exemplary "wrapped" diagonal marked:

           8  6  6* 1           8  6* 6  1
          -3  4  0  5*         -3  4  0  5
           4* 2  1  9           4  2  1  9*
           1 -9* 9 -2           1 -9  9*-2

The marked diagonal of the right-hand matrix includes two nines
(the highest available number) and a six for a total of 24. This
is the best possible sum for this matrix and includes only three
of the four possible elements on its diagonal.

PROBLEM NAME: lepr

INPUT FORMAT:

* Line 1: A single integer: N

* Lines 2..N+1: Line i+1 contains N space-separated integers that
        compose row i of the matrix

SAMPLE INPUT:

4
8 6 6 1
-3 4 0 5
4 2 1 9
1 -9 9 -2

OUTPUT FORMAT:

* Line 1: A single integer that is the largest possible sum computable
        using the rules above

SAMPLE OUTPUT:

24
 */
public class Leprechaun {

    public static void main(String[] args) {
        System.out.println(-1 % 3);
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[][] matrix = new int[n][n]; //y,x
        int[] dx = {1, 0, 1, 1}; // right, down, up right, down right
        int[] dy = {0, 1, 1, -1};

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                matrix[i][j] = s.nextInt();
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++)
            {
                for (int direc = 0; direc < dx.length; direc++)
                {
                    int currX = x;
                    int currY = y;
                    int currDx = dx[direc];
                    int currDy = dy[direc];
                    int total = matrix[y][x];
                    ans = Math.max(total, ans);
                    while (true)
                    {
                        currX = (currX + currDx + n) % n;
                        currY = (currY + currDy + n) % n;
                        if (currX == x && currY == y)
                        {
                            break;
                        }
                        total += matrix[currY][currX];
                        ans = Math.max(total, ans);
                    }
                }
            }
        }
        System.out.println(ans);

    }
}
