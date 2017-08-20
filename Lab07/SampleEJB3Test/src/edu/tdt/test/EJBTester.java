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
package edu.tdt.test;

import edu.tdt.persistence.Book;
import edu.tdt.persistence.LibraryPersistentBean;
import edu.tdt.persistence.LibraryPersistentBeanRemote;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Kelvin
 */
public class EJBTester {
    
    private Properties props;
    private InitialContext ctx;
    
    public EJBTester()
    {
        readJNDI();
    }
    
    /**
     * Read the JNDI properties file
     */
    private void readJNDI()
    {
        props = new Properties();
        
        try
        {
            props.load(new FileInputStream("jndi.properties"));
        } catch(IOException e)
        {
            System.err.println(e.toString());
        }
        
        try
        {
            ctx = new InitialContext(props);
            //ctx.close();
        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    /**
     * Construct and return the JNDI address of called class
     * @return String
     */
    private String getJNDI()
    {
        String appName = "";
        String moduleName = "SampleEJB3";
        String distinctName = "";
        String sessionBeanName = LibraryPersistentBean.class.getSimpleName();
        String viewClassName = LibraryPersistentBeanRemote.class.getName();
        
        return "ejb:"+appName+"/"+moduleName+"/"+distinctName+"/"+sessionBeanName+"!"+viewClassName;
    }
    
    /**
     * Show the GUI in console window
     */
    private void showGUI()
    {
        System.out.println("\n=========================");
        System.out.println("Welcome to TDTU Bookstore");
        System.out.println("=========================");
        System.out.print("Options: \n1. Add Book \n2. List All Books (Current Session) \n3. List All Books (New Session) \n4. Exit \nEnter Choice: ");
    }
    
    /**
     * Declare a bean to invoke getBooks() method in LibrarySessionBeanRemote
     */
    private void getAllBooks()
    {
        try
        {
            // We can define another bean to access the LibrarySessionBeanRemote
            LibraryPersistentBeanRemote libBean2 = (LibraryPersistentBeanRemote) ctx.lookup(getJNDI());
            List<Book> booksList = libBean2.getBooks();
            
            // Print all books
            if(booksList.isEmpty())
            {
                System.out.println("There is no book in the bookstore!\n");
                return;
            }
            
            System.out.println("\n=========================");
            System.out.println("Listing Books in TDTU Bookstore");
            System.out.println("=========================");
            for (int i = 0; i < booksList.size(); i++)
            {
                System.out.println((i+1) + "\t" + booksList.get(i));
            }
            System.out.println();
            
        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
    /**
     * Test the Stateless EJB
     */
    public void testEntityEJB()
    {
        try
        {
            // Scanner definition
            Scanner sc = new Scanner(System.in);
            
            // Lookup the LibrarySessionBeanRemote
            LibraryPersistentBeanRemote libBean = (LibraryPersistentBeanRemote) ctx.lookup(getJNDI());
            
            int choice = 0;
            while(choice != 4)
            {
                this.showGUI();
                
                // Use this approach to avoid the error cause by nextInt() follows by nextLine()
                choice = Integer.parseInt(sc.nextLine());
                
                if(choice == 1)
                {
                    // Add a book
                    System.out.print("Enter book name: ");
                    String bookName = sc.nextLine();
                    
                    Book b = new Book();
                    //b.setId(libBean.getBooks().size()+1);  // Set ID of this current book
                    b.setName(bookName);
                    
                    libBean.addBook(b);
                }
                else if(choice == 2)
                {
                    // Print all books (using current session bean)
                    List<Book> booksList = libBean.getBooks();
                    
                    if(booksList.isEmpty())
                    {
                        System.out.println("There is no book in the bookstore!\n");
                    }
                    
                    System.out.println("\n=========================");
                    System.out.println("Listing Books in TDTU Bookstore");
                    System.out.println("=========================");
                    for (int i = 0; i < booksList.size(); i++)
                    {
                        System.out.println((i+1) + "\t" + booksList.get(i));
                    }
                    System.out.println();
                }
                else if(choice == 3)
                {
                    // Print all books (using new session bean)
                    getAllBooks();
                }
                else
                {
                    // Exit
                    break;
                }
            }
            
            sc.close();
            
        } catch (NamingException ex)
        {
            ex.getMessage();
        }
    }
    
}
