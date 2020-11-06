package week8;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
Space Exploration II
====================

This problem is the same as 'Space Exploration' problem but this time
you need to implement it with Breadth First Search.

Farmer John's cows have finally blasted off from earth and are now
floating around space in their Moocraft. The cows want to reach
their fiery kin on Jupiter's moon of Io, but to do this they must
first navigate through the dangerous asteroid belt.

Bessie is piloting the craft through this treacherous N x N (1 <=
N <= 1,000) sector of space. Asteroids in this sector comprise some
number of 1 x 1 squares of space-rock connected along their edges
(two squares sharing only a corner count as two distinct asteroids).
Please help Bessie maneuver through the field by counting the number
of distinct asteroids in the entire sector.

Consider the 10 x 10 space shown below on the left. The '*'s represent
asteroid chunks, and each '.' represents a .vast void of empty space. The
diagram on the right shows an arbitrary numbering applied to the asteroids.

               ...**.....           ...11.....
               .*........           .2........
               ......*...           ......3...
               ...*..*...           ...3..3...
               ..*****...           ..33333...
               ...*......           ...3......
               ....***...           ....444...
               .*..***...           .5..444...
               .....*...*          ......4...6
               ..*.......          ..7........

It's easy to see there are 7 asteroids in this sector.

In this problem, you won't submit the entire code. Instead, you can
assume that the grading system has the code skeleton below and you
just need to fill inside the solve() function:

C++:                           Java:
-------------------------      ------------------
#include <iostream>            import java.util.*;
#include <queue>
			       public class lake2
using namespace std;           {
                                   private static void solve()
void solve()                       {
{				       // JUST WRITE THIS CODE
    // JUST WRITE THIS CODE        }
}
                                   public static void main(String[] args)
int main()                         {
{                                      solve();
    solve();                       }
}			       }
-------------------------------------------------------------------------

Python3:
-----------------------------
def solve():
    // JUST WRITE THIS CODE

solve()
------------------------------

PROBLEM NAME: space2

INPUT FORMAT:

* Line 1: A single integer: N

* Lines 2..N+1: Line i+1 contains row i of the asteroid field: N
        characters

SAMPLE INPUT:

10
...**.....
.*........
......*...
...*..*...
..*****...
...*......
....***...
.*..***...
.....*...*
..*.......

OUTPUT FORMAT:

* Line 1: A single integer indicating the number of asteroids in the
        field.

SAMPLE OUTPUT:

7
 */
public class SpaceExploration
{
    private static void solve()
    {

        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.nextLine();
        boolean[][] asteroidMatrix = new boolean[n][n]; //(x,y) ; true = asteroid

        for (int i = 0; i < n; i++) //y
        {
            String strBelt = s.nextLine();
            for (int j = 0; j < n; j++) //x
            {
                if (strBelt.charAt(j) == '.')
                {
                    asteroidMatrix[j][i]  = false;
                }
                else
                {
                    asteroidMatrix[j][i]  = true;
                }
            }
        }

        int ans = 0;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0 , -1};

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (asteroidMatrix[i][j])
                {
                    Queue<java.awt.Point> bfs = new LinkedList<>();
                    bfs.add(new java.awt.Point(i,j));
                    ans++;

                    while (!bfs.isEmpty())
                    {
                        java.awt.Point myAsteroid = bfs.poll();
                        if (asteroidMatrix[myAsteroid.x][myAsteroid.y])
                        {
                            asteroidMatrix[myAsteroid.x][myAsteroid.y] = false;
                            for (int dir = 0; dir < dx.length; dir++)
                            {
                                java.awt.Point newAstr = new java.awt.Point(myAsteroid.x + dx[dir],
                                        myAsteroid.y + dy[dir]);
                                if (newAstr.y < n && newAstr.y >= 0 &&
                                        newAstr.x < n && newAstr.x >= 0)
                                {
                                    bfs.add(newAstr);
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(ans);




    }

    public static void main(String[] args) {
        solve();
    }
}
