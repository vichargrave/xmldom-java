/*
   XmlDomDocument

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
package com.vichargrave;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class XmlDomDocument {

    private Document m_doc;
    
    public XmlDomDocument(String xmlfile) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        m_doc = builder.parse(new FileInputStream(new File(xmlfile)));
    }
    
    public XmlDomDocument() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        m_doc = builder.newDocument();
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
    
    public void addChildElement(String parentTag, int parentIndex, String childTag, String childValue) {
        NodeList list = m_doc.getElementsByTagName(parentTag);
        Element parent = (Element) list.item(parentIndex);
        Element child = m_doc.createElement(childTag);
        if (childValue != null) {
            child.appendChild(m_doc.createTextNode(childValue));
        }
        if (parent == null) {
            m_doc.appendChild(child);
        }
        else {
            parent.appendChild(child);
        }
    }
    
    public void setAttributeValue(String elementTag, int elementIndex, String attributeTag,
                                  String attributeValue) {
        NodeList list = m_doc.getElementsByTagName(elementTag);
        Element element = (Element) list.item(elementIndex);
        element.setAttribute(attributeTag, attributeValue);
    }
    
    public String renderXml() throws Exception {
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        Writer out = new StringWriter();
        tf.transform(new DOMSource(m_doc), new StreamResult(out));
        return out.toString();
    }
}