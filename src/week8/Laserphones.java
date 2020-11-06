package week8;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
Laserphones
===========

The cows have a new laser-based system so they can have casual
conversations while out in the pasture which is modeled as a W x H
grid of points (1 <= W <= 100; 1 <= H <= 100).

The system requires a sort of line-of-sight connectivity in order
to sustain communication. The pasture, of course, has rocks and
trees that disrupt the communication but the cows have purchased
diagonal mirrors that deflect the laser beam through a 90 degree
turn. Below is a map that illustrates the problem.

H is 8 and W is 7 for this map.  The two communicating cows are
notated as 'C's; rocks and other blocking elements are notated as
'*'s:

7 . . . . . . .         7 . . . . . . .
6 . . . . . . C         6 . . . . . /-C
5 . . . . . . *         5 . . . . . | *
4 * * * * * . *         4 * * * * * | *
3 . . . . * . .         3 . . . . * | .
2 . . . . . . .         2 . /-------/ .
1 . C . . . * .         1 . C . . . * .
0 . . . . . . .         0 . . . . . . .
  0 1 2 3 4 5 6           0 1 2 3 4 5 6

Determine the minimum number of mirrors M that must be installed to
maintain laser communication between the two cows.

PROBLEM NAME: lphone

INPUT FORMAT:

* Line 1: Two space separated integers: W and H

* Lines 2..H+1: The entire pasture.

SAMPLE INPUT:

7 8
.......
......C
......*
*****.*
....*..
.......
.C...*.
.......

OUTPUT FORMAT:

* Line 1: A single integer: M

SAMPLE OUTPUT:

3
 */

public class Laserphones
{

    static final char EMPTY = '.';
    static final char WALL = '*';
    static final char COW = 'C';

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int w = s.nextInt();
        int h = s.nextInt();
        s.nextLine();

        Point startCow = null;
        Point endCow = null;
        char[][] matrix = new char[w][h];
        boolean[][] seen = new boolean[w][h];
        for (int i = 0; i < h; i++)
        {
            String input = s.nextLine();
            for (int j = 0; j < w; j++)
            {
                matrix[j][i] = input.charAt(j);
                if (input.charAt(j) == COW)
                {
                    if (startCow == null)
                    {
                        startCow = new Point(j, i);
                    }
                    else
                    {
                        endCow = new Point(j, i);
                    }
                }
            }
        }

        Queue<Laser> bfs = new LinkedList<>();
        bfs.add(new Laser(startCow.x, startCow.y, -1, -1));
        seen[startCow.x][startCow.y] = true;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0 , -1};

        while (!bfs.isEmpty())
        {
            Laser currLaser = bfs.poll();
            if (currLaser.x == endCow.x && currLaser.y == endCow.y)
            {
                System.out.println(currLaser.numOfTurns);
                return;
            }

            for (int dir = 0; dir < dx.length; dir++)
            {
                int multiplier = 1;
                while (true)
                {
                    Laser newLaser = new Laser(currLaser.x + multiplier*dx[dir],
                            currLaser.y + multiplier*dy[dir],
                            (currLaser.dir == dir ? currLaser.numOfTurns : currLaser.numOfTurns + 1), dir);

                    if (newLaser.x < w && newLaser.x >= 0
                            && newLaser.y < h && newLaser.y >= 0
                            && matrix[newLaser.x][newLaser.y] != WALL)
                    {
                        if (!seen[newLaser.x][newLaser.y])
                        {
                            seen[newLaser.x][newLaser.y] = true;
                            bfs.add(newLaser);
                        }
                    }
                    else
                    {
                        break;
                    }
                    multiplier++;
                }
            }



        }



    }
}

class Laser
{
    int x,  y, numOfTurns, dir;

    public Laser(int x, int y, int numOfTurns, int dir) {
        this.x = x;
        this.y = y;
        this.numOfTurns = numOfTurns;
        this.dir = dir;
    }
}
