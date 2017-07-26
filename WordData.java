package model;


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Noah G
 */
public class WordData {
    
    private ArrayList<URLData> data;
    
    /**
    * Default constructor.
    * Initializes the data ArrayList
    */
    public WordData()
    {
        data = new ArrayList();
    }
    
    /**
    * Sets the Data entirely.
    * Should be avoided at all costs
    * 
    * @param data The data to bring in entirely
    */
    public void setData(ArrayList<URLData> data)
    {
        this.data = data;
    }
    
    /**
    * Gets the line from the data.
    * 
    * @param line The line to get
    * @return The data from the line as a String Array
    */
    public URLData getLine(int line)
    {
        return data.get(line);
    }
    
    /**
    * Gets the word from the data.
    * 
    * @param line The line to get the word from
    * @param word The word to get
    * @return The word as a String
    */
    public String getWord(int line, int word)
    {
        return data.get(line).getDescription()[word];
    }
    
    /**
    * Sets the word from the data.
    * 
    * @param line The line to set the word from
    * @param word The word to set
    * @param value The value of the word
    */
    public void setWord(int line, int word, String value)
    {
        data.get(line).getDescription()[word] = value;
    }
    
    /**
    * Removes a line from the data.
    * 
    * @param line The index of the line to remove
    */
    public void removeLine(int line)
    {
        data.remove(line);
    }
    
    /**
    * Gets the number of lines in the data.
    * 
    * @return The number of lines in the data
    */
    public int getNumLines()
    {
        return data.size();
    }
    
    /**
    * Adds a line to the data.
    * 
    * @param line the line to add
    */
    public void addLine(URLData line)
    {
        data.add(line);
    }
    
    /**
    * Adds a line to the data at the specified index.
    * 
    * @param pos the position to add the line to
    * @param line the line to add
    */
    public void addLine(int pos, URLData line)
    {
        data.add(pos, line);
    }
    
    /**
    * Gets the entirety of the data.
    * 
    * @return The entirety of the data
    */
    public ArrayList<URLData> getData()
    {
        return data;
    }
    
    /**
    * Prints the data to System.out.
    * For debugging purposes.
    */
    public void printData()
    {
        for(int i = 0; i < data.size(); i++)
        {
            String[] line = data.get(i).getDescription();
            for(int j = 0; j < line.length; j++)
            {
                System.out.print(line[j] + " ");
            }
            System.out.println(data.get(i).getURL());
        }
    }
    
    public void printFullData()
    {
        for(int i = 0; i < data.size(); i++)
        {
            String[] line = data.get(i).getDescription();
            for(int j = 0; j < line.length; j++)
            {
                System.out.print(line[j] + " ");
            }
            System.out.print(data.get(i).getURL() + " ");
            System.out.println(data.get(i).getFullDescription());
        }
    }
    
}
