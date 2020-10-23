package week6quiz.solutions;

import java.util.*;
/*
Convention II
=============

Despite long delays in airport pickups, Farmer John's convention for
cows interested in eating grass has been going well so far. It has
attracted cows from all over the world. The main event of the
conference, however, is looking like it might cause Farmer John some
further scheduling woes. A very small pasture on his farm features a
rare form of grass that is supposed to be the tastiest in the world,
according to discerning cows. As a result, all of the N cows at the
conference (1<=N<=10^5) want to sample this grass. This will likely
cause long lines to form, since the pasture is so small it can only
accommodate one cow at a time.

Farmer John knows the time a_i that each cow i plans to arrive at the
special pasture, as well as the amount of time ti she plans to spend
sampling the special grass, once it becomes her turn. Once cow i
starts eating the grass, she spends her full time of ti before
leaving, during which other arriving cows need to wait. If multiple
cows are waiting when the pasture becomes available again, the cow
with the highest seniority is the next to be allowed to sample the
grass. For this purpose, a cow who arrives right as another cow is
finishing is considered "waiting". Similarly, if a number of cows all
arrive at exactly the same time while no cow is currently eating,
then the one with highest seniority is the next to eat.

Please help FJ compute the maximum amount of time any cow might
possibly have to wait in line (between time a_i and the time the cow
begins eating).

PROBLEM NAME: convention2

INPUT FORMAT :

The first line of input contains N. Each of the next N lines specify
the details of the N cows in order of seniority (the most senior cow
being first). Each line contains a_i and t_i for one cow. The t_i's
are positive integers each at most 10^4, and the a_i's are positive
integers at most 10^9.

OUTPUT FORMAT :

Please print the longest potential waiting time over all the cows.

SAMPLE INPUT:

5
25 3
105 30
20 50
10 17
100 10

SAMPLE OUTPUT:

10

In this example, we have 5 cows (numbered 1..5 according to their
order in the input). Cow 4 is the first to arrive (at time 10),
and before she can finish eating (at time 27) cows 1 and 3 both
arrive. Since cow 1 has higher seniority, she gets to eat next,
having waited 2 units of time beyond her arrival time. She finishes
at time 30, and then cow 3 starts eating, having waited for 10 units
of time beyond her starting time. After a gap where no cow eats,
cow 5 arrives and then while she is eating cow 2 arrives, eating 5
units of time later. The cow who is delayed the most relative to
her arrival time is cow 3.

Problem credits: Brian Dean
 */
public class ConventionII
{


    public static void main(String[] args) {
        Comparator<Cow> seniorityCompare =
                Comparator.comparingInt(o -> o.senior);

        Comparator<Cow> arrivalCompare = Comparator.comparingInt(o -> o.arrival);

        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        PriorityQueue<Cow> timeQueue = new PriorityQueue<>(arrivalCompare);
        for (int i = 0; i < n; i++)
        {
            int arrival = s.nextInt();
            int dur = s.nextInt();
            timeQueue.add(new Cow(i, arrival, dur));
        }
        PriorityQueue<Cow> waitingLine = new PriorityQueue<>(seniorityCompare);
        allCowsInTime(timeQueue, timeQueue.peek().arrival, waitingLine);
        int numOfCowsThatWent = 1;
        int currTime = waitingLine.peek().arrival;
        int maxWaitingTime = 0;

        while(numOfCowsThatWent < n)
        {
            if (timeQueue.size() == 0 && waitingLine.size() == 0)
            {
                break;
            }
            else if (waitingLine.size() == 0)
            {
                allCowsInTime(timeQueue, timeQueue.peek().arrival, waitingLine);
                currTime = waitingLine.peek().arrival;
            }
            else
            {
                numOfCowsThatWent++;
                Cow cowToGo = waitingLine.poll();
                maxWaitingTime = Math.max(maxWaitingTime, currTime - cowToGo.arrival);
                currTime += cowToGo.duration;
                allCowsInTime(timeQueue, currTime, waitingLine);
            }
        }

        System.out.println(maxWaitingTime);

    }

    public static PriorityQueue<Cow> allCowsInTime(PriorityQueue<Cow> timeQueue, int time, PriorityQueue<Cow> waitingLine)
    {
        while (timeQueue.size() > 0)
        {
            if (timeQueue.peek().arrival <= time)
            {
                waitingLine.add(timeQueue.poll());
            }
            else
            {
                break;
            }
        }
        return waitingLine;
    }

}
class Cow {
    Integer senior, arrival, duration;

    public Cow(int senior, int arrival, int duration) {
        this.senior = senior;
        this.arrival = arrival;
        this.duration = duration;
    }

    public Cow() {
        senior = Integer.MAX_VALUE;
        arrival = null;
        duration = null;
    }

    @Override
    public String toString() {
        return "Cow{" +
                "senior=" + senior +
                ", arrival=" + arrival +
                ", duration=" + duration +
                '}';
    }
}
