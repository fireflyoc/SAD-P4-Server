package control;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Nathan W. Crawley
 */
public class Server {

    private final String ip;
    private final int port;
    private Socket client;
    private DataOutputStream sendDataOutput;
    private DataInputStream receiveDataInput;
    private boolean connectionStatus = false;
    private ServerSocket server;

//    The overloaded constructor sets the IP addess and the port Adress
    public Server(String ip, int port) throws Exception {
        this.ip = ip;
        this.port = port;

        initializeServer();     // initialize server

    }

    public Server() throws Exception {
        this.ip = "localHost";
        this.port = 9001;
        initializeServer();     // initialize server

    }

//  this functions performs the itialization of the server and performs a stop 
//  thread until it connects to a client with "client = server.accept();".
    private void initializeServer() throws Exception {
        server = new ServerSocket(port, 8, InetAddress.getByName(ip));
        client = server.accept();
        client.setSoTimeout(3600000);       //allows the client the time out
        try {
            receiveDataInput = new DataInputStream(client.getInputStream());
        } catch (InterruptedIOException iioe) {
            System.out.println("timed out");
            connectionStatus = false;
        } catch (IOException ioe) {
            System.out.println("Network I/O error - " + ioe);
            connectionStatus = false;
        }
        sendDataOutput = new DataOutputStream(client.getOutputStream());
        connectionStatus = true;
        //weAreServer = true;
        System.out.println("CLIENT HAS REQUESTED TO JOIN, AND WE HAVE ACCEPTED");
    }

//  We use this to pull client data
    public String pullDataFromClient() throws IOException {
        return receiveDataInput.readUTF();
    }

//  We can use this to push data to the client
    public void pushDataToClient(String data) throws IOException {
        System.out.println(data + " server");
        sendDataOutput.writeUTF(data);
        sendDataOutput.flush();
    }

 //    this checks the connection status    
    public boolean getConnectionStatus() {
        return connectionStatus;
    }
}
