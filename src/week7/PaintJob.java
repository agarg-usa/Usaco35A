package week7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
Paint Job
=========

It's been springtime in the rocky mountains, which means storms
have been coming and going on the farm. The barns and stalls
used to have beautiful colors on the outside, but now they've
all faded away! Farmer John enlisted Bessie to help restore order
by giving her various paint jobs around the farm.

As you know, buckets of paint have numerical colors ranging from
1 to P (1 <= P <= 10,000). Bessie owns a paint bucket of color A
(1 <= A <= P) that she will use for the job, but FJ wants her to
paint a color B (1 <= B <= P) instead. To help her, FJ looks for
spare paint buckets that he has lying around in the barn and finds
N (1 <= N <= 10,000) paint buckets. Bessie can use these paint
buckets by picking one of them and pouring some of its paint into
her bucket, effectively mixing the two colors. The resulting color
is the remainder upon division by P of the product of her bucket's
color and the color she mixed in. Being the skilled painter that
she is, Bessie can use the N paint buckets as many times as she likes.

FJ is having some relatives over soon, so he wants the farm to be
clean and pristine as soon as possible. What is the minimum number
of mixes Bessie needs in order to get Farmer John’s color?

PROGRAM NAME: paintjob

INPUT FORMAT:

* Line 1: contains two integers, A and B, indicating the color of
        Bessie’s paint and the desired paint color respectively.

* Line 2: contains two integers, N and P, as explained in the
        problem statement.

* Line 3: contains N integers, the list of paint colors at Bessie's
        disposal.

OUTPUT FORMAT:

* Line 1: a single integer, M, representing the minimum number of
        mixes Bessie needs to perform. Output -1 if Bessie cannot
        make FJ’s desired paint color.

SAMPLE INPUT:

3 30
3 100
2 5 7

SAMPLE OUTPUT:

2

OUTPUT DETAILS:

Bessie starts with a paint color of 3. She can then mix the paint
color 2 into it, and then the paint color 5 to get a paint color of
3x2x5=30 as desired.
 */
public class PaintJob
{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int a = s.nextInt(); //bessie current num
        int b = s.nextInt(); // desired num

        int n = s.nextInt(); //number of buckets she has
        int p = s.nextInt(); //number to mod

        ArrayList<Integer> paints = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
        {
            paints.add(s.nextInt());
        }

        boolean[] seen = new boolean[p+1];
        Queue<Paint> paintQueue = new LinkedList<>();
        seen[a] = true;
        paintQueue.add(new Paint(a, 0));

        while (!paintQueue.isEmpty())
        {
            Paint myPaint = paintQueue.poll();
            if (myPaint.paintColor == b)
            {
                System.out.println(myPaint.paintsMixed);
                return;
            }

            for (Integer paintToMix : paints)
            {
                Paint newPaint = new Paint((myPaint.paintColor * paintToMix)%p, myPaint.paintsMixed+1);
                if (!seen[newPaint.paintColor])
                {
                    paintQueue.add(newPaint);
                    seen[newPaint.paintColor] = true;
                }
            }
        }

        System.out.println(-1);


    }
}

class Paint
{
    int paintColor, paintsMixed;

    public Paint(int paintColor, int paintsMixed) {
        this.paintColor = paintColor;
        this.paintsMixed = paintsMixed;
    }
}
