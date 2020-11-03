package week6quiz.solutions;

import java.util.Scanner;
/*
COW
===

Bessie the cow has stumbled across an intriguing inscription carved
into a large stone in the middle of her favorite grazing field. The
text of the inscription appears to be from a cryptic ancient
language involving an alphabet with only the three characters C, O,
and W. Although Bessie cannot decipher the text, she does
appreciate the fact that C, O, and W in sequence forms her favorite
word, and she wonders how many times COW appears in the text.

Bessie doesn't mind if there are other characters interspersed
within COW, only that the characters appear in the correct order.
She also doesn't mind if different occurrences of COW share some
letters. For instance, COW appears once in CWOW, twice in CCOW, and
eight times in CCOOWW.

Given the text of the inscription, please help Bessie count how
many times COW appears.

PROBLEM NAME: cow

INPUT FORMAT:

The first line of input consists of a single integer N <= 10^5. The
second line contains of a string of N characters, where each
character is either a C, O, or W.

OUTPUT FORMAT:

Output the number of times COW appears as a subsequence, not
necessarily contiguous, of the input string.

Note that the answer can be very large, so make sure to use 64 bit
integers ("long long" in C++, "long" in Java) to do your
calculations.

SAMPLE INPUT:

6
COOWWW

SAMPLE OUTPUT:

6
 */
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
