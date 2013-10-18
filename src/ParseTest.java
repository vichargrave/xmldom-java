/*
   ParseTest

   Test application for the DOM parser classes.

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
public class ParseTest {
  public static void main(String[] args) {
    ParseTest test = new ParseTest();
    try {
      XmlDOMDocument doc = new XmlDOMDocument(new XmlDOMParser(), "./bookstore.xml");
      int count = doc.getChildCount("bookstore", 0, "book");
      for (int i = 0; i < count; i++) {
        System.out.println("Book "+Integer.toString(i+1));
        System.out.println("book category - "+doc.getAttributeValue("book", i, "category"));
        System.out.println("book title    - "+doc.getChildValue("book", i, "title", 0));
        System.out.println("book author   - "+doc.getChildValue("book", i, "author", 0));
        System.out.println("book year     - "+doc.getChildValue("book", i, "year", 0));
        System.out.println("book price    - "+doc.getChildValue("book", i, "price", 0));
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
