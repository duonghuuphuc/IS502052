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
public class Employee implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String name;
    private String address;
    private transient int SSN;

    public Employee(String name, String address, int SSN)
    {
        this.name = name;
        this.address = address;
        this.SSN = SSN;
    }
    
    public void mailCheck()
    {
        System.out.println("Mailing a check to " + this.name
                + ", at " + this.address);
    }
    
    public void printInfo()
    {
        System.out.println("Name: " + this.name 
                + " Address: " + this.address 
                + " SSN: " + this.SSN);
    }

    public String getName()
    {
        return name;
    }

    public String getAddress()
    {
        return address;
    }

    public int getSSN()
    {
        return SSN;
    }
    
}
