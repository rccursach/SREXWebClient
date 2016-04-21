/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.srex.srexwebclient.socket;

/**
 *
 * @author rccursach
 */
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author rccursach
 */
public class SocketClient {
    
    Socket srv_sock;
    PrintWriter out;
    //BufferedReader in;
    DataInputStream in;
    BufferedReader stdin;
    String inputLine, outputLine;    
    

    /**
     * @param args the command line arguments
     */
    
    public SocketClient() {
        try{
            srv_sock = new Socket(System.getenv("hostname"), 3030);
            out = new PrintWriter(srv_sock.getOutputStream(), true);
            //in = new BufferedReader(new InputStreamReader(srv_sock.getInputStream()));
            in = new DataInputStream(new BufferedInputStream(srv_sock.getInputStream()));

            stdin = new BufferedReader(new InputStreamReader(System.in));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public String executeQuery(String query){
        // TODO code application logic here
        String result = "";
        
        try {
            
            out.println(query);
            System.out.println("sended: "+outputLine);

            //long ti = System.currentTimeMillis();
            //int timeout = 1000;
            while( (inputLine = in.readLine()) != null ) {
                result += inputLine;
                break;
            }
            
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            if(e.getMessage().equals("Connection reset")){System.err.println("Connection closed by Server.");}
        }
        return result;
    }
    
    public void closeConnection(){
        
        try {            
            out.println("close_sock");
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            if(e.getMessage().equals("Connection reset")){System.err.println("Connection closed by Server.");}
        }
    }
    
}
