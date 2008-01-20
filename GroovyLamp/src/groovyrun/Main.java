/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package groovyrun;

import java.util.Map;

/**
 *
 * @author abj
 */
public class Main extends CommandLineTool {
    
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
    
}
