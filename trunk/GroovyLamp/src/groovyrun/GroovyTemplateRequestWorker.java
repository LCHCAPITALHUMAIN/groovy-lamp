/*
 * GroovyRequestWorker.java
 *
 * Created on September 6, 2007, 9:02 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package groovyrun;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import groovy.text.SimpleTemplateEngine;
import groovy.text.TemplateEngine;

import groovyrun.http.*;
import java.io.FileNotFoundException;

/**
 *
 * @author alastairjames
 */
public class GroovyTemplateRequestWorker extends RequestWorker{
    
    TemplateEngine eng;
            
    public GroovyTemplateRequestWorker(InputStream in, OutputStream out) {
        
        super(in, out);
        
        eng = new SimpleTemplateEngine();
        
    }

    
    public void work(HTTPRequest request, HTTPResponse response)
    {
        
        try {
            
            File file = new File(request.env.get("SCRIPT_FILENAME"));
            FileReader reader = new FileReader(file);
            
            HashMap<String, Object> binding = new HashMap();
            binding.put("request", request);
            binding.put("response", response);
            
            PrintWriter writer = response.getOutputWriter();
           
            eng.createTemplate(reader).make(binding).writeTo(writer);            
            
            response.setStatus(200);
            
        } catch (FileNotFoundException ex) {

            ex.printStackTrace();
            
            response.setStatus(404);
          
        } catch (Exception ex) {
            
            ex.printStackTrace();
            
            response.setStatus(500);
            
        }
    }
    
}
