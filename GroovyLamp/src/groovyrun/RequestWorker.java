/*
 * RequestWorker.java
 *
 * Created on September 6, 2007, 8:51 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package groovyrun;

import groovyrun.http.*;
import groovyrun.scgi.*;

import java.io.*;
import java.util.*;
import java.net.*;

/**
 *
 * @author alastairjames
 */
public class RequestWorker implements Runnable {
    
    Thread thread;
    BufferedInputStream in;
    OutputStream out;
    long time_started;
    
    /** Creates a new instance of RequestWorker */
    public RequestWorker(InputStream in, OutputStream out) {
        
        this.thread = new Thread(this);
        this.in = new BufferedInputStream(in, 4096);
        this.out = out;
        
    }
    
    public void start()
    {
        thread.start();
        time_started = System.currentTimeMillis();
    }
    
    public void run()
    {
       
        try
        {
        
            HashMap<String, String> env = SCGIParser.parse(in);
            
            HTTPRequest request = new HTTPRequest(env, in);
            HTTPResponse response = new HTTPResponse();
            
            work(request, response);
            
            PrintWriter writer = new PrintWriter(out);
            response.writeTo(writer);
            writer.close();
            
            long time = (System.currentTimeMillis() - time_started);
            float ftime = time / 1000.0f;
            
            //out.write(("Content-type: text/html\n\n").getBytes());
            System.out.println("Time taken "+ftime+" seconds");
            
            in.close();
            out.close();
            echo("Worker done!");
            
        } catch (Exception e) {
            
            echo("ERROR: Exception in worker");
            e.printStackTrace();
            
        }
        
    }
    
    public void work(HTTPRequest request, HTTPResponse response)
    {
        
    }
    
    public static void echo(String str)
    {
        System.out.println(str);
    }
    
}
