package week4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/*
Name That Number
================

Among the large Wisconsin cattle ranchers, it is customary to brand
cows with serial numbers to please the Accounting Department. The
cow hands don't appreciate the advantage of this filing system,
though, and wish to call the members of their herd by a pleasing
name rather than saying, "C'mon, #4734, get along."

Help the poor cowhands out by writing a program that will translate
the brand serial number of a cow into possible names uniquely
associated with that serial number. Since the cow hands all have
cellular saddle phones these days, use the standard Touch-Tone(R)
telephone keypad mapping to get from numbers to letters (except
for "Q" and "Z"):

          2: A,B,C     5: J,K,L    8: T,U,V
          3: D,E,F     6: M,N,O    9: W,X,Y
          4: G,H,I     7: P,R,S

Acceptable names for cattle are provided to you in the input file,
which contains a list of fewer than 5,000 acceptable cattle names
(all letters capitalized). Take a cow's brand number and report
which of all the possible words to which that number maps are in
the given dictionary (and is sorted into ascending order).

For instance, the brand number 4734 produces all the following names:

  GPDG GPDH GPDI GPEG GPEH GPEI GPFG GPFH GPFI GRDG GRDH GRDI
  GREG GREH GREI GRFG GRFH GRFI GSDG GSDH GSDI GSEG GSEH GSEI
  GSFG GSFH GSFI HPDG HPDH HPDI HPEG HPEH HPEI HPFG HPFH HPFI
  HRDG HRDH HRDI HREG HREH HREI HRFG HRFH HRFI HSDG HSDH HSDI
  HSEG HSEH HSEI HSFG HSFH HSFI IPDG IPDH IPDI IPEG IPEH IPEI
  IPFG IPFH IPFI IRDG IRDH IRDI IREG IREH IREI IRFG IRFH IRFI
  ISDG ISDH ISDI ISEG ISEH ISEI ISFG ISFH ISFI

As it happens, the only one of these 81 names that is in the list
of valid names is "GREG".

Write a program that is given the brand number of a cow and prints
all the valid names that can be generated from that brand number
or "NONE" if there are no valid names. Serial numbers can be as
many as a dozen digits long.

PROGRAM NAME: namenum

INPUT FORMAT

* Line 1: A number from 1 through 12 digits in length.

* Line 2..?: Dictionary of acceptable cattle names.

SAMPLE INPUT

4734
AARON
ABBIE
ABBOTT
ABBRA
...

OUTPUT FORMAT

A list of valid names that can be generated from the input, one per
line, in ascending alphabetical order.

SAMPLE OUTPUT

GREG
 */
public class NameThatNumber
{
    public static HashMap<String, String> charToNum = new HashMap<>();

    public static void main(String[] args) {
        hashmapIntializer();
        Scanner s = new Scanner(System.in);
        String id = s.nextLine();
        ArrayList<String> answers = new ArrayList<>();
        while (s.hasNextLine())
        {
            String str = s.nextLine();
            if (stringComparer(id, str))
            {
                answers.add(str);
            }
        }
        for (int i = 0; i < answers.size(); i++)
        {
            System.out.println(answers.get(i));
        }
        if (answers.size() == 0)
        {
            System.out.println("NONE");
        }
    }

    public static boolean stringComparer(String id, String name)
    {
        if (id.length() != name.length())
        {
            return false;
        }
        for (int i = 0; i < name.length(); i++)
        {
            if ( !charToNum.get(name.charAt(i) + "").equals(id.charAt(i) + "") )
            {
                return false;
            }
        }
        return true;
    }

    public static void hashmapIntializer()
    {
        charToNum.put("A", "2");
        charToNum.put("B", "2");
        charToNum.put("C", "2");
        charToNum.put("D", "3");
        charToNum.put("E", "3");
        charToNum.put("F", "3");
        charToNum.put("G", "4");
        charToNum.put("H", "4");
        charToNum.put("I", "4");
        charToNum.put("J", "5");
        charToNum.put("K", "5");
        charToNum.put("L", "5");
        charToNum.put("M", "6");
        charToNum.put("N", "6");
        charToNum.put("O", "6");
        charToNum.put("P", "7");
        charToNum.put("R", "7");
        charToNum.put("S", "7");
        charToNum.put("T", "8");
        charToNum.put("U", "8");
        charToNum.put("V", "8");
        charToNum.put("W", "9");
        charToNum.put("X", "9");
        charToNum.put("Y", "9");
        charToNum.put("Z", "-1");
        charToNum.put("Q", "-1");
    }
}
