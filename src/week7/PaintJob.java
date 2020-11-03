package week7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//todo runs out of memory on last case (?)
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
