package week6quiz.solutions;

import java.util.Scanner;

public class CowProblem
{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        s.nextLine();
        String str = s.nextLine();

        long numOfCSaw = 0;
        long numOfCOSaw = 0;
        long ans = 0;
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == 'C')
            {
                numOfCSaw++;
            }
            else if(str.charAt(i) == 'O')
            {
                numOfCOSaw += numOfCSaw;
            }
            else
            {
                ans += numOfCOSaw;
            }
        }
        System.out.println(ans);

    }
}
