package model;


import java.util.ArrayList;

/* ngeorge1@uco.edu -- fireflyoc@gmail.com
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Noah G
 */
public class URLData
{
    String[] description;
    String url;
    String fullDescription;
    
    public URLData(String[] description, String url, String fullDescription)
    {
        this.description = description;
        this.url = url;
        this.fullDescription = fullDescription;
    }
        
    public String[] getDescription()
    {
        return description;
    }
        
    public String getURL()
    {
        return url;
    }
    
    public String getFullDescription()
    {
        return fullDescription;
    }
}
