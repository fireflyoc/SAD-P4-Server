package control;

import java.util.ArrayList;
import model.URLData;
import model.WordData;

public class Alphabetizer implements Filter
{
    private final WordData wordData;
    private final URLData database;
    /**
    * Default constructor.
    * Saves its references to the shared data that it needs
    * 
    * @param wordData Word Data to Process
    */
    public Alphabetizer(WordData wordData)
    {
        this.wordData = wordData;
        database=null;
    }
    
    public  Alphabetizer(URLData database){
        this.database = database;
        wordData=null;
    }
    
    @Override
    public void run()
    {
        sort(wordData.getData());
    }
    
    /**
    * Heapsort for ArrayLists.
    * Sorts the Arraylist using a heapsort.
    * Utilizes the siftDown method.
    * 
    * @param lines the lines of strings to be sorted
    * @see #siftDown(java.util.ArrayList, int, int)
    */
    private void sort(ArrayList lines)
    {
        int size = lines.size();

        for(int i = (size / 2 - 1); i >= 0; i--)
            siftDown(lines, i, size);

        for(int i = (size - 1); i >= 1; i--)
        {
            Object tmp = lines.get(0);
            lines.set(0, lines.get(i));
            lines.set(i, tmp);
            siftDown(lines, 0, i);      
        }
    }

    /**
    * Sifting method for Arraylists.
    * Sorts the Arraylist using a heapsort.
    * Utilizes the compare method to compare strings.
    * 
    * @param lines the lines of strings to be sorted
    * @param root the start of the sift
    * @param bottom the bottom of the sift
    * @see #compare(java.lang.String, java.lang.String) 
    */
    private void siftDown(ArrayList lines, int root, int bottom)
    {    
        int max_child = root * 2 + 1;

        while(max_child < bottom)
        {
            if((max_child + 1) < bottom)
                if(compare(((URLData) lines.get(max_child + 1)).getDescription(), (((URLData) lines.get(max_child)).getDescription())) > 0)
                    max_child++;

            if(compare(((URLData) lines.get(root)).getDescription(), ((URLData) lines.get(max_child)).getDescription()) < 0)
            {
                Object tmp = lines.get(root);
                lines.set(root, lines.get(max_child));
                lines.set(max_child, tmp);
                root = max_child;
                max_child = root * 2 + 1;
            }else
                break;
        }    
    }

    /**
    * Custom compare for String Arrays.
    * Compares the strings in the following manner where "a<A<b<B< … <y<Y<z<Z"
    * Compares the first words with each other. If they match, it compares the next words until a mismatch is found.
    * @param s1 String to compare
    * @param s2 String to compare
    * @return -1 if s1 < s2
    * @return 1 if s1 > s2
    * @return 0 if s1 == s2
    * @see #compare(java.lang.String, java.lang.String)
    */
    private int compare(String[] s1, String[] s2)
    {

        int l1 = s1.length;
        int l2 = s2.length;
        int shortest = (l2 > l1) ? l1 : l2;
        for(int i = 0; i < shortest; i++)
        {
            int comp = compare(s1[i], s2[i]);
            if(comp != 0)
                return comp;
        }
        
        if(l1 == l2)
            return 0;
        
        return (l1 < l2) ? -1 : 1;
    }
    
    /**
    * Custom compare for Strings.
    * Compares the strings in the following manner where “a<A<b<B< … <y<Y<z<Z”
    * 
    * @param s1 String to compare
    * @param s2 String to compare
    * @return -1 if s1 < s2
    * @return 1 if s1 > s2
    * @return 0 if s1 == s2
    */
    private int compare(String s1, String s2)
    {
        int l1 = s1.length();
        int l2 = s2.length();
        int shortest = (l2 > l1) ? l1 : l2;
        for(int i = 0; i < shortest; i++)
        {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if(c1 != c2)
            {
                if(Character.toLowerCase(c1) == Character.toLowerCase(c2))
                {
                    if(c1 > c2)
                        return -1;
                    return 1;
                }
                if(Character.toLowerCase(c1) < Character.toLowerCase(c2))
                    return -1;
                return 1;
            }
        }
        
        if(l1 == l2)
            return 0;
        
        return (l1 < l2) ? -1 : 1;
    }
    
}
