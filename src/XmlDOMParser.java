/*
   XmlDOMParser

   DOM parser class definition.

   ------------------------------------------

   Copyright © 2013 [Vic Hargrave - http://vichargrave.com]

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
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;

public class XmlDOMParser {
  private DocumentBuilderFactory m_factory;
  private DocumentBuilder m_db;

  public XmlDOMParser() throws Exception {
    m_factory = DocumentBuilderFactory.newInstance();
    m_db = m_factory.newDocumentBuilder();
  }

  public Document parse(String xmlfile) throws Exception {
    return m_db.parse(new FileInputStream(new File(xmlfile)));
  }
}
