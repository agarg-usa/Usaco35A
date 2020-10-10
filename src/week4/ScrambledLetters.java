package week4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

//TODO finish
public class ScrambledLetters
{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        Name[] names = new Name[N];
        for (int i = 0; i < N; i++)
        {
            names[i] = new Name(s.next());
        }

        Name[] leastSortedArr = Arrays.copyOfRange(names, 0, names.length);
        Arrays.sort(leastSortedArr, new CompareLeast());
        Name[] mostSortedArr = Arrays.copyOfRange(names, 0, names.length);
        Arrays.sort(mostSortedArr, new CompareLeast());

        for (Name name : names) {
            String leastName = name.sortedStrLeast;
            System.out.print(Math.abs(Arrays.binarySearch(mostSortedArr, new Name(leastName, leastName, leastName), new CompareMost())) + " ");
            String greatestName = name.sortedStrGreatest;
            System.out.println(Arrays.binarySearch(leastSortedArr, new Name(greatestName, greatestName, greatestName), new CompareLeast()));
        }
    }

    public static int runner(Name[] arr, Name obj, Comparator<Name> comparator)
    {
        int ogIndex = Arrays.binarySearch(arr, obj, comparator);
        return 1;
    }
}

class Name
{
    String mainStr, sortedStrGreatest, sortedStrLeast;

    Name(String str)
    {
        mainStr = str;
        char[] least = stringToCharArr(str);
        char[] most = stringToCharArr(str);
        Arrays.sort(least);
        int j = 0;
        for (int i = least.length-1; i >= 0; i--)
        {
            most[j] = least[i];
            j++;
        }
        sortedStrGreatest = new String(most);
        sortedStrLeast = new String(least);
    }

    public Name(String mainStr, String sortedStrGreatest, String sortedStrLeast) {
        this.mainStr = mainStr;
        this.sortedStrGreatest = sortedStrGreatest;
        this.sortedStrLeast = sortedStrLeast;
    }

    public static char[] stringToCharArr(String str)
    {
        char[] arr = new char[str.length()];
        for (int i = 0; i < str.length(); i++)
        {
            arr[i] = str.charAt(i);
        }
        return arr;
    }

}

class CompareLeast implements Comparator<Name>
{
    @Override
    public int compare(Name o1, Name o2) {
        return o1.sortedStrLeast.compareTo(o2.sortedStrLeast);
    }
}
class CompareMost implements Comparator<Name>
{
    @Override
    public int compare(Name o1, Name o2) {
        return o1.sortedStrGreatest.compareTo(o2.sortedStrGreatest);
    }
}