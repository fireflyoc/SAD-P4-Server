//Nathan Crawley
//Joshua Drumm
//Noah George
package control;

import java.util.ArrayList;
import model.*;

public class Output implements Filter
{
    private final WordData wordData;
    private String keyword, output;
    
    /**
    * Default constructor.
    * Saves its references to the shared data that it needs
    * 
    * @param wordData Word Data
    * @param keyword Keyword to search for
    */
    public Output(WordData wordData, String keyword)
    {
        this.wordData = wordData;
        this.keyword = keyword;
    }

    public String getOutputText()
    {
        return output;
    }

    @Override
    public void run()
    {
        output = new String();
        ArrayList<URLData> urls = new ArrayList();
        String[] keywords = keyword.split("\\s+");
        for(URLData url : wordData.getData())
        {
            String[] words = url.getDescription();
            if(words[0].toLowerCase().equals(keywords[0].toLowerCase()))
                urls.add(url);
        }
        if(keywords.length > 1)
        {
            for(int j = 0; j < urls.size(); j++)
            {
                URLData url = urls.get(j);
                for(int i = 1; i < keywords.length; i++)
                {
                    boolean found = false;
                    for(String word : url.getDescription())
                        if(word.toLowerCase().equals(keywords[i].toLowerCase()))
                            found = true;
                    if(!found)
                        urls.remove(j--);
                }
            }
        }
        for(URLData url : urls)
            output += url.getFullDescription() + '\n';
        if(urls.size() == 0)
            output = " ";
    }
}
