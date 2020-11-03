package week7;

import java.util.*;

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
