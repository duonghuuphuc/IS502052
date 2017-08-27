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
package edu.tdt.entity;

import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kelvin
 */
@Stateful
public class SystemManagement implements SystemManagementRemote {
    
    @PersistenceContext(unitName = "SampleEJB-04PU")
    private EntityManager em;
    
    public SystemManagement()
    {
        
    }

    @Override
    public void insertUser(String userName, String userPassword)
    {
        User user = new User(userName, userPassword);
        em.persist(user);
    }

    @Override
    public void insertRole(String roleName)
    {
        Role role = new Role(roleName);
        em.persist(role);
    }

    @Override
    public void insertUserRole(String userName, String roleName)
    {
        User user = em.find(User.class, userName);
        Role role = em.find(Role.class, roleName);
        
        user.getRoleCollection().add(role);
        role.getUserCollection().add(user);
    }
    
    @Override
    public ArrayList<String> searchRole(String rolename)
    {
        Role role = em.find(Role.class, rolename);
        
        if(role != null)
        {
            ArrayList<String> arrOutput = new ArrayList<String>();
            for(User user : role.getUserCollection())
            {
                arrOutput.add(user.getUserName());
            }
            return arrOutput;
        }
        return null;
    }

    @Override
    public int getUserPassword(String username)
    {
        User user = em.find(User.class, username);
        if(user != null)
            return user.getUserPassword().hashCode();
        return -1;
    }
    
    
    
}
