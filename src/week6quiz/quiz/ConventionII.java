package week6quiz.quiz;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ConventionII
{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        ArrayList<Cow> arr = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
        {
            arr.add(new Cow(i, s.nextInt(), s.nextInt()));
        }

        arr.sort(new Cow());

        ArrayList<Cow> queueOfCows = (ArrayList<Cow>)arr.clone();
        int curTime = queueOfCows.get(0).arrival;

    }

    public static ArrayList<Cow> getListOfCows(ArrayList<Cow> queue, int currTime)
    {
        ArrayList<Cow> ans = new ArrayList<Cow>();
        for (int i = 0; i < queue.size(); i++)
        {
            if (queue.get(i).arrival < currTime)
            {
                ans.add(queue.get(i));
            }
        }

        return ans;
    }

    public static Cow getNextCow(ArrayList<Cow> queue, int currTime)
    {
        Cow ans = new Cow();
        for (int i = 0; i < queue.size(); i++)
        {
            if (queue.get(i).arrival < currTime && ans.senior < queue.get(i).senior)
            {
                ans = queue.get(i);
            }
            else
            {
                break;
            }
        }

        if (ans.arrival == null)
        {
            return null;
        }
        return ans;
    }
}

class Cow implements Comparator<Cow>
{
    Integer senior, arrival, duration,
            waiting = 0, lastWait = arrival;

    public Cow(int senior, int arrival, int duration) {
        this.senior = senior;
        this.arrival = arrival;
        this.duration = duration;
    }

    public Cow()
    {
        senior = Integer.MAX_VALUE;
        arrival = null;
        duration = null;
    }

    @Override
    public int compare(Cow o1, Cow o2) {
        return (o1.arrival - o2.arrival) == 0 ? (o2.senior - o1.senior) : (o1.arrival - o2.arrival);
    }

    public void addWaitingTime(int currTime)
    {
        waiting += currTime - lastWait;
        lastWait = currTime;
    }
}
