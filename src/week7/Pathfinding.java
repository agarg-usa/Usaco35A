package week7;

import java.util.*;
/*
Pathfinding
===========

Bessie is stranded on a deserted arctic island and wants to determine
all the paths she might take to return to her pasture. She has
tested her boat and knows she can travel from one island to another
island in 1 unit of time if a route with proper currents connects
the pair.

She has experimented to create a map of the ocean with valid
single-hop routes between each pair of the N (1 <= N <= 100) islands,
conveniently numbered 1..N. The routes are one-way (unidirectional),
owing to the way the currents push her boat in the ocean. It's
possible that a pair of islands is connected by two routes that use
different currents and thus provide a bidirectional connection. The
map takes care to avoid specifying that a route exists between an
island and itself.

Given her starting location M (1 <= M <= N) and a representation
of the map, help Bessie determine which islands are one 'hop' away,
two 'hops' away, and so on. If Bessie can take multiple different
paths to an island, consider only the path with the shortest distance.

By way of example, below are N=4 islands with connectivity as shown
(for this example, M=1):

       start--> 1-------->2
                |         |
                |         |
                V         V
                4<--------3

Bessie can visit island 1 in time 0 (since M=1), islands 2 and 4
at time 1, and island 3 at time 2.

The input for this task is a matrix C where a nonzero entry C_rc
(0 <= C_rc <= 1) in row r and column c means "Currents enable Bessie
to travel directly from island r to island c in one time unit". Row
C_r has N elements, respectively C_r1..C_rN, each one of which is
0 or 1.

PROBLEM NAME: pathfind

INPUT FORMAT:

* Line 1: Two space-separated integers: N and M

* Lines 2..N+1: Line i+1 contains N space-separated integers: C_r

SAMPLE INPUT:

4 1
0 1 0 1
0 0 1 0
0 0 0 1
0 0 0 0

OUTPUT FORMAT:

* Lines 1..???: Line i+1 contains the list of islands (in ascending
        numerical order) that Bessie can visit at time i.  Do not
        include any lines of output after all reachable islands have
        been listed.

SAMPLE OUTPUT:

1
2 4
3
 */
public class Pathfinding
{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(); //num of islands
        int m = s.nextInt() - 1; // start loc



        TreeMap<Integer, List<Integer>> timeToIslands = new TreeMap<>();
        TreeSet<Integer> allTimes = new TreeSet<>();
        int maxTime = 0;
        int[][] adjMatrix = new int[n][n]; //second value gives you the island the first value connects to (firstValue -> second value)
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                adjMatrix[i][j] = s.nextInt();
            }
        }

        Queue<Island> islandQueue = new LinkedList<>();
        boolean[] seen = new boolean[n];

        islandQueue.add(new Island(m, 0));

        while (!islandQueue.isEmpty())
        {
            Island myIsland = islandQueue.poll();
            if (!seen[myIsland.island])
            {
                timeToIslands.computeIfAbsent(myIsland.time, k -> new ArrayList<>());
                timeToIslands.get(myIsland.time).add(myIsland.island);
                allTimes.add(myIsland.time);
                maxTime = Math.max(maxTime, myIsland.time);
                seen[myIsland.island] = true;

                for (int i = 0; i <adjMatrix[myIsland.island].length; i++)
                {
                    if (adjMatrix[myIsland.island][i] == 1)
                    {
                        islandQueue.add(new Island(i, myIsland.time+1));
                    }
                }
            }
        }

        int finalMaxTime = maxTime;
        allTimes.iterator().forEachRemaining
                (time ->
                {
                    List<Integer> allIslands = timeToIslands.get(time);
                    allIslands.sort(Integer::compareTo);
                    for (int i = 0; i < allIslands.size(); i++)
                    {
                        System.out.print( allIslands.get(i) + 1);
                        if (i != allIslands.size()-1)
                        {
                            System.out.print(" ");
                        }
                    }

                    if (time != finalMaxTime)
                    {
                        System.out.println();
                    }

                });


    }
}

class Island
{
    int island, time;

    public Island(int island, int time) {
        this.island = island;
        this.time = time;
    }
}
