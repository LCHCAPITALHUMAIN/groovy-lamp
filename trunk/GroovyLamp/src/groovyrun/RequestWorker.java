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
    SCGIApplicationServer server;
    
    /** Creates a new instance of RequestWorker */
    public RequestWorker(SCGIApplicationServer server, InputStream in, OutputStream out) {
        
        this.server = server;
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
            
            PrintWriter out = new PrintWriter(this.out);
            response.writeTo(out);
            out.close();
            
            long time = (System.currentTimeMillis() - time_started);
            float ftime = time / 1000.0f;
            
            this.server.getLog().notice("Time taken "+ftime+" seconds");
            
            in.close();
            out.close();
            this.server.getLog().notice("Worker done!");
            
        } catch (Exception e) {
            
            this.server.getLog().error("ERROR: Exception in worker");
            e.printStackTrace();
            
        }
        
    }
    
    public void work(HTTPRequest request, HTTPResponse response)
    {
        
    }
    
}
