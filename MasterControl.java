package control;

import model.URLData;
import java.io.IOException;
import model.WordData;
//import model.URLData;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Noah G
 */
public class MasterControl {

    Filter input, circularShift, alphabetizer, removeConnectors, output, urlcomp;

    public WordData data;
    public URLData database;
    Server server;

    public MasterControl(String ip, int port) throws Exception {
        server = new Server(ip, port);
        if (server.getConnectionStatus() == true) {
            System.out.println("A Client has connected \n ip: " + ip + " and port: " + port);
        }
    }

    /**
     * Run Function for the Filter. This should be overwritten and called when
     * data is ready to be processed.
     *
     * @param inText String Input
     * @param outText Output for Displaying
     * @throws java.io.IOException
     */
    public void run(String inText, String outText) throws IOException {
        while (true) {
            inText = server.pullDataFromClient();
            if (inText.equals("t32f0k6m")) {
                break;
            }
            char firstChar = inText.charAt(0);
            inText = inText.substring(1);
            switch(firstChar)
            {
                case 'd':
                    //Initialize Data Classes
                    data = new WordData();
                    //Initialize Filters
                    input = new Input(data, inText);
                    circularShift = new CircularShift(data);
                    alphabetizer = new Alphabetizer(data);
                    removeConnectors = new RemoveConnectors(data);
                    //urlcomp = new UrlCompare(data, database);
                    //output = new Output(database);

                    //Run the Filters
                    input.run();
                    circularShift.run();
                    alphabetizer.run();
                    removeConnectors.run();
                    //urlcomp.run();
                    //output.run();
                    //server.pushDataToClient(((Output) output).getOutputText()); 
                    data.printFullData();
                    break;
                default:
                    output = new Output(data, inText);
                    output.run();
                    server.pushDataToClient(((Output) output).getOutputText()); 
            }
        }
    }
}
