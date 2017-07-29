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
package edu.tdt.list;

import edu.tdt.serialization.Employee;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Kelvin
 */
public class DeserializeDemo {
    
    public static void main(String[] args)
    {
        ArrayList<Employee> arr = new ArrayList<>();
        
        try
        {
            FileInputStream file = new FileInputStream("data/employeeList.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            
            arr = (ArrayList<Employee>) in.readObject();
            
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
        
        for(Employee e : arr)
        {
            e.printInfo();
        }
    }
}
