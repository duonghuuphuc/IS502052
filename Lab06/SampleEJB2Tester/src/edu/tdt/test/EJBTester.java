/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tdt.test;

import edu.tdt.stateful.LibrarySessionBean;
import edu.tdt.stateful.LibrarySessionBeanRemote;
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
            ex.printStackTrace();
        }
    }
    
    /**
     * Construct and return the JNDI address of called class
     * @return String
     */
    private String getJNDI()
    {
        String appName = "";
        String moduleName = "SampleEJB2";
        String distinctName = "";
        String sessionBeanName = LibrarySessionBean.class.getSimpleName();
        String viewClassName = LibrarySessionBeanRemote.class.getName() + "?stateful";
        
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
            LibrarySessionBeanRemote libBean2 = (LibrarySessionBeanRemote) ctx.lookup(getJNDI());
            List<String> booksList = libBean2.getBooks();
            
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
                System.out.println(i + "\t" + booksList.get(i));
            }
            System.out.println();
            
        } catch (NamingException ex)
        {
            ex.printStackTrace();
        }
    }
    
    /**
     * Test the Stateless EJB
     */
    public void testStatefulEJB()
    {
        try
        {
            // Scanner definition
            Scanner sc = new Scanner(System.in);
            
            // Lookup the LibrarySessionBeanRemote
            LibrarySessionBeanRemote libBean = (LibrarySessionBeanRemote) ctx.lookup(getJNDI());
            
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
                    libBean.addBook(bookName);
                }
                else if(choice == 2)
                {
                    // Print all books (using current session bean)
                    List<String> booksList = libBean.getBooks();
                    
                    if(booksList.isEmpty())
                    {
                        System.out.println("There is no book in the bookstore!\n");
                    }
                    
                    System.out.println("\n=========================");
                    System.out.println("Listing Books in TDTU Bookstore");
                    System.out.println("=========================");
                    for (int i = 0; i < booksList.size(); i++)
                    {
                        System.out.println(i + "\t" + booksList.get(i));
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
            ex.printStackTrace();
        }
    }
    
}
