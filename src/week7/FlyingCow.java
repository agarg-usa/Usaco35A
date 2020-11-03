package week7;

import java.util.*;
/*
The Flying Cow
==============

Recently, Farmer John has been interested in buying new machines
to plow his farm. His newest purchase, the Flying Cow 2000, is
the latest model available on the market which comes with the
ability of flight. Farmer John has too much fun flying the machine
however and accidentally crashes the Flying Cow, which means that
now it can only fly to specific spots on his farm.

Farmer John's farm can be visualized as a number line with a plot
of grass at every number. FJ lives at the number 1 (and so does
his Flying Cow), but he would like to plow the plot located at the
number N (1 <= N <= 1,000,000). The Flying Cow can only fly from a
patch i to a patch 3*i. Farmer John can also push the Flying Cow
either one patch to the right or to the left, i.e. from patch i to
patch i+1 or to patch i-1. All three of these moves consumes one
unit of energy each.

Being the highly efficient farmer that he is, Farmer John wants to
know the minimum units of energy needed to get to his desired plot.
Can you help him?

PROGRAM NAME: flyingcow

INPUT FORMAT:

* Line 1: a single integer N, indicating the location of the plot
        to be plowed

OUTPUT FORMAT:

* Line 1: a single integer, M, the minimum units of energy needed
        to get to the plot

SAMPLE INPUT:

5

SAMPLE OUTPUT:

3

OUTPUT DETAILS:

Farmer John can fly with the Flying Cow once, consuming one units
of energy, moving from 1->3. Then he can push the Flying Cow two
units to the right, using a total of three units of energy.
 */
public class FlyingCow
{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        Queue<Location> locationQueue = new LinkedList<>();
        locationQueue.add(new Location(1, 0));
        //Set<Integer> seen = new HashSet<>();
        boolean[] seen = new boolean[n+6];
        seen[0] = true;
        while (!locationQueue.isEmpty())
        {
            Location myLocation = locationQueue.poll();
            if (myLocation.location == n)
            {
                System.out.println(myLocation.energy);
                return;
            }

            if (myLocation.location*3 < n + 4 && !seen[3*myLocation.location])
            {
                seen[myLocation.location*3] = true;
                locationQueue.add(new Location(myLocation.location*3, myLocation.energy+1));
            }
            if (!seen[myLocation.location + 1])
            {
                seen[myLocation.location + 1 ] = true;
                locationQueue.add(new Location(myLocation.location + 1, myLocation.energy+1));
            }
            if (!seen[myLocation.location - 1])
            {
                seen[myLocation.location - 1] = true;
                locationQueue.add(new Location(myLocation.location - 1, myLocation.energy+1));
            }

        }

        System.out.println(0);
    }
}
class Location
{
    int location, energy;

    public Location(int location, int energy) {
        this.location = location;
        this.energy = energy;
    }
}
