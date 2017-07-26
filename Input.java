
//Nathan Crawley
//Joshua Drumm
//Noah George

package control;

import java.util.ArrayList;
import model.*;

public class Input implements Filter
{
    private String text;
    
    private final WordData wordData;
    private final URLData database;
    /**
    * Default constructor.
    * Saves its references to the shared data that it needs
    * 
    * @param wordData Character Word Data
    * @param text Input Text
    */
    public Input(WordData wordData, String text)
    {
        this.wordData = wordData;
        this.text = text;
        database = null;
    }
    
    @Override
    public void run()
    {
        //If the input is empty, do nothing
        if(text.length() == 0)
            return;
        
        if(text.charAt(text.length() - 1) != '\n') //if no new line at end add one
            text += '\n';
        ArrayList<String[]> data = new ArrayList();  
        String line = new String();
        for(int pos = 0; pos < text.length(); pos++)
        {
            char c = text.charAt(pos);
            if(c == '\n')
            {
                String[] words = line.split("\\s+"); //Split each line by spaces
                String[] words2 = new String[words.length - 1];
                System.arraycopy(words, 0, words2, 0, words2.length);
                String fullDescription = words[0];
                for(int i = 1; i < words.length; i++)
                {
                    fullDescription += " " + words[i];
                }
                URLData url = new URLData(words2, words[words.length - 1], fullDescription);
                wordData.addLine(url);
                line = new String();
            }
            else
                line += (char)(c);
        }
    }
}
