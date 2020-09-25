package week2;

import java.util.HashSet;
import java.util.Scanner;

public class BovineGenomics
{
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int M = s.nextInt();
        String[]  spotCows = new String[N];
        String[]  plainCows = new String[N];
        s.nextLine();
        for (int i = 0; i < N; i++)
        {
            spotCows[i] = s.nextLine();
        }
        for (int i = 0; i < N; i++)
        {
            plainCows[i] = s.nextLine();
        }

        int ans = 0;
        for (int i = 0; i < M; i++)
        {
            for (int j = i+1; j < M; j++)
            {
                for (int k = j+1; k < M; k++)
                {
                    HashSet<String> spotCombos = new HashSet<>();
                    for (int spotCowPointer = 0; spotCowPointer < N; spotCowPointer++)
                    {
                        String str = spotCows[spotCowPointer];
                        spotCombos.add(charAdder(str.charAt(i), str.charAt(j), str.charAt(k)));
                    }
                    boolean setIsUnique = true;
                    for (int plainCowPointer = 0; plainCowPointer < N; plainCowPointer++)
                    {
                        String str = plainCows[plainCowPointer];
                        if (spotCombos.contains(charAdder(str.charAt(i), str.charAt(j), str.charAt(k))))
                        {
                            setIsUnique = false;
                            break;
                        }
                    }

                    if (setIsUnique)
                    {
                        ans++;
                    }

                }
            }
        }

        System.out.println(ans);
    }

    public static String charAdder(char a, char b, char c)
    {
        return "" + a + b + c;
    }

}
