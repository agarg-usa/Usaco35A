package week8;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeSet;
/*
Word Morph
==========

Farmer John is playing a word game against his cows. It
starts like this:
  * Farmer John chooses a word, such as 'cat'
  * The cows then choose their own word, perhaps 'dog'

Farmer John then must morph his word to the cows' word by performing
a number of steps, each one of which changes one single letter at
a time to make a new, valid word.

The dictionary of valid words is given in the input. There are no
more than 25,000 words, each with length in the range 1..20. These
'words' contain only letters in the range 'a'..'z'.

For this example, Farmer John could make the following sequence of
four words:

      'cat' -> 'cot' -> 'cog' -> 'dog'

to morph words from the first word 'cat' to the final word 'dog'
in just three moves. The cows will never give Farmer John an
impossible task. Farmer John must get from his word to the cows'
word in as few moves as possible.

You will be given a starting word and an ending word. Determine
and output a number which is the least number of legal letter changes
required to morph the starting word to the ending word.

PROBLEM NAME: wmorph

INPUT FORMAT:

* Line 1: A single string that is the starting word

* Line 2: A single string that is the end word

* Line 3..?: Words in the dictionary; one word per line

SAMPLE INPUT:

cat
dog

OUTPUT FORMAT:

* Line 1: A single integer that is the number of times a letter must
        be changed to transform the starting word into the ending
        word.

SAMPLE OUTPUT:

3
 */
public class WordMorph
{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String startWord = s.nextLine();
        String endingWord = s.nextLine();

        TreeSet<String> dictionary = new TreeSet<>();
        while (s.hasNextLine())
        {
            String dicWord = s.nextLine();
            if (dicWord.length() == startWord.length())
            {
                dictionary.add(dicWord);
            }
        }

        Queue<WordSteps> bfs = new LinkedList<>();

        bfs.add(new WordSteps(startWord, 0));

        char[] allChars = new char[26];
        for (int i = 0; i < 26; i++)
        {
            allChars[i] = (char)(i+97);
        }


        while (!bfs.isEmpty())
        {
            WordSteps currStep = bfs.poll();
            if (currStep.str.equals(endingWord))
            {
                System.out.println(currStep.steps);
                return;
            }

            for (int charItr = 0; charItr < allChars.length; charItr++)
            {
                for (int wordItr = 0; wordItr < currStep.str.length(); wordItr++)
                {
                    if (currStep.str.charAt(wordItr) != allChars[charItr])
                    {
                        String currentStrToAdd = currStep.str.substring(0,wordItr) + allChars[charItr];
                        if (wordItr != currStep.str.length()-1)
                        {
                            currentStrToAdd +=  currStep.str.substring(wordItr+1);
                        }

                        if (dictionary.contains(currentStrToAdd))
                        {
                            bfs.add(new WordSteps(currentStrToAdd, currStep.steps+1));
                            dictionary.remove(currentStrToAdd);
                        }
                    }
                }
            }


        }

    }
}

class WordSteps
{
    String str;
    int steps;

    public WordSteps(String str, int steps) {
        this.str = str;
        this.steps = steps;
    }
}
