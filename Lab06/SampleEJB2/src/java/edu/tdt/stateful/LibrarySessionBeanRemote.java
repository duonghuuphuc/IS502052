/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tdt.stateful;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Kelvin
 */
@Remote
public interface LibrarySessionBeanRemote {
    
    void addBook(String bookName);
    List getBooks();
    
}
