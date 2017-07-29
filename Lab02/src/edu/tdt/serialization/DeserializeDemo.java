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
package edu.tdt.serialization;

import java.io.*;

/**
 *
 * @author Kelvin Duong (huuphucduong@gmail.com)
 */
public class DeserializeDemo {
    
    public static void main(String[] args)
    {
        Employee e = null;
        
        try
        {
            FileInputStream file = new FileInputStream("data/employee.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            
            e = (Employee) in.readObject();
            
            in.close();
            file.close();
            
            System.out.println("Deserialized data completely.");
        } catch(IOException i)
        {
            System.err.println(i.getMessage());
        } catch (ClassNotFoundException ex)
        {
            System.err.println(ex.getMessage());
        }
        
        System.out.println("Deserialized Employee...");
        System.out.println("Name: " + e.getName());
        System.out.println("Address: " + e.getAddress());
        System.out.println("SSN: " + e.getSSN());
    }
    
}
