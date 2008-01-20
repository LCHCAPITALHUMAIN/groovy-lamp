/*
 * ApplicationServer.java
 *
 * Created on September 6, 2007, 8:10 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package groovyrun;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author alastairjames
 */
public class SCGIApplicationServer extends CommandLineTool implements Runnable {
    
    private boolean running;
    private ServerSocket server_socket;
    private int port = 4444;
    private Thread thread;
    
    
    /** Creates a new instance of ApplicationServer */
    public SCGIApplicationServer(int port) {
        
        this.port = port;
        this.thread = new Thread(this);
        
    }
    
    public void run()
    {
        echo("Starting server");
        
        try{
        
            openServerSocket();
        
        } catch (Exception e) {
            
            
            echo("Unable to open server socket.");
            running = false;
            //throw e;
            return;
            
        }
        
        echo("SCGIApplicationServer running on port: "+server_socket.getLocalPort());
        
        while(running)
        {
            try 
            {
            
                Socket client_socket = server_socket.accept();

                echo("Accepted new connection");
                
                (new GroovyTemplateRequestWorker(client_socket.getInputStream(), client_socket.getOutputStream())).start();
                
                echo("Dispatched");
                
            } catch (Exception e) {
                
                echo("ERROR: in accept loop.");
                
            }
        }
        
        try{
        
            closeServerSocket();
        
        } catch (Exception e) {
            
            echo("Unable to close server socket");
            //throw e;
            return;
            
        }
           
    }
    
    public void start(){
        
        this.running = true;
        
        this.thread.start();
        
    }
    
    public void stop(){
        
        this.running = false;
        
    }
    
    private void openServerSocket() throws Exception
    {
    
        server_socket = new ServerSocket(port);
    
    }
    
    private void closeServerSocket() throws Exception
    {
    
        server_socket.close();
    
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Map<String, String> arguments = parseArguments(args);
    
        String[] required = {"port"};
        
        if (checkRequiredArguments(required, arguments))
        {
            SCGIApplicationServer app_server = new SCGIApplicationServer(Integer.parseInt(arguments.get("port")));
            app_server.start();
            
        } else {
            
            printUsageAndExit();
            
        }
        
    }
    
    public static void printUsageAndExit(){
        
        System.err.println("Usage:");
        System.err.println("------");
        
        System.err.println("java -jar groovyscgi.jar -port=xxxx");
        System.err.println();
        
        System.exit(1);
    
    }
    
    
    public static void echo(String str)
    {
        System.out.println(str);
    }
    
}
