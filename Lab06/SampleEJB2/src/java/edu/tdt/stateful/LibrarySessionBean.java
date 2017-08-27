/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tdt.stateful;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author Kelvin
 */
@Stateful
public class LibrarySessionBean implements LibrarySessionBeanRemote {
    
    private List<String> bookShelf;
    
    public LibrarySessionBean()
    {
        this.bookShelf = new ArrayList<String>();
    }

    @Override
    public void addBook(String bookName)
    {
        this.bookShelf.add(bookName);
    }

    @Override
    public List getBooks()
    {
        return this.bookShelf;
    }
    
    
}
