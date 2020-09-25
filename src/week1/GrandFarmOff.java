package week1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

//todo Polynomial class is broken, please refer to https://www.geeksforgeeks.org/modulo-1097-1000000007/ to fix

public class GrandFarmOff
{
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        Long a= s.nextLong(), b= s.nextLong(), c= s.nextLong(), d= s.nextLong(),
                e= s.nextLong(), f= s.nextLong(), g= s.nextLong(), h= s.nextLong(), M= s.nextLong();
        Polynomial weightPoly = new Polynomial(a, b, c, d);
        Polynomial utilityPoly = new Polynomial(e, f, g, h);
        ArrayList<Cow> cows = new ArrayList<>(3*N);
        for (int i = 0; i < 3*N; i++)
        {
            cows.add(new Cow(weightPoly.weightPolynomial(i), utilityPoly.utilityPolynomial(i)));
        }
        cows.sort(new Cow());
        BigInteger ans = BigInteger.valueOf(0);
        for (int i = cows.size()-1; i > cows.size()-1-N; i--)
        {
            ans = ans.add(cows.get(i).weight);
        }
        System.out.println(ans.mod(BigInteger.valueOf(M)));
    }

}
class Cow implements Comparator<Cow>
{
    BigInteger weight;
    BigInteger utility;

    @Override
    public int compare(Cow o1, Cow o2) {
        if (o1.utility.equals(o2.utility))
        {
            return o1.weight.compareTo(o2.weight);
        }
        return o1.utility.compareTo(o2.utility);
    }
    Cow(BigInteger weight, BigInteger utility)
    {
        this.weight = weight;
        this.utility = utility;
    }
    Cow(){}
}
class Polynomial
{
    BigInteger a, b, c, d;
    Polynomial(long a, long b, long c, long d)
    {
        this.a = BigInteger.valueOf(a);
        this.b = BigInteger.valueOf(b);
        this.c = BigInteger.valueOf(c);
        this.d = BigInteger.valueOf(d);
    }
    BigInteger weightPolynomial(long i)
    {
        BigInteger ans = (a.multiply(BigInteger.valueOf(i).pow(5))).mod(d);
        ans = ans.add( (b.multiply (BigInteger.valueOf(i).pow(2))).mod(d));
        ans = ans.add(c.mod(d));
        return ans;
    }
    BigInteger utilityPolynomial(long i)
    {

        BigInteger ans = (a.multiply(BigInteger.valueOf(i).pow(5))).mod(d);
        ans = ans.add( (b.multiply (BigInteger.valueOf(i).pow(3))).mod(d));
        ans = ans.add(c.mod(d));
        return ans;
    }
}