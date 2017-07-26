//Nathan Crawley
//Joshua Drumm
//Noah George
package view;

import control.MasterControl;
import control.Server;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author josh
 */
class KWICServer extends JFrame {

    private MasterControl masterControl;
    private int port = 9001;
    private String ip = "localhost";

    public KWICServer() throws Exception {

        String inputText, outputText;
        inputText = outputText = null;

        System.out.println("Starting KWIC Server!!!");
        System.out.println("=======================");
        //setIPandPORT();
        System.out.println("Waiting for client .... ");
        masterControl = new MasterControl(ip, port);
        masterControl.run(inputText, outputText);
        

    }

    private void setIPandPORT() {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter your port number: ");
        port = in.nextInt();
        System.out.print("Please enter your IP address:  ");
        ip = in.nextLine();
    }

    /**
     * Starts the program. Asks for input of the user to start a server in
     * command line format
     *
     * @param args the arguments to be passed to the program (ignores all
     * arguments)
     */
    public static void main(String[] args) throws Exception {
        KWICServer kwicServer = new KWICServer();
    }
}
