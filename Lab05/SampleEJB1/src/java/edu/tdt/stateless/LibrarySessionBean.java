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
package edu.tdt.stateless;

import java.util.*;
import javax.ejb.Stateless;

/**
 *
 * @author Kelvin
 */
@Stateless
public class LibrarySessionBean implements LibrarySessionBeanRemote {
    
    private List<String> bookShelf;

    public LibrarySessionBean()
    {
        bookShelf = new ArrayList<String>();
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

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
