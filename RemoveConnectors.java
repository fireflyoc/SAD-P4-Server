package control;

import model.URLData;
import model.WordData;

public class RemoveConnectors implements Filter
{
    private final WordData wordData;
    /**
    * Default constructor.
    * Saves its references to the shared data that it needs
    * 
    * @param wordData Word Data to Process
    */
    public RemoveConnectors(WordData wordData)
    {
        this.wordData = wordData;
    }

    @Override
    public void run()
    {
        for(int i = 0; i < wordData.getNumLines(); i++)
        {
            if(searchForWords(wordData.getWord(i, 0)))
                wordData.removeLine(i--);
        }
    }
    
    /**
    * Compares a given word to the list of bad words.
    * 
    * @param word The word to compare
    * @return true if word equals one of the bad words
    * @return false if word is not equal to any of the bad words
    */
    private boolean searchForWords(String word)
    {
        String[] badWords = new String[]{"a", "an", "the", "and", "or", "of", "to", "be", "is", "in", "out", "by", "as", "at", "off"};
        for(int i = 0; i < badWords.length; i++)
        {
            if(word.toLowerCase().equals(badWords[i]))
                return true;
        }
        return false;
    }
}
