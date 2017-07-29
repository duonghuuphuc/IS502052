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
package edu.tdt.oop;

/**
 *
 * @author Kelvin
 */
public class Sub extends Super {
    
    protected int y;
    
    public Sub()
    {
        super();
        this.y = 0;
    }
    
    public Sub(int x, int y)
    {
        super(x);
        this.y = y;
    }
    
    public void display()
    {
        super.display();
        System.out.println("Sub Class, y = " + y);
    }
    
}
