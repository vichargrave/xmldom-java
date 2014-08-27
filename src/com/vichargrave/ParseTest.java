/*
   ParseTest

   DOM document parsing test program.

   ------------------------------------------

   Copyright � 2013 [Vic Hargrave - http://vichargrave.com]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package com.vichargrave;

public class ParseTest {
    public static void main(String[] args) {
        ParseTest test = new ParseTest();
        try {
            // Parse the given document
            XmlDomDocument doc = new XmlDomDocument("./bookstore.xml");
            int count = doc.getChildCount("bookstore", 0, "book");
            for (int i = 0; i < count; i++) {
                System.out.println("Book "+Integer.toString(+1));
                System.out.println("book category   - "+doc.getChildAttribute("bookstore", 0, "book", i, "category"));
                System.out.println("book title      - "+doc.getChildValue("book", i, "title", 0));
                System.out.println("book title lang - "+doc.getChildAttribute("book", i, "title", 0, "lang"));
                System.out.println("book author     - "+doc.getChildValue("book", i, "author", 0));
                System.out.println("book year       - "+doc.getChildValue("book", i, "year", 0));
                System.out.println("book price      - "+doc.getChildValue("book", i, "price", 0));
            }
            
            // Build the same document programmatically
            XmlDomDocument newdoc = new XmlDomDocument();
            newdoc.addChildElement(null, 0, "bookstore", null);
            newdoc.addChildElement("bookstore", 0, "book", null);
            newdoc.setAttributeValue("book", 0, "category", "cooking");
            newdoc.addChildElement("book", 0, "title", "Everyday Italian");
            newdoc.setAttributeValue("title", 0, "lang", "en");
            newdoc.addChildElement("book", 0, "author", "Giada De Laurentis");
            newdoc.addChildElement("book", 0, "year", "2005");
            newdoc.addChildElement("book", 0, "price", "30.00");
            newdoc.addChildElement("bookstore", 0, "book", null);
            newdoc.setAttributeValue("book", 1, "category", "children");
            newdoc.addChildElement("book", 1, "title", "Harry Potter and the Half-Blood Prince");
            newdoc.setAttributeValue("title", 1, "lang", "en");
            newdoc.addChildElement("book", 1, "author", "J. K. Rowling");
            newdoc.addChildElement("book", 1, "year", "2005");
            newdoc.addChildElement("book", 1, "price", "29.99");
            System.out.println(newdoc.renderXml());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
