/*
 * Copyright 2017 Kelvin.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.tdt.rmiserver;

import edu.tdt.rmiinterface.*;
import java.rmi.registry.*;
import java.rmi.server.*;

/**
 *
 * @author Kelvin
 */

public class Server extends ImplHello {
    
    public Server()
    {
        
    }
    
    public static void main(String[] args)
    {
        try
        {
        	// Parsing the argument
        	int index = 0;
        	int port = Integer.parseInt(args[index++]);
        	
            // Instantiating the implementation class
            ImplHello obj = new ImplHello();
            
            // Exporting the object of implementation class
            // (here we are exporting the remote object to the skeleton)
            Hello skeleton = (Hello) UnicastRemoteObject.exportObject(obj, 0);
            
            // Binding the remote object (stub) in the registry
            Registry registry = LocateRegistry.getRegistry(port);
            registry.rebind("Hello", skeleton);
            
            System.err.println("Server ready");
            
        } catch(Exception e)
        {
            System.err.println(e.toString());
        }
    }
    
}
