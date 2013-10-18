/*
   XmlDOMDocument

   DOM document class definition.

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
import org.w3c.dom.*;

public class XmlDOMDocument {

  private final Document m_doc;

  public XmlDOMDocument(XmlDOMParser parser, String xmlfile) throws Exception {
    m_doc = parser.parse(xmlfile);
  }

  public int getChildCount(String parentTag, int parentIndex, String childTag) {
    NodeList list = m_doc.getElementsByTagName(parentTag);
    Element parent = (Element) list.item(parentIndex);
    NodeList childList = parent.getElementsByTagName(childTag);
    return childList.getLength();
  }

  public String getChildValue(String parentTag, int parentIndex, String childTag, int childIndex) {
    NodeList list = m_doc.getElementsByTagName(parentTag);
    Element parent = (Element) list.item(parentIndex);
    NodeList childList = parent.getElementsByTagName(childTag);
    Element field = (Element) childList.item(childIndex);
    Node child = field.getFirstChild();
    if (child instanceof CharacterData) {
      CharacterData cd = (CharacterData) child;
      return cd.getData();
    }
    return "";
  }

  public String getAttributeValue(String elementTag, int elementIndex, String attributeTag) {
    NodeList list = m_doc.getElementsByTagName(elementTag);
    Element element = (Element) list.item(elementIndex);
    return element.getAttribute(attributeTag);
  }

  public int getRootElementCount(String rootElementTag) {
      NodeList list = m_doc.getElementsByTagName(rootElementTag);
      return list.getLength();
  }
}
