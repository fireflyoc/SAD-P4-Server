package control;



import model.URLData;
import model.WordData;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Noah G
 */
public class CircularShift implements Filter
{
        private final WordData wordData;
    
    /**
    * Default constructor.
    * Saves its references to the shared data that it needs
    * 
    * @param wordData Word Data to Process
    */
    public CircularShift(WordData wordData)
    {
        this.wordData = wordData;
    }
    
    @Override
    public void run()
    {
        //ArrayList<String[]> data = wordData.getData();//new ArrayList();
        int lineCount = -1;
        for(int line = 0; line < wordData.getNumLines(); line++)
        {
            URLData url = wordData.getLine(line);
            String[] words = url.getDescription();
            for(int i = 0; i < words.length; i++)
            {
                int wordCount = -1;
                wordData.addLine(line, new URLData(new String[words.length], url.getURL(), url.getFullDescription()));
                
                //Writes each word starting at a different spot
                for(int j = i; j < words.length; j++)
                {
                    wordCount++;
                    wordData.setWord(line, wordCount, words[j]);

                }
                //Writes the rest of the words from the beginning of the line
                for(int j = 0; j < i; j++)
                {
                    wordCount++;
                    wordData.setWord(line, wordCount, words[j]);
                }
                line++;
            }
            wordData.removeLine(line--);
        }
    }
}
