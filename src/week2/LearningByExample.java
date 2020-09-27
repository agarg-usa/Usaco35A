package week2;

import java.util.*;
/*
Learning by Example
===================

Farmer John has been reading all about the exciting field of machine
learning, where one can learn interesting and sometimes unexpected
patterns by analyzing large data (he has even started calling one of
the fields on his farm the "field of machine learning"!).  FJ decides
to use data about his existing cow herd to build an automatic
classifier that can guess whether a cow will have spots or not.

Unfortunately, FJ hasn't been very good at keeping track of data about
his cows.  For each of his N cows (1 <= N <= 50,000), all he knows is
the weight of the cow, and whether the cow has spots.  Each of his
cows has a distinct weight.  Given this data, he builds what is called
a "nearest neighbor classifier".  To guess whether a new cow C will
have spots or not, FJ first finds the cow C' in his herd with weight
closest to that of C.  If C' has spots, then FJ guesses that C will
also have spots; if C' has no spots, FJ guesses the same for C.  If
there is not one unique nearest neighbor C' but rather a tie between
two of FJ's cows, then FJ guesses that C will have spots if one or
both these nearest neighbors has spots.

FJ wants to test his new automatic spot predictor on a group of new
cows that are just arriving at his farm.  After weighing these cows,
he sees that the new shipment of cows contains a cow of every integer
weight between A and B (inclusive).  Please determine how many of
these cows will be classified as having spots, using FJ's new
classifier.  Note that the classifier only makes decisions using data
from FJ's N existing cows, not any of the new cows.  Also note that
since A and B can both be quite large, your program will not likely
run fast enough if it loops from A to B counting by ones.

PROBLEM NAME: learning

INPUT:

The first line of the input contains three integers N, A, and B
(1 <= A <= B <= 1,000,000,000).

The next N lines each describe a single cow.  Each line contains
either S W, indicating a spotted cow of weight W, or NS W, indicating
a non-spotted cow of weight W.  Weights are all integers in the range
1 ... 1,000,000,000.

SAMPLE INPUT:

3 1 10
S 10
NS 4
S 1

OUTPUT:

A single integer giving the number of incoming cows that FJ's
algorithm will classify as having spots.  In the example shown
here, the incoming cows of weights 1, 2, 7, 8, 9, and 10
will all be classified as having spots.

SAMPLE OUTPUT:

6
 */
public class LearningByExample {
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int a = s.nextInt();
        int b = s.nextInt();

        Cow[] givenCows = new Cow[n];
        for (int i = 0; i < n; i ++)
        {
            String isSpot = s.next();
            int weight = s.nextInt();
            givenCows[i] = new Cow(isSpot.equalsIgnoreCase("s"), weight);
        }

        Arrays.sort(givenCows);
        ArrayList<Interval> intervals = new ArrayList<>(2*n);
        intervals.add(new Interval(Integer.MIN_VALUE, givenCows[0].getWeight(), givenCows[0].isSpotted()));
        intervals.add(new Interval(givenCows[n-1].getWeight(), Integer.MAX_VALUE, givenCows[n-1].isSpotted()));

        for (int i = 1; i < givenCows.length; i++)
        {
            Pair<Interval> intervalPair = intervalFinder(givenCows[i-1], givenCows[i]);
            intervals.add(intervalPair.getVar1());
            if (intervalPair.getVar2() != null)
            {
                intervals.add(intervalPair.getVar2());
            }
        }

        intervals.sort(new Interval());

        /*
        System.out.println(Arrays.toString(givenCows));
        System.out.println(intervals);
        System.out.println(intervalCombiner(intervals));
        */


        intervals = intervalCombiner(intervals);


        /*
            ArrayList<Interval> intervalTester = new ArrayList<>();
            intervalTester.add(new Interval(-1, 1, true));
            intervalTester.add(new Interval(1, 2, true));
            intervalTester.add(new Interval(3, 4, false));
            intervalTester.add(new Interval(4, 5, false));
            intervalTester.add(new Interval(5, 6, false));
            intervalTester.add(new Interval(7, 10, true));
            intervalTester.add(new Interval(10, 11, true));
            intervalTester.add(new Interval(12, 13, false));
            intervalTester.add(new Interval(14, 20, true));
            intervalTester.add(new Interval(20, 40, true));
            intervalTester.add(new Interval(40, 54, true));

            System.out.println(intervalTester);
            System.out.println(intervalCombiner(intervalTester));

            intervalTester = intervalCombiner(intervalTester);

            System.out.println(Collections.binarySearch(intervalTester, new Interval(8, 8, false)));
            System.out.println(Collections.binarySearch(intervalTester, new Interval(15, 15, false)));
            System.out.println(Collections.binarySearch(intervalTester, new Interval(7, 7, false)));
            System.out.println(Collections.binarySearch(intervalTester, new Interval(100, 100, false)));

         */


        int indexOfA = binarySearchIndexToUsableIndex(Collections.binarySearch(intervals, new Interval(a,a,false)));
        int indexOfB = binarySearchIndexToUsableIndex(Collections.binarySearch(intervals, new Interval(b,b,false)));

        int ans = 0;
        for (int i = indexOfA; i <= indexOfB; i++)
        {
            Interval currInterval = intervals.get(i);
            if (currInterval.isSpotted())
            {
                Interval myInterval = new Interval(Math.max(a, currInterval.getA()), Math.min(b, currInterval.getB()), true);
                ans += myInterval.getSize();
            }
        }

        System.out.println(ans);
    }

    public static int binarySearchIndexToUsableIndex(int index)
    {
        if (index < 0)
        {
            index = -1*index -2;
        }
        return index;
    }

    public static ArrayList<Interval> intervalCombiner(ArrayList<Interval> arr)
    {
        ArrayList<Interval> combinedIntervals = new ArrayList<>();
        for (int i = 1; i < arr.size(); i++)
        {
            Integer a = null, b = null;
            Boolean isSpot = null;
            boolean didAddI = false;
            while (i < arr.size() && arr.get(i - 1).isSpotted() == arr.get(i).isSpotted())
            {
                Interval intervalOne = arr.get(i-1);
                Interval intervalTwo = arr.get(i); //guaranteed that intervalOne and IntervalTwo would have same spottieness
                if (a == null)
                {
                    a = intervalOne.getA();
                    b = intervalTwo.getB();
                    isSpot = intervalOne.isSpotted();
                }
                else
                {
                    b = intervalTwo.getB();
                }
                didAddI = true;
                i++; //didAddI var to make sure it doesnt jump to a totally new spot once it breaks out of loop
            }
            if (i != arr.size() && didAddI) //if i is equivalent to arr.size, we dont want it to continue its loop
            {
                i--;
            }
            if (a == null) //current I interval does not match with last interval
            {
                //if last interval, add
                //if not last interval and doesnt match with next, add
                //if not last interval and does match with next, dont add
                if( i == arr.size()-1 ||  (i < arr.size() - 1 && arr.get(i).isSpotted() != arr.get(i+1).isSpotted()) )
                {
                    combinedIntervals.add(arr.get(i));
                }
            }
            else //combined interval found
            {
                combinedIntervals.add(new Interval(a, b, isSpot));
            }
        }

        return combinedIntervals;
    }


    public static Pair<Interval> intervalFinder(Cow firstCow, Cow secondCow) //WATCH OUT, B CAN BE NULL
    {
        Interval a, b = null;
        if (firstCow.isSpotted() == secondCow.isSpotted()) //they both have the same spotted value
        {
            a = new Interval(firstCow.getWeight(), secondCow.getWeight(), firstCow.isSpotted());
        }
        else if ((firstCow.getWeight() + secondCow.getWeight()) % 2 == 1) //no midpoint
        {
            double midPoint = (double) (firstCow.getWeight() + secondCow.getWeight()) / 2; //this would be having a 0.5
            a = new Interval(firstCow.getWeight(), (int)(midPoint - 0.5), firstCow.isSpotted());
            b = new Interval((int)(midPoint + 0.5), secondCow.getWeight(), secondCow.isSpotted());
        }
        else //midpoint urgency, figure which one is what
        {
            double midPoint = (double) (firstCow.getWeight() + secondCow.getWeight()) / 2;
            if (firstCow.isSpotted()) //first cow interval claims the cow
            {
                a = new Interval(firstCow.getWeight(), (int)midPoint, firstCow.isSpotted());
                b = new Interval((int)midPoint + 1, secondCow.getWeight(), secondCow.isSpotted());
            }
            else //second cow claims the cow
            {
                a = new Interval(firstCow.getWeight(), (int) midPoint -1, firstCow.isSpotted());
                b = new Interval((int)midPoint , secondCow.getWeight(), secondCow.isSpotted());
            }
        }
        return new Pair<>(a, b);
    }

}

class Cow implements Comparator<Cow>, Comparable<Cow>
{

    private boolean isSpotted;
    private int weight;

    public boolean isSpotted() {
        return isSpotted;
    }


    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Cow o) {
        return weight - o.weight;
    }

    @Override
    public int compare(Cow o1, Cow o2) {
        return o1.weight - o2.weight;
    }

    public Cow(boolean isSpotted, int weight) {
        this.isSpotted = isSpotted;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "\n\tWeight: " + weight + ", " + (isSpotted ? "S" : "NS");
    }
}

class Interval implements Comparator<Interval>, Comparable<Interval>
{
    private int a, b; //inclusive
    private boolean isSpotted;

    @Override
    public String toString() {
        return "\n\t[" + a + ", " + b + "], " + (isSpotted ? "S" : "NS") ;
    }

    @Override
    public int compareTo(Interval o) {
        if (a > o.a)
        {
            return 1;
        }
        else if (a < o.a)
        {
            return -1;
        }
        return 0;
    }

    @Override
    public int compare(Interval o1, Interval o2) {
        if (o1.a > o2.a)
        {
            return 1;
        }
        else if (o1.a < o2.a)
        {
            return -1;
        }
        return 0;
    }

    public int getA() {
        return a;
    }

    public int getSize()
    {
        return b - a + 1;
    }

    public int getB() {
        return b;
    }

    public boolean isSpotted() {
        return isSpotted;
    }

    public Interval(int a, int b, boolean isSpotted) {
        this.a = a;
        this.b = b;
        this.isSpotted = isSpotted;
    }

    public Interval(){}
}

class Pair<T>
{
    private T var1, var2;

    public T getVar1() {
        return var1;
    }

    public T getVar2() {
        return var2;
    }

    public Pair(T var1, T var2) {
        this.var1 = var1;
        this.var2 = var2;
    }
}